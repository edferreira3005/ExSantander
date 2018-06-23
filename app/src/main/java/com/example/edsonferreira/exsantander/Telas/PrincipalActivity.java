package com.example.edsonferreira.exsantander.Telas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.edsonferreira.exsantander.Background.SincronizaDados;
import com.example.edsonferreira.exsantander.Permissoes.PedePermissao;
import com.example.edsonferreira.exsantander.R;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //Pedindo permissões necessárias
        PedePermissao permiss = new PedePermissao();

        permiss.Permissao(this);

        //Sincronizando dados
        new SincronizaDados(this).execute();
    }
}
