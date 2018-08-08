package com.example.gregorio.kamusku;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView recyclerView;
    private EnglishAdapter englishAdapter;
    private IndonesiaAdapter indonesiaAdapter;
    private ArrayList<IndoModel> indoModels;
    private ArrayList<EnglishModel> englishModels;
    private KamusHelper kamusHelper;
    private static int index;
    NavigationView navigationView;
    Toolbar toolbar;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(indonesiaAdapter);
        kamusHelper.open();
        indoModels = kamusHelper.getAllDataIndo();
        englishModels = kamusHelper.getAllDataEng();
        kamusHelper.close();
        indonesiaAdapter.addItem(indoModels);
        drawer = (DrawerLayout) findViewById(R.id.activity_main);

        FloatingActionButton floating = (FloatingActionButton) findViewById(R.id.floating);
        floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Ganti dengan aksi", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState != null) {
            navigationView.getMenu().findItem(savedInstanceState.getInt("Tag")).setChecked(true);
            if (savedInstanceState.getInt("Tag") == R.id.nav_indoeng) {
                toolbar.setTitle(R.string.indoeng);
                recyclerView.setAdapter(indonesiaAdapter);
                indonesiaAdapter.addItem(indoModels);
            } else if (savedInstanceState.getInt("Tag") == R.id.nav_engindo) {
                toolbar.setTitle(R.string.engindo);
                recyclerView.setAdapter(englishAdapter);
                englishAdapter.addItem(englishModels);
            }
            return;
        }
        index = R.id.nav_indoeng;
    }

    

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
