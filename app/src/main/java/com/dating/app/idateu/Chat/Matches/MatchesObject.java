package com.dating.app.idateu.Chat.Matches;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class MatchesObject {
    private String matchUserID;
    private String name;
    private Bitmap MatchImage;

    public MatchesObject (int matchUserID, String name, Bitmap MatchImage){
        this.matchUserID = String.valueOf(matchUserID);
        this.name = name;
        this.MatchImage = MatchImage;
    }

    public String getUser_ID() { return matchUserID;}
    public void setUser_ID(String user_ID) {
        this.matchUserID = user_ID;
    }

    public String getName() { return name;}
    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getMatchImage() { return MatchImage;}
    public void setMatchImage(ImageView profile_pic) {
        this.MatchImage = MatchImage;
    }







}
