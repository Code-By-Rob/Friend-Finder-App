package com.bowden.robert.friend_finder_app.TestClasses;

public class FriendsChatTest {

    /*
    I feel that these TextClasses don't need much explanation.
    They were used to test certain aspects of the front-end.
     */

    // Members
    String friendName;
    String recentText;
    int friendimage;

    public FriendsChatTest (String name, String text, int image) {
        this.friendName = name;
        this.recentText = text;
        this.friendimage = image;
    }

    public String getFriendName() {
        return friendName;
    }
    public String getRecentText() {
        return recentText;
    }
    public int getFriendimage() {
        return friendimage;
    }
}
