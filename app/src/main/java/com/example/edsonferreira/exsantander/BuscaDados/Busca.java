package com.example.edsonferreira.exsantander.BuscaDados;

import android.content.Context;
import android.content.res.Resources;

import com.example.edsonferreira.exsantander.BD.CriaBanco;
import com.example.edsonferreira.exsantander.BuscaDados.Conexao.Conexao;
import com.example.edsonferreira.exsantander.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Busca {

    public void BuscaInfo(Context tela) throws JSONException {

        //Criar Banco de dados antes de tudo.
        CriaBanco banco = new CriaBanco(tela);

        //Buscando dados da URL
        Conexao con = new Conexao();

        //Primeira URL de dados
        String Dados1 = con.BuscaUrl(Resources.getSystem().getString(R.string.url1));

        //Segunda URL de dados
        String Dados2 = con.BuscaUrl(Resources.getSystem().getString(R.string.url2));

        //Inserindo dados na tabela CELLS
        JSONObject cellsJson;

        cellsJson = new JSONObject(Dados1);

        if(cellsJson.length() > 0){

            banco.Delete("CELLS");

            banco.InsertCells(cellsJson.getInt("id"),cellsJson.getInt("type"),
                    cellsJson.getString("message"),cellsJson.getInt("typefield"),
                    cellsJson.getBoolean("hidden"),cellsJson.getDouble("topSpacing"),
                    cellsJson.getInt("show"),cellsJson.getBoolean("required"));

        }

        //Inserindo dados nas tabelas SCREEN,MOREINFO,INFO e DOWNINFO
        //Pegando id da CELLS

        JSONObject ScreenJson;

        ScreenJson = new JSONObject(Dados2);

        //Adicionando na tabela SCREEN
        if(ScreenJson.length() > 0) {

            banco.Delete("SCREEN");

            banco.InsertScreen(ScreenJson.getString("title"), ScreenJson.getString("fundName"),
                    ScreenJson.getString("whatIs"), ScreenJson.getString("definition"),
                    ScreenJson.getString("riskTitle"), ScreenJson.getInt("risk"),
                    ScreenJson.getString("infoTile"));

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

                banco.InsertInfo(idScreen,InfoJson.getString("name"),
                        InfoJson.getString("data"));
            }

            //Adicionando na tabela DOWNINFO
            JSONArray DownInfoArray;
            DownInfoArray = new JSONArray(ScreenJson.getString("downInfo"));

            for (i = 0; i < InfoArray.length(); i++) {

                if (i == 0 && InfoArray.length() > 0) {
                    banco.Delete("DOWNINFO");
                }

                JSONObject DownInfoJson = new JSONObject(DownInfoArray.getString(i));

                banco.InsertDownInfo(idScreen,DownInfoJson.getString("name"),
                        DownInfoJson.getString("data"));
            }


        }

    }

}
