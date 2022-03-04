package leetcode.subset;

import java.util.LinkedList;
import java.util.List;

/*
*
给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
你可以按 任何顺序 返回答案。
* */
public class Combine77 {
    List<List<Integer>> result = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();
    public List<List<Integer>> combine(int n, int k) {
        backTrack(1,n,k);//从1 开始
        return result;
    }

    /*
    * start 开始位置
    * n 总共多少个数字
    * k 子集的容量
    * */
    private void backTrack(int start,int n,int k){
        //如果回溯的容量 已经达到了要求容量 直接结束
        //达到容量要求 之后 将路径 track加入到 结果中 并停止回溯
        if (k == track.size()){
            result.add(new LinkedList<>(track));
            return;
        }
        for (int i = start; i <=n ; i++) {
            track.addLast(i);//将当前节点加入路径
            backTrack(i+1,n,k);//递归 深入
            track.removeLast();//退出  将之前的节点拿出去 返回上一层 撤销选择
        }
    }
}
