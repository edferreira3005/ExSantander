package com.example.edsonferreira.exsantander;

import android.Manifest;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.widget.Button;

import com.example.edsonferreira.exsantander.BuscaDados.Conexao.Conexao;
import com.example.edsonferreira.exsantander.Telas.PrincipalActivity;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class TestesUnitarios {

    PrincipalActivity mockTela = Mockito.mock(PrincipalActivity.class);
    PrincipalActivity spyTela = Mockito.spy(PrincipalActivity.class);

    @Test
    public void TestBanco() {

        mockTela.checkSelfPermission(Manifest.permission.INTERNET);

        mockTela.databaseList();


    }

    @Test
    public void url(){

        Conexao mockconect = Mockito.mock(Conexao.class);

        String result="";
        result = mockconect.BuscaUrl("https://floating-mountain-50292.herokuapp.com/cells.json");


    }
    @Test
    public void BtnEnviar(){


        Button btnEnviar = Mockito.spy((Button) spyTela.findViewById(R.id.btnEnviar));

        btnEnviar.performClick();



    }


}