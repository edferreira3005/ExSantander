package com.example.edsonferreira.exsantander.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBanco extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ExSantander";
    protected static final String CELLS_TABLE = "CELLS";
    protected static final String FUND_TABLE = "FUND";

    public static final String CREATE_CELLS = "create table if not exists "
            + CELLS_TABLE + " ( _id integer primary key autoincrement, ID_CELL int, TYPE_CELL int" +
            ",MESSAGE text, TYPEFIELD text, HIDDEN text,"
            + "TOPSPACING double,SHOW int, REQUIRED text);";

    public CriaBanco(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //Executando comando para criar a tabela CELLS
        sqLiteDatabase.execSQL(CREATE_CELLS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
