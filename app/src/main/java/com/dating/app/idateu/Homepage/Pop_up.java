package com.dating.app.idateu.Homepage;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dating.app.idateu.R;

public class Pop_up extends Fragment implements GestureDetector.OnGestureListener{

    private static final String TAG = "Pop_up";
    private static final int SWIPE_THRESHOLD = 120;
    private static final int SWIPE_VELOCITY_THRESHOLD = 200;

    ImageView imgPopUp;
    private GestureDetector gestureDetector;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.matched_pop_up, container, false);

        imgPopUp = view.findViewById(R.id.matched_pop_up_img);
        imgPopUp.setImageResource(R.drawable.img_001);

        GestureListener listener = new GestureListener(this);
        gestureDetector = new GestureDetector(this);
        onFling(gestureDetector);
        return view;
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
        public boolean onFling(MotionEvent downEvent, MotionEvent upEvent, float velocityX, float velocityY) {
            boolean result=false;
            float diffY = upEvent.getY() - downEvent.getY();
            float diffX=upEvent.getX() - downEvent.getX();

            if (Math.abs(diffY) > Math.abs(diffX))
            {
                if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY)> SWIPE_VELOCITY_THRESHOLD)
                {
                    if (diffY > 0) onSwipeBottom();
                    else onSwipeTop();
                }
            }

            return result;
        }

        private void onSwipeTop() {
        }

        private void onSwipeBottom() {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.container, new Pop_up_bio(), "PopUpBio");
            transaction.commit();
        }



    public static class Pop_up_bio extends Fragment {

        private static final String TAG = "PopUpBio";

        TextView bio;

        @Nullable
        @Override
        public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.bio_pop_up, container, false);
            bio = view.findViewById(R.id.bio_txt);
            bio.setText("Is it working?");
            return view;
        }

    }
}