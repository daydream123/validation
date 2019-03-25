package com.feizhang.validation.sample;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.feizhang.validation.IntentParams;

public class SecondActivity extends AppCompatActivity {

    public static class Params extends IntentParams {
        Man man = new Man();

        Params() {
            super(SecondActivity.class);
        }

        @NonNull
        @Override
        public String toString() {
            return man.toString();
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        Params params = (Params) intent.getSerializableExtra(Params.EXTRA_INTENT_PARAMS);
        TextView textView = findViewById(R.id.paramInfoText);
        textView.setText("params info : " + params.toString());
    }
}
