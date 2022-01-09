package com.example.seorganize.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.seorganize.R;
import com.example.seorganize.databinding.ActivityPrincipalBinding;
import com.example.seorganize.databinding.ActivityReceitasBinding;
import com.example.seorganize.helper.DateCustom;
import com.example.seorganize.model.Movimentacao;

public class ReceitasActivity extends AppCompatActivity {

    private ActivityReceitasBinding binding;
    private Movimentacao movimentacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReceitasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.editData.setText(DateCustom.dataAtual());

    }

    public void salvarReceita(View view){
        movimentacao = new Movimentacao();
        String data = binding.editData.getText().toString();
        movimentacao.setCategoria(binding.editCategoria.getText().toString());
        movimentacao.setData(data);
        movimentacao.setDescricao(binding.editDescricao.getText().toString());
        movimentacao.setValor(Double.parseDouble(binding.inputReceita.getText().toString()));
        movimentacao.setTipo("r");
        movimentacao.salvar(data);
        voltarPrincipal();

    }
    public void voltarPrincipal(){
        startActivity(new Intent(this, PrincipalActivity.class));

    }


}