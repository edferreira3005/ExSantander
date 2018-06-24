package com.example.edsonferreira.exsantander.Telas;

import android.content.res.ColorStateList;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.edsonferreira.exsantander.BD.Consultas;
import com.example.edsonferreira.exsantander.Background.EnvioMensagem;
import com.example.edsonferreira.exsantander.Background.SincronizaDados;
import com.example.edsonferreira.exsantander.Permissoes.PedePermissao;
import com.example.edsonferreira.exsantander.R;
import com.example.edsonferreira.exsantander.Validacao.MascaraTel;
import com.example.edsonferreira.exsantander.Validacao.ValidaEmail;

import org.w3c.dom.Text;

import java.util.concurrent.ExecutionException;

public class PrincipalActivity extends AppCompatActivity {
    private ConstraintLayout Fragmento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        final BottomNavigationView Menu = findViewById(R.id.Menu);

        //As telas aparecerão dentro deste layout
        Fragmento = findViewById(R.id.Fragmento);

        //Setando cores de validação de email
        final ColorStateList corInValid = ColorStateList.valueOf(ContextCompat.getColor(PrincipalActivity.this,
                R.color.btnnormal));

        final ColorStateList corValid = ColorStateList.valueOf(ContextCompat.getColor(PrincipalActivity.this,
                R.color.valid));

        //Pegando resultado das consultas para montagem dos campos
        Consultas consulta = new Consultas(this);
        final Cursor Cells = consulta.ConsultaCells();
        final Cursor Screen = consulta.ConsultaSreen();
        final Cursor MoreInfo = consulta.ConsultaMoreInfo();
        final Cursor InfoDown = consulta.ConsultaInfoDown();

        //Layouts que irão inflar
        final LayoutInflater inflater = LayoutInflater
                .from(getApplicationContext());
        final View[] VInvest = new View[1];
        final View[] VContact = new View[1];
        final View[] VEnvio = new View[1];
        VInvest[0] = inflater.inflate(R.layout.activity_invest, null);
        VContact[0] = inflater.inflate(R.layout.activity_form, null);
        VEnvio[0] = inflater.inflate(R.layout.activity_enviado, null);

        //Abrindo Validador de Email
        final ValidaEmail valid = new ValidaEmail();


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

        //Componentes em uso na tela de Contato
        Menu.setSelectedItemId(R.id.TagContatos);
        final Button btn_envio = findViewById(R.id.btnEnviar);
        final EditText name = findViewById(R.id.edName);
        final CheckBox checkemail = findViewById(R.id.cbEmail);
        final EditText email = findViewById(R.id.edEmail);
        final EditText tel = findViewById(R.id.EdTel);
        final TextView tvnome = findViewById(R.id.tvNome);
        int VisibleField = 1;
        final boolean[] nomeRequired = {false};
        final boolean[] emailRequired = {false};
        boolean telRequired = false;
        Cells.moveToFirst();

        //Colocando valores do banco de dados nos devidos campos
        do{

            switch (Cells.getInt(1)){

                case 1:
                    tvnome.setText(Cells.getString(3));
                    if(Boolean.valueOf(Cells.getString(5))){
                        tvnome.setVisibility(View.INVISIBLE);
                    }
                    break;

                case 2:
                    name.setHint(Cells.getString(3));
                    if(Boolean.valueOf(Cells.getString(5))){
                        name.setVisibility(View.INVISIBLE);
                    }
                    nomeRequired[0] = Boolean.valueOf(Cells.getString(8));

                    break;

                case 3:
                    checkemail.setText(Cells.getString(3));

                    VisibleField = Cells.getInt(7);

                    break;

                case 4:
                    email.setHint(Cells.getString(3));
                    if(Boolean.valueOf(Cells.getString(5))){
                        email.setVisibility(View.INVISIBLE);
                    }
                    emailRequired[0] = Boolean.valueOf(Cells.getString(8));

                    break;

                case 6:
                    tel.setHint(Cells.getString(3));
                    if(Boolean.valueOf(Cells.getString(5))){
                        tel.setVisibility(View.INVISIBLE);
                    }
                    telRequired = Boolean.valueOf(Cells.getString(8));

                    break;

                case 7:
                    btn_envio.setText(Cells.getString(3));
                    if(Boolean.valueOf(Cells.getString(5))){
                        btn_envio.setVisibility(View.INVISIBLE);
                    }
                    break;

            }

        }while(Cells.moveToNext());

        //Validando se quer ou não cadastrar email.
        final int finalVisibleField = VisibleField;
        checkemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkemail.isChecked()){

                    if(finalVisibleField == 1){
                        tvnome.setVisibility(View.VISIBLE);
                    }else if(finalVisibleField == 2){
                        name.setVisibility(View.VISIBLE);
                    }else if(finalVisibleField == 4){
                        email.setVisibility(View.VISIBLE);

                    }else if(finalVisibleField == 6){
                        tel.setVisibility(View.VISIBLE);
                    }else if(finalVisibleField == 7){
                        btn_envio.setVisibility(View.VISIBLE);
                    }

                }

                else{

                    if(finalVisibleField == 1){
                        tvnome.setVisibility(View.INVISIBLE);
                    }else if(finalVisibleField == 2){
                        name.setVisibility(View.INVISIBLE);
                    }else if(finalVisibleField == 4){
                        email.setVisibility(View.INVISIBLE);
                    }else if(finalVisibleField == 6){
                        tel.setVisibility(View.INVISIBLE);
                    }else if(finalVisibleField == 7){
                        btn_envio.setVisibility(View.INVISIBLE);
                    }

                }
            }
        });

        //Criando seleção para Investimentos e Contato
        Menu.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        Menu.setBackgroundResource(R.drawable.menu_check);

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
        //Mudando cor para validação do email
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onFocusChange(View view, boolean b) {

               if(!valid.ValidaEmail(email.getText().toString())){
                   email.setBackgroundTintList(corInValid);
               }else{
                   email.setBackgroundTintList(corValid);
               }

            }
        });

        //Colocando máscara para número de telefone
        MascaraTel mascaraTel = new MascaraTel("(##) #####-####",tel);
        tel.addTextChangedListener(mascaraTel);

        //Setando ocorrência do botão "Enviar" para mostrar que a menssagem foi enviada.
        //Validando campos na hora de enviar.
        final boolean finalTelRequired = telRequired;
        btn_envio.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                if(nomeRequired[0] && name.getText().toString().trim().length() == 0){

                    alerta("Erro","'Nome Completo' é um campo obrigatório!");

                }else if(emailRequired[0]&& email.getText().toString().trim().length() ==0
                        && email.getVisibility() == View.VISIBLE){

                    alerta("Erro","'Email' é um campo obrigatório!");

                }else if(finalTelRequired && tel.getText().toString().trim().length() == 0){

                    alerta("Erro","'Telefone' é um campo obrigatório!");

                }else if(!valid.ValidaEmail(email.getText().toString()) && emailRequired[0] &&
                        email.getVisibility() == View.VISIBLE){

                    email.setBackgroundTintList(corInValid);
                    alerta("Erro","Email inválido!");

                }else if(tel.length() < 14){

                    alerta("Erro","Formato de telefone inválido!");

                }
                    else{
                    email.setBackgroundTintList(corInValid);

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

            }
        });


    }

    //Criando alerta para erros
    private void alerta(String Titulo,String Mensagem){
        AlertDialog.Builder alertar =  new Builder(PrincipalActivity.this);
        alertar.setTitle(Titulo);
        alertar.setMessage(Mensagem);
        alertar.setNeutralButton("Ok",null);
        alertar.show();

    }

}
