package com.example.seorganize.activity;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import com.example.seorganize.R;
import com.example.seorganize.databinding.ActivityPrincipalBinding;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

public class PrincipalActivity extends AppCompatActivity {

    private ActivityPrincipalBinding binding;
    private MaterialCalendarView materialCalendarView;
    private TextView campoSaldo, campoSaudacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPrincipalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        campoSaudacao = findViewById(R.id.textSaudacao);
        campoSaldo = findViewById(R.id.textSaldo);
        materialCalendarView = findViewById(R.id.calendarView);
        configuraCalendar();


    }

    public void configuraCalendar(){
        CharSequence meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        materialCalendarView.setTitleMonths(meses);
        materialCalendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {

            }
        });
    }

    public void adicionarReceita(View v){
        startActivity(new Intent(this, ReceitasActivity.class));
    }
    public void adicionarDespesa(View v){
        startActivity(new Intent(this, DespesasActivity.class));
    }


}