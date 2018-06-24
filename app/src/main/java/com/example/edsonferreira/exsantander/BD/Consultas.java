package com.example.edsonferreira.exsantander.BD;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Consultas extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ExSantander";

    private SQLiteDatabase sqLiteDatabase = getWritableDatabase();

    private Cursor Consulta;

    public Consultas(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Cursor ConsultaCells(){

        Consulta = sqLiteDatabase.rawQuery("SELECT" +
                "                                     _id," +
                "                                     IDCELLS," +
                "                                     TYPE_CELL," +
                "                                     MESSAGE, " +
                "                                     TYPEFIELD, HIDDEN," +
                "                                     TOPSPACING," +
                "                                     SHOW, " +
                "                                     REQUIRED " +
                "                                FROM " +
                "                                   CELLS " +
                "                                ORDER BY" +
                "                                       _id",null);


        return Consulta;

    }
}
