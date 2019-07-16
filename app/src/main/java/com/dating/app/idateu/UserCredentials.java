package com.dating.app.idateu;

import android.content.Context;
import android.content.SharedPreferences;

public class UserCredentials {

    public static final String SP_NAME = "userCredentials";

    SharedPreferences userLocalDatabase;

    public UserCredentials(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(String email, String password) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putString("email", email);
        userLocalDatabaseEditor.putString("password",password);
        userLocalDatabaseEditor.apply();
    }

    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putBoolean("loggedIn", loggedIn);
        userLocalDatabaseEditor.apply();
    }

    public SharedPreferences getSP()
        {
            return userLocalDatabase;
        }

    public void clearUserData() {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.clear();
        userLocalDatabaseEditor.apply();
    }

}
