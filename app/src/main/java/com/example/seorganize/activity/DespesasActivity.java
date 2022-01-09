package com.example.seorganize.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.seorganize.R;
import com.example.seorganize.databinding.ActivityDespesasBinding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DespesasActivity extends AppCompatActivity {

    private ActivityDespesasBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas);

        binding.editData.setText("");

    }
    public void voltarPrincipal(View view){
        startActivity(new Intent(this, PrincipalActivity.class));
    }

}