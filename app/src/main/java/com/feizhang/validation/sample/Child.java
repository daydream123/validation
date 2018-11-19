package com.feizhang.validation.sample;

import com.feizhang.validation.annotations.Len;
import com.feizhang.validation.annotations.Min;
import com.feizhang.validation.annotations.NotBlank;
import com.feizhang.validation.annotations.NotNull;
import com.feizhang.validation.annotations.Range;

public class Child {
    @NotNull
    @Len(len = 4)
    @NotBlank
    String name;

    @Min(min = 10)
    @Range(min = 1, max = 20)
    int age;

    @Min(min = 170)
    int height = 190;

    Child(String name, int age) {
        this.name = name;
        this.age = age;
    }
}