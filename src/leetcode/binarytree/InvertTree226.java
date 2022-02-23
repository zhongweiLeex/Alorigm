package leetcode.binarytree;
/**
 * @author Zhongwei Li
 * @version 2.0
 * */
public class InvertTree226 {
    public TreeNode invertTree(TreeNode root){
        /*
        * 递归出口
        * */
        if (root == null){
            return null;
        }

        /*
        * 交换左右节点
        * */
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp ;

        //左右子树递归
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}
