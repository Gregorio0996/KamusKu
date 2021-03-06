package com.example.gregorio.kamusku;

import android.app.ActionBar;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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
    boolean doubleBackToExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        kamusHelper = new KamusHelper(this);
        englishAdapter = new EnglishAdapter(this);
        indonesiaAdapter = new IndonesiaAdapter(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(indonesiaAdapter);
        kamusHelper.open();
        indoModels = kamusHelper.getAllDataIndo();
        englishModels = kamusHelper.getAllDataEng();
        kamusHelper.close();
        indonesiaAdapter.addItem(indoModels);
        drawer = (DrawerLayout) findViewById(R.id.activity_main);


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
    public void onBackPressed() {
        try{if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (doubleBackToExit) {
            super.onBackPressed();
            return;
        }}catch (Exception e){
            e.printStackTrace();
        }
        this.doubleBackToExit = true;
        Toast.makeText(this, "Pencet Sekali lagi untuk KELUAR", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExit = false;
            }
        }, 2000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        final MenuItem search = menu.findItem(R.id.search);
        android.widget.SearchView searchView = (android.widget.SearchView) search.getActionView();
        searchView.setLayoutParams(new ActionBar.LayoutParams(Gravity.RIGHT));
        searchView.setMaxWidth(R.dimen.search_max_width);
        searchView.setQueryHint(getResources().getString(R.string.masukan_kata_disini));
        searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                kamusHelper.open();
                indoModels = kamusHelper.getDataByNameIndo(newText);
                englishModels = kamusHelper.getDataByNameEng(newText);
                kamusHelper.close();
                if (index == R.id.nav_indoeng) {
                    recyclerView.setAdapter(indonesiaAdapter);
                    indonesiaAdapter.addItem(indoModels);
                }
                else if (index == R.id.nav_engindo) {
                    recyclerView.setAdapter(englishAdapter);
                    englishAdapter.addItem(englishModels);
                }
                return false;
            }
        });
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Tag", index);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementKosong")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_indoeng) {
            toolbar.setTitle(R.string.indoeng);
            recyclerView.setAdapter(indonesiaAdapter);
            indonesiaAdapter.addItem(indoModels);
        } else if (id == R.id.nav_engindo) {
            toolbar.setTitle(R.string.engindo);
            recyclerView.setAdapter(englishAdapter);
            englishAdapter.addItem(englishModels);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
