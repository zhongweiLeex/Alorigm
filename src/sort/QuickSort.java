package sort;

import java.util.Arrays;

/*
* 快速排序
* 递归实现
* */
public class QuickSort {
    public void quickSort1(int[] nums, int start, int end){
        //递归出口
        if (start >= end){
            return;
        }

        int temp = nums[start];//枢轴 选取最左边的数字作为中心轴

        int left = start;//i为左指针
        int right = end;//j为右指针

        while (left < right){//左右两个指针没有相遇
            while(nums[right] >= temp && left < right){//如果右边的是大于数轴的 则 右指针向左移动
                right--;//右指针向左移动
            }
            nums[left] = nums[right];//右指针找到一个元素比数轴小的 直接
            while(nums[left] <= temp && left < right){
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = temp;//两个指针遇到了 将枢纽值放到当前指针处
        quickSort1(nums, start, left -1);
        quickSort1(nums, left +1, end);
    }

    public static void main(String[] args) {
        int[] array = {0,3,45,45,6,1,2,34};
        QuickSort a = new QuickSort();
        a.quickSort1(array,0,array.length-1);
        System.out.println(Arrays.toString(array));
    }
}
