package com.bowden.robert.friend_finder_app.ServerClasses;

import android.util.Base64;

import java.time.LocalDate;
import java.util.ArrayList;

public class UserProfile extends Profile {

    private boolean authenticated = false;
    private String username;
    private String password;
    private String email;
    private ArrayList<Profile> matches;
    private ArrayList<Profile> notMatches;

    public static final UserProfile userProfile = new UserProfile();

    public UserProfile() {
        super();
    }

    public UserProfile (int id, String username, String namefirst,
                        String namelast, String dob, String image1, String image2, String bio,
                        String email, String password) {
        super(id, namefirst, namelast, dob, image1, image2,  bio);
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public void setPassword(String password) { this.password = password; }
    public String getPassword() { return password; }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

}
