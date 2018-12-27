package com.dating.app.idateu.SignUp_LogIn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dating.app.idateu.Homepage.HomePage;
import com.dating.app.idateu.R;

public class SignUpLogIn extends AppCompatActivity
    {

    Button register_btn;
    Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_view);

        register_btn = (Button)findViewById(R.id.register_button);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
                {
                startActivity(new Intent(SignUpLogIn.this, SecondSignUpPage.class));
                }
        });

        login_btn = (Button)findViewById(R.id.login_button);
        login_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                startActivity(new Intent(SignUpLogIn.this, HomePage.class));
                }

        });
        }
    }