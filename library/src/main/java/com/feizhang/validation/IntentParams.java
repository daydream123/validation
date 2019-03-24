package com.feizhang.validation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import java.io.Serializable;

public class IntentParams implements Serializable {
    public static final String EXTRA_INTENT_PARAMS = "intentParams";

    private Class<?> clazz;
    private String action;
    private Bundle bundle;

    public IntentParams(Class<?> clazz){
        this.clazz = clazz;
    }

    public IntentParams(String action){
        this.action = action;
    }

    public void setBundle(Bundle bundle){
        this.bundle = bundle;
    }

    Intent toIntent(Context context){
        Intent intent = new Intent();
        if (clazz != null){
            intent.setClass(context, clazz);
            intent.putExtra(EXTRA_INTENT_PARAMS, this);
        } else if (!TextUtils.isEmpty(action)){
            intent.setAction(action);
            intent.putExtra(EXTRA_INTENT_PARAMS, this);
        }

        return intent;
    }

    Bundle getBundle() {
        return bundle;
    }
}
