package com.dating.app.idateu.Homepage;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dating.app.idateu.R;

public class Pop_up_bio extends Fragment {

    private final String TAG = "PopUpBio";

    TextView bio;
    int index;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bio_pop_up, container, false);
        bio = view.findViewById(R.id.bio_txt);
        switch (index)
        {
            case 1:
                bio.setText(R.string.bio_1);
            case 2:
                bio.setText(R.string.bio_2);
            case 3:
                bio.setText(R.string.bio_3);
        }
        return view;
    }

    public void input_bio(int bio_index)
    {
        this.index = bio_index;
    }

}