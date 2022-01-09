package com.example.seorganize.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.seorganize.R;
import com.example.seorganize.databinding.ActivityReceitasBinding;

public class ReceitasActivity extends AppCompatActivity {

    private ActivityReceitasBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receitas);


    }
    public void voltarPrincipal(View view){
        startActivity(new Intent(this, PrincipalActivity.class));

    }


}