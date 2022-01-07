package com.example.seorganize;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

public class MainActivity extends IntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        setButtonBackVisible(false);
        setButtonNextVisible(false);

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.holo_green_dark)
                .fragment(R.layout.slide_1)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.holo_red_light)
                .fragment(R.layout.slide_2)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.holo_orange_dark)
                .fragment(R.layout.slide_3)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.holo_blue_dark)
                .fragment(R.layout.slide_4)
                //.canGoForward(false) // para nao ir adiante o ultimo slide
                .build()
        );


    }
}