package com.dating.app.idateu.Homepage;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;


import com.dating.app.idateu.R;

public class PopUp_launcher extends AppCompatActivity {

    private static final String TAG = "Pop_luancher";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_for_pop_up);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));

        Pop_up fragment = new Pop_up();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment, "Pop_up");
        transaction.commit();

    }


    private static final int SWIPE_THRESHOLD = 120;
    private static final int SWIPE_VELOCITY_THRESHOLD = 200;


}