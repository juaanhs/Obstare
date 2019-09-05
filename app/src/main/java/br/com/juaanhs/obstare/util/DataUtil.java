package br.com.juaanhs.obstare.util;

import android.widget.DatePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataUtil {

    public DataUtil() {

    }

    public Date convertStringToDate(String string) {
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = format.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public String convertDateToString(Date date) {
        String dataFormatada = "";
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(dataFormatada);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

    public String IntsToString(int year, int month, int dayOfMonth) {
        Date date = null;
        String dataFormatada = dayOfMonth + "/" + (month + 1) + "/" + year;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(dataFormatada);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

    public String calculaDPP(Date date) {
        Calendar d = Calendar.getInstance();
        d.setTime(date);
        d.add(Calendar.DAY_OF_MONTH, 280);
        Date dpp = d.getTime();
        return convertDateToString(dpp);
    }

    public String calculaDPC(Date dataDUM) {
        Calendar d = Calendar.getInstance();
        d.setTime(dataDUM);
        d.add(Calendar.DAY_OF_MONTH, 14);
        Date dpc = d.getTime();
        return convertDateToString(dpc);
    }

    public String calculaIG(Date dum) {
        Date data = new Date(System.currentTimeMillis());
        int numDias = (int) ((data.getTime() - dum.getTime()) / 1000 / 60 / 60 / 24);
        int numSemana = 0;
        while (numDias >= 7){
            numDias = numDias - 7;
            numSemana++;
        }
        return formataIG(numDias, numSemana);
    }

    private String formataIG(int numDias, int numSemana) {
        String dia = " dia";
        String semana = " semana";

        if(numDias < 0 || numDias == 0 && numSemana == 0)
            return "";

        if(numDias > 1){
            dia = " dias";
        } else if(numDias == 0 ){
            if(numSemana > 1){
                semana = " semanas";
            }
            return numSemana + semana;
        }
        if(numSemana > 1){
            semana = " semanas";
        } else if(numSemana == 0){
            return  numDias + dia;
        }
        return numSemana + semana + " e " + numDias + dia;
    }

    public Date calculaDPPporIG (int semanas, int dias, Date dataUSG){
        int totalDias = (semanas * 7) + dias;
        int igRestante = 280 - totalDias;

        Calendar d = Calendar.getInstance();
        d.setTime(dataUSG);
        d.add(Calendar.DAY_OF_MONTH, igRestante);
        dataUSG = d.getTime();
        return dataUSG;
    }

    public String calculaDUMporDPP(Date dataDPP) {
        Calendar d = Calendar.getInstance();
        d.setTime(dataDPP);

        d.add(Calendar.DAY_OF_MONTH, -280);
        Date dpp = d.getTime();
        return convertDateToString(dpp);
    }
}