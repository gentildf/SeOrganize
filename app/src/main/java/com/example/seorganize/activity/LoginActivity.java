package com.example.seorganize.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.seorganize.R;
import com.example.seorganize.config.ConfiguracaoFirebase;
import com.example.seorganize.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao;
    private Usuario usuario;
    private EditText textEmail, textSenha;
    private Button botaoAcessar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textEmail = findViewById(R.id.loginEmail);
        textSenha = findViewById(R.id.loginPassword);
        botaoAcessar = findViewById(R.id.buttonAcessar);

        botaoAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Recuperar dados digitados no momento do clique
                String loginEmail = textEmail.getText().toString();
                String loginSenha = textSenha.getText().toString();

                if (!loginEmail.isEmpty()){
                    if(!loginSenha.isEmpty()){
                        // Utilizar objeto usuario
                        usuario = new Usuario();
                        usuario.setEmail(loginEmail);
                        usuario.setSenha(loginSenha);

                        validarAcesso();

                    }else {
                        Toast.makeText(LoginActivity.this,
                                "Preencha a senha!",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this,
                            "Preencha o Email!",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    public void validarAcesso(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                Toast.makeText(
                                        LoginActivity.this,
                                        "Sucesso ao fazer login",
                                        Toast.LENGTH_SHORT
                                ).show();

                            }else{
                                String excecao ="";
                                try {
                                    throw task.getException();
                                }catch ( FirebaseAuthInvalidCredentialsException e){
                                    excecao = "A senha esta incorreta";
                                }catch (FirebaseAuthInvalidUserException e){
                                    excecao = "Email nao cadastrado";
                                }catch (Exception e){
                                    excecao = " Nao foi possivel fazer login: " + e.getMessage();
                                    e.printStackTrace();
                                }
                                Toast.makeText(
                                        LoginActivity.this,
                                        excecao,
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
                        }
                    });
    }

}