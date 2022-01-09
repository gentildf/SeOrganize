package com.example.seorganize.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.seorganize.databinding.ActivityDespesasBinding;
import com.example.seorganize.helper.DateCustom;
import com.example.seorganize.model.Movimentacao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DespesasActivity extends AppCompatActivity {

    private ActivityDespesasBinding binding;
    private Movimentacao movimentacao;
    private String textoValor, textoCategoria, data, textoDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDespesasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.editData.setText(DateCustom.dataAtual());




    }
    public void salvarDespesa(View view){
        if(validarCamposDespesa()){
            movimentacao = new Movimentacao();
            movimentacao.setCategoria(textoCategoria);
            movimentacao.setData(data);
            movimentacao.setDescricao(textoDescricao);
            movimentacao.setValor(Double.parseDouble(textoValor));
            movimentacao.setTipo("d");
            movimentacao.salvar(data);
            voltarPrincipal();
        }

    }
    public void voltarPrincipal(){
        startActivity(new Intent(this, PrincipalActivity.class));
    }
    public Boolean validarCamposDespesa(){
        textoValor = binding.inputDespesa.getText().toString();
        textoCategoria = binding.editCategoria.getText().toString();
        textoDescricao = binding.editDescricao.getText().toString();
        data = binding.editData.getText().toString();

        if(!textoValor.isEmpty()){
            if(!data.isEmpty()){
                if(!textoCategoria.isEmpty()){
                    if(!textoDescricao.isEmpty()){
                        return true;
                    } else{
                        Toast.makeText(
                                DespesasActivity.this,
                                "Preencha a descricao!",
                                Toast.LENGTH_SHORT
                        ).show();
                        return false;
                    }
                } else{
                    Toast.makeText(
                            DespesasActivity.this,
                            "Preencha a categoria!",
                            Toast.LENGTH_SHORT
                    ).show();
                    return false;
                }
            } else{
                Toast.makeText(
                        DespesasActivity.this,
                        "Preencha a data!",
                        Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        } else{
            Toast.makeText(
                    DespesasActivity.this,
                    "Preencha o valor!",
                    Toast.LENGTH_SHORT
            ).show();
            return false;
        }
    }

}