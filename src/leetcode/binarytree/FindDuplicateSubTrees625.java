package leetcode.binarytree;

import leetcode.ListNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
/*
* 两个主要组成部分
* 1. 认清自己并存储自己
* 2. 和别人对比
* */
public class FindDuplicateSubTrees625 {
    HashMap<String,Integer> times = new HashMap<>();//用来存储子树
    List<TreeNode> result = new LinkedList<>();//存储所有重复的节点

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        traverse(root);
        return result;
    }
    private String traverse(TreeNode root){
        if (root == null){
            return "#";
        }
        String left = traverse(root.left);//递归遍历左子树
        String right = traverse(root.right);//递归遍历右子树

        //构造出了子树
        String subTree = left + "," + right + "," + root.val;

        //存储子树
        int frequency = times.getOrDefault(subTree,0)+1;
        //无论相同子树重复多少次，都只会被加入到结果集中一次
        if (frequency == 2){
            result.add(root);
        }
        times.put(subTree,frequency);
        return subTree;
    }
}
