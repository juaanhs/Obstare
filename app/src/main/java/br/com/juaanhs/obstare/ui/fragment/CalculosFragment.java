package br.com.juaanhs.obstare.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.juaanhs.obstare.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalculosFragment extends Fragment {


    public CalculosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculos, container, false);
    }

}
