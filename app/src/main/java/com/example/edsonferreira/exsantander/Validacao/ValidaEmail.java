package com.example.edsonferreira.exsantander.Validacao;

public class ValidaEmail {

    //Processo de validação do email
    public boolean ValidaEmail(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
