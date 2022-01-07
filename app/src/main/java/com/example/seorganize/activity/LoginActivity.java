package com.example.seorganize.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.seorganize.R;
import com.example.seorganize.config.ConfiguracaoFirebase;
import com.example.seorganize.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

}