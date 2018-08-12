package com.example.gregorio.kamusku;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Loading_bar extends AppCompatActivity {


    ProgressBar progressBar1, progressBar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_bar);
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar4);
        progressBar1 = (ProgressBar) findViewById(R.id.progressBar3);
        new LoadData().execute();
    }
        private class LoadData extends AsyncTask<Void, Integer, Void> {
            final String TAG = LoadData.class.getSimpleName();
            KamusHelper kamusHelper;
            AppPreference appPreference;
            double progress;
            double maxprogress = 280;

            @Override
            protected void onPreExecute() {
                kamusHelper = new KamusHelper(Loading_bar.this);
                appPreference = new AppPreference(Loading_bar.this);
            }

            @Override
            protected Void doInBackground(Void... params) {
                Boolean firstRun = appPreference.getFirstRun();
                if (firstRun) {
                    ArrayList<IndoModel> indoModels = preLoadRaw();
                    ArrayList<EnglishModel> englishModels = preLoadRaw2();
                    kamusHelper.open();
                    progress = 30;
                    publishProgress((int) progress);
                    Double progressMaxInsert = 80.0;
                    Double progressDiff = (progressMaxInsert - progress) / indoModels.size();
                    Double progressDiff2 = (progressMaxInsert - progress) / englishModels.size();
                    kamusHelper.beginTransaction();
                    try {
                        for (IndoModel model : indoModels) {
                            kamusHelper.insertTransactionInd(model);
                            progress += progressDiff;
                            publishProgress((int) progress);
                        }


                        for (EnglishModel model2 : englishModels) {
                            kamusHelper.insertTransactionEng(model2);
                            progress += progressDiff2;
                            publishProgress((int) progress);
                        }
                        kamusHelper.setTransactionSuccess();
                    }catch (Exception e){
                        Log.e(TAG, "Sukses");
                        e.printStackTrace();
                    }
                    kamusHelper.endTransaction();
                    kamusHelper.close();
                    appPreference.setFirstRun(false);
                    publishProgress((int) maxprogress);

                } else {
                    try {
                        synchronized (this) {
                            this.wait(2000);

                            publishProgress(50);

                            this.wait(2000);
                            publishProgress((int) maxprogress);
                        }
                    } catch (Exception e) {
                    }
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                progressBar2.setProgress(values[0]);


            }

            @Override
            protected void onPostExecute(Void result) {
                Intent i = new Intent(Loading_bar.this, MainActivity.class);
                startActivity(i);
                Log.d("pesan", "Sukses di load");
                finish();

            }
        }

    public ArrayList<IndoModel> preLoadRaw(){
        ArrayList<IndoModel> indoModels = new ArrayList<>();
        String line = null;
        BufferedReader reader;
        try{
            Resources res = getResources();
            InputStream raw_dict = res.openRawResource(R.raw.indonesia_english);
            reader = new BufferedReader(new InputStreamReader(raw_dict));
            int count = 0;
            do {
                line = reader.readLine();
                String[] splitstr = line.split("\t");
                IndoModel indoModel;
                indoModel = new IndoModel(splitstr[0], splitstr[1]);
                indoModels.add(indoModel);
                count++;
            }while (true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return indoModels;
    }

    public ArrayList<EnglishModel> preLoadRaw2(){
        ArrayList<EnglishModel> englishModels = new ArrayList<>();
        String line = null;
        BufferedReader reader;
        try{
            Resources res = getResources();
            InputStream raw_dict = res.openRawResource(R.raw.english_indonesia);
            reader = new BufferedReader(new InputStreamReader(raw_dict));
            int count = 0;
            do {
                line = reader.readLine();
                String[] splitstr = line.split("\t");
                EnglishModel englishModel;
                englishModel = new EnglishModel(splitstr[0], splitstr[1]);
                englishModels.add(englishModel);
                count++;
            }while (true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return englishModels;
    }

}