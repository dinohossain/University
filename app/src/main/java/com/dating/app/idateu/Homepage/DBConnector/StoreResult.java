package com.dating.app.idateu.Homepage.DBConnector;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;

public class StoreResult implements Serializable {
    //Makes object transferring to different activities possible

    String name = null;
    String bio = null;
    String picture = null;

    public StoreResult(String pic, String name, String bio)
        {
        this.picture=pic;
        this.name=name;
        this.bio=bio;
        }

    public String getProfilePic()
        {
        return picture;
        }

    public String getName(){return name;}
    public String getBio(){return bio;}
}