package com.example.demo.algorithm;

/**
 * @author xurui
 * @description 排序相关
 * @date 2023/2/9 15:19
 */
import java.util.Arrays;
import java.util.Random;

public class Sort {
    public static void main(String[] args) {
        int[] arr = new int[30];
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            /* arr[i] = (int) (Math.random()*100); */
            arr[i] = r.nextInt(100);
            System.out.print(" " + arr[i]);
        }
        System.out.println(" ");
//        int[] sort = bubbleSort(arr);
//        int[] sort = selectionSort(arr);
//        int[] sort = insertionSort(arr);
//        int[] sort = shellSort(arr);
//        int[] sort = MergeSort(arr);
        //错误
//        int[] sort = heapSort(arr);
        //错误
//        int[] sort = countingSort(arr);
//        for (int i = 0; i < sort.length; i++) {
//            System.out.print(" " + sort[i]);
//        }
        quickSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" " + arr[i]);
        }
    }


    /**
     * 1.冒泡排序，依次比较相邻的两个元素，并置换其大小顺序，O(n^2)
     */
    public static int[] bubbleSort(int []arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }


    /**
     * 2.选择排序，依次选出最小的元素与第一（++）个元素置换位置，O(n^2)
     */
    public static int[] selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }


    /**
     * 3.插入排序，从头开始，取当前元素与左侧比较，
     * 若小，插入顺序，右侧全部后移
     */
    public static int[] insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int preIndex = i - 1;
            int current = arr[i];
            while (preIndex >= 0 && arr[preIndex] >= current) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }
        return arr;
    }


    /**
     * 4.希尔排序，分割成若干个增量序列，分别进行直接插入排序
     */
    public static int[] shellSort(int[] arr) {
        int gap = 1;
        //动态定义间隔序列
        while (gap < arr.length / 3) {
            gap = gap * 3 + 1;
        }
        while (gap >= 1) {
            for (int i = gap; i < arr.length; i++) {
                int temp = arr[i];
                int j = i - gap;
                while ( j >= 0 && arr[j] > temp) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = temp;
            }
            gap /= 3;
        }
        return arr;
    }


    /**
     * 5.归并排序，分治法不断递归地分成两部分，最后归并起来
     */
    public static int[] MergeSort(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }
        int[] left = Arrays.copyOfRange(arr, 0, arr.length / 2);
        int[] right = Arrays.copyOfRange(arr, arr.length / 2, arr.length);
        return merge(MergeSort(left), MergeSort(right));
    }

    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            //  左边数组完毕
            if (i >= left.length) {
                result[index] = right[j++];
                //  右边数组完毕
            } else if (j >= right.length) {
                result[index] = left[i++];
                //  比较两个数组的索引数据大小
            } else if (left[i] > right[j]) {
                result[index] = right[j++];
            } else {
                result[index] = left[i++];
            }
        }
        return result;
    }

    /**
     * 6.快速排序，随机选取数组中的某个数为基准，从两端开始小的在左边，大的在右边，递归排序
     *
     * @param arr 数组
     * @param start 0
     * @param end arr.length - 1
     */
    public static void quickSort(int[] arr, int start, int end) {
        int pivot = 0;
        if (start < end) {
            pivot = partition(arr, start, end);
            quickSort(arr, start, pivot);
            quickSort(arr, pivot + 1, end);
        }
    }

    private static int partition(int[] arr, int start, int end) {
        // 取数组内的随机一个元素为基准
        int pivot = arr[start];
        while (start < end) {
            while (start < end && arr[end] >= pivot) {
                end--;
            }
            arr[start] = arr[end];
            while (start < end && arr[start] <= pivot) {
                start++;
            }
            arr[end] = arr[start];
        }
        arr[start] = pivot;
        return start;
    }

    /**
     * 7.堆排序
     */
    public static int[] heapSort(int[] arr) {
        int len = arr.length;
        if (len < 1) return arr;
        //建立最大的堆
        buildMaxHeap(arr);
        while (len > 0) {
            swap(arr, 0, len - 1);
            len--;
            adjustHeap(arr, 0);
        }
        return arr;
    }

    public static void buildMaxHeap(int[] arr) {
        //从最后一个非叶子结点向上构造最大堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i);
        }
    }

    public static void adjustHeap(int[] arr, int i) {
        int len = arr.length;
        int maxIndex = i;
        //左子树大于父结点的话，则指向左子树
        if (i * 2 < len && arr[i * 2] > arr[maxIndex]) {
            maxIndex = i * 2;
        }
        //同理，指向右子树的情况
        if (i * 2 + 1 < len && arr[i * 2 + 1] > arr[maxIndex]) {
            maxIndex = i * 2 + 1;
        }
        if (maxIndex != i) {
            swap(arr, i, maxIndex);
            adjustHeap(arr, maxIndex);
        }
    }

    /**
     * 8.计数排序
     */
    public static int[] countingSort(int[] arr) {
        if (arr.length == 0) {
            return arr;
        }
        int bias, min = arr[0], max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        bias = -min;
        int[] bucket = new int[max - min + 1];
        Arrays.fill(bucket, 0);
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i] + bias]++;
        }
        return arr;
    }

    /**
     * 交换两个数的位置
     */
    public static void swap(int[] arr, int i, int j) {
        int swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }
    //桶排序
    //基数排序
}
