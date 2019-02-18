package com.dating.app.idateu.Homepage.DBConnector;

import java.sql.Blob;

public class StoreResult {

    Blob picture = null;
    String name = null;

    public void setProfilePic(Blob pic)
        {
        this.picture = pic;
        }

    public void setName(String name)
        {
        this.name = name;
        }

    public Blob getProfilePic()
        {
        return picture;
        }
    public String getName(){return name;}
}
