package br.com.juaanhs.obstare.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import br.com.forusers.heinsinputdialogs.CalculatorInputDialog;
import br.com.forusers.heinsinputdialogs.interfaces.OnInputDoubleListener;
import br.com.juaanhs.obstare.R;
import br.com.juaanhs.obstare.util.ImcUtil;

public class CalculaImcActivity extends AppCompatActivity {

    private ImageButton btnPeso, btnAltura, btnIgSemanas;
    private TextView resultadoPeso, resultadoAltura, resultadoSemanas, resultadoImc, faixaAbaixo,
    faixaIdeal, faixaSobrepeso, faixaObesidade;
    private View barraAbaixo,barraIdeal, barraSobrePeso, barraObeso;
    private ImcUtil imcUtil;
    private Toolbar toolbar;
    private Double peso, altura, imc;
    private int igSemanas;
    private boolean entrada1, entrada2, entrada3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcula_imc);
        toolbar = findViewById(R.id.toolbar);
        imcUtil = new ImcUtil();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        inicializaCamposAndButtons();
        configuraButtons();
        //barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }

    private void configuraButtons() {
        configuraBtnPeso();
        configuraBtnAltura();
        configuraBtnIgSemanas();
    }

    private void configuraBtnIgSemanas() {
        btnIgSemanas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalculatorInputDialog dialog = new CalculatorInputDialog(CalculaImcActivity.this);
                dialog.setPositiveButton(new OnInputDoubleListener() {
                    @Override
                    public boolean onInputDouble(AlertDialog alertDialog, Double aDouble) {
                        if(aDouble.intValue() > 42 || aDouble.intValue() < 6){
                            Toast.makeText(CalculaImcActivity.this,
                                    "O número de semanas deve ser maior que 6 e menor 42",
                                    Toast.LENGTH_LONG).show();
                            resultadoSemanas.setText("_");
                            return false;
                        }
                        imprimeIgSemanas(aDouble);
                        entrada3 = true;
                        verificaEntradas();
                        return false;
                    }
                }).show();
                dialog.getCurrencyTextView().setVisibility(View.GONE);
                dialog.getDotButton().setVisibility(View.INVISIBLE);
            }
        });
    }

    private void imprimeIgSemanas(Double aDouble) {
        igSemanas = aDouble.intValue();
        String strSemanas = "" + aDouble.intValue();
        resultadoSemanas.setText(strSemanas);
    }

    private void verificaEntradas() {
        if(entrada1 && entrada2 && entrada3){
            imprimeImc();
            setCoresPadrao();
            verificaImprimeFaixaImc();
        }
    }

    private void verificaImprimeFaixaImc() {
        switch (igSemanas) {
            case 6:
            case 7:
                faixaAbaixo.setText("Abaixo de 19,90");
                faixaIdeal.setText("Entre 20,00 a 24,90");
                faixaSobrepeso.setText("Entre 25,00 a 30,00");
                faixaObesidade.setText("Acima de 30,10");
                if (imc <= 19.9) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if (imc >= 20.0 && imc <= 24.9) {
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if (imc >= 25.0 && imc <= 30.0) {
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if (imc >= 30.1) {
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 8:
            case 9:
                faixaAbaixo.setText("Abaixo de 20,10");
                faixaIdeal.setText("Entre 20,20 a 25,00");
                faixaSobrepeso.setText("Entre 25,10 a 30,10");
                faixaObesidade.setText("Acima de 30,20");
                if(imc <= 20.1) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 20.2 && imc <= 25.0){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 25.1 && imc <= 30.1){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 30.2){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 10:
                faixaAbaixo.setText("Abaixo de 20,20");
                faixaIdeal.setText("Entre 20,30 a 25,20");
                faixaSobrepeso.setText("Entre 25,30 a 30,20");
                faixaObesidade.setText("Acima de 30,30");
                if(imc <= 20.2) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 20.3 && imc <= 25.2){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 25.3 && imc <= 30.2){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 30.3){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 11:
                faixaAbaixo.setText("Abaixo de 20,30");
                faixaIdeal.setText("Entre 20,40 a 25,30");
                faixaSobrepeso.setText("Entre 25,40 a 30,30");
                faixaObesidade.setText("Acima de 30,40");
                if(imc <= 20.3) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 20.4 && imc <= 25.3){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 25.4 && imc <= 30.3){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 30.4){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 12:
                faixaAbaixo.setText("Abaixo de 20,40");
                faixaIdeal.setText("Entre 20,50 a 25,40");
                faixaSobrepeso.setText("Entre 25,50 a 30,30");
                faixaObesidade.setText("Acima de 30,40");
                if(imc <= 20.4) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 20.5 && imc <= 25.4){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 25.5 && imc <= 30.3){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 30.4){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 13:
                faixaAbaixo.setText("Abaixo de 20,60");
                faixaIdeal.setText("Entre 20,70 a 25,60");
                faixaSobrepeso.setText("Entre 25,70 a 30,40");
                faixaObesidade.setText("Acima de 30,50");
                if(imc <= 20.6) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 20.7 && imc <= 25.6){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 25.7 && imc <= 30.4){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 30.5){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 14:
                faixaAbaixo.setText("Abaixo de 20,70");
                faixaIdeal.setText("Entre 20,80 a 25,70");
                faixaSobrepeso.setText("Entre 25,80 a 30,50");
                faixaObesidade.setText("Acima de 30,60");
                if(imc <= 20.7) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 20.8 && imc <= 25.7){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 25.8 && imc <= 30.5){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 30.6){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 15:
                faixaAbaixo.setText("Abaixo de 20,80");
                faixaIdeal.setText("Entre 20,90 a 25,80");
                faixaSobrepeso.setText("Entre 25,90 a 30,60");
                faixaObesidade.setText("Acima de 30,70");
                if(imc <= 20.8) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 20.9 && imc <= 25.8){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 25.9 && imc <= 30.6){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 30.7){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 16:
                faixaAbaixo.setText("Abaixo de 21,00");
                faixaIdeal.setText("Entre 21,10 a 25,90");
                faixaSobrepeso.setText("Entre 26,00 a 30,70");
                faixaObesidade.setText("Acima de 30,80");
                if(imc <= 21.0) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 21.1 && imc <= 25.9){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 26.0 && imc <= 30.7){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 30.8){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 17:
                faixaAbaixo.setText("Abaixo de 21,10");
                faixaIdeal.setText("Entre 21,20 a 26,00");
                faixaSobrepeso.setText("Entre 26,10 a 30,80");
                faixaObesidade.setText("Acima de 30,90");
                if(imc <= 21.1) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 21.2 && imc <= 26.0){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 26.1 && imc <= 30.8){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 30.9){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 18:
                faixaAbaixo.setText("Abaixo de 21,20");
                faixaIdeal.setText("Entre 21,30 a 26,10");
                faixaSobrepeso.setText("Entre 26,20 a 30,90");
                faixaObesidade.setText("Acima de 31,00");
                if(imc <= 21.2) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 21.3 && imc <= 26.1){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 26.2 && imc <= 30.9){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 31.0){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 19:
                faixaAbaixo.setText("Abaixo de 21,40");
                faixaIdeal.setText("Entre 21,50 a 26,20");
                faixaSobrepeso.setText("Entre 26,30 a 30,90");
                faixaObesidade.setText("Acima de 31,00");
                if(imc <= 21.4) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 21.5 && imc <= 26.2){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 26.3 && imc <= 30.9){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 31.0){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 20:
                faixaAbaixo.setText("Abaixo de 21,50");
                faixaIdeal.setText("Entre 21,60 a 26,30");
                faixaSobrepeso.setText("Entre 26,40 a 31,00");
                faixaObesidade.setText("Acima de 31,10");
                if(imc <= 21.5) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 21.6 && imc <= 26.3){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 26.4 && imc <= 31.0){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 31.1){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 21:
                faixaAbaixo.setText("Abaixo de 21,70");
                faixaIdeal.setText("Entre 21,80 a 26,40");
                faixaSobrepeso.setText("Entre 26,50 a 31,10");
                faixaObesidade.setText("Acima de 31,20");
                if(imc <= 21.7) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 21.8 && imc <= 26.4){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 26.5 && imc <= 31.10){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 31.2){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 22:
                faixaAbaixo.setText("Abaixo de 21,80");
                faixaIdeal.setText("Entre 21,90 a 26,60");
                faixaSobrepeso.setText("Entre 26,70 a 31,20");
                faixaObesidade.setText("Acima de 31,30");
                if(imc <= 21.8) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 21.9 && imc <= 26.6){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 26.7 && imc <= 31.2){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 31.3){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 23:
                faixaAbaixo.setText("Abaixo de 22,00");
                faixaIdeal.setText("Entre 22,10 a 26,80");
                faixaSobrepeso.setText("Entre 26,90 a 31,30");
                faixaObesidade.setText("Acima de 31,40");
                if(imc <= 22.0) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 22.1 && imc <= 26.8){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 26.9 && imc <= 31.3){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 31.4){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 24:
                faixaAbaixo.setText("Abaixo de 22,20");
                faixaIdeal.setText("Entre 22,30 a 26,90");
                faixaSobrepeso.setText("Entre 27,00 a 31,50");
                faixaObesidade.setText("Acima de 31,60");
                if(imc <= 22.2) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 22.3 && imc <= 26.9){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 27.0 && imc <= 31.5){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 31.6){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 25:
                faixaAbaixo.setText("Abaixo de 22,40");
                faixaIdeal.setText("Entre 22,50 a 27,00");
                faixaSobrepeso.setText("Entre 27,10 a 31,60");
                faixaObesidade.setText("Acima de 31,70");
                if(imc <= 22.4) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 22.5 && imc <= 27.0){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 27.1 && imc <= 31.60){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 31.7){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 26:
                faixaAbaixo.setText("Abaixo de 22,60");
                faixaIdeal.setText("Entre 22,70 a 27,20");
                faixaSobrepeso.setText("Entre 27,30 a 31,70");
                faixaObesidade.setText("Acima de 31,80");
                if(imc <= 22.6) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 22.7 && imc <= 27.2){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 27.3 && imc <= 31.7){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 31.8){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 27:
                faixaAbaixo.setText("Abaixo de 22,70");
                faixaIdeal.setText("Entre 22,80 a 27,30");
                faixaSobrepeso.setText("Entre 27,40 a 31,80");
                faixaObesidade.setText("Acima de 31,90");
                if(imc <= 22.7) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 22.8 && imc <= 27.3){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 27.4 && imc <= 31.8){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 31.9){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 28:
                faixaAbaixo.setText("Abaixo de 22,90");
                faixaIdeal.setText("Entre 23,00 a 27,50");
                faixaSobrepeso.setText("Entre 27,60 a 31,90");
                faixaObesidade.setText("Acima de 32,00");
                if(imc <= 22.9) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 23.0 && imc <= 27.5){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 27.6 && imc <= 31.9){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 32.0){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 29:
                faixaAbaixo.setText("Abaixo de 23,10");
                faixaIdeal.setText("Entre 23,20 a 27,60");
                faixaSobrepeso.setText("Entre 27,70 a 32,00");
                faixaObesidade.setText("Acima de 32,10");
                if(imc <= 23.1) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 23.2 && imc <= 27.6){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 27.7 && imc <= 32.0){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 32.1){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 30:
                faixaAbaixo.setText("Abaixo de 23,30");
                faixaIdeal.setText("Entre 23,40 a 27,80");
                faixaSobrepeso.setText("Entre 27,90 a 32,10");
                faixaObesidade.setText("Acima de 32,20");
                if(imc <= 23.3) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 23.4 && imc <= 27.8){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 27.9 && imc <= 32.1){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 32.2){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 31:
                faixaAbaixo.setText("Abaixo de 23,40");
                faixaIdeal.setText("Entre 23,50 a 27,90");
                faixaSobrepeso.setText("Entre 28,00 a 32,20");
                faixaObesidade.setText("Acima de 32,30");
                if(imc <= 23.4) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 23.5 && imc <= 27.9){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 28.0 && imc <= 32.2){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 32.3){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 32:
                faixaAbaixo.setText("Abaixo de 23,60");
                faixaIdeal.setText("Entre 23,70 a 28,00");
                faixaSobrepeso.setText("Entre 28,10 a 32,30");
                faixaObesidade.setText("Acima de 32,40");
                if(imc <= 23.6) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 23.7 && imc <= 28.0){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 28.1 && imc <= 32.3){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 32.4){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 33:
                faixaAbaixo.setText("Abaixo de 23,80");
                faixaIdeal.setText("Entre 23,90 a 28,10");
                faixaSobrepeso.setText("Entre 28,20 a 32,40");
                faixaObesidade.setText("Acima de 32,50");
                if(imc <= 23.8) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 23.9 && imc <= 28.1){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 28.2 && imc <= 32.4){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 32.5){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 34:
                faixaAbaixo.setText("Abaixo de 23,90");
                faixaIdeal.setText("Entre 24,00 a 28,30");
                faixaSobrepeso.setText("Entre 28,40 a 32,50");
                faixaObesidade.setText("Acima de 32,60");
                if(imc <= 23.9) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 24.0 && imc <= 28.3){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 28.4 && imc <= 32.5){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 32.6){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 35:
                faixaAbaixo.setText("Abaixo de 24,10");
                faixaIdeal.setText("Entre 24,20 a 28,40");
                faixaSobrepeso.setText("Entre 28,50 a 32,60");
                faixaObesidade.setText("Acima de 32,70");
                if(imc <= 24.1) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 24.2 && imc <= 28.4){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 28.5 && imc <= 32.6){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 32.7){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 36:
                faixaAbaixo.setText("Abaixo de 24,20");
                faixaIdeal.setText("Entre 24,30 a 28,50");
                faixaSobrepeso.setText("Entre 28,60 a 32,70");
                faixaObesidade.setText("Acima de 32,80");
                if(imc <= 24.2) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 24.3 && imc <= 28.5){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 28.6 && imc <= 32.7){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 32.8){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 37:
                faixaAbaixo.setText("Abaixo de 24,40");
                faixaIdeal.setText("Entre 24,50 a 28,70");
                faixaSobrepeso.setText("Entre 28,80 a 32,80");
                faixaObesidade.setText("Acima de 32,90");
                if(imc <= 24.4) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 24.5 && imc <= 28.7){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 28.8 && imc <= 32.8){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 32.9){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 38:
                faixaAbaixo.setText("Abaixo de 24,50");
                faixaIdeal.setText("Entre 24,60 a 28,80");
                faixaSobrepeso.setText("Entre 28,90 a 32,90");
                faixaObesidade.setText("Acima de 33,00");
                if(imc <= 24.5) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 24.6 && imc <= 28.8){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 28.9 && imc <= 32.9){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 33.0){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 39:
                faixaAbaixo.setText("Abaixo de 24,70");
                faixaIdeal.setText("Entre 23,80 a 28,90");
                faixaSobrepeso.setText("Entre 29,00 a 33,00");
                faixaObesidade.setText("Acima de 33,10");
                if(imc <= 24.7) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 23.8 && imc <= 28.9){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 29.0 && imc <= 33.0){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 33.1){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 40:
                faixaAbaixo.setText("Abaixo de 24,90");
                faixaIdeal.setText("Entre 25,00 a 29,10");
                faixaSobrepeso.setText("Entre 29,20 a 33,10");
                faixaObesidade.setText("Acima de 33,20");
                if(imc <= 24.9) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 25.0 && imc <= 29.1){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 29.2 && imc <= 33.1){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 33.2){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 41:
                faixaAbaixo.setText("Abaixo de 25,00");
                faixaIdeal.setText("Entre 25,10 a 29,20");
                faixaSobrepeso.setText("Entre 29,30 a 33,20");
                faixaObesidade.setText("Acima de 33,30");
                if(imc <= 25.0) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 25.1 && imc <= 29.2){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 29.3 && imc <= 33.2){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 33.3){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;

            case 42:
                faixaAbaixo.setText("Abaixo de 25,00");
                faixaIdeal.setText("Entre 25,10 a 29,20");
                faixaSobrepeso.setText("Entre 29,30 a 33,20");
                faixaObesidade.setText("Acima de 33,30");
                if(imc <= 25.0) {
                    barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 25.1 && imc <= 29.2){
                    barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }else if(imc >= 29.3 && imc <= 33.2){
                    barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                } else if(imc >= 33.3){
                    barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
                }
                break;
        }
    }

    private void setCoresPadrao() {
        barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraPadrao));
        barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraPadrao));
        barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraPadrao));
        barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraPadrao));
    }

    private void imprimeImc() {
        imc = imcUtil.calculaImc(altura, peso);
        resultadoImc.setText(imcUtil.formata2CasasDecimais(imc));
    }

    private void configuraBtnAltura() {
        btnAltura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalculatorInputDialog dialog = new CalculatorInputDialog(CalculaImcActivity.this);
                dialog.setPositiveButton(new OnInputDoubleListener() {
                    @Override
                    public boolean onInputDouble(AlertDialog alertDialog, Double aDouble) {
                        if(aDouble > 3.0){
                            Toast.makeText(CalculaImcActivity.this,
                                    "A altura deve ser menor que 3 metros",
                                    Toast.LENGTH_LONG).show();
                            resultadoAltura.setText("_");
                            return false;
                        } else if(aDouble == 0) {
                            resultadoAltura.setText("_");
                            Toast.makeText(CalculaImcActivity.this,
                                    "Informe uma altura válida",
                                    Toast.LENGTH_LONG).show();
                            return false;
                        }
                        imprimeAltura(aDouble);
                        entrada2 = true;
                        verificaEntradas();
                        return false;
                    }
                }).show();
                dialog.getCurrencyTextView().setVisibility(View.GONE);
                //dialog.getDotButton().setVisibility(View.INVISIBLE);
            }
        });
    }

    private void imprimeAltura(Double aDouble) {
        altura = aDouble;
        String alturaFormatado = imcUtil.formata2CasasDecimais(aDouble);
        resultadoAltura.setText(alturaFormatado);
    }

    private void configuraBtnPeso() {
        btnPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalculatorInputDialog dialog = new CalculatorInputDialog(CalculaImcActivity.this);
                dialog.setPositiveButton(new OnInputDoubleListener() {
                    @Override
                    public boolean onInputDouble(AlertDialog alertDialog, Double aDouble) {
                        if(aDouble > 200.00){
                            Toast.makeText(CalculaImcActivity.this,
                                    "O peso deve ser menor que 400kg",
                                    Toast.LENGTH_LONG).show();
                            resultadoPeso.setText("_");
                            return false;
                        } else if(aDouble == 0) {
                            resultadoPeso.setText("_");
                            Toast.makeText(CalculaImcActivity.this,
                                    "Informe um peso válido",
                                    Toast.LENGTH_LONG).show();
                            return false;
                        }
                        imprimePeso(aDouble);
                        entrada1 = true;
                        verificaEntradas();
                        return false;
                    }
                }).show();
                dialog.getCurrencyTextView().setVisibility(View.GONE);
                //dialog.getDotButton().setVisibility(View.INVISIBLE);
            }
        });
    }

    private void imprimePeso(Double aDouble) {
        peso = aDouble;
        String pesoFormatado = imcUtil.formata2CasasDecimais(aDouble);
        resultadoPeso.setText(pesoFormatado);
    }

    private void inicializaCamposAndButtons() {
        btnPeso = findViewById(R.id.calcula_imc_btn_peso);
        btnAltura = findViewById(R.id.calcula_imc_btn_altura);
        btnIgSemanas = findViewById(R.id.calcula_imc_btn_ig);
        resultadoPeso = findViewById(R.id.calcula_imc_resultado_peso);
        resultadoAltura = findViewById(R.id.calcula_imc_resultado_altura);
        resultadoSemanas = findViewById(R.id.calcula_imc_resultado_ig);
        resultadoImc = findViewById(R.id.calcula_imc_resultado_imc);
        barraAbaixo = findViewById(R.id.calcula_imc_barra_abaixo);
        barraIdeal = findViewById(R.id.calcula_imc_barra_adequado);
        barraSobrePeso = findViewById(R.id.calcula_imc_barra_sobrepeso);
        barraObeso = findViewById(R.id.calcula_imc_barra_obeso);
        faixaAbaixo = findViewById(R.id.calcula_imc_faixa_abaixo);
        faixaIdeal = findViewById(R.id.calcula_imc_faixa_ideal);
        faixaSobrepeso = findViewById(R.id.calcula_imc_faixa_sobrepeso);
        faixaObesidade = findViewById(R.id.calcula_imc_faixa_obesidade);
        entrada1 = false;
        entrada2 = false;
        entrada3 = false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
