package com.bowden.robert.friend_finder_app.TestClasses;

public class FriendsChatTest {
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
