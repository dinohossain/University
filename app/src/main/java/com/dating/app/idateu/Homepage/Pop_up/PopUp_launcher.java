package com.dating.app.idateu.Homepage.Pop_up;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;


import com.dating.app.idateu.R;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;


public class PopUp_launcher extends AppCompatActivity {

    private static final String TAG = "Pop_launcher";

    Bundle extras;
    public VerticalPager mVerticalPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_for_pop_up);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        extras= getIntent().getExtras();
        getWindow().setLayout((int)(width*.8),(int)(height*.6));
        findViews();

    }


    private void findViews() {
        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this, Pop_up.class.getName(),extras));
        fragments.add(Fragment.instantiate(this, Pop_up_bio.class.getName(),extras));
        mVerticalPager = (VerticalPager) findViewById(R.id.verticalPagerXML);
        initViews();
    }

    private void initViews() {
        snapPageWhenLayoutIsReady(mVerticalPager, 0);
    }


    private void snapPageWhenLayoutIsReady(final View pageView, final int page) {
        /*
         * VerticalPager is not fully initialized at the moment, so we want to snap to the central page only when it
         * layout and measure all its pages.
         */
        pageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {
                mVerticalPager.snapToPage(page, VerticalPager.PAGE_SNAP_DURATION_INSTANT);

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN)
                    // recommended removeOnGlobalLayoutListener method is available since API 16 only
                    pageView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                else
                    removeGlobalOnLayoutListenerForJellyBean(pageView);
            }

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            private void removeGlobalOnLayoutListenerForJellyBean(final View pageView) {
                pageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    @Override
    public void finish()
    {
    super.finish();
    overridePendingTransition(R.anim.fade_out,R.anim.fade_out);
    }

}

