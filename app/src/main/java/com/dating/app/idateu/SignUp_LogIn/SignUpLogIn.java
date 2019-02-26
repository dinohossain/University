package com.dating.app.idateu.SignUp_LogIn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dating.app.idateu.Homepage.HomePage;
import com.dating.app.idateu.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpLogIn extends AppCompatActivity
    {

    Button register_btn;
    Button login_btn;

    EditText email_edit;
    EditText pass_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_view);

        email_edit = findViewById(R.id.emailInput);
        pass_edit = findViewById(R.id.passwordInput);

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

        private boolean isThereAError()
            {
            boolean status = false;
            String email = email_edit.getText().toString();
            String password = pass_edit.getText().toString();
            if (isEmailValid(email)==false)
                {
                    email_edit.setError("Invalid Email");
                    return status = true;
                }
            if (doesRecordExistInDB(email,password))
                {

                }
            return status;}

        private boolean doesRecordExistInDB(String email, String password)
            {

            return false;
            }

        private boolean isEmailValid(String s)
            {
                Pattern VALID_EMAIL_ADDRESS_REGEX =
                        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
                Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(s);
                boolean stat = matcher.find() == true ? true : false;
                return stat;
            }

    }