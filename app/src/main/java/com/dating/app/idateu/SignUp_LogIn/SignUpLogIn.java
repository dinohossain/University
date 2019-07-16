package com.dating.app.idateu.SignUp_LogIn;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dating.app.idateu.Homepage.HomePage;
import com.dating.app.idateu.R;
import com.dating.app.idateu.SignUp_LogIn.DBConnection.DatabaseConnectorLogIn;
import com.dating.app.idateu.UserCredentials;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpLogIn extends AppCompatActivity
    {
    Button register_btn;
    EditText email_edit, pass_edit;
    TextView noRecordMsg;

    UserCredentials uc;
    SharedPreferences myPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        uc= new UserCredentials(getApplicationContext());
        if (!isNetworkAvailable())
            {
            notifyUser();
            }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_view);
        noRecordMsg=findViewById(R.id.no_record);
        noRecordMsg.setVisibility(View.INVISIBLE);
        email_edit = findViewById(R.id.emailInput);
        pass_edit = findViewById(R.id.passwordInput);
        register_btn = findViewById(R.id.register_button);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
                {
                startActivity(new Intent(SignUpLogIn.this, SecondSignUpPage.class));
                }
        });
        myPrefs = getSharedPreferences("userCredentials", MODE_PRIVATE);
        String defaultEmail = getResources().getString(R.string.default_email);
        if (!myPrefs.getString("email",defaultEmail).equals(defaultEmail))
            {
            automaticLogin();
            }
        }

        private void automaticLogin()
            {
            myPrefs = this.getSharedPreferences("userCredentials", MODE_WORLD_READABLE);
            String email = myPrefs.getString("email","");
            String password = myPrefs.getString("password","");
            AsyncCaller runner = new AsyncCaller(this);
            runner.execute(email,password);
            }

        private void notifyUser()
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

        public void clickedLogin(View view)
            {
            if(noError()) makeConnection();
            //noError checks for valid email and password
            // makeConnection will call the Async to make a connection with the DB
            }

        private boolean isNetworkAvailable() {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null;
        }

        private boolean noError() {
            boolean status = false;
            String email = email_edit.getText().toString();
            String password = pass_edit.getText().toString();
            boolean emailOk = isEmailValid(email), passwordOk = didUserTypeInPassword(password);
            if (emailOk && passwordOk) {
                status = true;
            } else if (!emailOk) {
                email_edit.setError("Invalid Email");
            } else if (!passwordOk){
                pass_edit.setError("Invalid Password");
            }

            return status;}

        private void makeConnection()
            {
            String email = email_edit.getText().toString();
            String password = pass_edit.getText().toString();
            AsyncCaller runner = new AsyncCaller(this);
            runner.execute(email,password);
            }

           //useful for writing unit test

        private boolean isEmailValid(String s)
            {
            Pattern VALID_EMAIL_ADDRESS_REGEX =
                    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{1,6}$", Pattern.CASE_INSENSITIVE);
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(s);
            boolean stat = matcher.find() == true ? true : false;
            return stat;
            }

        private boolean didUserTypeInPassword(String password)
            {
            if (password.length() > 0 && !password.contains(" ")) return true;
            else { return false; }
            }


        private class AsyncCaller extends AsyncTask<String, Integer, Integer> {

            private ProgressDialog dialog;
            boolean didLogInPass = false;


            public AsyncCaller(SignUpLogIn activity) {
                dialog = new ProgressDialog(activity);
            }


            @Override
            protected void onPreExecute() {
                lockScreenOrientation();
                dialog.setMessage("Logging you in ...");
                dialog.show();
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
            }

            @Override
            protected Integer doInBackground(String... params) {
                Integer status1 = new DatabaseConnectorLogIn(params[0], params[1]).loginUser();
                return status1;
            }

            @Override
            protected void onPostExecute(Integer result) {
                super.onPostExecute(result);
                dialog.dismiss();
                unlockScreenOrientation();
                switch (result) {
                    case -1:
                        noRecordMsg.setVisibility(View.VISIBLE);
                        noRecordMsg.setText("Server not responding");
                        break;
                    case 0:
                        noRecordMsg.setVisibility(View.VISIBLE);
                        noRecordMsg.setText("This account does not exist");
                        break;
                    case 1:
                        pass_edit.setError("Wrong Password");
                        break;
                    case 2:
                        didLogInPass = true;
                        uc.storeUserData(email_edit.getText().toString(), pass_edit.getText().toString());
                        uc.setUserLoggedIn(true);
                        startActivity(new Intent(SignUpLogIn.this,
                                HomePage.class));
                }
            }

            private void lockScreenOrientation()
                {
                int currentOrientation = getResources().getConfiguration().orientation;
                if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    }
                else
                    {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    }
                }
            private void unlockScreenOrientation()
                {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
                }
            }
    }