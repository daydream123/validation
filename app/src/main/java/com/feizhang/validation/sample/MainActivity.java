package com.feizhang.validation.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.feizhang.validation.Validator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.validateBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                People people = new People();
                boolean valid = Validator.validate(v.getContext(), people);
                if (valid){
                    // do something like: start new Activity, new Service or call some api
                    Toast.makeText(MainActivity.this, "params are valid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
