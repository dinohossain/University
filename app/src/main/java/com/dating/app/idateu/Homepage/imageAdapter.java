package com.dating.app.idateu.Homepage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dating.app.idateu.R;

public class imageAdapter extends BaseAdapter
    {
        private Integer[] matchImages =
                {
                        R.drawable.dummy_pic,
                        R.drawable.water_girl
                };

        private Context context;
        private LayoutInflater thisInflater;

        public imageAdapter(Context con)
        {
        this.context = con;
        this.thisInflater = LayoutInflater.from(con);
        }

        @Override
        public int getCount() {
            return matchImages.length;
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
        public View getView(int position, View convertView, ViewGroup parent)
        {
            if (convertView == null)
            {
                convertView = thisInflater.inflate(R.layout.activity_home_page, parent, false);
                ImageView thumbnailImage = (ImageView) convertView.findViewById(R.id.match_pic);
                if (position == matchImages.length) position--;
                thumbnailImage.setImageResource(matchImages[position]);
            }
            return convertView;
        }
    }
