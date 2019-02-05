package com.dating.app.idateu.Homepage;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import com.dating.app.idateu.R;

import java.util.List;
import java.util.Vector;


public class PopUp_launcher extends FragmentActivity {

    private static final String TAG = "Pop_launcher";


    Bundle extras;
    Integer selected_img;
    int indexForBio;

    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_for_pop_up);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));

        this.initialisePaging();
    }

    private void initialisePaging() {

        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this, Pop_up.class.getName()));
        fragments.add(Fragment.instantiate(this, Pop_up_bio.class.getName()));
        this.mPagerAdapter  = new VerticalViewPager(super.getSupportFragmentManager(), fragments);
        //
        ViewPager pager = super.findViewById(R.id.container);
        pager.setAdapter(this.mPagerAdapter);
    }



}