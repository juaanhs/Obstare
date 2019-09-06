package br.com.juaanhs.obstare.util;

import java.text.DecimalFormat;

public class ImcUtil {
    private DecimalFormat df;

    public ImcUtil(){
        df = new DecimalFormat("0.00");
    }

    public String formata2CasasDecimais(double valor){
        return df.format(valor);
    }

    public Double calculaImc(double altura, double peso){
        double imc = peso / (altura * altura);

        return imc;
    }
}
