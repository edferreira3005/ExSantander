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

    public Cursor ConsultaSreen(){

        Consulta = sqLiteDatabase.rawQuery("SELECT " +
                "                                     _id," +
                "                                     TITLE ," +
                "                                     FUNDNAME," +
                "                                     WHATIS," +
                "                                     DEFINITION," +
                "                                     RISKTITLE," +
                "                                     RISK," +
                "                                     INFOTITLE " +
                "                                FROM " +
                "                                   SCREEN " +
                "                                ORDER BY " +
                "                                       _id",null);

        return Consulta;
    }

    public Cursor ConsultaMoreInfo(){
        Consulta = sqLiteDatabase.rawQuery("SELECT " +
                "                                     _id," +
                "                                     IDSCREEN, " +
                "CASE WHEN MONTHFUND = 0 THEN CASE WHEN YEARFUND = 0 THEN YMONTHSFUND ELSE YEARFUND END" +
                " ELSE MONTHFUND END AS 'FUND'," +
                "CASE WHEN MONTHCDI = 0 THEN CASE WHEN YEARCDI = 0 THEN YMONTHSCDI ELSE YEARCDI END" +
                " ELSE MONTHCDI END AS 'CDI'," +
                "CASE WHEN MONTHFUND = 0 THEN CASE WHEN YEARFUND = 0 THEN '12 meses' ELSE 'No Ano' END" +
                "               ELSE 'No mês' END AS 'DESCR' " +
                "FROM " +
                "    MOREINFO " +
                " ORDER BY " +
                "        _id",null);

        return Consulta;
    }

    public Cursor ConsultaInfo(){
        Consulta = sqLiteDatabase.rawQuery("SELECT " +
                "                                     _id," +
                "                                     IDSCREEN, " +
                "                                     NAME as 'NAME'," +
                "                                     DATA as 'DATA'" +
                "                                FROM " +
                "                                   INFO",null);

        return Consulta;
    }

    public Cursor ConsultaInfoDown(){
        Consulta = sqLiteDatabase.rawQuery("SELECT " +
                "                                     _id," +
                "                                     IDSCREEN," +
                "                                     NAME as 'NAME'," +
                "                                     DATA as 'DATA'" +
                "                                 FROM" +
                "                                    DOWNINFO",null);
        return Consulta;
    }
}
