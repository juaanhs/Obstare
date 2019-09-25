package br.com.juaanhs.obstare.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import org.honorato.multistatetogglebutton.MultiStateToggleButton;
import org.honorato.multistatetogglebutton.ToggleButton;

import br.com.juaanhs.obstare.R;

public class CalculaBishopActivity extends AppCompatActivity {

    private MultiStateToggleButton mstb1, mstb2, mstb3, mstb4, mstb5;
    private View barraBishop1, barraBishop2, barraBishop3;
    private boolean entrada1, entrada2, entrada3, entrada4, entrada5;
    private int resultBishop, result1, result2, result3, result4, result5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcula_bishop);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        inicializaCamposAndButtons();
        configurabotoes();
    }

    private void configurabotoes() {
        configuraTgButton1();
        configuraTgButton2();
        configuraTgButton3();
        configuraTgButton4();
        configuraTgButton5();
    }

    private void inicializaCamposAndButtons() {
        entrada1 = false;
        entrada2 = false;
        entrada3 = false;
        entrada4 = false;
        entrada5 = false;
        mstb1 = findViewById(R.id.bishop_multibtn1);
        mstb2 = findViewById(R.id.bishop_multibtn2);
        mstb3 = findViewById(R.id.bishop_multibtn3);
        mstb4 = findViewById(R.id.bishop_multibtn4);
        mstb5 = findViewById(R.id.bishop_multibtn5);
        barraBishop1 = findViewById(R.id.barraBishop1);
        barraBishop2 = findViewById(R.id.barraBishop2);
        barraBishop3 = findViewById(R.id.barraBishop3);
    }

    private void setCoresPadrao() {
        barraBishop1.setBackgroundColor(getResources().getColor(R.color.colorBarraPadrao));
        barraBishop2.setBackgroundColor(getResources().getColor(R.color.colorBarraPadrao));
        barraBishop3.setBackgroundColor(getResources().getColor(R.color.colorBarraPadrao));
    }

    private void imprimeResultadoBishop() {
        setCoresPadrao();
        setValorBishop();
        verificaEntradasBishop();
    }

    private void verificaEntradasBishop() {
        if(entrada1 && entrada2 && entrada3 && entrada4 && entrada5){
            marcaBarraBishop();
        }
    }

    private void marcaBarraBishop() {
        if(resultBishop >= 8){
            barraBishop1.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
        } else if(resultBishop >= 5){
            barraBishop2.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
        } else {
            barraBishop3.setBackgroundColor(getResources().getColor(R.color.colorBarraSelected));
        }
    }

    private void setValorBishop() {
        resultBishop = result1 + result2 + result3 + result4 + result5;
        Log.d("tag", "bishop" + resultBishop);
    }

    private void configuraTgButton5() {
        mstb5.setOnValueChangedListener(new ToggleButton.OnValueChangedListener() {
            @Override
            public void onValueChanged(int value) {
                result5=0;
                switch (value){
                    case 0:
                        result5=0;
                        break;
                    case 1:
                        result5=1;
                        break;
                    case 2:
                        result5=2;
                        break;
                }
                entrada5 = true;
                imprimeResultadoBishop();
            }
        });
    }

    private void configuraTgButton4() {

        mstb4.setOnValueChangedListener(new ToggleButton.OnValueChangedListener() {
            @Override
            public void onValueChanged(int value) {
                result4 = 0;
                switch (value){
                    case 0:
                        result4=0;
                        break;
                    case 1:
                        result4=1;
                        break;
                    case 2:
                        result4=2;
                        break;
                }
                entrada4 = true;
                imprimeResultadoBishop();
            }
        });
    }

    private void configuraTgButton3() {
        result3 = 0;
        mstb3.setOnValueChangedListener(new ToggleButton.OnValueChangedListener() {
            @Override
            public void onValueChanged(int value) {
                result3 = 0;
                switch (value){
                    case 0:
                        result3=0;
                        break;
                    case 1:
                        result3=1;
                        break;
                    case 2:
                        result3=2;
                        break;
                    case 3:
                        result3=3;
                        break;
                }
                entrada3 = true;
                imprimeResultadoBishop();
            }
        });
    }

    private void configuraTgButton2() {
        result2 = 0;
        mstb2.setOnValueChangedListener(new ToggleButton.OnValueChangedListener() {
            @Override
            public void onValueChanged(int value) {
                result2 = 0;
                switch (value){
                    case 0:
                        result2=0;
                        break;
                    case 1:
                        result2=1;
                        break;
                    case 2:
                        result2=2;
                        break;
                    case 3:
                        result2=3;
                        break;
                }
                entrada2 = true;
                imprimeResultadoBishop();
            }
        });

    }

    private void configuraTgButton1() {
        mstb1.setOnValueChangedListener(new ToggleButton.OnValueChangedListener() {
            @Override
            public void onValueChanged(int value) {
                result1 = 0;
                switch (value){
                    case 0:
                        result1=0;
                        break;
                    case 1:
                        result1=1;
                        break;
                    case 2:
                        result1=2;
                        break;
                    case 3:
                        result1=3;
                        break;

                }
                Log.d("test", "Position: " + value);
                entrada1 = true;
                imprimeResultadoBishop();
            }
        });
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
