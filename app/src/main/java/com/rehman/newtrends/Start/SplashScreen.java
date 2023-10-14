package com.rehman.newtrends.Start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.rehman.newtrends.MainActivity;
import com.rehman.newtrends.R;

public class SplashScreen extends AppCompatActivity {

    TextView text_splash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        text_splash = findViewById(R.id.text_splash);

        Animation myAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        text_splash.startAnimation(myAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();

            }
        },4000);

    }
}