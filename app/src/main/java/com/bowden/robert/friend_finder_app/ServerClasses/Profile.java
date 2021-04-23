package com.bowden.robert.friend_finder_app.ServerClasses;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.util.ArrayList;
import java.util.List;

public class Profile {

    /*
    * Members
    * The profile of the person using the application
    * Their details will be accessed here
    * Store their details in SharedPreferences for ease of access and use
    * e.g. logging in quickly.
    * This will also be where the data used on signing up will be stored
    * and the data to send to the server database.
     */

    private int id;
    private String namefirst;
    private String namelast;
    private String dob;
    private String image1;
    private String image2;
    private String bio;
    private List<String> interests = new ArrayList<>();

    // Constructors

    public Profile() {

    }

    public Profile (int id, String namefirst, String namelast,
                    String dob, String image1, String image2, String bio) {
        this.id = id;
        this.namefirst = namefirst;
        this.namelast = namelast;
        this.dob = dob;
        this.image1 = image1;
        this.image2 = image2;
        this.bio = bio;
    }

    // public getter and setters

    public void setID(int id) {
        this.id = id;
    }
    public int getID() { return id; }

    public void setNamefirst(String namefirst) {
        this.namefirst = namefirst;
    }
    public String getNamefirst() {
        return namefirst;
    }

    public void setNamelast(String namelast) {
        this.namelast = namelast;
    }
    public String getNamelast() {
        return namelast;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
    public String getDob() {
        return dob;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }
    public String getImage1() {
        return image1;
    }
    public Bitmap getImage1Bitmap() {
        byte[] decodedString = Base64.decode(image1, Base64.DEFAULT);
        Bitmap pImage = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return pImage;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }
    public String getImage2() {
        return image2;
    }
    public Bitmap getImage2Bitmap() {
        byte[] decodedString = Base64.decode(image2, Base64.DEFAULT);
        Bitmap pImage = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return pImage;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
    public String getBio() {
        return bio;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }
    public void addInterest(String i) {
        interests.add(i);
    }
    public List<String> getInterests() {
        return interests;
    }

}
