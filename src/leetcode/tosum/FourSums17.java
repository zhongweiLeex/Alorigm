package leetcode.tosum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSums17 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;//计算数组长度
        List<List<Integer>> results = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            List<List<Integer>> tuples = threeSum(nums,i+1,target-nums[i]);
            for (List<Integer> tuple: tuples){
                tuple.add(nums[i]);
                results.add(tuple);
            }
            while (i<n-1 && nums[i] == nums[i+1]) i++;
        }
        return results;
    }



    private List<List<Integer>> threeSum(int[] nums,int start,int target){
        Arrays.sort(nums);

        int low = start;
        int high = nums.length-1;
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);//排序
        for (int i = start; i < nums.length; i++) {
            List<List<Integer>> tuples = twoSum( nums, i+1, target-nums[i]);
            for (List<Integer> tuple: tuples){
                tuple.add(nums[i]);
                result.add(tuple);
            }
            while(i< nums.length-1 && nums[i] == nums[i+1]){
                i++;
            }
        }
        return result;
    }
    private List<List<Integer>> twoSum(int[] nums,int start ,int target){
        int low = start;
        int high = nums.length-1;
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);
        while (low < high){
            int left = nums[low];
            int right = nums[high];
            int sum = nums[low]+nums[high];
            if (target < sum){
                while(low < high && nums[high] == right){ high--; }
            }else if (target > sum){
                while (low < high && nums[low] == left){ low++; }
            }else{
                List<Integer> temp = new ArrayList<>();
                temp.add(left);
                temp.add(right);
                result.add(temp);
                while (low <high && nums[low] == left) low++;
                while(low <high && nums[high] == right) high--;
            }
        }
        return result;
    }


}
