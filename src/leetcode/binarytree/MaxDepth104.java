package leetcode.binarytree;

/**
 * @author Zhongwei Li
 * @version 2
 * @apiNote 2.22.2022 求最大深度
 *
 * */
public class MaxDepth104 {
    /*
    * 动态规划
    *
    * */
    public int maxDepth(TreeNode root){
        if (root == null){
            return 0;
        }
        int leftMaxDepth = maxDepth(root.left);
        int rightMaxDepth = maxDepth(root.right);
        return 1+Math.max(leftMaxDepth,rightMaxDepth);
    }
    /*
    * 回溯
    * */
    int result = 0;
    int depth=0;
    public int maxDepth1(TreeNode root){
        traverse(root);
        return result;
    }
    private void traverse(TreeNode root){
        //到达叶子节点  更新最大深度
        if (root == null){
            result = Math.max(result,depth);
            return;
        }
        //没有到达叶子节点
        depth++;

//        assert root != null;
        traverse(root.left);//遍历左子树
        traverse(root.right);//遍历右子树
        depth--;
    }
}
