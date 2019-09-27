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
                verificaImprimeFaixaIMC("Abaixo de 19,90", "Entre 20,00 a 24,90",
                        "Entre 25,00 a 30,00", "Acima de 30,10",
                        19.9, 20.0, 24.9, 25.0, 30.0, 30.1);
                break;

            case 8:
            case 9:
                verificaImprimeFaixaIMC("Abaixo de 20,10", "Entre 20,20 a 25,00",
                        "Entre 25,10 a 30,10", "Acima de 30,20",
                        20.1, 20.2, 25.0, 25.1, 30.1, 30.2);
                break;

            case 10:
                verificaImprimeFaixaIMC("Abaixo de 20,20", "Entre 20,30 a 25,20",
                        "Entre 25,30 a 30,20", "Acima de 30,30",
                        20.2, 20.3, 25.2, 25.3, 30.2, 30.3);
                break;

            case 11:
                verificaImprimeFaixaIMC("Abaixo de 20,30", "Entre 20,40 a 25,30",
                        "Entre 25,40 a 30,30", "Acima de 30,40",
                        20.3, 20.4, 25.3, 25.4, 30.3, 30.4);
                break;

            case 12:
                verificaImprimeFaixaIMC("Abaixo de 20,40", "Entre 20,50 a 25,40",
                        "Entre 25,50 a 30,30", "Acima de 30,40",
                        20.4, 20.5, 25.4, 25.5, 30.3, 30.4);
                break;

            case 13:
                verificaImprimeFaixaIMC("Abaixo de 20,60", "Entre 20,70 a 25,60",
                        "Entre 25,70 a 30,40", "Acima de 30,50",
                        20.6, 20.7, 25.6, 25.7, 30.4, 30.5);
                break;

            case 14:
                verificaImprimeFaixaIMC("Abaixo de 20,70", "Entre 20,80 a 25,70",
                        "Entre 25,80 a 30,50", "Acima de 30,60",
                        20.7, 20.8, 25.7, 25.8, 30.5, 30.6);
                break;

            case 15:
                verificaImprimeFaixaIMC("Abaixo de 20,80", "Entre 20,90 a 25,80",
                        "Entre 25,90 a 30,60", "Acima de 30,70",
                        20.8, 20.9, 25.8, 25.9, 30.6, 30.7);
                break;

            case 16:
                verificaImprimeFaixaIMC("Abaixo de 21,00", "Entre 21,10 a 25,90",
                        "Entre 26,00 a 30,70", "Acima de 30,80",
                        21.0, 21.1, 25.9, 26.0, 30.7, 30.8);
                break;

            case 17:
                verificaImprimeFaixaIMC("Abaixo de 21,10", "Entre 21,20 a 26,00",
                        "Entre 26,10 a 30,80", "Acima de 30,90",
                        21.1, 21.2, 26.0, 26.1, 30.8, 30.9);
                break;

            case 18:
                verificaImprimeFaixaIMC("Abaixo de 21,20", "Entre 21,30 a 26,10",
                        "Entre 26,20 a 30,90", "Acima de 31,00",
                        21.2, 21.3, 26.1, 26.2, 30.9, 31.0);
                break;

            case 19:
                verificaImprimeFaixaIMC("Abaixo de 21,40", "Entre 21,50 a 26,20",
                        "Entre 26,30 a 30,90", "Acima de 31,00",
                        21.4, 21.5, 26.2, 26.3, 30.9, 31.0);
                break;

            case 20:
                verificaImprimeFaixaIMC("Abaixo de 21,50", "Entre 21,60 a 26,30",
                        "Entre 26,40 a 31,00", "Acima de 31,10",
                        21.5, 21.6, 26.3, 26.4, 31.0, 31.1);
                break;

            case 21:
                verificaImprimeFaixaIMC("Abaixo de 21,70", "Entre 21,80 a 26,40",
                        "Entre 26,50 a 31,10", "Acima de 31,20",
                        21.7, 21.8, 26.4, 26.5, 31.10, 31.2);
                break;

            case 22:
                verificaImprimeFaixaIMC("Abaixo de 21,80", "Entre 21,90 a 26,60",
                        "Entre 26,70 a 31,20", "Acima de 31,30",
                        21.8, 21.9, 26.6, 26.7, 31.2, 31.3);
                break;

            case 23:
                verificaImprimeFaixaIMC("Abaixo de 22,00", "Entre 22,10 a 26,80",
                        "Entre 26,90 a 31,30", "Acima de 31,40",
                        22.0, 22.1, 26.8, 26.9, 31.3, 31.4);
                break;

            case 24:
                verificaImprimeFaixaIMC("Abaixo de 22,20", "Entre 22,30 a 26,90",
                        "Entre 27,00 a 31,50", "Acima de 31,60",
                        22.2, 22.3, 26.9, 27.0, 31.5, 31.6);
                break;

            case 25:
                verificaImprimeFaixaIMC("Abaixo de 22,40", "Entre 22,50 a 27,00",
                        "Entre 27,10 a 31,60", "Acima de 31,70",
                        22.4, 22.5, 27.0, 27.1, 31.60, 31.7);
                break;

            case 26:
                verificaImprimeFaixaIMC("Abaixo de 22,60", "Entre 22,70 a 27,20",
                        "Entre 27,30 a 31,70", "Acima de 31,80", 22.6, 22.7,
                        27.2, 27.3, 31.7, 31.8);
                break;

            case 27:
                verificaImprimeFaixaIMC("Abaixo de 22,70", "Entre 22,80 a 27,30",
                        "Entre 27,40 a 31,80", "Acima de 31,90",
                        22.7, 22.8, 27.3, 27.4, 31.8, 31.9);
                break;

            case 28:
                verificaImprimeFaixaIMC("Abaixo de 22,90", "Entre 23,00 a 27,50",
                        "Entre 27,60 a 31,90", "Acima de 32,00",
                        22.9, 23.0, 27.5, 27.6, 31.9, 32.0);
                break;

            case 29:
                verificaImprimeFaixaIMC("Abaixo de 23,10", "Entre 23,20 a 27,60",
                        "Entre 27,70 a 32,00", "Acima de 32,10",
                        23.1, 23.2, 27.6, 27.7, 32.0, 32.1);
                break;

            case 30:
                verificaImprimeFaixaIMC("Abaixo de 23,30", "Entre 23,40 a 27,80",
                        "Entre 27,90 a 32,10", "Acima de 32,20",
                        23.3, 23.4, 27.8, 27.9, 32.1, 32.2);
                break;

            case 31:
                verificaImprimeFaixaIMC("Abaixo de 23,40", "Entre 23,50 a 27,90",
                        "Entre 28,00 a 32,20", "Acima de 32,30",
                        23.4, 23.5, 27.9, 28.0, 32.2, 32.3);
                break;

            case 32:
                verificaImprimeFaixaIMC("Abaixo de 23,60", "Entre 23,70 a 28,00",
                        "Entre 28,10 a 32,30", "Acima de 32,40",
                        23.6, 23.7, 28.0, 28.1, 32.3, 32.4);
                break;

            case 33:
                verificaImprimeFaixaIMC("Abaixo de 23,80", "Entre 23,90 a 28,10",
                        "Entre 28,20 a 32,40", "Acima de 32,50",
                        23.8, 23.9, 28.1, 28.2, 32.4, 32.5);
                break;

            case 34:
                verificaImprimeFaixaIMC("Abaixo de 23,90", "Entre 24,00 a 28,30",
                        "Entre 28,40 a 32,50", "Acima de 32,60",
                        23.9, 24.0, 28.3, 28.4, 32.5, 32.6);
                break;

            case 35:
                verificaImprimeFaixaIMC("Abaixo de 24,10", "Entre 24,20 a 28,40",
                        "Entre 28,50 a 32,60", "Acima de 32,70",
                        24.1, 24.2, 28.4, 28.5, 32.6, 32.7);
                break;

            case 36:
                verificaImprimeFaixaIMC("Abaixo de 24,20", "Entre 24,30 a 28,50",
                        "Entre 28,60 a 32,70", "Acima de 32,80",
                        24.2, 24.3, 28.5, 28.6, 32.7, 32.8);
                break;

            case 37:
                verificaImprimeFaixaIMC("Abaixo de 24,40", "Entre 24,50 a 28,70",
                        "Entre 28,80 a 32,80", "Acima de 32,90",
                        24.4, 24.5, 28.7, 28.8, 32.8, 32.9);
                break;

            case 38:
                verificaImprimeFaixaIMC("Abaixo de 24,50", "Entre 24,60 a 28,80",
                        "Entre 28,90 a 32,90", "Acima de 33,00",
                        24.5, 24.6, 28.8, 28.9, 32.9, 33.0);
                break;

            case 39:
                verificaImprimeFaixaIMC("Abaixo de 24,70", "Entre 23,80 a 28,90",
                        "Entre 29,00 a 33,00", "Acima de 33,10",
                        24.7, 23.8, 28.9, 29.0, 33.0, 33.1);
                break;

            case 40:
                verificaImprimeFaixaIMC("Abaixo de 24,90", "Entre 25,00 a 29,10",
                        "Entre 29,20 a 33,10", "Acima de 33,20",
                        24.9, 25.0, 29.1, 29.2, 33.1, 33.2);
                break;

            case 41:
                verificaImprimeFaixaIMC("Abaixo de 25,00", "Entre 25,10 a 29,20", "Entre 29,30 a 33,20", "Acima de 33,30", 25.0, 25.1, 29.2, 29.3, 33.2, 33.3);
                break;

            case 42:
                verificaImprimeFaixaIMC("Abaixo de 25,00", "Entre 25,10 a 29,20",
                        "Entre 29,30 a 33,20", "Acima de 33,30",
                        25.0, 25.1, 29.2, 29.3, 33.2, 33.3);
                break;
        }
    }

    private void verificaImprimeFaixaIMC(String abaixo, String ideal, String sobre, String obesa,
                                         double abaixoPeso, double idealPeso1, double idealPeso2, double sobrePeso1, double sobrePeso2, double obesaPeso) {
        faixaAbaixo.setText(abaixo);
        faixaIdeal.setText(ideal);
        faixaSobrepeso.setText(sobre);
        faixaObesidade.setText(obesa);
        if (imc <= abaixoPeso) {
            barraAbaixo.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
        } else if (imc >= idealPeso1 && imc <= idealPeso2) {
            barraIdeal.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
        } else if (imc >= sobrePeso1 && imc <= sobrePeso2) {
            barraSobrePeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
        } else if (imc >= obesaPeso) {
            barraObeso.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
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
                                    "O peso deve ser menor que 200kg",
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
