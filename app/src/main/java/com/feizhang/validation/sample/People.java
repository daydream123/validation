package com.feizhang.validation.sample;

import android.util.ArrayMap;

import com.feizhang.validation.annotations.Len;
import com.feizhang.validation.annotations.NotBlank;
import com.feizhang.validation.annotations.NotEmpty;
import com.feizhang.validation.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class People {
    @NotEmpty
    Map<Integer, Child> map = new HashMap<Integer, Child>(){
        {
            put(1, new Child("hello", 1));
        }
    };

    @NotEmpty
    Child[] children = {new Child("world", 2)};

    @NotEmpty
    List<Child> list = Collections.singletonList(new Child("hell", 3));

    @NotBlank
    String name;
}