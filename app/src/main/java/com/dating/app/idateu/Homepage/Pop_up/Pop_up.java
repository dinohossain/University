package com.dating.app.idateu.Homepage.Pop_up;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dating.app.idateu.R;

public class Pop_up extends Fragment {
    private static final String TAG = "Pop_up";

    ImageView imgPopUp;
    Integer currentImage;
    Bundle extras;

    public void input_image(Integer cI) {
        this.currentImage = cI;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.matched_pop_up, container, false);
        extras = getActivity().getIntent().getExtras();
        imgPopUp = view.findViewById(R.id.matched_pop_up_img);
        imgPopUp.setImageResource(extras.getInt("picture_ID"));
        return view;
    }
}
