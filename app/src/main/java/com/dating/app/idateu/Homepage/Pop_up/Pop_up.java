package com.dating.app.idateu.Homepage.Pop_up;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dating.app.idateu.Homepage.DBConnector.StoreResult;
import com.dating.app.idateu.R;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pop_up extends Fragment {
    private static final String TAG = "Pop_up";

    ImageView imgPopUp;
    TextView username;
    StoreResult sr;
    Bitmap bmp;
    String mName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.matched_pop_up, container, false);
        imgPopUp = view.findViewById(R.id.matched_pop_up_img);
        username = view.findViewById(R.id.username_pop_txt);
        Intent intent = getActivity().getIntent();

        sr = (StoreResult) intent.getSerializableExtra("Object");
        if(sr!=null) {
            loadImage();
            imgPopUp.setImageBitmap(bmp);
            loadName();
            username.setText(mName);
        }
        return view;
    }

    private void loadImage()
        {
            try
            {
                InputStream in = null;
                in = new ByteArrayInputStream(sr.getProfilePic().getBytes(StandardCharsets.UTF_8));
                BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
                bmp = BitmapFactory.decodeStream(bufferedInputStream);
            }
            catch (Exception e)
            {
            }
        }

    private void loadName()
        {
        mName = sr.getName();
        }

}
