package com.example.gregorio.kamusku;

import android.provider.BaseColumns;

public class DatabaseContract {

    static String TABEL_INDO = "tabel_indonesia";
    static String TABEL_ENG = "tabel_inggris";

    static final class KamusColumns implements BaseColumns {
        static String KATA = "Kata";
        static String ARTI = "Arti";
    }

}
