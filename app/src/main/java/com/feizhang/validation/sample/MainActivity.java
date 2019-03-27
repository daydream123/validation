package com.feizhang.validation.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.feizhang.validation.Validator;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.validateBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecondActivity.Params params = new SecondActivity.Params();
                params.man = new Man();
                params.man.name = "张三";

                params.man.childrenArray = new Man.Child[] {new Man.Child("hello world", 10)};
                params.man.childrenList.add(new Man.Child("hello world", 8));

                Man.Child child = new Man.Child("hello world", 8);
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.YEAR, -5);

                child.setToys(new Man.Toy("toy1", 999F, calendar.getTimeInMillis()));
                params.man.childrenMap.put(1, child);
                params.startActivity(v.getContext(), params);
            }
        });
    }
}
