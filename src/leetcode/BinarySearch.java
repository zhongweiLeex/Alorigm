package leetcode;
//702 二分查找
import java.util.Arrays;

public class BinarySearch {
    //二分查找 使用不同参数列表 实现search1 递归实现
     public int search(int[] nums,int target){
         int left = 0;
         int right = nums.length-1;
//     int middle = left + (right - left) / 2;
         return search1(nums,0,right,target);
     }
//普通循环方式
     public int search2(int[] nums,int target) {
         int left = 0;
         int right = nums.length - 1;
         while (right >= left) {
             int middle = left + (right - left) / 2;
             if (nums[middle] < target) {
                 left = middle + 1;
             } else if (nums[middle] > target) {
                 right = middle - 1;
             } else {
                 return middle;
             }
         }
         return -1;
     }

//二分查找 递归实现方式
    public int search1(int[] nums,int start,int end,int target){
        int middle = start + (end - start)/2 ;
        if (nums[middle] == target){
            return middle;
        }
        if (start >= end){
          return -1;
        } else if (nums[middle] < target){
            return search1(nums,middle+1,end,target);
        }else if (nums[middle] > target){
            return search1(nums, start, middle -1, target);
        }
        return -1;
    }
}
