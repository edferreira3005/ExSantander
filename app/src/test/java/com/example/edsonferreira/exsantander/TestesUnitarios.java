package com.example.edsonferreira.exsantander;

import android.content.res.Resources;

import com.example.edsonferreira.exsantander.BuscaDados.Conexao.Conexao;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestesUnitarios {
    @Test
    public void TestConection() {

        Conexao con = new Conexao();

        String valor = con.BuscaUrl(Resources.getSystem().getString(R.string.url1));


        assertFalse(valor.isEmpty());
    }
}