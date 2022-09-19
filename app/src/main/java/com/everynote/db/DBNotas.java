package com.everynote.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBNotas extends SQLiteOpenHelper {
    private static final int VERSAO = 1;   
    public DBNotas(Context context) {
        super(context, "notas.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE notas( id INTEGER PRIMARY KEY AUTOINCREMENT, titulo VARCHAR (100), texto VARCHAR (200), prioridade INTEGER);");
     }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL ("DROP TABLE IF EXISTS notas");
        onCreate(db);
    }
}

