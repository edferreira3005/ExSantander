package com.example.edsonferreira.exsantander.BuscaDados;

import android.content.Context;
import android.content.res.Resources;

import com.example.edsonferreira.exsantander.BD.CriaBanco;
import com.example.edsonferreira.exsantander.BuscaDados.Conexao.Conexao;
import com.example.edsonferreira.exsantander.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class Busca {

    public void BuscaInfo(Context tela) throws JSONException, UnsupportedEncodingException {

        //Criar Banco de dados antes de tudo.
        CriaBanco banco = new CriaBanco(tela);

        //Buscando dados da URL
        Conexao con = new Conexao();

        //Primeira URL de dados
        String Dados1 = con.BuscaUrl(tela.getResources().getString(R.string.url1));

        //Segunda URL de dados
        String Dados2 = con.BuscaUrl(tela.getResources().getString(R.string.url2));

        //Inserindo dados na tabela CELLS
        JSONObject cellsJson;

        cellsJson = new JSONObject(Dados1);

        if(cellsJson.length() > 0){

            JSONArray CellsArray;
            int i;
            CellsArray = new JSONArray(cellsJson.getString("cells"));

            for (i = 0; i < CellsArray.length(); i++) {

                cellsJson = new JSONObject(CellsArray.getString(i));

                if (i == 0 && CellsArray.length() > 0) {
                    banco.Delete("CELLS");
                }
                //Transformando letras com acentuação
                byte formatedtext[] = cellsJson.getString("message").trim().getBytes("ISO-8859-1");
                String message = new String(formatedtext, "UTF-8");

                banco.InsertCells(cellsJson.getInt("id"), cellsJson.getInt("type"),
                        message, cellsJson.getString("typefield"),
                        cellsJson.getString("hidden"), cellsJson.getDouble("topSpacing"),
                        cellsJson.getString("show"), cellsJson.getString("required"));
            }

        }

        //Inserindo dados nas tabelas SCREEN,MOREINFO,INFO e DOWNINFO
        //Pegando id da CELLS


        JSONObject ScreenJson1;

        ScreenJson1 = new JSONObject(Dados2);

        JSONObject ScreenJson = new JSONObject(ScreenJson1.getString("screen"));



        //Adicionando na tabela SCREEN
        if(ScreenJson.length() > 0) {

            banco.Delete("SCREEN");
            byte ptext1[] = ScreenJson.getString("title").trim().getBytes("ISO-8859-1");
            String title = new String(ptext1, "UTF-8");
            byte ptext2[] = ScreenJson.getString("fundName").trim().getBytes("ISO-8859-1");
            String fundName = new String(ptext2, "UTF-8");
            byte ptext3[] = ScreenJson.getString("whatIs").trim().getBytes("ISO-8859-1");
            String whatIs = new String(ptext3, "UTF-8");
            byte ptext4[] = ScreenJson.getString("definition").trim().getBytes("ISO-8859-1");
            String definition = new String(ptext4, "UTF-8");
            byte ptext5[] = ScreenJson.getString("riskTitle").trim().getBytes("ISO-8859-1");
            String riskTitle = new String(ptext5, "UTF-8");
            byte ptext6[] = ScreenJson.getString("infoTitle").trim().getBytes("ISO-8859-1");
            String infoTitle = new String(ptext6, "UTF-8");

            banco.InsertScreen(title,fundName,
                    whatIs,definition,
                    riskTitle, ScreenJson.getInt("risk"),
                    infoTitle);

            int idScreen = banco.getScreenId();

            JSONObject MoreInfoJson;

            MoreInfoJson = new JSONObject(ScreenJson.getString("moreInfo"));

            //Adicionando na tabela MOREINFO
            if(MoreInfoJson.length() > 0){

                banco.Delete("MOREINFO");

                JSONObject Month = new JSONObject(MoreInfoJson.getString("month"));

                if(Month.length() > 0) {

                    banco.InsertMoreInfo(idScreen, Month.getDouble("fund"),Month.getDouble("CDI"),
                            0.00,0.00,0.00,0.00);

                }
                JSONObject Year = new JSONObject(MoreInfoJson.getString("year"));

                if(Year.length() > 0) {

                    banco.InsertMoreInfo(idScreen, 0.00,0.00,
                            Year.getDouble("fund"),Year.getDouble("CDI"),
                            0.00,0.00);

                }
                JSONObject TMonth = new JSONObject(MoreInfoJson.getString("12months"));

                if(TMonth.length() > 0) {

                    banco.InsertMoreInfo(idScreen, 0.00,0.00,
                            0.00,0.00,
                            TMonth.getDouble("fund"),TMonth.getDouble("CDI"));

                }

            }

            //Adicionando na tabela INFO
            JSONArray InfoArray;
            int i;
            InfoArray = new JSONArray(ScreenJson.getString("info"));

            for (i = 0; i < InfoArray.length(); i++) {

                if (i == 0 && InfoArray.length() > 0) {
                    banco.Delete("INFO");
                }

                JSONObject InfoJson = new JSONObject(InfoArray.getString(i));

                byte ptext7[] = InfoJson.getString("name").trim().getBytes("ISO-8859-1");
                String name = new String(ptext7, "UTF-8");

                banco.InsertInfo(idScreen,name,
                        InfoJson.getString("data"));
            }

            //Adicionando na tabela DOWNINFO
            JSONArray DownInfoArray;
            DownInfoArray = new JSONArray(ScreenJson.getString("downInfo"));

            for (i = 0; i < DownInfoArray.length(); i++) {

                if (i == 0 && DownInfoArray.length() > 0) {
                    banco.Delete("DOWNINFO");
                }

                JSONObject DownInfoJson = new JSONObject(DownInfoArray.getString(i));

                byte ptext8[] = DownInfoJson.getString("name").trim().getBytes("ISO-8859-1");
                String name2 = new String(ptext8, "UTF-8");

                banco.InsertDownInfo(idScreen,name2,
                        DownInfoJson.getString("data"));
            }


        }

    }

}
