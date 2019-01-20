package com.dating.app.idateu.Homepage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.dating.app.idateu.R;

public class Pop_up extends Activity {

    ImageView imgPopUp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matched_pop_up);

        imgPopUp = findViewById(R.id.matched_pop_up_img);

        Bundle extras = getIntent().getExtras();
        imgPopUp.setImageResource(extras.getInt("picture_ID"));

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));
    }
}
