package com.example.silviq.bmi;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EasySplashScreen easySplashScreen = new EasySplashScreen(SplashScreen.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(5000)
                .withBackgroundColor(getResources().getColor(R.color.colorPrimary ))
                .withLogo(R.drawable.bmi)
                .withFooterText("Copyright Silvia");

        easySplashScreen.getFooterTextView().setTextColor(Color.BLACK);

        View view = easySplashScreen.create();
        setContentView(view);
    }
}
