package com.dating.app.idateu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dating.app.idateu.SignUp_LogIn.SignUpLogIn;

/*
* The purpose of this class is to check
* if the app has already stored in the log in details.
* If it login details are present then it should load to the homepage.
* Otherwise it should boot the SignUpLogIn class.
*/

public class AppLauncher extends AppCompatActivity
    {
        @Override
        protected void onCreate(Bundle savedInstanceState)
            {
            super.onCreate(savedInstanceState);
            startActivity(new Intent(AppLauncher.this, SignUpLogIn.class));
            }
    }
