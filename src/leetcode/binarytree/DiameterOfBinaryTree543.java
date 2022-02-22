package leetcode.binarytree;

public class DiameterOfBinaryTree543 {
    int maxDepthMeter =0;
    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return maxDepthMeter;
    }
    private int maxDepth(TreeNode root){
        if (root == null){
            return 0;
        }
        int leftMax = maxDepth(root.left);//递归求左子树的最大深度
        int rightMax = maxDepth(root.right);//递归求右子树的最大深度
        int myDepthMeter = leftMax + rightMax;//后序位置可以获得子树的所有信息 获得所有当前节点的最大深度
        maxDepthMeter = Math.max(maxDepthMeter,myDepthMeter);
        return 1+Math.max(leftMax,rightMax);
    }
}
