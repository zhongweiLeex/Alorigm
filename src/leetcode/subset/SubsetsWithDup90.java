package leetcode.subset;
/*

输入 有重复 数组 输出无重复的 子集

给你一个整数数组 nums ，其中可能 包含重复元素，请你返回该数组所有可能的子集（幂集）。
解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
链接：https://leetcode-cn.com/problems/subsets-ii
* */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//包含重复元素
public class SubsetsWithDup90 {
    List<List<Integer>> result = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);//对nums排序 如果大于相邻的两个元素相同则在 回溯的时候 跳过
        backTrack(nums,0);
        return result;
    }
    private void backTrack(int[] nums,int start){

        result.add(new LinkedList<>(track));

        for (int i = start; i < nums.length; i++) {

            if (i>start && nums[i]==nums[i-1]){
                continue;
            }
            track.addLast(nums[i]);
            backTrack(nums,i+1);
            track.removeLast();
        }
    }
}
