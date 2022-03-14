package leetcode.arrayquestions;

import java.util.HashMap;
/*
给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。


*/
public class SubarraySum560 {
    public int subarraySum(int[] nums, int k) {
        //存储 key:count      key --> 和为k    count  连续子数组的个数  就是从数组中选几个组成一个小数组
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        int sum = 0;
        int result = 0;
        for(int i = 0; i< nums.length;i++){
            sum += nums[i];
            if(map.containsKey(sum-k)){
                result += map.get(sum-k);
            }
            map.put(sum,map.getOrDefault(sum,0)+1);
        }
        return result;
    }
}
