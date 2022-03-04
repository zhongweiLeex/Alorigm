package leetcode.subset;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
*
* 输入 有重复 数组  输出 和为 target的 组合
给定一个候选人编号的集合 candidates 和一个目标数 target ，
找出 candidates 中所有可以使数字和为 target 的组合。
candidates 中的每个数字在每个组合中只能使用 一次。
注意：解集不能包含重复的组合。
链接：https://leetcode-cn.com/problems/combination-sum-ii
* */
public class CombinationSum40 {
    List<List<Integer>> result = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();
    int trackSum = 0;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //先排序 让两个相同的元素 靠在一起
        Arrays.sort(candidates);
        backTrack(candidates,0,target);
        return result;
    }
    private void backTrack(int[] candidates,int start,int target){
        if (trackSum == target){//找到了对应 符合加起来值等于 target要求的 candidate
            result.add(new LinkedList<>(track));
            return;
        }
        if (trackSum > target){//如果找到的值 加起来 > target 则 直接退出 因为这个不符合按要求
            return;
        }
        //当 trackSum < target 的时候
        for (int i = start; i < candidates.length ; i++) {
            if ( i > start && candidates[i] == candidates[i-1] ){
                continue;
            }
            track.add(candidates[i]);
            trackSum += candidates[i];
            backTrack(candidates,i+1,target);
            track.removeLast();
            trackSum-= candidates[i];
        }
    }

    /*
    * 第二种回溯方法尝试
    * */
    private void backTrack2(int[] candidates,int start,int target){
        if (target < 0){
            return;
        }
        if (target == 0){
            result.add(new LinkedList<>(track));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i>start && candidates[i] == candidates[i-1]){
                continue;
            }
            track.add(candidates[i]);
            target = target - candidates[i];//目前的目标变小了
            backTrack2(candidates,i+1,target);
            track.removeLast();
            target = target+candidates[i];
        }
    }
}





































