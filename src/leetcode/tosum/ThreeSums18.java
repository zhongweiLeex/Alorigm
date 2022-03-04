package leetcode.tosum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSums18 {
    public List<List<Integer>> threeSum(int[] nums) {
        return threeSumTarget(nums,0);
    }

    List<List<Integer>> threeSumTarget(int[] nums,int target){

        Arrays.sort(nums);//对数组从小到大排序
        int len = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            List<List<Integer>> tuples = twoSumTarget(nums,i+1,target-nums[i]);//从i个元素后面找

            //如果存在满足的后面的两元组
            for (List<Integer> tuple : tuples) {
                tuple.add(nums[i]);//将nums[i]加入
                result.add(tuple);
            }
            while(i<len-1 && nums[i] == nums[i+1]){
                //跳过第一个重复的元素 如果数组中下一个元素和目前的元素相等，会出现重复的元素
                i++;
            }
        }
        return result;
    }
    /*
    * 寻找数组中两数之和 等于 target的 的数字 将其加入到  result中
    * */
    List<List<Integer>> twoSumTarget(int[] nums,int start,int target){
        int low = start;
        int high = nums.length-1;
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);//从小到大排序

        while (low < high){
            int left = nums[low];
            int right = nums[high];
            int sum = nums[low] + nums[high];
            if (target < sum){
                while(low < high && nums[high] == right){
                    high--;//指针左移 跳过重复元素
                }
            }else if (target > sum){
                while (low < high && nums[low] == left){
                    low++;//跳过重复元素
                }
            }else{
                List<Integer> temp = new ArrayList<>();
                temp.add(left);
                temp.add(right);
                result.add(temp);
                //即使相等 也要跳过重复元素
                while(low < high && nums[low] == left) low++;//跳过重复元素
                while(low < high && nums[high] == right) high--;//跳过重复元素
            }

        }
        return result;
    }
}
