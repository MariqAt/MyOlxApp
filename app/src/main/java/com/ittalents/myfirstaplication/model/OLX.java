package com.ittalents.myfirstaplication.model;

import com.ittalents.myfirstaplication.MainActivity;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by ASUS on 15.3.2017 г..
 */

public class OLX {


    public static TreeSet<RegularUser> regularUsers;

    public static TreeSet<RegularUser> loggedRegularUsers;

    public static TreeMap<RegularUser.Category, ArrayList<RegularUser.Notice>> ads;

    public static TreeMap<RegularUser.Category, TreeSet<RegularUser.Notice>> archivedAds;

    private static OLX instance;

    private OLX() {
        super();

        this.regularUsers = new TreeSet<RegularUser>();
        this.loggedRegularUsers = new TreeSet<RegularUser>();
        this.ads = new TreeMap<RegularUser.Category, ArrayList<RegularUser.Notice>>();
        this.archivedAds = new TreeMap<RegularUser.Category, TreeSet<RegularUser.Notice>>();
    }

    public static void regUser(RegularUser user) {
        regularUsers.add(user);
    }


  /*
    public static void addNoticeOLX(RegularUser.Notice notice) {
        if (!ads.containsKey(notice.getCategory())) {
            ads.put(notice.getCategory(), new ArrayList<RegularUser.Notice>());
        }
        ads.get(notice.getCategory()).add(notice);
    }
   */


    public boolean logInUser(String mail, String password) {
        for (RegularUser regularUser : regularUsers) {
            if (regularUser.getMail().equals(mail)) {
                if (regularUser.getPassword().equals(password)) {
                    // System.out.println("Welcome " + regularUser.getName());
                    this.loggedRegularUsers.add(regularUser);
                    MainActivity.loggedUser = regularUser;
                    return true;
                }
            }
        }

        if (Admin.getCreatedInstance() != null && Admin.getCreatedInstance().getMail().equals(mail) && Admin.getCreatedInstance().getPassword().equals(password)) {
            MainActivity.loggedUser = Admin.getCreatedInstance();
            return true;
        }


        return false;
    }

    public void logOutUser(RegularUser user) {
        if (MainActivity.loggedUser != Admin.getCreatedInstance()) {
            for (RegularUser regularUser : loggedRegularUsers) {
                if (regularUser.equals(user)) {
                    this.loggedRegularUsers.remove(regularUser);
                }
            }
        }
    }

    public static OLX getInstance() {
        if (instance == null) {
            instance = new OLX();
        }
        return instance;
    }


}
