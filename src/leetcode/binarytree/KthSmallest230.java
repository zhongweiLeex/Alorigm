package leetcode.binarytree;

import leetcode.binarytree.TreeNode;

public class KthSmallest230 {
    /*
    * 方法1
    * */
    int result = 0;
    int nums = 0;
    public int kthSmallest(TreeNode root, int k){
        if (root == null){
            return result;
        }
        kthSmallest(root.left,k);
        nums++;
        if (k == nums){
            result  = root.val;
            return result;
        }
        kthSmallest(root.right,k);
        return result;
    }
/*
* 方法2
* */
    int num =0;
//    int result;
    public int kthSmallest2(TreeNode root, int k) {
        traverse(root,k);
        return result;
    }
    private void traverse(TreeNode root, int k){
        //递归终止条件
        if(root == null){
            return;
        }

        traverse(root.left,k);
        num++;
        if (num == k){
            result = root.val;
            return;
        }
        traverse(root.right,k);
    }
}
