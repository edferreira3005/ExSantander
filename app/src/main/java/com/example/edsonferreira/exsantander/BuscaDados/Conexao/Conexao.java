package com.example.edsonferreira.exsantander.BuscaDados.Conexao;

import android.os.StrictMode;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.IOException;

public class Conexao {
    private static final long TIMEOUT = 30000;
    private static final int TIMEOUT_CONEXAO = 30000;
    private static final int TIMEOUT_SO = 30000;

    public String BuscaUrl(String url) {

        //Setando parâmetros de conexão
        HttpParams Parametros = new BasicHttpParams();

        ConnManagerParams.setTimeout(Parametros, TIMEOUT);
        HttpConnectionParams.setConnectionTimeout(Parametros, TIMEOUT_CONEXAO);
        HttpConnectionParams.setSoTimeout(Parametros, TIMEOUT_SO);

        HttpClient cliente = new DefaultHttpClient(Parametros);

        HttpGet BuscaUrl = new HttpGet(url);

        String retorno = "";

        try {

            //Setando política e handler de resposta
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);

            ResponseHandler<String> HandlerResposta = new BasicResponseHandler();

            //Requisitando a URL e pegando seu conteúdo
            String resposta = cliente.execute(BuscaUrl,
                    HandlerResposta);

            retorno = resposta;

            //Retornando o conteúdo da URL
            return retorno;


        } catch (ClientProtocolException e) {
            e.printStackTrace();

            retorno = "";
            return retorno;

        } catch (IOException e) {
            e.printStackTrace();

            retorno = "";
            return retorno;
        } catch (Throwable t) {

            retorno = "";
            return retorno;
        }


    }
}
