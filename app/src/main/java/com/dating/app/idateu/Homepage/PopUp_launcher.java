package com.dating.app.idateu.Homepage;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import com.dating.app.idateu.R;


public class PopUp_launcher extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private static final String TAG = "Pop_launcher";

    private GestureDetector gestureDetector;

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    FragmentTransaction transaction;
    Bundle extras;
    Integer selected_img;
    int indexForBio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_for_pop_up);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));
        gestureDetector = new GestureDetector(this);
        popUpImageInit();
    }

    private void popUpImageInit()
    {
        extras = getIntent().getExtras();
        selected_img = extras.getInt("picture_ID");
        Pop_up fragment = new Pop_up();
        fragment.input_image(selected_img);
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment, "Pop_up");
        transaction.commit();
    }

    private void popUpImage()
        {
            extras = getIntent().getExtras();
            selected_img = extras.getInt("picture_ID");
            Pop_up fragment = new Pop_up();
            fragment.input_image(selected_img);
            transaction = getFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.animator.slide_out_down, R.animator.slide_out_up);
            transaction.replace(R.id.container, fragment, "Pop_up");
            transaction.commit();
        }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)  {

        switch (getSlope(e1.getX(), e1.getY(), e2.getX(), e2.getY())) {
            case 1:
                onUpSwipe();
                return true;
            case 2:
                onDownSwipe();
                return true;
        }
        return false;
    }

    private int getSlope(float x1, float y1, float x2, float y2) {
        Double angle = Math.toDegrees(Math.atan2(y1 - y2, x2 - x1));
        if (angle > 45 && angle <= 135)
            // top
            return 1;
        if (angle < -45 && angle>= -135)
            // down
            return 2;
        return 0;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public void onDownSwipe()
        {
            popUpImage();

        }

    public void onUpSwipe()
    {
    popUpImage();
    Pop_up_bio fragment = new Pop_up_bio();
    indexForBio = extras.getInt("index");
    fragment.input_bio(indexForBio);
    transaction = getFragmentManager().beginTransaction();
    transaction.setCustomAnimations(R.animator.slide_in_up, R.animator.slide_out_down);
    transaction.replace(R.id.container, fragment, "Pop_up");
    transaction.commit();
    }
}