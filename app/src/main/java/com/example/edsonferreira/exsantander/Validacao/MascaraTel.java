package com.example.edsonferreira.exsantander.Validacao;

/**
 * Created by edson.ferreira on 27/06/2016.
 */
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.edsonferreira.exsantander.Utilidades.Utils;

import java.util.HashSet;
import java.util.Set;

public class MascaraTel implements TextWatcher{

    private static MascaraTel instance;

    private String mMask;
    private EditText mEditText;
    private Set<String> symbolMask = new HashSet<String>();
    private boolean isUpdating;
    private String old = "";

    public MascaraTel(String mask, EditText editText) {
        mMask = mask;
        mEditText = editText;
        initSymbolMask();
    }

    private void initSymbolMask(){
        for (int i=0; i < mMask.length(); i++){
            char ch = mMask.charAt(i);
            if (ch != '#')
                symbolMask.add(String.valueOf(ch));
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String str = Utils.unmask(s.toString(), symbolMask);
        String mascara = "";

        if (isUpdating) {
            old = str;
            isUpdating = false;
            return;
        }

        if(str.length() > old.length())
            mascara = Utils.mask(mMask,str);
        else
            mascara = s.toString();

        isUpdating = true;

        mEditText.setText(mascara);
        mEditText.setSelection(mascara.length());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }


    @Override
    public void afterTextChanged(Editable s) {

    }
}