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
    private String mail;
    private String gsm;
    private String password;


    private TreeMap<User, TreeSet<Message>> messages;

    public User(String name, String mail, String gsm) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
        if (mail != null && !mail.isEmpty()) {
            this.mail = mail;
        }
        if (gsm != null && !gsm.isEmpty()) {
            this.gsm = gsm;
        }
        this.password = createPassword();
        this.messages = new TreeMap<User, TreeSet<Message>>();
    }

    String getName() {
        return this.name;
    }

    String getMail() {
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

    private static String createPassword() {
        Scanner sc = new Scanner(System.in);
        boolean lowerCaseLetter = false;
        boolean upperCaseLetter = false;
        boolean number = false;
        String attemptedPassword;
        do { System.out.println("Please input password between 8 and 20 characters, with at least 1 number, capital letter and lower case letter");

            attemptedPassword = sc.nextLine();

            for (int index = 0; index <= attemptedPassword.length() - 1; index++) {
                if (attemptedPassword.charAt(index) >= 'a' && attemptedPassword.charAt(index) <= 'z') {
                    lowerCaseLetter = true;
                    break;
                }
            }

            for (int index = 0; index <= attemptedPassword.length() - 1; index++) {
                if (attemptedPassword.charAt(index) >= 'A' && attemptedPassword.charAt(index) <= 'Z') {
                    upperCaseLetter = true;
                    break;
                }
            }

            for (int index = 0; index <= attemptedPassword.length() - 1; index++) {
                if (attemptedPassword.charAt(index) >= '0' && attemptedPassword.charAt(index) <= '9') {
                    number = true;
                    break;
                }
            }
        } while ( !(lowerCaseLetter && upperCaseLetter && number && attemptedPassword.length() > 8 && attemptedPassword.length()<20) );
        System.out.println("Password successful");
        return attemptedPassword;

    }
    String getPassword() {
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
