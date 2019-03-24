package com.feizhang.validation.sample;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.feizhang.validation.IntentParams;

public class SecondActivity extends AppCompatActivity {

    public static class SendActivityParams extends IntentParams {
        People people = new People();

        public SendActivityParams() {
            super(SecondActivity.class);
        }

        @NonNull
        @Override
        public String toString() {
            return people.toString();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        SendActivityParams params = (SendActivityParams) intent.getSerializableExtra(SendActivityParams.EXTRA_INTENT_PARAMS);
        Toast.makeText(this, "params info : " + params.toString(), Toast.LENGTH_SHORT).show();
    }
}
