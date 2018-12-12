package com.dating.app.idateu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.tasin.idateu.R;

public class MainActivity extends AppCompatActivity
    {

    Button register_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_view);

        register_btn = (Button)findViewById(R.id.register_button);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
                {
                startActivity(new Intent(MainActivity.this, ThirdSignUpPage.class));
                }
        });
        }

    }
