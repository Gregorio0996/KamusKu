package com.example.gregorio.kamusku;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;

public class IndoModel {
    private int id;
    private String kata;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKata() {
        return kata;
    }

    public void setKata(String kata) {
        this.kata = kata;
    }

    public String getArti() {
        return arti;
    }

    public void setArti(String arti) {
        this.arti = arti;
    }

    private String arti;

    public IndoModel(){

    }

    public IndoModel(String kata, String arti){
        this.kata = kata;
        this.arti = arti;
    }

    public IndoModel(int id, String kata, String arti){
        this.id = id;
        this.kata = kata;
        this.arti = arti;
    }

    /*@Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(this.id);
        dest.writeString(this.kata);
        dest.writeString(this.arti);
    }*/

    protected IndoModel(Parcel in){
        this.id = in.readInt();
        this.kata = in.readString();
        this.arti = in.readString();
    }

    public static final Parcelable.Creator<IndoModel> CREATOR = new Parcelable.Creator<IndoModel>() {
        @Override
        public IndoModel createFromParcel(Parcel source) {
            return new IndoModel(source);
        }

        @Override
        public IndoModel[] newArray(int size) {
            return new IndoModel[size];
        }
    };

}
