package leetcode.subset;

import java.util.LinkedList;
import java.util.List;

/*
* 输入一组无重复数 返回全排列
* k个数字的全排列 (n=>k>=0 )
*
* */
public class PermuteK1 {
    List<List<Integer>> result = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();
    boolean[] used;

    public List<List<Integer>> permuteK(int[] nums,int k){
        used = new boolean[nums.length];
        backTrack(nums,k);
        return result;
    }

    private void backTrack(int[] nums,int k) {
        if (track.size() == k){
            result.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]){
                continue;
            }
            track.addLast(nums[i]);
            used[i] = true;
            backTrack(nums, k);
            track.removeLast();
            used[i] = false;
        }
    }

}
