package com.myapp.unitempo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Recupera a preferência do modo escuro
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isDarkModeEnabled = sharedPreferences.getBoolean("dark_mode", false);

        if (isDarkModeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Configuração do ViewPager e TabLayout
        viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        // Adiciona os fragments da primeira aba com WeatherFragment e segunda aba com MapFragment
        adapter.addFragment(new WeatherListFragment(), "Previsão do Tempo");
        adapter.addFragment(new MapFragment(), "Mapa");

        // Configura o ViewPager com o Adapter
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        // Configuração AppBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("UniTempo"); // Título da AppBar
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu); // Infla o menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_settings) {
            // Configurações
            SettingsDialogFragment settingsDialog = new SettingsDialogFragment();
            settingsDialog.show(getSupportFragmentManager(), "SettingsDialogFragment");
            return true;
        } else if (item.getItemId() == R.id.menu_about) {
            // Sobre
            AboutDialogFragment aboutDialog = new AboutDialogFragment();
            aboutDialog.show(getSupportFragmentManager(), "AboutDialogFragment");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
