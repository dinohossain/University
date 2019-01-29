package com.dating.app.idateu.Homepage;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dating.app.idateu.R;

public class Pop_up extends Fragment{

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


        return view;
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