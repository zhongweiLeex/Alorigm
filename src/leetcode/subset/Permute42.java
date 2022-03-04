package leetcode.subset;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
* 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
* */

public class Permute42 {
    boolean[] used;
    List<List<Integer>> result = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        used = new boolean[nums.length];
        Arrays.sort(nums);
        backTrack(nums);
        return result;

    }

    private void backTrack(int[] nums){
        if (track.size() == nums.length){
            result.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]){
                continue;
            }
            //固定相同元素在 排列中的相同顺序
            if (i>0 && nums[i-1] == nums[i] && !used[i-1]){
                continue;
            }
            track.add(nums[i]);
            used[i] = true;
            backTrack(nums);
            track.removeLast();
            used[i] = false;

        }

    }
}
