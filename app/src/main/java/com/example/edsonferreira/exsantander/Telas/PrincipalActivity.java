package com.example.edsonferreira.exsantander.Telas;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.edsonferreira.exsantander.Background.EnvioMensagem;
import com.example.edsonferreira.exsantander.Background.SincronizaDados;
import com.example.edsonferreira.exsantander.Permissoes.PedePermissao;
import com.example.edsonferreira.exsantander.R;

import java.util.concurrent.ExecutionException;

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
        final View[] VEnvio = new View[1];

        //Layouts que irão inflar
        VInvest[0] = inflater.inflate(R.layout.activity_invest, null);
        VContact[0] = inflater.inflate(R.layout.activity_form, null);
        VEnvio[0] = inflater.inflate(R.layout.activity_enviado, null);


        //Pedindo permissões necessárias
        PedePermissao permiss = new PedePermissao();

        permiss.Permissao(this);

        //Sincronizando dados
        new SincronizaDados(this).execute();

        //Deixando o formulário como o principal
        Fragmento.removeView(VInvest[0]);
        Fragmento.removeView(VContact[0]);
        Fragmento.removeView(VEnvio[0]);

        Fragmento.addView(VContact[0]);


        //Criando seleção para Investimentos e Contato
        Menu.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                        switch (item.getItemId()) {

                            case R.id.TagInvest:

                                Fragmento.removeView(VInvest[0]);
                                Fragmento.removeView(VContact[0]);
                                Fragmento.removeView(VEnvio[0]);

                                Fragmento.addView(VInvest[0]);

                                break;

                            case R.id.TagContatos:

                                Fragmento.removeView(VInvest[0]);
                                Fragmento.removeView(VContact[0]);
                                Fragmento.removeView(VEnvio[0]);

                                Fragmento.addView(VContact[0]);

                                break;

                        }
                        return false;
                    }
                });

        //Setando ocorrência do botão "Enviar" para mostrar que a menssagem foi enviada.
        Button btn_envio = findViewById(R.id.btnEnviar);
        btn_envio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //AsynkTask somente para aguardar 5 segundos e enviar
                new EnvioMensagem(PrincipalActivity.this).execute();


                Fragmento.removeView(VInvest[0]);
                Fragmento.removeView(VContact[0]);
                Fragmento.removeView(VEnvio[0]);

                Fragmento.addView(VEnvio[0]);

                //Quando for nova menssagem, voltar para a tela de envio
                TextView nova = findViewById(R.id.tvNova);

                nova.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Fragmento.removeView(VInvest[0]);
                        Fragmento.removeView(VContact[0]);
                        Fragmento.removeView(VEnvio[0]);

                        Fragmento.addView(VContact[0]);

                    }
                });


            }
        });


    }
}
