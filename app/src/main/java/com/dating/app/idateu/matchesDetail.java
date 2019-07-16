package com.dating.app.idateu;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;

public class matchesDetail implements Serializable {
    //Makes object transferring to different activities possible

    static int mId;
    static String name = null;
    Date dob = null;
    String bio = null;
    static String picture = null;
    String orientation  = null;

    String error = null;
    /**
    * The constructor must have details of the person
    *  @param mId matches id
    *  @param pic picture
    *  @param name matches name
     * @param dob Date of Birth
     * @param  orientation Orientation of the person
     * @param bio short biography
    */
    public matchesDetail(int mId, String pic, String name, Date dob , String orientation,
                         String bio)
        {
        this.mId = mId;
        this.picture=pic;
        this.name=name;
        this.dob = dob;
        this.bio=bio;
        this.orientation = orientation;
        }

    public matchesDetail(String error)
    {
        this.error = error;
    }

    public String getErrorMsg()
        {
        return error;
        }

    public void clearProfilePic()
        {
        this.picture = "";
        } //so that text data can be transferred to pop up

    public static int getmId(){return mId;}
    public static String getProfilePic() {return picture;}
    public static String getName(){return name;}
    public String getBio(){return bio;}
    public String getOrientation() {return orientation;}
    public Date getDOB() {return dob;}
}