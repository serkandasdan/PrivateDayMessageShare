package com.proje.serkan.bayrammesajlari.Modal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gökhan on 20.07.2017.
 */

public class MyDataBaseClass {

    private static MyDataBaseClass INSTANCE;
    private static Context context;
    private static SQLiteDatabase database;
    private static MySQLiteHelper myhelper;

    public MyDataBaseClass() {
        myhelper = new MySQLiteHelper(context);

    }

    public static MyDataBaseClass getInstance(Context context) {

        MyDataBaseClass.context = context;

        if (INSTANCE == null) {
            INSTANCE = new MyDataBaseClass();
        }
        return INSTANCE;
    }

    public List<MessagesClass> getAllMessageData() {

        List<MessagesClass> MessagesList = new ArrayList<MessagesClass>();

        openDB();
        String sql = "SELECT * FROM tbl_kurban";
        MessagesClass messsage;
        Cursor cr = database.rawQuery(sql, null);

        if (cr != null && cr.getCount() != 0) {

           // cr.moveToFirst();

            while (cr.moveToNext()) {
                messsage = new MessagesClass();
                messsage.setmID(cr.getInt(0));
                messsage.setIcerik(cr.getString(cr.getColumnIndex("icerik")));
                MessagesList.add(messsage);
            }
        }


        closeDB();
        return MessagesList;

    }

    private void closeDB() {

        database.close();
    }


    private void openDB() {
        database = myhelper.getWritableDatabase();

    }
}
