package leetcode.subset;

import java.util.LinkedList;
import java.util.List;

/*
* 输入一组无重复数 返回全排列
* */

public class Permute41 {
    List<List<Integer>> result = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();
    boolean[] used;
    public List<List<Integer>> permute(int[] nums){
        used = new boolean[nums.length];
        permuteTrack(nums);
        return result;
    }
    private void permuteTrack(int[] nums){
        if (track.size() == nums.length){
            result.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {


            /**********   剪  枝  位  置     *********/

            if (used[i]){//已经有了 就直接下一个
                continue;
            }

            /***************************************/

            track.addLast(nums[i]);
            used[i] = true;
            permuteTrack(nums);
            track.removeLast();//撤退 取消选择
            used[i] = false;
        }
    }
}
