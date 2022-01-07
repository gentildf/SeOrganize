package com.example.seorganize.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.seorganize.R;

public class CadastroActivity extends AppCompatActivity {

    private EditText textNome, textEmail, textSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        // Instanciar itens do layout
        textNome = findViewById(R.id.textNome);
        textEmail = findViewById(R.id.textEmail);
        textSenha = findViewById(R.id.textPassword);

    }
    public void botaoCadastrar(){

    }

}