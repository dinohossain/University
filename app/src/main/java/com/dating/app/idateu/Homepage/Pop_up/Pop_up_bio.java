package com.dating.app.idateu.Homepage.Pop_up;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dating.app.idateu.R;

public class Pop_up_bio extends Fragment {

    int bioIndex;
    TextView bio;
    Bundle extras;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bio_pop_up, container, false);
        bio = view.findViewById(R.id.bio_txt);
        extras = getActivity().getIntent().getExtras();

        switch (extras.getInt("index"))
        {
            case 1:
                bio.setText(R.string.bio_1);
                break;
            case 2:
                bio.setText(R.string.bio_2);
                break;
            case 3:
                bio.setText(R.string.bio_3);
                break;
        }

    return view;}
}

