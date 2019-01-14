package com.dating.app.idateu.Homepage;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dating.app.idateu.R;


import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

    ImageView current_match;
    Button like_button;
    int number_of_clicks = 0;
    Dialog mDialog;


    private ArrayList<Integer> matchImages = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        matchImages.add(R.drawable.img_001);
        matchImages.add(R.drawable.img_002);
        matchImages.add(R.drawable.img_003);

        current_match=(ImageView)findViewById(R.id.match_pic);
        current_match.setImageResource(matchImages.get(0));
        like_button=(Button) findViewById(R.id.like_btn);
        like_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
                {
                if (matchImages.size() != number_of_clicks+1)
                    {
                    number_of_clicks++;
                    current_match.setImageResource(matchImages.get(number_of_clicks));
                    }
                else
                    {
                    number_of_clicks = 0;
                    current_match.setImageResource(matchImages.get(number_of_clicks));
                    }
                }
            });
        }

    public void ShowPopUp(View v)
        {
        ImageView match_pop;
        TextView name_txt;
        mDialog.setContentView(R.layout.matched_pop_up);
        match_pop = findViewById(R.id.matched_pop_up_img);
        name_txt = findViewById(R.id.matched_pop_txt);
        }


    }


