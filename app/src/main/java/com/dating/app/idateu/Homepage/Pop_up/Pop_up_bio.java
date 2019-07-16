package com.dating.app.idateu.Homepage.Pop_up;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dating.app.idateu.matchesDetail;
import com.dating.app.idateu.R;

public class Pop_up_bio extends Fragment {

    TextView bio;
    matchesDetail sr;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bio_pop_up, container, false);
        bio = view.findViewById(R.id.bio_txt);
        Intent intent = getActivity().getIntent();
        sr = (matchesDetail) intent.getSerializableExtra("Object");
        bio.setText(sr.getBio());

    return view;}
}

