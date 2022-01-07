package com.example.seorganize;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.seorganize.activity.CadastroActivity;
import com.example.seorganize.activity.LoginActivity;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

public class MainActivity extends IntroActivity {

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        setButtonBackVisible(false);
        setButtonNextVisible(false);

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.holo_blue_light)
                .fragment(R.layout.boas_vindas)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.holo_blue_dark)
                .fragment(R.layout.slide_1)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.holo_blue_light)
                .fragment(R.layout.slide_2)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.holo_blue_dark)
                .fragment(R.layout.slide_3)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.holo_blue_light)
                .fragment(R.layout.slide_4)
                //.canGoForward(false) // para nao ir adiante o ultimo slide
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.slide_5)
                .canGoForward(false)
                .canGoBackward(false)
                .build()
        );

    }

    public void btEntrar(View view){
        startActivity(new Intent(this, LoginActivity.class));

    }
    public void btCadastrar(View view){
        startActivity(new Intent(this, CadastroActivity.class));

    }
}