package com.ittalents.myfirstaplication.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by vankor on 14-Mar-17.
 */

public abstract class User implements Comparable<User> {

    private String name;
    private String address;
    private String mail;
    private String gsm;
    private String password;


    public TreeMap<User, TreeSet<Message>> messages;

    public User(String name, String address, String mail, String password, String gsm) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
        if (address != null && !address.isEmpty()) {
            this.address = address;
        }
        if (mail != null && !mail.isEmpty()) {
            this.mail = mail;
        }
        if (password != null && !mail.isEmpty()) {
            this.password = password;
        }
        if (gsm != null && !gsm.isEmpty()) {
            this.gsm = gsm;
        }
        if (isValidPassword(password)){
            this.password = password;
        }

        this.messages = new TreeMap<User, TreeSet<Message>>();
    }

    public String getName() {
        return this.name;
    }

    public String getMail() {
        return this.mail;
    }

    String getGsm() {
        return this.gsm;
    }

    public void sendMessage(User receiver, String content) {

        if (!this.messages.containsKey(receiver)) {
            this.messages.put(receiver, new TreeSet<Message>());
        }
        Message message = Message.createMessage(this, receiver, content);

        messages.get(receiver).add(message);
        if (!receiver.messages.containsKey(this)) {
            receiver.messages.put(this, new TreeSet<Message>());
        }
        receiver.messages.get(this).add(message);

    }

    public static boolean isValidPassword(String password) {
        boolean lowerCaseLetter = false;
        boolean upperCaseLetter = false;
        boolean number = false;

            for (int index = 0; index <= password.length() - 1; index++) {
                if (password.charAt(index) >= 'a' && password.charAt(index) <= 'z') {
                    lowerCaseLetter = true;
                    break;
                }
            }

            for (int index = 0; index <= password.length() - 1; index++) {
                if (password.charAt(index) >= 'A' && password.charAt(index) <= 'Z') {
                    upperCaseLetter = true;
                    break;
                }
            }

            for (int index = 0; index <= password.length() - 1; index++) {
                if (password.charAt(index) >= '0' && password.charAt(index) <= '9') {
                    number = true;
                    break;
                }
            }
        if ((lowerCaseLetter && upperCaseLetter && number && password.length()<15) ){
            return true;
        }
        return false;

    }
    public String getPassword() {
        return this.password;
    }
    public void printMessage() {
        for (Map.Entry<User, TreeSet<Message>> entry : this.messages.entrySet()) {
            System.out.println("USER: " + entry.getKey().name);
            System.out.println("---------------");
            for (Message m : entry.getValue()) {
                System.out.println(m.toString());
            }
        }
    }



    @Override
    public int compareTo(User arg0) {
        return this.mail.compareTo(arg0.getMail());
    }

    @Override
    public String toString() {
        return this.name + " " + this.mail + " " + this.gsm;
    }


}
