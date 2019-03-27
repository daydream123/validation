package com.feizhang.validation.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                SecondActivityParams params = new SecondActivityParams();
                params.name = "张三";

                params.childrenArray = new SecondActivityParams.Child[] {new SecondActivityParams.Child("hello world", 10)};
                params.childrenList.add(new SecondActivityParams.Child("hello world", 8));

                SecondActivityParams.Child child = new SecondActivityParams.Child("hello world", 8);
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.YEAR, -5);

                child.setToys(new SecondActivityParams.Toy("toy1", 999F, calendar.getTimeInMillis()));
                params.childrenMap.put(1, child);
                params.startActivity(v.getContext(), params);
            }
        });
    }
}
