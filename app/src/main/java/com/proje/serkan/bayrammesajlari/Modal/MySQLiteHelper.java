package com.proje.serkan.bayrammesajlari.Modal;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by gökhan on 20.07.2017.
 */

public class MySQLiteHelper extends SQLiteAssetHelper{

    private static final String DATABASE_NAME = "MESSAGE.db";
    private static final int DATABASE_VERSION = 1;


    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


}
