package com.dating.app.idateu.Homepage;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;

import com.dating.app.idateu.R;
import com.dating.app.idateu.SignUp_LogIn.SecondSignUpPage;
import com.dating.app.idateu.SignUp_LogIn.ThirdSignUpPage;

public class HomePage extends AppCompatActivity {

    ImageView current_match;
    Button like_button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        current_match=(ImageView)findViewById(R.id.match_pic);
        current_match.setImageResource(R.drawable.dummy_pic);
        like_button=(Button) findViewById(R.id.like_btn);
        like_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current_match.setImageResource(R.drawable.water_girl);
            }
            });
        }

    }


