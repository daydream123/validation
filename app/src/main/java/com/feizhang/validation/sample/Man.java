package com.feizhang.validation.sample;

import com.feizhang.validation.annotations.Len;
import com.feizhang.validation.annotations.Max;
import com.feizhang.validation.annotations.Min;
import com.feizhang.validation.annotations.NotBlank;
import com.feizhang.validation.annotations.NotEmpty;
import com.feizhang.validation.annotations.NotNull;
import com.feizhang.validation.annotations.Size;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Man implements Serializable{
    @NotBlank
    String name = "zhang";

    @NotEmpty(message = "childrenMap 为空")
    Map<Integer, Child> childrenMap = new HashMap<>();

    @NotEmpty(message = "childrenArray 为空")
    Child[] childrenArray;

    @NotEmpty(message = "childrenList 为空")
    List<Child> childrenList = new ArrayList<>();

    public static class Child implements Serializable {
        @Len(min = 4, max = 20)
        String name;

        @Size(min = 1, max = 20)
        int age;

        @Min(min = 50)
        int height = 190;

        @NotNull(message = "玩具可以没有，但是不可以没有放玩具的箱子")
        List<Toy> toys;

        Child(String name, int age) {
            this.name = name;
            this.age = age;
            this.toys = new ArrayList<>();
        }

        void setToys(Toy... toys){
            this.toys = Arrays.asList(toys);
        }
    }

    static class Toy implements Serializable{
        @NotBlank(message = "不知道是什么玩具就乱买")
        String toyName;

        @Max(max = 100, message = "价格太贵了")
        float toyPrice;

        @Min(min = 1451567252308L, message = "玩具太旧了")
        long boughtDate;

        Toy(String toyName, float toyPrice, long boughtDate){
            this.toyName = toyName;
            this.toyPrice = toyPrice;
            this.boughtDate = boughtDate;
        }
    }
}