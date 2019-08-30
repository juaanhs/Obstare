package br.com.juaanhs.obstare.ui.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import br.com.forusers.heinsinputdialogs.CalculatorInputDialog;
import br.com.forusers.heinsinputdialogs.interfaces.OnInputDoubleListener;
import br.com.juaanhs.obstare.R;
import br.com.juaanhs.obstare.util.DataUtil;

public class CalculaUsgActivity extends AppCompatActivity {

    private TextView resultadoDum, resultadoDpc, resultadoIgSemanas, resultadoUsg, resultadoDpp, resultadoIgDias;
    private ImageButton btnUsgSemanas, btnUsgDias, btnDataUsg;
    private Calendar calendar;
    private int ano, mes, dia;
    private DatePickerDialog datePickerDialog;
    private DataUtil dataUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcula_usg);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dataUtil = new DataUtil();
        setDataCalendario();
        inicializaCamposAndBotoes();
        configuraBtnUsg();

        btnUsgDias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalculatorInputDialog dialog = new CalculatorInputDialog(CalculaUsgActivity.this);
                dialog.setPositiveButton(new OnInputDoubleListener() {
                    @Override
                    public boolean onInputDouble(AlertDialog alertDialog, Double aDouble) {
                        if(aDouble.intValue() >=7){
                            Toast.makeText(CalculaUsgActivity.this, "O número de dias deve ser menor que 7", Toast.LENGTH_SHORT).show();
                            resultadoIgDias.setText("_");
                            return false;
                        }
                        String string = "" + aDouble.intValue();
                        resultadoIgDias.setText(string);
                        return false;
                    }
                }).show();
                dialog.getCurrencyTextView().setVisibility(View.GONE);
                dialog.getDotButton().setVisibility(View.INVISIBLE);
            }
        });
    }

    private void configuraBtnUsg() {
        btnDataUsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configuraCalendario();
            }
        });
    }

    private void configuraCalendario() {
        datePickerDialog = new DatePickerDialog(CalculaUsgActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String stringUSG = dataUtil.IntsToString(year, month, dayOfMonth);
                        resultadoUsg.setText(stringUSG);
                        setNovaDataAtual(view);
                    }
                }, ano, mes, dia);
        datePickerDialog.show();
    }

    private void inicializaCamposAndBotoes() {
        btnUsgSemanas = findViewById(R.id.calcula_usg_btn_semanas);
        btnUsgDias = findViewById(R.id.calcula_usg_btn_dias);
        btnDataUsg = findViewById(R.id.calcula_usg_btn_calendario_usg);
        resultadoDum = findViewById(R.id.calcula_usg_resultado_dum);
        resultadoDpc = findViewById(R.id.calcula_usg_resultado_concepcao);
        resultadoDpp = findViewById(R.id.calcula_usg_resultado_dpp);
        resultadoUsg = findViewById(R.id.calcula_usg_resultado_usg);
        resultadoIgDias = findViewById(R.id.calcula_usg_resultado_dias_ultrasom);
        resultadoIgSemanas = findViewById(R.id.calcula_usg_resultado_semanas_ultrasom);
    }

    private void setNovaDataAtual(DatePicker view) {
        ano = view.getYear();
        mes = view.getMonth();
        dia = view.getDayOfMonth();
    }
    //TODO delegar responsabilidade para DataUtil
    private void setDataCalendario() {
        calendar = Calendar.getInstance();
        ano = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH);
        dia = calendar.get(Calendar.DAY_OF_MONTH);
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
