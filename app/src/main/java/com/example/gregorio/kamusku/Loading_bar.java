package com.example.gregorio.kamusku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class Loading_bar extends AppCompatActivity {
    ProgressBar progressBar1, progressBar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_bar);
        progressBar1 = (ProgressBar)findViewById(R.id.progressBar3);
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar4);

    }
}
