package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.*;

public class DemoTest {
    private static final Random random = new Random();

    @Test
    public void ttt() {
//        Random random = new Random();
        System.out.println(random.nextInt(3));;
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(getMap("QQQREEWR"));
//        System.out.println(repeatedSubstringPattern("bb"));;
//        List<String> list = new ArrayList<>();
//        list.add("1");
//        list.add("2");
//        ListIterator<String> iterator = list.listIterator();
//        while (iterator.hasNext()) {
//            String item = iterator.next();
//            if ("2".equals(item)) {
//                iterator.remove();
//            }
//        }
////        for (String item : list) {
////            if ("2".equals(item)) {
////                list.remove(item);
////            }
////        }
//        System.out.println(list.toString());
    }

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

    public String intToRoman(int num) {
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            if (num >= nums[i]) {
                sb.append(romans[i]);
                num -= nums[i];
            }
        }
        return sb.toString();
    }

    public static int getMap(String s) {
        Map<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char charAt = s.charAt(i);
            count(countMap, charAt);
        }
        int result = 0;
        int num = s.length() / 4;
        for (Integer value : countMap.values()) {
            result += value - num;
        }
        return result;
    }

    private static void count(Map<Character, Integer> countMap, char c) {
        if (countMap.containsKey(c)) {
            countMap.put(c, countMap.get(c) + 1);
        } else {
            countMap.put(c, 1);
        }
    }

    public static boolean repeatedSubstringPattern(String s) {
        for (int i = 2; i <= s.length(); i++) {
            int index = s.length() / i;
            String result = s.substring(0, index);
            for (int j = 2; j <= i; j++) {
                result = result + s.substring(0, index);
                if (result.equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }
}
