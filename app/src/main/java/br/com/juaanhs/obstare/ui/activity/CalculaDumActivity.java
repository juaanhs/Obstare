package br.com.juaanhs.obstare.ui.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import br.com.juaanhs.obstare.R;
import br.com.juaanhs.obstare.util.DataUtil;

public class CalculaDumActivity extends AppCompatActivity {


    private ImageButton btnDataDum;
    private Button btnSalvar;
    private TextView resultadoDum, resultadoDdc, resultadoDpp, resultadoIg;
    private DatePickerDialog datePickerDialog;
    private DataUtil dataUtil;
    private Calendar calendar;
    private int ano, mes, dia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcula_dum);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dataUtil = new DataUtil();
        setDataCalendario();
        inicializaCamposAndBotoes();
        configuraBtnDataDum();
    }

    private void configuraBtnDataDum() {
        btnDataDum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configuraCalendario();
            }
        });
    }

    private void configuraCalendario() {
        datePickerDialog = new DatePickerDialog(CalculaDumActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String stringDUM = dataUtil.IntsToString(year, month, dayOfMonth);
                        resultadoDum.setText(stringDUM);
                        Date date = dataUtil.convertStringToDate(stringDUM);
                        resultadoDpp.setText(dataUtil.calculaDPP(date));
                        resultadoDdc.setText(dataUtil.calculaDPC(date));
                        resultadoIg.setText(dataUtil.calculaIG(date));
                        setNovaDataAtual(view);
                    }
                }, ano, mes, dia);
        datePickerDialog.show();
    }

    //TODO delegar responsabilidade para DataUtil
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

    private void inicializaCamposAndBotoes() {
        btnDataDum = findViewById(R.id.calcula_dum_btn_calendario_dum);
        resultadoDum = findViewById(R.id.calcula_usg_resultado_dum);
        resultadoDdc = findViewById(R.id.calcula_usg_resultado_concepcao);
        resultadoDpp = findViewById(R.id.calcula_usg_resultado_dpp);
        resultadoIg = findViewById(R.id.calcula_usg_resultado_ig);
        btnSalvar = findViewById(R.id.calcula_usg_btn_salvar);
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
