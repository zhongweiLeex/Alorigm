package leetcode.binarytree;
/**
 * @author Zhongwei Li
 * @apiNote https://leetcode-cn.com/problems/maximum-binary-tree/submissions/
 * */
public class ConstructMaximumBinaryTree654 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums,0, nums.length-1);
    }
    private TreeNode build(int[] nums,int low,int high){
        if (low > high){
            return null;
        }

        int index = -1;
        int max = Integer.MIN_VALUE;

        for (int i = low; i <=high ; i++) {
            if (max < nums[i]){
                index = i;//找到最大的节点的索引 并依次划分
                max = nums[i];//最大节点
            }
        }
        TreeNode root = new TreeNode(max);//将找到的最大节点 作为根节点开始构造
        root.left = build(nums,low,index-1);//递归构造root的左子树
        root.right = build(nums,index+1,high);//递归构造 root的右子树
        return root;
    }
}
