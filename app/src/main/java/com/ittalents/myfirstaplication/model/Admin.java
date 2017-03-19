package com.ittalents.myfirstaplication.model;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

import com.ittalents.myfirstaplication.model.RegularUser.Notice;
import com.ittalents.myfirstaplication.model.RegularUser.SortNotice;

public class Admin extends User {

    OLX olx;

    private static Admin instance;

    public static ArrayList<Notice> pendingAds = new ArrayList<Notice>();
    ;

    private Admin(String name, String address, String mail, String password, String gsm, OLX olx) {
        super(name, address, mail, password, gsm);
        this.olx = olx;
    }

    public static Admin getInstance(String name, String address, String mail, String password, String gsm, OLX olx) {
        if (instance == null) {
            instance = new Admin(name, address, mail, password, gsm, olx);
        }
        return instance;
    }

    public static Admin getCreatedInstance() {
        return instance != null ? instance : null;

    }

    public void reviewAds() {
        for (Notice n : pendingAds) {
            n.printInfo();
            System.out.println();
            this.approveAd(n);
            System.out.println();
        }
    }

    public void approveAd(Notice n) {


        if (olx.ads.containsKey(n.getCategory())) {
            olx.ads.get(n.getCategory()).add(n);
        } else {

            olx.ads.put(n.getCategory(), new ArrayList<Notice>());
            olx.ads.get(n.getCategory()).add(n);
        }
        if (!n.getOuterType().poster.containsKey(SortNotice.ACTIVE)) {
            n.getOuterType().poster.put(SortNotice.ACTIVE, new TreeSet<Notice>());
        }
        n.getOuterType().poster.get(SortNotice.ACTIVE).add(n);

        if (!olx.ads.containsKey(n.category)) {
            olx.ads.put(n.category, new ArrayList<Notice>());
        }
        olx.ads.get(n.category).add(n);

    }

    public void deleteAd(Notice notice) {
        if (olx.ads.containsKey(notice.getCategory()) && olx.ads.get(notice.getCategory()).contains(notice)) {
            olx.ads.get(notice.getCategory()).remove(notice);
        }
    }

    public void archiveAd(Notice notice) {
        if (olx.archivedAds.containsKey(notice.getCategory())) {
            olx.archivedAds.get(notice.getCategory()).add(notice);
        } else {

            olx.archivedAds.put(notice.getCategory(), new TreeSet<Notice>());
            olx.archivedAds.get(notice.getCategory()).add(notice);
        }

        olx.ads.get(notice.getCategory()).remove(notice);
    }

    public void blockUser(RegularUser user) {

        user = null;

    }

    public void printUsers() {
        for (RegularUser ru : olx.regularUsers) {
            System.out.println(ru.toString());
        }
    }
}
