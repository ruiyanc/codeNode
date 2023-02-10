package com.example.demo.algorithm;

/**
 * @author xurui
 * @description 二分查找，针对有序数组找出目录数字
 * @date 2023/2/9 15:25
 */
public class ArrayProblem {
    public static void main(String[] args) {
        int[] nums = {1,3,4,8,10,11,15,17,24};
        System.out.println(binarySearchOne(nums, 11));
    }


    /**
     * 1.二分查找，左闭右闭[left,right]
     * while (left <= right) 要使用 <= ，因为left == right是有意义的，所以使用 <=
     * if (nums[middle] > target) right 要赋值为 middle - 1，
     * 因为当前这个nums[middle]一定不是target，那么接下来要查找的左区间结束下标位置就是 middle - 1
     * @param nums
     * @param target
     * @return
     */
    public static int binarySearchOne(int[] nums, int target) {
        // 避免当 target 小于nums[0]或大于 nums[nums.length - 1]时多次循环运算
        if (target < nums[0] || target > nums[nums.length - 1]) {
            return -1;
        }
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1;
            else if (nums[mid] > target)
                right = mid - 1;
        }
        return -1;
    }

    /**
     * 2.二分查找，左闭右开[left, right)
     *  while (left < right)，这里使用 < ,因为left == right在区间[left, right)是没有意义的
     * if (nums[middle] > target) right 更新为 middle，因为当前nums[middle]不等于target，
     * 去左区间继续寻找，而寻找区间是左闭右开区间，所以right更新为middle，即：下一个查询区间不会去比较nums[middle]
     */
    public static int binarySearchTwo(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target){
                return mid;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target){
                left = mid + 1;
            }
        }
        return -1;
    }
}
