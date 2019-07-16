package com.dating.app.idateu.Homepage.Pop_up;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dating.app.idateu.matchesDetail;
import com.dating.app.idateu.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Pop_up extends Fragment {
    private static final String TAG = "Pop_up";

    ImageView imgPopUp;
    TextView mNameTxt,dobTxt,orientationTxt;
    matchesDetail sr;
    String mName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.matched_pop_up, container, false);
        imgPopUp = view.findViewById(R.id.matched_pop_up_img);
        mNameTxt = view.findViewById(R.id.mName_pop_txt);
        dobTxt = view.findViewById(R.id.dob_pop_txt);
        orientationTxt = view.findViewById(R.id.orientation_pop_txt);
        Intent intent = getActivity().getIntent();

        sr = (matchesDetail) intent.getSerializableExtra("Object");
        if(sr!=null)
            {
            imgPopUp.setImageBitmap(loadImageFromStorage());
            mNameTxt.setText(sr.getName());
            String orientation = determineOrientation(sr.getOrientation());
            dobTxt.setText(String.valueOf("Date of Birth: "+sr.getDOB()));
            orientationTxt.setText("Orientation: "+orientation);
            }
        return view;
    }

    private String determineOrientation(String orientation)
        {
        switch (orientation)
            {
            case ("B"):
                return "Bisexual";
            case ("G"):
                return "Homosexual";
            case ("S"):
                return "Heterosexual";
            case ("L"):
                return "Homosexual";
            }
        return null;
        }

    public static Bitmap loadImageFromStorage()
    {

        try {
            File f=new File("/data/data/com.dating.app.idateu/app_imageDir", "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            return b;
            }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    return null;}
}
