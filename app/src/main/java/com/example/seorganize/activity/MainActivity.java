package com.example.seorganize.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.seorganize.R;
import com.example.seorganize.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

public class MainActivity extends IntroActivity {

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        verificarUsuarioLogado();



        setButtonBackVisible(false);
        setButtonNextVisible(false);

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.holo_blue_light)
                .fragment(R.layout.boas_vindas)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.holo_blue_dark)
                .fragment(R.layout.slide_1)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.holo_blue_light)
                .fragment(R.layout.slide_2)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.holo_blue_dark)
                .fragment(R.layout.slide_3)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.holo_blue_light)
                .fragment(R.layout.slide_4)
                //.canGoForward(false) // para nao ir adiante o ultimo slide
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.slide_5)
                .canGoForward(false)
                .canGoBackward(false)
                .build()
        );

    }

/*    @Override
    protected void onStart() {
        super.onStart();
        verificarUsuarioLogado();
    }*/

    public void btEntrar(View view){
        startActivity(new Intent(this, LoginActivity.class));

    }
    public void btCadastrar(View view){
        startActivity(new Intent(this, CadastroActivity.class));

    }
    public void verificarUsuarioLogado(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        //autenticacao.signOut();
        if(autenticacao.getCurrentUser() != null ){
            abrirTelaPrincipal();
        }
    }
    public void abrirTelaPrincipal(){
        startActivity(new Intent(this, PrincipalActivity.class));
    }

}