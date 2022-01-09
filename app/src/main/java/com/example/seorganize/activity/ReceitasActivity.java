package com.example.seorganize.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.seorganize.config.ConfiguracaoFirebase;
import com.example.seorganize.databinding.ActivityReceitasBinding;
import com.example.seorganize.helper.Base64Custom;
import com.example.seorganize.helper.DateCustom;
import com.example.seorganize.model.Movimentacao;
import com.example.seorganize.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class ReceitasActivity extends AppCompatActivity {

    private ActivityReceitasBinding binding;
    private Movimentacao movimentacao;
    private String textoValor, textoCategoria, data, textoDescricao;
    private DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private Double receitaGerada, receitaTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReceitasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.editData.setText(DateCustom.dataAtual());

        recuperarReceitaTotal(); // Recuperar assim que carregar a tela.

    }
    public void salvarReceita(View view){
        if (validarCamposReceita()){
            movimentacao = new Movimentacao();
            movimentacao.setCategoria(textoCategoria);
            movimentacao.setData(data);
            movimentacao.setDescricao(textoDescricao);
            receitaGerada = Double.parseDouble(textoValor);
            Double receitaAtualizada = receitaTotal + receitaGerada;
            movimentacao.setValor(receitaGerada);
            movimentacao.setTipo("r");
            movimentacao.salvar(data);
            atualizarReceita(receitaAtualizada);
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
    public void recuperarReceitaTotal(){
        String emailUsuario = autenticacao.getCurrentUser().getEmail(); // Recupera o email
        String idUsuario = Base64Custom.codificarBase64(emailUsuario); // Codifica o email
        DatabaseReference usuarioRef = firebaseRef.child("usuarios").child(idUsuario);

        usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usuario usuario = snapshot.getValue(Usuario.class);
                receitaTotal = usuario.getReceitaTotal();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void atualizarReceita(Double receita){
        String emailUsuario = autenticacao.getCurrentUser().getEmail(); // Recupera o email
        String idUsuario = Base64Custom.codificarBase64(emailUsuario); // Codifica o email
        DatabaseReference usuarioRef = firebaseRef.child("usuarios").child(idUsuario);

        usuarioRef.child("receitaTotal").setValue(receita);
    }
}