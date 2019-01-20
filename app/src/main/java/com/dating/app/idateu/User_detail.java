package com.dating.app.idateu;

/*Singleton pattern has been used
because only ONE user can use the app*/

public class User_detail implements get_user_info
    {
    private static User_detail user = null;

    public String s;

    private User_detail()
        {
         s = "User initialised";
        }

    public static User_detail getInstance()
        {
        if (user == null)
            {
            user = new User_detail();
            }
        return user;
        }

        private void setFirstName(String fName) {

        }

        private void setLastName(String lName) {

        }

        private void setUserName(String uName) {

        }

        private void setGender(String uGender) {

        }

        private void setDOB(int uDob) {

        }

        private void setOrientation(String uOrientation) {

        }

        private void setEmail(String uEmail) {

        }

        private void setPassword(String uPassword) {

        }

        @Override
        public String getFirstName() {
            return null;
        }

        @Override
        public String getLastName() {
            return null;
        }

        @Override
        public String getUserName() {
            return null;
        }

        @Override
        public String getGender() {
            return null;
        }

        @Override
        public int getDOB() {
            return 0;
        }

        @Override
        public String getOrientation() {
            return null;
        }

        @Override
        public String getEmail() {
            return null;
        }

        @Override
        public String getPassword() {
            return null;
        }
    }
