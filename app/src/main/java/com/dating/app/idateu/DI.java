package com.dating.app.idateu;

import java.sql.Blob;

public class DI {

    Blob picture = null;


    public void setProfilePic(Blob pic)
        {
        this.picture = pic;
        }

    public Blob getProfilePic()
        {
        return picture;
        }
}
