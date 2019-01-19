package com.dating.app.idateu.Homepage;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.dating.app.idateu.R;

public class Pop_up extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matched_pop_up);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));
    }
}
