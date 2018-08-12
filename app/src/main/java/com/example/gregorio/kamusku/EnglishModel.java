package com.example.gregorio.kamusku;

import android.os.Parcel;
import android.os.Parcelable;

public class EnglishModel {
    private int id;

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

    private String kata;
    private String arti;

    public EnglishModel() {

    }

    public EnglishModel(String kata, String arti) {
        this.kata = kata;
        this.arti = arti;
    }


    public EnglishModel(int id, String kata, String arti) {
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

    protected EnglishModel(Parcel in) {
        this.id = in.readInt();
        this.kata = in.readString();
        this.arti = in.readString();
    }

    public static final Parcelable.Creator<EnglishModel> CREATOR = new Parcelable.Creator<EnglishModel>() {
        @Override
        public EnglishModel createFromParcel(Parcel source) {
            return new EnglishModel(source);
        }

        @Override
        public EnglishModel[] newArray(int size) {
            return new EnglishModel[size];
        }
    };

}
