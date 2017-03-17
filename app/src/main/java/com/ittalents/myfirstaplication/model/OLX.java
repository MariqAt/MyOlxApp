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
    // mail -> password -> user
    // HashMap<String, TreeSet<RegularUser>> regularUsers;

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

    public void regUser(RegularUser user) {
        this.regularUsers.add(user);
    }

    public boolean logInUser(String mail, String password) {
        for (RegularUser regularUser : regularUsers) {
            if (regularUser.getMail().equals(mail)) {
                if (regularUser.getPassword().equals(password)) {
                    // System.out.println("Wolcome " + regularUser.getName());
                    this.loggedRegularUsers.add(regularUser);
                    MainActivity.loggedUser = regularUser;
                    return true;
                }
            }
        }
        //System.out.println("Your mail or your password aren't correct! Please, try again!");
        return false;
    }

    public void logOutUser(RegularUser user) {
        for (RegularUser regularUser : loggedRegularUsers) {
            if (regularUser.equals(user)) {
                this.loggedRegularUsers.remove(regularUser);
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
