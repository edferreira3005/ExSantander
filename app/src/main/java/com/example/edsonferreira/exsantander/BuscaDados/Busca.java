package com.example.edsonferreira.exsantander.BuscaDados;

import android.content.Context;
import android.content.res.Resources;

import com.example.edsonferreira.exsantander.BD.CriaBanco;
import com.example.edsonferreira.exsantander.BuscaDados.Conexao.Conexao;
import com.example.edsonferreira.exsantander.R;

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



    }

}
