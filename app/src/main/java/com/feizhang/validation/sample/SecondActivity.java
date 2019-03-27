package com.feizhang.validation.sample;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        SecondActivityParams params = (SecondActivityParams)
                intent.getSerializableExtra(SecondActivityParams.EXTRA_INTENT_PARAMS);
        TextView textView = findViewById(R.id.paramInfoText);
        textView.setText("params info : " + params.toString());
    }
}
