package com.dating.app.idateu.Homepage;

import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dating.app.idateu.R;

public class Pop_up extends Fragment {

    private static final String TAG = "Pop_up";

    ImageView imgPopUp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.matched_pop_up, container, false);

        imgPopUp = view.findViewById(R.id.matched_pop_up_img);
        imgPopUp.setImageResource(R.drawable.img_001);

        imgPopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, new Pop_up_bio(), "PopUpBio");
                transaction.commit();
            }
        });

        return view;
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