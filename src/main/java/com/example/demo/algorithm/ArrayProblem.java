package com.example.demo.algorithm;

/**
 * @author xurui
 * @description 数组相关题目
 * @date 2023/2/9 15:25
 */
public class ArrayProblem {
    public static void main(String[] args) {
        int[] nums = {1,3,4,8,10,11,15,17,24};
        System.out.println(binarySearchOne(nums, 11));
    }


    /**
     * 二分查找，针对有序数组找出目录数字
     *
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
     * 二分查找，针对有序数组找出目录数字
     *
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
            }  else if (nums[mid] < target){
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }
        return -1;
    }

    /**
     * 快慢指针
     *
     * 一个数组nums和一个值val，移除所有数值等于val的元素，并返回移除后数组的新长度。
     * 1,2,3,2,2,4,5 val=2 -> 4
     * 移除数组元素
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        //快慢指针
        int slowIndex = 0;
        for (int fastIndex = 0; fastIndex < nums.length; fastIndex++) {
            if (nums[fastIndex] != val) {
                nums[slowIndex] = nums[fastIndex];
                slowIndex++;
            }
        }
        return slowIndex;
    }

    /**
     * 双指针法
     *
     * 有序数组的平方
     * [-4,-1,0,3,5] ->[0,1,9,16,25]
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {
        int right = nums.length - 1;
        int left = 0;
        int[] result = new int[nums.length];
        int index = result.length - 1;
        while (left <= right) {
            if (nums[left] * nums[left] > nums[right] * nums[right]) {
                // 正数的相对位置是不变的， 需要调整的是负数平方后的相对位置
                result[index--] = nums[left] * nums[left];
                ++left;
            } else {
                result[index--] = nums[right] * nums[right];
                --right;
            }
        }
        return result;
    }

    /**
     * 滑动窗口(就是不断的调节子序列的起始位置和终止位置，从而得出我们想要的结果)
     *
     * 给一个含有n个正整数的数组和一个正整数s,找出该数组中满足其和≥s的长度最小的连续子数组
     * s = 7, nums = [2,3,1,2,4,3] 输出：2([4,3])
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        int left = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= s) {
                result = Math.min(result, right - left + 1);
                sum -= nums[left++];
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }


    /**
     * 螺旋矩阵
     *
     * 给定一个正整数n，生成一个包含1到n^2所有元素，且元素按顺时针螺旋排列的正方形矩阵。
     * 输入: 3 -> [[1,2,3],[8,9,4],[7,6,5]]
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int loop = 0;  // 控制循环次数
        int[][] res = new int[n][n];
        int start = 0;  // 每次循环的开始点(start, start)
        int count = 1;  // 定义填充数字
        int i, j;

        while (loop++ < n / 2) { // 判断边界后，loop从1开始
            // 模拟上侧从左到右
            for (j = start; j < n - loop; j++) {
                res[start][j] = count++;
            }

            // 模拟右侧从上到下
            for (i = start; i < n - loop; i++) {
                res[i][j] = count++;
            }

            // 模拟下侧从右到左
            for (; j >= loop; j--) {
                res[i][j] = count++;
            }

            // 模拟左侧从下到上
            for (; i >= loop; i--) {
                res[i][j] = count++;
            }
            start++;
        }

        if (n % 2 == 1) {
            res[start][start] = count;
        }

        return res;
    }
}
