package com.example.edsonferreira.exsantander.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBanco extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ExSantander";
    private static final String CELLS_TABLE = "CELLS";
    private static final String SCREEN_TABLE = "SCREEN";
    private static final String MOREINFO_TABLE = "MOREINFO";
    private static final String INFO_TABLE = "INFO";
    private static final String DOWNINFO_TABLE = "DOWNINFO";

    private static final String CREATE_CELLS = "create table if not exists "
            + CELLS_TABLE + " ( _id integer primary key autoincrement, ID_CELL int, TYPE_CELL int" +
            ",MESSAGE text, TYPEFIELD text, HIDDEN text,"
            + "TOPSPACING double,SHOW int, REQUIRED text);";

    private static final String CREATE_SCREEN = "create table if not exists "
            + SCREEN_TABLE + " ( _id integer primary key autoincrement, TITLE text, FUNDNAME text" +
            ",WHATIS text, DEFINITION text, RISKTITLE text,"
            + "RISK int,INFOTITLE text);";

    private static final String CREATE_MOREINFO = "create table if not exists "
            + MOREINFO_TABLE + " ( _id integer primary key autoincrement, IDSCREEN int, MONTHFUND double" +
            ",MONTHCDI double, YEARFUND double, YEARCDI double,"
            + "YMONTHSFUND double,YMONTHSCDI double);";

    private static final String CREATE_INFO = "create table if not exists "
            + INFO_TABLE + " ( _id integer primary key autoincrement, IDSCREEN int, NAME text" +
            ",DATA text);";

    private static final String CREATE_DOWNINFO = "create table if not exists "
            + DOWNINFO_TABLE + " ( _id integer primary key autoincrement, IDSCREEN int, NAME text" +
            ",DATA text);";

    public CriaBanco(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //Executando comando para criar a tabela CELLS
        sqLiteDatabase.execSQL(CREATE_CELLS);
        sqLiteDatabase.execSQL(CREATE_SCREEN);
        sqLiteDatabase.execSQL(CREATE_MOREINFO);
        sqLiteDatabase.execSQL(CREATE_INFO);
        sqLiteDatabase.execSQL(CREATE_DOWNINFO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
