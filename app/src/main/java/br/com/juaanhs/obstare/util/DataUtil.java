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

    private String convertDateToString(Date date) {
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

    public String calculaDPC(Date date) {
        Calendar d = Calendar.getInstance();
        d.setTime(date);
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

        if(numDias < 0 || numDias ==0 && numSemana == 0)
        return "";
        return numSemana + " Semana(s) e " + numDias + " dia(s)";
    }
}