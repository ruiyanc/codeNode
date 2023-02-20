package com.example.demo.algorithm;

/**
 * @author xurui
 * @description 哈希表相关
 * @date 2023/2/14 17:20
 */
public class HashTableProblem {

    public static void main(String[] args) {
        System.out.println(isAnagram("abbbbnagram", "nabgarbbbam"));
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
}
