package com.dating.app.idateu.Homepage;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dating.app.idateu.AgeCalculator;
import com.dating.app.idateu.Chat.Matches.MatchesActivity;
import com.dating.app.idateu.Chat.Matches.MatchesAdapter;
import com.dating.app.idateu.Maps.MapsActivity;
import com.dating.app.idateu.SignUp_LogIn.SignUpLogIn;
import com.dating.app.idateu.matchesDetail;
import com.dating.app.idateu.Homepage.DBConnector.DatabaseLoader;
import com.dating.app.idateu.Homepage.DBConnector.WriteLikeDislike;
import com.dating.app.idateu.Homepage.Pop_up.PopUp_launcher;
import com.dating.app.idateu.R;
import com.dating.app.idateu.UserCredentials;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class HomePage extends AppCompatActivity {
    private static final String TAG = "MyActivity";

    ImageView current_match;
    TextView matchName, matchAge;
    private long mLastClickTime = 0; //checks if the user has double tapped
    Queue<matchesDetail> cache = new LinkedList<>();
    int index = 1;
    matchesDetail singleData;
    Thread cacheUpdater,showData,writeLorD;
    UserCredentials userCredentials;
    ProgressBar imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
//        if (!isNetworkAvailable())
//        {
//        }
        userCredentials = new UserCredentials(this);
        current_match=findViewById(R.id.match_pic);
        matchName = findViewById(R.id.name_txt);
        matchAge = findViewById(R.id.age_txt);
        updateCache();
        displayData();
        current_match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) //stops double clicking (double pop_up)
                    {
                    return;
                    }
                mLastClickTime = SystemClock.elapsedRealtime();
                startPop_up();
            }
        });
    }

    public void tappedLogo(View view)
    {
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        startActivity(new Intent(HomePage.this,
                MapsActivity.class));
        overridePendingTransition(R.anim.slide_up, R.anim.slide_down);

    }

    public void userLiked(View view)
        {
        if (!showData.isAlive())
            {
            if (cache.isEmpty()) return; //if the app is loading, ignore the like or dislike
            writeLorD = new Thread(new writeLikeDislike(singleData.getmId(),'l'));
            writeLorD.start();
            displayData();
            }
        }

    public void userDisliked(View view) {
        if (!showData.isAlive())
            {
            if (cache.isEmpty()) return;
            writeLorD = new Thread(new writeLikeDislike(singleData.getmId(),'d'));
            writeLorD.start();
            displayData();
            }
        }

    public void message(View view)
        {
        startActivity(new Intent(HomePage.this,
                MatchesActivity.class));
        }

//    private boolean isNetworkAvailable() {
//        ConnectivityManager connectivityManager
//                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
//        return activeNetworkInfo != null;
//    }

    private void displayData()
        {
        showData = new Thread(new displayMatches());
        showData.start();
        }

    private void startPop_up()
    {
        if (cache.isEmpty()) return;
        Intent start_popup = new Intent(HomePage.this, PopUp_launcher.class);
        Bundle be = new Bundle();
        singleData.clearProfilePic();
        be.putSerializable("Object", singleData);
        start_popup.putExtras(be);
        startActivity(start_popup);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    private void saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateCache()
        {
        cacheUpdater = new Thread(new updateCache());
        cacheUpdater.start();
        }

    class updateCache implements Runnable
        {
            @Override
            public void run()
            {
            while (true) { //always keeps thread alive
                while (cache.size() < 3) {
                    matchesDetail newRecord = DatabaseLoader.addData(index);
                    DatabaseLoader.clearTemp(); //avoid memory leak
                    if (newRecord.getErrorMsg() == null) {
                        cache.add(newRecord);
                        index++;}
                    else if (newRecord.getErrorMsg().equals("unsuitable match"))
                        {
                        index++;
                        }
                    else if (newRecord.getErrorMsg().equals("End")) {
                        //check why data is null i.e. last record or non suitable match
                        Log.d(TAG, "empty");
                        index = 1;
                        cache.add(DatabaseLoader.addData(index));
                        index++;
                        }
                    else if (newRecord.getErrorMsg().equals("No_connection"))
                        {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run()
                                {
                                    current_match.setImageDrawable(null);
                                    matchName.setText("");
                                    imageLoader.setVisibility(View.VISIBLE);
                                }
                        });
                        break;
                        }
                    }
                }
            }
        }

    class displayMatches implements Runnable {

        private Bitmap bmp;
        private String mName = null;
        private int mAge;

        @Override
        public void run() {
            imageLoader=findViewById(R.id.loadingImage);
            while (cache.isEmpty()) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        current_match.setImageDrawable(null);
                        matchName.setText("");
                        imageLoader.setVisibility(View.VISIBLE);
                    }
                });
            }
            if (!cache.isEmpty()) {
                singleData = cache.remove();
                mName = singleData.getName();
                bmp = DatabaseLoader.image(singleData.getProfilePic());
                saveToInternalStorage(bmp);
                Date dob = singleData.getDOB();
                mAge = AgeCalculator.ageCalculation(dob);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            imageLoader.setVisibility(View.GONE);
                            current_match.setImageBitmap(bmp);
                            matchName.setText(mName+",");
                            matchAge.setText(String.valueOf(mAge));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        return;}
    }

    class writeLikeDislike implements Runnable
        {
        int mId;
        char status;

        public writeLikeDislike(int mId, char status)
            {
            this.mId = mId;
            this.status = status;
            }

        @Override
        public void run()
            {
            new WriteLikeDislike(mId,status);
            }
        }

    @Override
    public void onRestart()
        {
        boolean stillLoggedIn = userCredentials.getSP().getBoolean("loggedIn",true);
        if (!stillLoggedIn)
            {
            startActivity(new Intent(HomePage.this, SignUpLogIn.class));
            }
        super.onRestart();
        }

    @Override
    public void onBackPressed()
        {
            final Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Log out");
            builder.setMessage("Would you like to log out?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    userCredentials.clearUserData();
                    userCredentials.setUserLoggedIn(false);
                    startActivity(a);
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    startActivity(a);
                }
            });
            builder.show();
        }

        }
