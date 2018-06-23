package com.example.edsonferreira.exsantander.Background;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;

import com.example.edsonferreira.exsantander.BuscaDados.Busca;
import com.example.edsonferreira.exsantander.R;

public class SincronizaDados extends AsyncTask<Void, Void, Void> {

    @SuppressLint("StaticFieldLeak")
    private Context tela;
    private ProgressDialog Sincroniza;

    public SincronizaDados(Context tela){

        this.tela = tela;
        Sincroniza = new ProgressDialog(tela);


    }

    @Override
    protected Void doInBackground(Void... voids) {

        Busca getInfo =new Busca();
        try {
            getInfo.BuscaInfo(tela);
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected  void onPreExecute()
    {

        Sincroniza.setTitle(tela.getResources().getString(R.string.titulo_dialogo));
        Sincroniza.setMessage(tela.getResources().getString(R.string.menssagem_dialogo));
        Sincroniza.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        Sincroniza.show();
        Sincroniza.setCancelable(false);

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        Sincroniza.dismiss();

    }
}
