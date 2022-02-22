package leetcode.binarytree;

/**
 * @author Zhongwei Li
 * @version 1
 * @apiNote 2.22.2022 求最大深度
 *
 * */
public class MaxDepth104 {
    public int maxDepth(TreeNode root){
        if (root == null){
            return 0;
        }
        int leftMaxDepth = maxDepth(root.left);
        int rightMaxDepth = maxDepth(root.right);
        return 1+Math.max(leftMaxDepth,rightMaxDepth);
    }
}
