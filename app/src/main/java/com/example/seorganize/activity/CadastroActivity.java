package com.example.seorganize.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.seorganize.R;
import com.example.seorganize.config.ConfiguracaoFirebase;
import com.example.seorganize.helper.Base64Custom;
import com.example.seorganize.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroActivity extends AppCompatActivity {

    private EditText textNome, textEmail, textSenha;
    private Button botaoCadastrar;
    private FirebaseAuth autenticacao;
    private Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().setTitle("Cadastro");

        botaoCadastrar = findViewById(R.id.buttonCadastrar);

        // Instanciar itens do layout
        textNome = findViewById(R.id.textNome);
        textEmail = findViewById(R.id.loginEmail);
        textSenha = findViewById(R.id.loginPassword);



        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Recuperar dados digitados
                String textoNome = textNome.getText().toString();
                String textoEmail = textEmail.getText().toString();
                String textoSenha = textSenha.getText().toString();

                if (!textoNome.isEmpty()){ // Verifica se o nome esta vazio. ! usado para pegar o inverso.
                    if(!textoEmail.isEmpty()){
                        if(!textoSenha.isEmpty()){

                            usuario = new Usuario();
                            usuario.setNome(textoNome);
                            usuario.setEmail(textoEmail);
                            usuario.setSenha(textoSenha);

                            cadastrarUsuario();

                        }else{
                            Toast.makeText(CadastroActivity.this,
                                    "Preencha a senha!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else{
                        Toast.makeText(CadastroActivity.this,
                                "Preencha o email!",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CadastroActivity.this,
                            "Preencha o nome!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    public void cadastrarUsuario(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                String idUsuario = Base64Custom.codificarBase64(usuario.getEmail());
                                usuario.setIdUsuario(idUsuario);
                                usuario.salvar();

                                finish();
                            } else {
                                String excecao = "";

                                try {
                                    throw task.getException();
                                } catch (FirebaseAuthWeakPasswordException e){
                                    excecao = "Digite uma senha mais forte!";
                                } catch (FirebaseAuthInvalidCredentialsException e){
                                    excecao = "Por favor, digite um email valido.";
                                } catch (FirebaseAuthUserCollisionException e){
                                    excecao = "Email ja cadastrado em outra conta";
                                } catch (Exception e){
                                    excecao = "Erro ao cadastrar usuario: "+ e.getMessage();
                                    e.printStackTrace();
                                }
                                Toast.makeText(CadastroActivity.this,
                                        excecao,
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

    }

}