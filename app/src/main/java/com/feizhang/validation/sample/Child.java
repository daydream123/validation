package com.feizhang.validation.sample;

import com.feizhang.validation.annotations.Len;
import com.feizhang.validation.annotations.Min;
import com.feizhang.validation.annotations.Size;

public class Child {
    @Len(min = 4, max = 20)
    String name;

    @Size(min = 1, max = 20)
    int age;

    @Min(min = 170)
    int height = 190;

    Child(String name, int age) {
        this.name = name;
        this.age = age;
    }
}