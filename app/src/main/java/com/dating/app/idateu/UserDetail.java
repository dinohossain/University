package com.dating.app.idateu;

/*
* The purpose of this class is to check
* if the app has already stored in the log in details.
* If it login details are present then it should load to the homepage.
* Otherwise it should boot the SignUpLogIn class.
*/

import java.util.Date;

public class UserDetail
    {
    private static int user_id = 5;
    private static String users_name;
    private static Date dob;
    private static String gender = "M";
    private static String orientation = "S";
    private static String bio;


        public static int getUser_id() {
            return user_id;
        }

        public static void setUser_id(int user_id) {
            UserDetail.user_id = user_id;}

        public static String getUsers_name() {
            return users_name;
        }

        public static void setUsers_name(String users_name) {
            UserDetail.users_name = users_name;
        }

        public static Date getDob() {
            return dob;
        }

        public static void setDob(Date dob) {
            UserDetail.dob = dob;
        }

        public static String getGender() {
            return gender;
        }

        public static void setGender(String gender) {
            UserDetail.gender = gender;
        }

        public static String getOrientation() {
            return orientation;
        }

        public static void setOrientation(String orientation) {
            UserDetail.orientation = orientation;
        }

        public static String getBio() {
            return bio;
        }

        public static void setBio(String bio) {
            UserDetail.bio = bio;
        }
    }
