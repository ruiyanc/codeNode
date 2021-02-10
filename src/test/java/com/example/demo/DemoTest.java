package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DemoTest {

    @Test
    public void test01() {
        List<String> list = new ArrayList<String>() {{
            add("111");
            add("这是第二行");
            add("333");
        }};
        StringBuilder stringBuilder = new StringBuilder();

        for (String s : list) {
            stringBuilder.append(s);
            stringBuilder.append(",");
        }

        System.out.println(list.toString().replace("[", "(").replace("]", ")"));
        System.out.println(stringBuilder.substring(0, stringBuilder.lastIndexOf(",")));
    }
}
