package com.example.edsonferreira.exsantander.BD;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CriaBanco extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ExSantander";
    private static final String CELLS_TABLE = "CELLS";
    private static final String SCREEN_TABLE = "SCREEN";
    private static final String MOREINFO_TABLE = "MOREINFO";
    private static final String INFO_TABLE = "INFO";
    private static final String DOWNINFO_TABLE = "DOWNINFO";
    private static final String USINFO_TABLE = "USINFO";

    private SQLiteDatabase sqLiteDatabase = getWritableDatabase();

    private static final String CREATE_CELLS = "create table if not exists "
            + CELLS_TABLE + " ( _id integer primary key autoincrement, IDCELLS int, TYPE_CELL int" +
            ",MESSAGE text, TYPEFIELD int, HIDDEN boolean,"
            + "TOPSPACING double,SHOW int, REQUIRED boolean);";

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

    private static final String CREATE_USINFO = "create table if not exists "
            + USINFO_TABLE + " ( _id integer primary key autoincrement, NAME text, EMAIL text" +
            ",TEL text);";

    public CriaBanco(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //Executando comandos para criar as tabelas
        sqLiteDatabase.execSQL(CREATE_CELLS);
        sqLiteDatabase.execSQL(CREATE_SCREEN);
        sqLiteDatabase.execSQL(CREATE_MOREINFO);
        sqLiteDatabase.execSQL(CREATE_INFO);
        sqLiteDatabase.execSQL(CREATE_DOWNINFO);
        sqLiteDatabase.execSQL(CREATE_USINFO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void Delete(String table){

        sqLiteDatabase.execSQL("DELETE FROM " + table);

    }

    public void InsertCells(int IDCELLS, int TYPE_CELL,String MESSAGE,String TYPEFIELD,
                            String HIDDEN,double TOPSPACING,String SHOW,String REQUIRED){

        sqLiteDatabase.execSQL("INSERT INTO CELLS (IDCELLS,TYPE_CELL" +
                ",MESSAGE, TYPEFIELD, HIDDEN," +
                "TOPSPACING,SHOW, REQUIRED )\n" +
                "VALUES(" + IDCELLS + "," + TYPE_CELL + ",'" + MESSAGE + "','" + TYPEFIELD + "'," +
                "'" + HIDDEN + "'," + TOPSPACING + ",'" + SHOW + "','" + REQUIRED + "');");

    }

    public void InsertScreen(String TITLE, String FUNDNAME,String WHATIS,String DEFINITION,
                            String RISKTITLE,int RISK,String INFOTITLE){

        sqLiteDatabase.execSQL("INSERT INTO SCREEN (TITLE , FUNDNAME" +
                "           ,WHATIS, DEFINITION, RISKTITLE," +
                "            RISK,INFOTITLE)" +
                "VALUES('" + TITLE + "','" + FUNDNAME + "','" + WHATIS + "','" + DEFINITION + "'," +
                "'" + RISKTITLE + "'," + RISK + ",'" + INFOTITLE + "');");

    }

    public void InsertMoreInfo(int IDSCREEN, double MONTHFUND,double MONTHCDI,double YEARFUND,
                               double YEARCDI,double YMONTHSFUND,double YMONTHSCDI){

        sqLiteDatabase.execSQL("INSERT INTO MOREINFO (IDSCREEN, MONTHFUND" +
                "            ,MONTHCDI, YEARFUND, YEARCDI," +
                "           YMONTHSFUND,YMONTHSCDI)" +
                "VALUES(" + IDSCREEN + "," + MONTHFUND + "," + MONTHCDI + "," + YEARFUND + "," +
                 + YEARCDI + "," + YMONTHSFUND + "," + YMONTHSCDI + ");");

    }
    public void InsertInfo(int IDSCREEN, String NAME,String DATA){

        sqLiteDatabase.execSQL("INSERT INTO INFO (IDSCREEN, NAME" +
                "           ,DATA)" +
                "VALUES(" + IDSCREEN + ",'" + NAME + "','" + DATA + "');");

    }

    public void InsertDownInfo(int IDSCREEN, String NAME,String DATA){

        sqLiteDatabase.execSQL("INSERT INTO DOWNINFO (IDSCREEN, NAME" +
                "           ,DATA)" +
                "VALUES(" + IDSCREEN + ",'" + NAME + "','" + DATA + "');");

    }

    public void InsertUser(String NAME, String EMAIL, String TEL){

        sqLiteDatabase.execSQL("DELETE FROM USINFO");

        sqLiteDatabase.execSQL("INSERT INTO USINFO (NAME,EMAIL,TEL)" +
                "VALUES('" + NAME + "','" + EMAIL + "','" + TEL + "')");

    }

    public int getScreenId(){

        int idCells = 0;

        Cursor id = sqLiteDatabase.rawQuery("SELECT _id FROM SCREEN",null);

        if(id.getCount() > 0){

            id.moveToFirst();
            idCells = id.getInt(0);


        }

        id.close();
        return idCells;
    }

}
