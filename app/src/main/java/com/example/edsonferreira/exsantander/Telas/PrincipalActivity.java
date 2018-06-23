package com.example.edsonferreira.exsantander.Telas;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.edsonferreira.exsantander.Background.SincronizaDados;
import com.example.edsonferreira.exsantander.Permissoes.PedePermissao;
import com.example.edsonferreira.exsantander.R;

public class PrincipalActivity extends AppCompatActivity {
    private ConstraintLayout Fragmento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        BottomNavigationView Menu = findViewById(R.id.Menu);

        //As telas aparecerão dentro deste layout
        Fragmento = findViewById(R.id.Fragmento);

        final LayoutInflater inflater = LayoutInflater
                .from(getApplicationContext());
        final View[] VInvest = new View[1];
        final View[] VContact = new View[1];

        //Layouts que irão inflar
        VInvest[0] = inflater.inflate(R.layout.activity_invest, null);
        VContact[0] = inflater.inflate(R.layout.activity_form, null);


        //Pedindo permissões necessárias
        PedePermissao permiss = new PedePermissao();

        permiss.Permissao(this);

        //Sincronizando dados
        new SincronizaDados(this).execute();

        //Deixando o formulário como o principal
        Fragmento.addView(VContact[0]);
        Fragmento.removeView(VInvest[0]);

        //Criando seleção para Investimentos e Contato
        Menu.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                        switch (item.getItemId()) {

                            case R.id.TagInvest:

                                Fragmento.removeView(VInvest[0]);
                                Fragmento.removeView(VContact[0]);

                                Fragmento.addView(VInvest[0]);

                                break;

                            case R.id.TagContatos:

                                Fragmento.removeView(VInvest[0]);
                                Fragmento.removeView(VContact[0]);

                                Fragmento.addView(VContact[0]);

                                break;

                        }
                        return false;
                    }
                });
    }
}
