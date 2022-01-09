package com.example.seorganize.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.seorganize.R;
import com.example.seorganize.config.ConfiguracaoFirebase;
import com.example.seorganize.databinding.ActivityPrincipalBinding;
import com.example.seorganize.databinding.ActivityReceitasBinding;
import com.example.seorganize.helper.DateCustom;
import com.example.seorganize.model.Movimentacao;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class ReceitasActivity extends AppCompatActivity {

    private ActivityReceitasBinding binding;
    private Movimentacao movimentacao;
    private String textoValor, textoCategoria, data, textoDescricao;
    private DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReceitasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.editData.setText(DateCustom.dataAtual());

    }

    public void salvarReceita(View view){
        if (validarCamposReceita()){
            movimentacao = new Movimentacao();
            movimentacao.setCategoria(textoCategoria);
            movimentacao.setData(data);
            movimentacao.setDescricao(textoDescricao);
            movimentacao.setValor(Double.parseDouble(textoValor));
            movimentacao.setTipo("r");
            movimentacao.salvar(data);
            voltarPrincipal();
        }
    }
    public void voltarPrincipal(){
        startActivity(new Intent(this, PrincipalActivity.class));
    }

    public Boolean validarCamposReceita(){
        textoValor = binding.inputReceita.getText().toString();
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
                                ReceitasActivity.this,
                                "Preencha a descricao!",
                                Toast.LENGTH_SHORT
                        ).show();
                        return false;
                    }
                } else{
                    Toast.makeText(
                            ReceitasActivity.this,
                            "Preencha a categoria!",
                            Toast.LENGTH_SHORT
                    ).show();
                    return false;
                }
            } else{
                Toast.makeText(
                        ReceitasActivity.this,
                        "Preencha a data!",
                        Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        } else{
            Toast.makeText(
                    ReceitasActivity.this,
                    "Preencha o valor!",
                    Toast.LENGTH_SHORT
            ).show();
            return false;
        }
    }

}