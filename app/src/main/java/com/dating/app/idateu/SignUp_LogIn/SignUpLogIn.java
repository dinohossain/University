package com.dating.app.idateu.SignUp_LogIn;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dating.app.idateu.Homepage.HomePage;
import com.dating.app.idateu.R;
import com.dating.app.idateu.SignUp_LogIn.DBConnection.DatabaseConnectorLogIn;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpLogIn extends AppCompatActivity
    {
    Button register_btn;
    Button login_btn;

    EditText email_edit;
    EditText pass_edit;

    TextView noRecordMsg;
    ProgressBar loadingCredentials;

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
            if (!isNetworkAvailable())
                {
                new AlertDialog.Builder(SignUpLogIn.this)
                        .setTitle("No Internet Connection")
                        .setMessage("Please make sure you are connected to the internet")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setNeutralButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which)
                                {
                                Intent a = new Intent(Intent.ACTION_MAIN);
                                a.addCategory(Intent.CATEGORY_HOME);
                                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(a);
                                }
                        })
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .show();
                 }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_view);

        noRecordMsg=findViewById(R.id.no_record);
        noRecordMsg.setVisibility(View.INVISIBLE);
        email_edit = findViewById(R.id.emailInput);
        pass_edit = findViewById(R.id.passwordInput);
        loadingCredentials = findViewById(R.id.loadingCredentials);
        loadingCredentials.setVisibility(View.INVISIBLE);

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
                if(noError()) startActivity(new Intent(SignUpLogIn.this, HomePage.class));
                loadingCredentials.setVisibility(View.INVISIBLE);
                }
            });

        }

        private boolean isNetworkAvailable() {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null;
        }

        private boolean noError()
            {
            loadingCredentials.setVisibility(View.VISIBLE);
            boolean status = false;
            String email = email_edit.getText().toString();
            String password = pass_edit.getText().toString();
            if (isEmailValid(email)
                && didUserTypeInPassword(password)
                && doesRecordExistInDB(email,password))
                {
                status = true;
                }
            return status;}

        private boolean doesRecordExistInDB(String email, String password)
            {
            internet_thread backendData = new internet_thread(email,password);
            Thread load = new Thread(backendData);
            load.start();
            while (load.isAlive()) {loadingCredentials.setVisibility(View.VISIBLE);}
            int status = backendData.getStatus();
            switch (status)
                {
                case 0: noRecordMsg.setVisibility(View.VISIBLE);
                        return false;
                case 1: pass_edit.setError("Wrong Password");
                        return false;
                case 2: return true;
                }
            return false;
            }

        private boolean isEmailValid(String s)
            {
                Pattern VALID_EMAIL_ADDRESS_REGEX =
                        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
                Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(s);
                boolean stat = matcher.find() == true ? true : false;
                if (!stat) email_edit.setError("Invalid Email");
                return stat;
            }

        private boolean didUserTypeInPassword(String password)
            {
            if (password.length() > 0) return true;
            else
                {
                pass_edit.setError("Enter your password");
                return false;
                }
            }

        private class internet_thread implements Runnable
            {
                String email;
                String password;
                int status;


                public internet_thread(String email, String password)
                    {
                    this.email = email;
                    this.password = password;
                    }

                @Override
                public void run()
                    {
                    int status = new DatabaseConnectorLogIn(email,password).loadDataUserDetail();
                    this.status=status;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try
                                {
                                loadingCredentials.setVisibility(View.VISIBLE);

                                }
                                catch (Exception e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }

                public int getStatus()
                    {
                    return status;
                    }
            }

    }