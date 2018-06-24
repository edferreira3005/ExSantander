package com.example.edsonferreira.exsantander.Background;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;

import com.example.edsonferreira.exsantander.BuscaDados.Busca;
import com.example.edsonferreira.exsantander.R;

public class EnvioMensagem extends AsyncTask<Void, Void, Void> {

    @SuppressLint("StaticFieldLeak")
    private Context tela;
    private ProgressDialog Envia;

    public EnvioMensagem(Context tela){

        this.tela = tela;
        Envia = new ProgressDialog(tela);


    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected  void onPreExecute()
    {

        Envia.setTitle(tela.getResources().getString(R.string.titulo_dialogo));
        Envia.setMessage(tela.getResources().getString(R.string.enviando));
        Envia.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        Envia.show();
        Envia.setCancelable(false);

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        Envia.dismiss();

    }

}
