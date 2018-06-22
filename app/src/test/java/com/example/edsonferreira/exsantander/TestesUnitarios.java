package com.example.edsonferreira.exsantander;

import android.content.res.Resources;

import com.example.edsonferreira.exsantander.BuscaDados.Conexao.Conexao;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestesUnitarios {
    @Test
    public void TestConection() {

        Conexao con = new Conexao();

        String valor = con.BuscaUrl("https://floating-mountain-50292.herokuapp.com/cells.json");


        assertFalse(valor.isEmpty());
    }
}