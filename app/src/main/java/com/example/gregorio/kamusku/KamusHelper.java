package com.example.gregorio.kamusku;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.gregorio.kamusku.DatabaseContract.KamusColumns.ARTI;
import static com.example.gregorio.kamusku.DatabaseContract.KamusColumns.KATA;
import static com.example.gregorio.kamusku.DatabaseContract.TABEL_ENG;
import static com.example.gregorio.kamusku.DatabaseContract.TABEL_INDO;

public class KamusHelper {
    private Context context;
    private DatabaseHelper databaseHelper;

    private SQLiteDatabase database;

    public KamusHelper(Context context){
        this.context = context;
    }

    public KamusHelper open() throws SQLException{
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        databaseHelper.close();
    }

    public ArrayList<IndoModel> getDataByNameIndo(String kata){
        String result = "";
        Cursor cursor = database.query(TABEL_INDO,null,KATA+ " LIKE ?",new String[]{kata},null,null,_ID+" ASC",null);
        cursor.moveToFirst();
        ArrayList<IndoModel> arrayIndo = new ArrayList<>();
        IndoModel indoModel;
        if(cursor.getCount()>0){
            do{
                indoModel = new IndoModel();
                indoModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                indoModel.setKata(cursor.getString(cursor.getColumnIndexOrThrow(KATA)));
                indoModel.setArti(cursor.getString(cursor.getColumnIndexOrThrow(ARTI)));

                arrayIndo.add(indoModel);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayIndo;
    }

    public ArrayList<EnglishModel> getDataByNameEng(String kata){
        String result = "";
        Cursor cursor = database.query(TABEL_ENG,null,KATA+ " LIKE ?",new String[]{kata},null,null,_ID+" ASC",null);
        cursor.moveToFirst();
        ArrayList<EnglishModel> arrayEnglish = new ArrayList<>();
        EnglishModel englishModel;
        if(cursor.getCount()>0){
            do{
                englishModel = new EnglishModel();
                englishModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                englishModel.setKata(cursor.getString(cursor.getColumnIndexOrThrow(KATA)));
                englishModel.setArti(cursor.getString(cursor.getColumnIndexOrThrow(ARTI)));

                arrayEnglish.add(englishModel);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayEnglish;
    }

    public ArrayList<IndoModel> getAllDataIndo(){
        Cursor cursor = database.query(TABEL_INDO, null, null, null,null, null,_ID+ " ASC",null);
        cursor.moveToFirst();
        ArrayList<IndoModel> arrayIndo = new ArrayList<>();
        IndoModel indoModel;
        if (cursor.getCount()>0){
            do{
                indoModel = new IndoModel();
                indoModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                indoModel.setKata(cursor.getString(cursor.getColumnIndexOrThrow(KATA)));
                indoModel.setArti(cursor.getString(cursor.getColumnIndexOrThrow(ARTI)));

                arrayIndo.add(indoModel);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayIndo;
    }

    public ArrayList<EnglishModel> getAllDataEng(){
        Cursor cursor = database.query(TABEL_ENG, null, null, null,null, null,_ID+ " ASC",null);
        cursor.moveToFirst();
        ArrayList<EnglishModel> arrayEng = new ArrayList<>();
        EnglishModel englishModel;
        if (cursor.getCount()>0){
            do{
                englishModel = new EnglishModel();
                englishModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                englishModel.setKata(cursor.getString(cursor.getColumnIndexOrThrow(KATA)));
                englishModel.setArti(cursor.getString(cursor.getColumnIndexOrThrow(ARTI)));

                arrayEng.add(englishModel);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayEng;
    }

    public void insertIndo(IndoModel indoModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put(KATA, indoModel.getKata());
        contentValues.put(ARTI, indoModel.getArti());
        database.insert(TABEL_INDO, null, contentValues);
    }

    public void insertEnglish(EnglishModel englishModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put(KATA, englishModel.getKata());
        contentValues.put(ARTI, englishModel.getArti());
        database.insert(TABEL_ENG, null, contentValues);
    }

    public void insertTransactionInd(IndoModel indoModel){
        String sql = "INSERT INTO "+ TABEL_INDO + " (" + KATA + "," + ARTI + ") VALUES (? ,?)";
        SQLiteStatement stIndo = database.compileStatement(sql);
        stIndo.bindString(1, indoModel.getKata());
        stIndo.bindString(2, indoModel.getArti());
        stIndo.execute();
        stIndo.clearBindings();
    }

    public void insertTransactionEng(EnglishModel englishModel){
        String sql = "INSERT INTO "+ TABEL_ENG + " (" + KATA + "," + ARTI + ") VALUES (? ,?)";
        SQLiteStatement stEng = database.compileStatement(sql);
        stEng.bindString(1, englishModel.getKata());
        stEng.bindString(2, englishModel.getArti());
        stEng.execute();
        stEng.clearBindings();
    }

    public void beginTransaction(){
        database.beginTransaction();
    }

    public void setTransactionSuccess(){
        database.setTransactionSuccessful();
    }

    public void endTransaction(){
        database.endTransaction();
    }


}
