package com.example.gregorio.kamusku;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    public static String EXTRA_KATA = "extra_kata";
    public static String EXTRA_ARTI = "extra_arti";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tvKata = findViewById(R.id.tvKata);
        TextView tvArti = findViewById(R.id.tvArti);
        String kata = getIntent().getStringExtra(EXTRA_KATA);
        String arti = getIntent().getStringExtra(EXTRA_ARTI);
        String title = "Detail" + kata;

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(title); //requiers api lollipop
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvKata.setText(kata);
        tvArti.setText(arti);

    }

    @Override
    public void onBackPressed() {
        Intent kembali = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(kembali);
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent kembali = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(kembali);
        return true;
    }
}
