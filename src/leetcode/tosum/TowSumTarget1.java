package leetcode.tosum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* TwoSum 类型题目思路
* */
public class TowSumTarget1 {
    public List<int[]> towSumTarget(int[] nums,int target){
        List<int[]> result = new ArrayList<>();

        Arrays.sort(nums);//先对目标数组排序
        int low = 0;
        int high = nums.length-1;

        while (low < high){
            int sum = nums[low] + nums[high];
            int left = nums[low];
            int right = nums[high];
            if (target < sum){
                while(low < high && nums[high] == right){//如果当前high指向的元素 和 之前的 一开始的 high指向的元素相等 我们直接跳过
                    high--;
                }
            }else if (target > sum){
                while (low < high && nums[low] == left){
                    low++;
                }
            }else{
                result.add(new int[]{left,right});
                while(low < high && nums[low] == left) low++;
                while(low < high && nums[high] == right) high--;
            }

        }
        return result;
    }
}
