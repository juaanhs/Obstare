package br.com.juaanhs.obstare.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.com.juaanhs.obstare.R;
import br.com.juaanhs.obstare.ui.activity.CalculaBishopActivity;
import br.com.juaanhs.obstare.ui.activity.CalculaDumActivity;
import br.com.juaanhs.obstare.ui.activity.CalculaImcActivity;
import br.com.juaanhs.obstare.ui.activity.CalculaUsgActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalculosFragment extends Fragment {

    private Button btnDum;
    private Button btnUsg;
    private Button btnImc;
    private Button btnBishop;

    public CalculosFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calculos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inicializaBotoes(view);
        configuraBtnDum();
        configuraBtnUsg();
        configuraBtnImc();
        configuraBtnBishop();
    }

    private void configuraBtnUsg() {
        btnUsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalculaUsgActivity.class);
                startActivity(intent);
            }
        });
    }

    private void configuraBtnImc() {
        btnImc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalculaImcActivity.class);
                startActivity(intent);
            }
        });
    }

    private void configuraBtnBishop() {
        btnBishop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalculaBishopActivity.class);
                startActivity(intent);
            }
        });
    }

    private void inicializaBotoes(@NonNull View view) {
        btnDum = view.findViewById(R.id.calculos_btn_dum);
        btnUsg = view.findViewById(R.id.calculos_btn_usg);
        btnImc = view.findViewById(R.id.calculos_btn_imc);
        btnBishop = view.findViewById(R.id.calculos_btn_bishop);
    }

    private void configuraBtnDum() {
        btnDum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalculaDumActivity.class);
                startActivity(intent);
            }
        });
    }
}
