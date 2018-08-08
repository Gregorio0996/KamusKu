package com.example.gregorio.kamusku;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.example.gregorio.kamusku.DatabaseContract.KamusColumns.ARTI;
import static com.example.gregorio.kamusku.DatabaseContract.KamusColumns.KATA;
import static com.example.gregorio.kamusku.DatabaseContract.TABEL_ENG;
import static com.example.gregorio.kamusku.DatabaseContract.TABEL_INDO;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "databaseKamus";

    private static final int DATABASE_VERSION = 1;

    public static String CREATE_TABLE_INDOENG = "create table "+TABEL_INDO+
            " ("+_ID+" integer primary key autoincrement, " +
            KATA+" text not null, " +
            ARTI+" text not null);";
    public static String CREATE_TABLE_ENGINDO = "create table "+TABEL_ENG+
            " ("+_ID+" integer primary key autoincrement, " +
            KATA+" text not null, " +
            ARTI+" text not null);";
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(CREATE_TABLE_ENGINDO);
    db.execSQL(CREATE_TABLE_INDOENG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABEL_ENG);
    db.execSQL("DROP TABLE IF EXISTS " + TABEL_INDO);
    onCreate(db);
    }
}
