package com.dating.app.idateu.Homepage.Pop_up;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dating.app.idateu.R;

public class Pop_up extends Fragment {
    private static final String TAG = "Pop_up";

    ImageView imgPopUp;
    Bundle extras;
    TextView username;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.matched_pop_up, container, false);
        extras = getActivity().getIntent().getExtras();
        imgPopUp = view.findViewById(R.id.matched_pop_up_img);
        username = view.findViewById(R.id.username_pop_txt);
        imgPopUp.setImageResource(extras.getInt("picture_ID"));
        getUserName();
        return view;
    }


    public void getUserName()
        {
            switch (extras.getInt("index"))
            {
                case 1:
                    username.setText(R.string.img1Name);
                    break;
                case 2:
                    username.setText(R.string.img2Name);
                    break;
                case 3:
                    username.setText(R.string.img3Name);
                    break;
            }
        }



}
