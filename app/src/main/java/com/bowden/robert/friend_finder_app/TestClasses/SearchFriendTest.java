package com.bowden.robert.friend_finder_app.TestClasses;

import java.util.List;

public class SearchFriendTest {

    // Members
    private int imageResourcePP;
    private int imageResourceMeme;
    private String personBio;
    private String personName;
    private String personAge;
    private List<String> personInterests;

    public SearchFriendTest (int imageResourcePP, int imageResourceMeme, String personBio,
                             String personName, String personAge, List<String> personInterests) {
        this.imageResourcePP = imageResourcePP;
        this.imageResourceMeme = imageResourceMeme;
        this.personBio = personBio;
        this.personName = personName;
        this.personAge = personAge;
        this.personInterests = personInterests;
    }

    // Methods i.e. get + set.
    public int getImageResourcePP() {
        return imageResourcePP;
    }
    public int getImageResourceMeme() {
        return imageResourceMeme;
    }
    public String getPersonBio() {
        return personBio;
    }
    public String getPersonName() {
        return personName;
    }
    public String getPersonAge() {
        return personAge;
    }
    public List<String> getPersonInterests() {
        return personInterests;
    }
}
