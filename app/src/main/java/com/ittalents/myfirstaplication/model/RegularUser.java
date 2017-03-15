package com.ittalents.myfirstaplication.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by vankor on 14-Mar-17.
 */

public  class RegularUser extends User {

    public enum Category {
        ESTATES, ANIMALS, FASHION
    }

    public enum Type {
        PRIVATE, BUSINESS
    }

    public enum StateGood {
        NEW, USED
    }

    public enum SortNotice {
        ACTIVE, ARCHIVE
    }

    public SortNotice sortNotice;
    public TreeMap<SortNotice, TreeSet<Notice>> poster;
    public TreeSet<Notice> view;

    private static int uniqueID = 1;

    private RegularUser(String name, String mail, String gsm) {
        super(name, mail, gsm);

        this.poster = new TreeMap<>();
        this.view = new TreeSet<>();
    }

    public static RegularUser createUser(String name, String mail, String gsm, OLX olx) {
        RegularUser user = new RegularUser(name, mail, gsm);
        olx.regUser(user);
        return user;
    }

    public void logInOlx() {
        User user = (User) this;
        OLX.getInstance().logInUser(this.getMail(), user.getPassword());
    }

    public void logOutOlx() {
        OLX.getInstance().logOutUser(this);
    }

    public void addNotice(Notice n) {
        Admin.getCreatedInstance().pendingAds.add(n);
    }

    public void deleteNotice(RegularUser.Notice n) {
        RegularUser.SortNotice arch = RegularUser.SortNotice.ARCHIVE;
        if (this.poster.containsKey(arch)) {
            if (this.poster.get(arch).contains(n)) {
                this.poster.get(arch).remove(n);
            }
        }
    }

    public void lookNotice(Notice n, SortNotice sort) {
        if (this.poster.containsKey(sort)) {
            if (poster.get(sort).contains(n)) {
                n.printInfo();
            }
        }
    }

    public void archivedNotice(Notice n) {
        SortNotice arch = SortNotice.ARCHIVE;
        SortNotice act = SortNotice.ACTIVE;
        if (poster.get(act).contains(n)) {
            if (!poster.containsKey(arch)) {
                poster.put(arch, new TreeSet<Notice>());
            }
            poster.get(arch).add(n);
            poster.get(act).remove(n);
        }
    }

    public Notice redactionNotice(SortNotice sort, Notice n) {
        if (!poster.get(sort).contains(n)) {
            return null;
        }
        return n;
    }

    public void searchNoticeByPrice(OLX olx) {
        sortedNotice(new Comparator<Notice>() {

            @Override
            public int compare(Notice o1, Notice o2) {
                return o1.price - o2.price;
            }
        }, olx);
    }

    private void sortedNotice(Comparator<Notice> comparator, OLX olx) {

        ArrayList<RegularUser.Notice> list = new ArrayList<>();
        for (Map.Entry<Category, ArrayList<Notice>> entry : olx.ads
                .entrySet()) {
            list.addAll(entry.getValue());
        }
        Collections.sort(list, comparator);
        for (RegularUser.Notice n : list) {
            n.printInfo();
        }
    }

    public void viewNotice(Notice notice) {
        this.view.add(notice);
    }

    public void activedNotice(Notice n) {
        if (this.poster.get(SortNotice.ARCHIVE).contains(n)) {
            addNotice(n);
            this.poster.get(SortNotice.ARCHIVE).remove(n);
        }
    }

    public void printAllNotice() {
        for (Map.Entry<SortNotice, TreeSet<Notice>> entry : poster.entrySet()) {
            System.out.println("SORT: " + entry.getKey());
            System.out.println("---------");
            for (Notice notice : entry.getValue()) {
                notice.printInfo();
                System.out.println("------");
            }
        }
    }

    public void printViewNotice() {
        System.out.println("View:");
        for (Notice notice : view) {
            notice.printInfo();
        }
    }



    public class Notice implements Comparable<Notice> {
        private RegularUser user;
        private String title;
        Category category;
        private Type type;
        private int price;
        private String desription;
        private StateGood state;
        private String mail;
        private String gsm;
        private String name;
        private Date date;
        private int id;
        // picture

        public Notice(String title, Category category, Type type, int price, String desription,
                      StateGood state) {
            this.user = RegularUser.this;
            setTitle(title);
            this.category = category;
            this.type = type;
            setPrice(price);
            setDesription(desription);
            setState(state);
            this.mail = user.getMail();
            this.gsm = user.getGsm();
            this.name = user.getName();
            this.date = new Date();
            this.id = uniqueID;
            RegularUser.this.uniqueID++;
        }

        public void setTitle(String title) {
            if (title != null && !title.isEmpty()) {
                this.title = title;
            } else {
                this.title = "something for sell";
            }
        }

        public void setPrice(int price) {
            if (price > 0) {
                this.price = price;
            }
        }

        public void setDesription(String desription) {
            if (desription != null && !desription.isEmpty()) {
                this.desription = desription;
            }
        }

        public void setState(RegularUser.StateGood state) {
            this.state = state;
        }

        public void printInfo() {
            System.out.println("Title: " + this.title);
            System.out.println("Category: " + this.category);
            System.out.println("PRIVATE OR BUSINESS: " + this.type);
            System.out.println("Price: " + this.price + " lv");
            System.out.println("Description: " + this.desription);
            System.out.println("State: " + this.state);
            System.out.println("Mail: " + this.mail);
            System.out.println("Contact person: " + this.name);
            System.out.println("ID: " + this.id);
        }

        RegularUser getOuterType() {
            return RegularUser.this;
        }

        int getId() {
            return this.id;
        }

        RegularUser.Category getCategory() {
            return this.category;
        }

        @Override
        public int compareTo(RegularUser.Notice o) {
            return this.getId() - o.getId();
        }
    }

}
