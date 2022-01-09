package com.example.seorganize.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.seorganize.config.ConfiguracaoFirebase;
import com.example.seorganize.databinding.ActivityDespesasBinding;
import com.example.seorganize.helper.Base64Custom;
import com.example.seorganize.helper.DateCustom;
import com.example.seorganize.model.Movimentacao;
import com.example.seorganize.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DespesasActivity extends AppCompatActivity {

    private ActivityDespesasBinding binding;
    private Movimentacao movimentacao;
    private String textoValor, textoCategoria, data, textoDescricao;
    private DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private Double despesaTotal, despesaGerada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDespesasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.editData.setText(DateCustom.dataAtual());

        recuperarDespesaTotal(); // Recuperar assim que carregar a tela.

    }
    public void salvarDespesa(View view){
        if(validarCamposDespesa()){
            movimentacao = new Movimentacao();
            movimentacao.setCategoria(textoCategoria);
            movimentacao.setData(data);
            movimentacao.setDescricao(textoDescricao);
            despesaGerada = Double.parseDouble(textoValor);
            Double despesaAtualizada = despesaTotal + despesaGerada; // Atualizar valor de despesa
            movimentacao.setValor(despesaGerada);
            movimentacao.setTipo("d");
            movimentacao.salvar(data); // salvar dados
            atualizarDespesa(despesaAtualizada);
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
    public void recuperarDespesaTotal(){
        String emailUsuario = autenticacao.getCurrentUser().getEmail(); // Recupera o email
        String idUsuario = Base64Custom.codificarBase64(emailUsuario); // Codifica o email
        DatabaseReference usuarioRef = firebaseRef.child("usuarios").child(idUsuario);

        usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usuario usuario = snapshot.getValue(Usuario.class);
                despesaTotal = usuario.getDespesaTotal();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void atualizarDespesa(Double despesa){
        String emailUsuario = autenticacao.getCurrentUser().getEmail(); // Recupera o email
        String idUsuario = Base64Custom.codificarBase64(emailUsuario); // Codifica o email
        DatabaseReference usuarioRef = firebaseRef.child("usuarios").child(idUsuario);

        usuarioRef.child("despesaTotal").setValue(despesa);
    }
}