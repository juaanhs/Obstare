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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import br.com.juaanhs.obstare.R;

public class CalculaDumActivity extends AppCompatActivity {


    private ImageButton btnDataDum;
    private Button btnSalvar;
    private TextView resultadoDum;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;
    int ano, mes, dia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcula_dum);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setDataCalendario();
        inicializaBotoes();
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
                        String dataFormatada = conversorDateString(year, month, dayOfMonth);
                        resultadoDum.setText(dataFormatada);
                        setNovaDataAtual(year, month, dayOfMonth);
                    }
                }, ano, mes, dia);
        datePickerDialog.show();
    }

    private void setNovaDataAtual(int year, int month, int dayOfMonth) {
        ano = year;
        mes = month;
        dia = dayOfMonth;
    }

    private void setDataCalendario() {
        calendar = Calendar.getInstance();
        ano = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH);
        dia = calendar.get(Calendar.DAY_OF_MONTH);
    }

    private String conversorDateString(int year, int month, int dayOfMonth) {
        Date date = null;
        String dataFormatada = dayOfMonth + "/" + (month+1) + "/" + year;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(dataFormatada);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

    private void inicializaBotoes() {
        btnDataDum = findViewById(R.id.calcula_dum_btn_calendario_dum);
        resultadoDum = findViewById(R.id.calcula_dum_resultado_dum);
        btnSalvar = findViewById(R.id.calcula_dum_btn_salvar);
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
