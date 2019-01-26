package com.dating.app.idateu.Homepage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.dating.app.idateu.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

    int matchPicIndex = 0;
    ImageView current_match;
    Button like_button, dislike_button;


    private ArrayList<Integer> matchImages = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        startImageLoading();
        current_match=(ImageView)findViewById(R.id.match_pic);
        ImageView image = new ImageView(this);
        Picasso.get().load(matchImages.get(0)).into(current_match); //loads initial image

        like_button=(Button) findViewById(R.id.like_btn);
        dislike_button = findViewById(R.id.dislike_btn);

        like_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
                {
                    startImageSwitching();
                }
            });

        current_match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
                {
                startPop_up();
                }
                });
        }

        private void imageChanger()
            {
            if (matchImages.size() != matchPicIndex +1)
                {
                matchPicIndex++;
                Picasso.get().load(matchImages.get(matchPicIndex)).into(current_match);
                }
            else
                {
                matchPicIndex = 0;
                Picasso.get().load(matchImages.get(matchPicIndex)).into(current_match);
                }
            }

    private void initialImages()
            {
            matchImages.add(R.drawable.img_001);
            matchImages.add(R.drawable.img_002);
            matchImages.add(R.drawable.img_003);
            }

    private void startPop_up()
        {
        Intent start_popup = new Intent(HomePage.this, Pop_up.class);
        start_popup.putExtra("picture_ID", matchImages.get(matchPicIndex));
        startActivity(start_popup);
        }

    public void startImageLoading() {
        new Thread(new Image_loader_thread()).start();
    }

    public void startImageSwitching() {
        new Thread(new image_switch_thread()).start();
    }

    class Image_loader_thread implements Runnable {
        @Override
        public void run() {
                initialImages();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    class image_switch_thread implements Runnable {
        @Override
        public void run() {
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageChanger();
                        }
                    });
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }


}





