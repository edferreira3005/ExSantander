package com.example.edsonferreira.exsantander.Permissoes;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

public class PedePermissao {

    public void Permissao(Context tela)

    {

        //Quais permissoes preciso
        String[] permissoes = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE
        };
        if (!hasPermissions(tela, permissoes)) {

            ActivityCompat.requestPermissions((Activity) tela, permissoes, 1);

        }
    }

    public static boolean hasPermissions(Context context, String... permissoes) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissoes != null) {
            for (String permission : permissoes) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

}
