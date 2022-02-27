package leetcode.binarytree;

import java.util.LinkedList;
import java.util.List;
/**
 * https://leetcode-cn.com/problems/permutations/
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * https://programmercarl.com/%E5%9B%9E%E6%BA%AF%E7%AE%97%E6%B3%95%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html#%E5%9B%9E%E6%BA%AF%E6%B3%95%E6%A8%A1%E6%9D%BF
 * */
public class Permute46 {
    List<List<Integer>> result = new LinkedList<>();
    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        backTrack(nums,track);
        return result;
    }
    //回溯方法
    private void backTrack(int[] nums,LinkedList<Integer> track){
        //如果当前 记录路径的数组长度和 待选数组长度 相同 说明已经选好了 则直接将 记录路径加入到 result中

        if (track.size() == nums.length){//回溯终止条件
            result.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            //如果记录路径中 已经存在了 当前nums中的 待选元素 nums[i] 则直接跳过  因为 不能出现重复的
            if (track.contains(nums[i])){
                continue;
            }
            track.add(nums[i]);//如果记录路径中 不存在 当前的 待选元素 nums[i] 则添加
            //递归进入下一层决策树
            backTrack(nums,track);
            //从当前代决策节点出来 以便做出同层 其他选择
            track.removeLast();
        }
    }
}
