package leetcode.subset;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
*
给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
* https://leetcode-cn.com/problems/subsets/
* */
public class SubSet78 {
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> track = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
        backTrack(nums,0);
        return result;
    }
    private void backTrack(int[] nums,int start){

        result.add(new LinkedList<>(track));//将每次的路径都加入到result中   * 每个节点的值 都是一个子集

        for (int i = start; i < nums.length; i++) {
            track.addLast(nums[i]);//将路径上的节点添加到路径中
            backTrack(nums, i+1);//递归向下遍历
            track.removeLast();//回溯退出
        }
    }

}
