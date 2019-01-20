package com.dating.app.idateu.Homepage;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dating.app.idateu.R;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {


    ImageView current_match;
    Button like_button, dislike_button;
    int matchPicIndex = 0;
    Dialog mDialog;


    private ArrayList<Integer> matchImages = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        matchImages.add(R.drawable.img_001);
        matchImages.add(R.drawable.img_002);
        matchImages.add(R.drawable.img_003);
        mDialog = new Dialog(this);

        current_match=(ImageView)findViewById(R.id.match_pic);
        current_match.setImageResource(matchImages.get(0));

        like_button=(Button) findViewById(R.id.like_btn);

        dislike_button = findViewById(R.id.dislike_btn);

        like_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
                {
                if (matchImages.size() != matchPicIndex +1)
                    {
                    matchPicIndex++;
                    current_match.setImageResource(matchImages.get(matchPicIndex));
                    }
                else
                    {
                    matchPicIndex = 0;
                    current_match.setImageResource(matchImages.get(matchPicIndex));
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


