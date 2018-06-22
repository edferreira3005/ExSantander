package com.example.edsonferreira.exsantander.BuscaDados;

import android.content.res.Resources;

import com.example.edsonferreira.exsantander.BuscaDados.Conexao.Conexao;
import com.example.edsonferreira.exsantander.R;

public class Busca {


    //Buscando dados da URL

    Conexao con = new Conexao();

    //Primeira URL de dados
    String Dados1 = con.BuscaUrl(Resources.getSystem().getString(R.string.url1));

    //Segunda URL de dados
    String Dados2 = con.BuscaUrl(Resources.getSystem().getString(R.string.url2));

}
