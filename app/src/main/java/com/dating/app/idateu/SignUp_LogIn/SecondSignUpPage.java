package com.dating.app.idateu.SignUp_LogIn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.dating.app.idateu.R;

public class SecondSignUpPage extends AppCompatActivity {

    Button continue_btn;
    CheckBox over_18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_sign_up_page);

        over_18 = findViewById(R.id.tick_box);
        over_18.setVisibility(View.VISIBLE);
        continue_btn = (Button) findViewById(R.id.Continue_button);
        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecondSignUpPage.this, ThirdSignUpPage.class));
            }
        });
    }
}
