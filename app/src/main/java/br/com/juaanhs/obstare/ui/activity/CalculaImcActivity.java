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
    private TextView resultadoPeso, resultadoAltura, resultadoSemanas, resultadoImc;
    private View barraAbaixo,barraIdeal, barraSobrePeso, barraObeso;
    private ImcUtil imcUtil;
    private Toolbar toolbar;
    private Double peso, altura;
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
        }
    }

    private void imprimeImc() {
        Double imc = imcUtil.calculaImc(altura, peso);
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
