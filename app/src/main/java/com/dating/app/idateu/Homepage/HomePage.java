package com.dating.app.idateu.Homepage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.dating.app.idateu.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {



    ImageView current_match;
    Button like_button, dislike_button;
    int matchPicIndex = 0;


    private ArrayList<Integer> matchImages = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        matchImages.add(R.drawable.img_001);
        matchImages.add(R.drawable.img_002);
        matchImages.add(R.drawable.img_003);

        current_match=(ImageView)findViewById(R.id.match_pic);
        ImageView image = new ImageView(this);
        Picasso.get().load(matchImages.get(0)).into(current_match); //loads initial image
        like_button=(Button) findViewById(R.id.like_btn);

        dislike_button = findViewById(R.id.dislike_btn);

        like_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
                {
                if (matchImages.size() != matchPicIndex +1)
                    {
                    matchPicIndex++;
                    Picasso.get().load(matchImages.get(matchPicIndex)).into(current_match);
                    }
                else
                    {
                    matchPicIndex = 0;
                    Picasso.get().load(matchImages.get(matchPicIndex)).into(current_match);
                    }
                }
            });

        current_match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent start_popup = new Intent(HomePage.this, Pop_up.class);
                start_popup.putExtra("picture_ID", matchImages.get(matchPicIndex));
                startActivity(start_popup);
            }
        });

        }

    }


