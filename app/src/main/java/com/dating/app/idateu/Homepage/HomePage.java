package com.dating.app.idateu.Homepage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dating.app.idateu.Homepage.DBConnector.StoreResult;
import com.dating.app.idateu.Homepage.DBConnector.DatabaseConnectorHomepage;
import com.dating.app.idateu.Homepage.Pop_up.PopUp_launcher;
import com.dating.app.idateu.R;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


public class HomePage extends AppCompatActivity {

    ImageView current_match;
    Button like_button, dislike_button;
    TextView userName;
    private long mLastClickTime = 0;
    int matchIndex = 1;
    StoreResult data;
    Bitmap bmp = null;
    String mName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        current_match=findViewById(R.id.match_pic);
        userName = findViewById(R.id.username_txt);

        like_button= findViewById(R.id.like_btn);
        dislike_button = findViewById(R.id.dislike_btn);
        LoadImageforIndex();

        like_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
                {
                matchIndex++;
                LoadImageforIndex();
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

    private void startPop_up()
        {
        Intent start_popup = new Intent(HomePage.this, PopUp_launcher.class);
        Bundle be = new Bundle();
        be.putSerializable("Object",data);
        start_popup.putExtras(be);
        startActivity(start_popup);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        }

    public void LoadImageforIndex() {
        new Thread(new internet_thread()).start();

    }

    class internet_thread implements Runnable {
        @Override
        public void run() {
            DatabaseConnectorHomepage connect_image = new DatabaseConnectorHomepage();
            data = connect_image.loadImage(matchIndex);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (data!=null)
                        {
                        try
                            {
                            mName = data.getName();
                            InputStream in = null;
                            in = new ByteArrayInputStream(data.getProfilePic().getBytes(StandardCharsets.UTF_8));
                            BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
                            bmp = BitmapFactory.decodeStream(bufferedInputStream);
                            current_match.setImageBitmap(bmp);
                            userName.setText(mName);
                            }
                        catch (Exception e)
                            {
                            e.printStackTrace();
                            }
                        }
                    else
                        {
                        matchIndex =1;
                        LoadImageforIndex();
                        }
                }
            });
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





