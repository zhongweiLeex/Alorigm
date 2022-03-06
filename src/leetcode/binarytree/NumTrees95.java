package leetcode.binarytree;

import java.util.LinkedList;
import java.util.List;

/*
* 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树
* 可以按 任意顺序 返回答案。  //一定要合法 合法 就是  满足 BST   左小右大的规则
* */
public class NumTrees95 {
    public List<TreeNode> generateTrees(int n) {
        if (n==0) return new LinkedList<>();
        return build(1,n);

    }
    private List<TreeNode> build(int low,int high){
        List<TreeNode> result = new LinkedList<>();
        //base case  递归出口
        if (low > high){
            result.add(null);
            return result;
        }
        //穷举所有root可能
        for (int i = low; i <=high ; i++) {
            //递归构造左右子树
            List<TreeNode> left = build(low,i-1);
            List<TreeNode> right = build(i+1,high);

            //穷举左右节点
            for (TreeNode leftNode : left){//leftNode 表示直接与root相连的左孩子节点
                for (TreeNode rightNode : right) {//rightNode表示直接与root相连的右孩子节点
                     TreeNode root = new TreeNode(i);
                     root.left = leftNode;
                     root.right = rightNode;
                     result.add(root);
                }
            }
        }
        return result;
    }
}
