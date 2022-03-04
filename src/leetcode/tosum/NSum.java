package leetcode.tosum;

import java.util.ArrayList;
import java.util.List;
/*
* 求 数组中 n个数的和为 target的 列表
* 调用这个递归函数之前 一定要预先给nums进行排序
* */
public class NSum {
    /*
    * nums 数组
    * n 几个数之和 （找几个数）
    * target  目标和
    * start  开始位置
    * */
    public List<List<Integer>> nSum(int[] nums,int n,int target,int start){
        int len = nums.length;
        List<List<Integer>> result = new ArrayList<>();//结果list
        if (n < 2  || len < n){//如果 要找的只是一个数字  或者 目标数组中的个数 小于 要找的个数直接返回

            return result;//返回结果
        }
        //如果要找的就是两个数组 直接处理
        if (n == 2){
            int low = start;
            int high = nums.length-1;
            while (low < high){
                int left = nums[low];
                int right = nums[high];
                int sum = nums[low] + nums[high];
                if (target < sum){
                    while (low < high && nums[high] == right) high--;
                }else if (target > sum){
                    while (low < high && nums[low] == left) low++;
                }
                else{
                    List<Integer> temp = new ArrayList<>();
                    temp.add(left);
                    temp.add(right);
                    result.add(temp);
                    while (low < high && nums[low] == left) low++;
                    while (low < high && nums[high] == right) high--;
                }
            }
//            return result;
        }else{//n>2的时候
            for (int i= start;i< nums.length;i++){
                List<List<Integer>> sub = nSum(nums, n-1, target-nums[i], i+1);
                for (List<Integer> tuple : sub) {
                    tuple.add(nums[i]);
                    result.add(tuple);
                }
                while(i < len && nums[i] == nums[i+1]) i++;
            }
        }
        return result;
    }
}
