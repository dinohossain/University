package com.dating.app.idateu.Homepage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.dating.app.idateu.R;

public class imageAdapter extends BaseAdapter {

    private Context mContext;

    private Integer[] matchImage =
            {
            R.drawable.dummy_pic,
            R.drawable.water_girl
            };

    public imageAdapter(Context context){
        mContext=context;
    }

    @Override
    public int getCount()
        {
        return matchImage.length;
        }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int index, View view, ViewGroup viewGroup) {
        ImageView i = new ImageView(mContext);

        i.setImageResource(matchImage[index]);
        i.setLayoutParams(new Gallery.LayoutParams(200, 200));
        i.setScaleType(ImageView.ScaleType.FIT_XY);
        return i;
    }
}