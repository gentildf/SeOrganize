package com.example.seorganize.helper;

import java.text.SimpleDateFormat;

public class DateCustom {

    public static String dataAtual(){
        long date = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataString = simpleDateFormat.format(date);
        return dataString;
    }
    public static String mesAno(String data){
        String retornoData[] = data.split("/");
        String mes = retornoData[1];
        String ano = retornoData[2];
        String meseAno = mes + ano;

        return meseAno;
    }

}
