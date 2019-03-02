package com.dating.app.idateu.SignUp_LogIn;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
                if(noError()) makeConnection();
                //noError checks for valid email and password
                // makeConnection will call the Async to make a connection with the DB
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
            boolean status = false;
            String email = email_edit.getText().toString();
            String password = pass_edit.getText().toString();
            if (isEmailValid(email)
                && didUserTypeInPassword(password))
                {
                status = true;
                }
            return status;}

        private void makeConnection()
            {
            String email = email_edit.getText().toString();
            String password = pass_edit.getText().toString();
            AsyncCaller runner = new AsyncCaller(this);
            runner.execute(email,password);
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

        private class AsyncCaller extends AsyncTask<String, Integer, Integer> {

            private ProgressDialog dialog;
            boolean didLogInPass = false;


            public AsyncCaller(SignUpLogIn activity)
                {
                dialog = new ProgressDialog(activity);
                }


            @Override
            protected void onPreExecute()
                {
                dialog.setMessage("Logging you in ...");
                dialog.show();
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
                }

            @Override
            protected Integer doInBackground(String... params)
                {
                Integer status1 = new DatabaseConnectorLogIn(params[0], params[1]).loadDataUserDetail();
                return status1;
                }

            @Override
            protected void onPostExecute(Integer result)
                {
                super.onPostExecute(result);
                dialog.dismiss();
                switch (result)
                    {
                    case -1:noRecordMsg.setVisibility(View.VISIBLE);
                            noRecordMsg.setText("Server not responding");
                            break;
                    case 0: noRecordMsg.setVisibility(View.VISIBLE);
                            noRecordMsg.setText("This account does not exist");
                            break;
                    case 1: pass_edit.setError("Wrong Password");
                            break;
                    case 2: didLogInPass=true;
                            startActivity(new Intent(SignUpLogIn.this,
                                          HomePage.class));
                    }
                }
        }
    }