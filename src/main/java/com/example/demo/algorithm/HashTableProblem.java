package com.example.demo.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xurui
 * @description 哈希表相关
 * @date 2023/2/14 17:20
 */
public class HashTableProblem {

    public static void main(String[] args) {
//        System.out.println(isAnagram("abbbbnagram", "nabgarbbbam"));
    }

    /**
     * 有效的字母异位词
     *
     * 给定两个字符串s和t，编写一个函数来判断t是否是s的字母异位词
     * 输入: s = "anagram", t = "nagaram" 输出: true
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram(String s, String t) {
        int[] record = new int[26];

        for (int i = 0; i < s.length(); i++) {
            int j = s.charAt(i) - 'a';
            // 并不需要记住字符a的ASCII，只要求出一个相对数值就可以了
            record[j]++;
        }
        for (int i = 0; i < t.length(); i++) {
            record[t.charAt(i) - 'a']--;
        }
        for (int count: record) {
            // record数组如果有的元素不为零0，说明字符串s和t 一定是谁多了字符或者谁少了字符。
            if (count != 0) {
                return false;
            }
        }
        // record数组所有元素都为零0，说明字符串s和t是字母异位词
        return true;
    }


    /**
     * 两个数组的交集
     *
     * 给定两个数组，编写一个函数来计算它们的交集
     * nums1=[1,2,2,1] nums2=[1,1,3] -> [1]
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> resSet = new HashSet<>();
        //遍历数组1
        for (int i : nums1) {
            set1.add(i);
        }
        //遍历数组2的过程中判断哈希表中是否存在该元素
        for (int i : nums2) {
            if (set1.contains(i)) {
                resSet.add(i);
            }
        }

        //方法1：将结果集合转为数组

        return resSet.stream().mapToInt(x -> x).toArray();

//        //方法2：另外申请一个数组存放setRes中的元素,最后返回数组
//        int[] arr = new int[setRes.size()];
//        int j = 0;
//        for(int i : setRes){
//            arr[j++] = i;
//        }
//
//        return arr;
    }


    /**
     * 快乐数
     *
     * 编写一个算法来判断一个数 n 是不是快乐数。
     * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，
     * 也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。
     * 如果 n 是快乐数就返回 True ；不是，则返回 False
     *
     * 输入：19
     * 输出：true
     * 解释：
     * 1^2 + 9^2 = 82
     * 8^2 + 2^2 = 68
     * 6^2 + 8^2 = 100
     * 1^2 + 0^2 + 0^2 = 1
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        Set<Integer> record = new HashSet<>();
        while (n != 1 && !record.contains(n)) {
            record.add(n);
            n = getNextNumber(n);
        }
        return n == 1;
    }

    private int getNextNumber(int n) {
        int res = 0;
        while (n > 0) {
            int temp = n % 10;
            res += temp * temp;
            n = n / 10;
        }
        return res;
    }
}
