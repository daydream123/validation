package com.feizhang.validation.sample;

import com.feizhang.validation.annotations.NotEmpty;
import com.feizhang.validation.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class People {
    @NotNull(message = "children is null")
    @NotEmpty
    List<Child> children = new ArrayList<>();

    @NotNull
    Child[] array;
}
