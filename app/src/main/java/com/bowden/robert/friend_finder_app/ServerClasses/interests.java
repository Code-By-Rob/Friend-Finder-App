package com.bowden.robert.friend_finder_app.ServerClasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum interests {

    FOOTBALL("Football"),
    RUGBY("Rugby"),
    TENNIS("Tennis"),
    WRITING("Writing"),
    BLOGGING("Blogging"),
    PROGRAMMING("Programming"),
    BAKING("Baking"),
    MUSIC("Music"),
    MAKINGMUSIC("Making Music"),
    DRINKING("Drinking"),
    TATTOOING("Tattooing"),
    GRAPHICDESIGN("Graphic Design"),
    DRIVING("Driving"),
    RACING("Racing"),
    YOUTUBE("YouTube"),
    VIDEOEDITING("Video Editing"),
    ANIMATION("Animation"),
    CYCLING("Cycling"),
    HIKING("Hiking"),
    SEWING("Sewing"),
    MAGIC("Magic"),
    PHOTOGRAPHY("Photography"),
    JOURNALISM("Journalism"),
    READING("Reading"),
    SINGING("Singing"),
    BOXING("Boxing"),
    SWIMMING("Swimming"),
    MARTIALARTS("Martial Arts"),
    GYM("Gym"),
    WEIGHTLIFTING("Weight Lifting"),
    FENCING("Fencing"),
    ARCHERY("Archery"),
    FLYING("Flying"),
    SHOOTING("Shooting"),
    YOGA("Yoga");

    private String interest;

    private static final List<String> VALUES;

    static {
        VALUES = new ArrayList<>();
        for (interests i :
                interests.values()) {
            VALUES.add(i.interest);
        }
    }

    interests(String s) {
        this.interest = s;
    }

    public static List<String> getInterests() {
        return Collections.unmodifiableList(VALUES);
    }

}
