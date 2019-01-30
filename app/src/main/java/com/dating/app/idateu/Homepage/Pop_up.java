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

public class Pop_up extends Fragment {

    private static final String TAG = "Pop_up";
    private static final int SWIPE_THRESHOLD = 120;
    private static final int SWIPE_VELOCITY_THRESHOLD = 200;

    ImageView imgPopUp;
    private GestureDetector gestureDetector;

    Integer currentImage;

    public void input_image(Integer cI) {
        this.currentImage = cI;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.matched_pop_up, container, false);

        imgPopUp = view.findViewById(R.id.matched_pop_up_img);
        imgPopUp.setImageResource(currentImage);
        return view;
    }
}