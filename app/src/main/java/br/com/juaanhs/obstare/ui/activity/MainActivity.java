package br.com.juaanhs.obstare.ui.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import br.com.juaanhs.obstare.R;
import br.com.juaanhs.obstare.ui.fragment.CalculosFragment;
import br.com.juaanhs.obstare.ui.fragment.PacientesFragment;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configuraToolbar();
        configuraAbas();


    }

    private void configuraAbas() {
        FragmentPagerItemAdapter adapter = configuraFragmentAdapter();
        configuraViewPager(adapter);
    }

    private void configuraViewPager(FragmentPagerItemAdapter adapter) {
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = findViewById(R.id.view_pager_tab);
        viewPagerTab.setViewPager(viewPager);
    }

    private FragmentPagerItemAdapter configuraFragmentAdapter() {
        return new FragmentPagerItemAdapter(
                getSupportFragmentManager(),
                FragmentPagerItems.with(this)
                    .add("Cálculos", CalculosFragment.class)
                    .add("Pacientes", PacientesFragment.class)
                    .create()
            );
    }

    private void configuraToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Obstare");
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_configuracoes :
                //feature
                break;

            case R.id.menu_sobre :
                //feature
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
