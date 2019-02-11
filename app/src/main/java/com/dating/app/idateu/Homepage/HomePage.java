package com.dating.app.idateu.Homepage;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dating.app.idateu.Homepage.Pop_up.PopUp_launcher;

import com.dating.app.idateu.R;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

    int matchPicIndex = 0;
    ImageView current_match;
    Button like_button, dislike_button;
    TextView userName;
    private long mLastClickTime = 0;

    private ArrayList<Integer> matchImages = new ArrayList<Integer>();
    private ArrayList<String> userNameList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        startImageLoading();
        connection();
        current_match=(ImageView)findViewById(R.id.match_pic);
        userName = findViewById(R.id.username_txt);
        ImageView image = new ImageView(this);
        Picasso.get().load(matchImages.get(matchPicIndex)).into(current_match); //loads initial image
        initiateUserName();
        userName.setText(userNameList.get(matchPicIndex));
        like_button=(Button) findViewById(R.id.like_btn);
        dislike_button = findViewById(R.id.dislike_btn);

        like_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
                {
                    startImageSwitching();
                }
            });

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

    private void connection()
    {
        try
        {
            PreparedStatement stmt;
            ResultSet rs;

            //Register the JDBC driver for MySQL

            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://172.31.82.74/idateu";

            Connection con = DriverManager.getConnection( url,"root","Admin123");

            //Get a Statement object

            // insert image
            File image = new File("img_001.png");
            FileInputStream inputStream = new FileInputStream(image);


            // Insert a row

            stmt = con.prepareStatement("INSERT INTO user (user_ID,profile_pic,name,dob,gender,orientation,bio) " + "VALUES(?,?,?,?,?,?,?);");
            stmt.setInt(1, 1);
            stmt.setBinaryStream(2, (InputStream) inputStream, (int)(image.length()));
            stmt.setString(3, "Thomas");
            stmt.setDate(4, java.sql.Date.valueOf("2013-09-04"));
            stmt.setString(5, "M");
            stmt.setString(6, "S");
            stmt.setString(7, "Depending on howmany columns you wish to modify it "
                    + "might be best to generate a script, "
                    + "or use some kind of mysql client GUI");
            stmt.executeUpdate();

        }
        catch (SQLException e) {
            System.out.println(e);}
        catch(FileNotFoundException e)
        {
            System.out.println("FileNotFoundException: - " + e);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }

    private void initiateUserName()
        {
        userNameList.add(getResources().getString(R.string.img1Name));
        userNameList.add(getResources().getString(R.string.img2Name));
        userNameList.add(getResources().getString(R.string.img3Name));

        }

    private void imageChanger()
            {
            if (matchImages.size() != matchPicIndex +1)
                {
                matchPicIndex++;
                Picasso.get().load(matchImages.get(matchPicIndex)).into(current_match);
                userName.setText(userNameList.get(matchPicIndex));
                }
            else
                {
                matchPicIndex = 0;
                Picasso.get().load(matchImages.get(matchPicIndex)).into(current_match);
                userName.setText(userNameList.get(matchPicIndex));
                }
            }

    private void initialImages()
            {
            matchImages.add(R.drawable.img_001);
            matchImages.add(R.drawable.img_002);
            matchImages.add(R.drawable.img_003);
            }

    private void startPop_up()
        {
        Intent start_popup = new Intent(HomePage.this, PopUp_launcher.class);
        start_popup.putExtra("picture_ID", matchImages.get(matchPicIndex));
        start_popup.putExtra("index",matchPicIndex+1);
        startActivity(start_popup);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        }

    public void startImageLoading() {
        new Thread(new Image_loader_thread()).start();
    }

    public void startImageSwitching() {
        new Thread(new image_switch_thread()).start();
    }

    class Image_loader_thread implements Runnable {
        @Override
        public void run() {
                initialImages();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    class image_switch_thread implements Runnable {
        @Override
        public void run() {
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageChanger();
                        }
                    });
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }

    @Override
    public void onBackPressed()
        {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }


}





