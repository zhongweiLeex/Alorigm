package leetcode.binarytree;

public class MaxSumBST1373 {
    /*
    * 判断三点：
    *   1. 当前节点的左右子树是否是合法BST                        左右子树 是否是BST
    *   2. 当前节点的左右子树 + 当前节点作为根节点是否是合法BST       左子树最大值和右子树最小值
    *   3. 当前节点加上 左右子树是合法BST 则 应该统计当前树的键值和    左右子树节点值之和
    *
    * */
    int maxSum =0; //因为题目规定所有节点值为负值的话 最后就返回 0 说明0就是最小值了 不可能是-的了
    public int maxSumBST(TreeNode root) {
        traverse(root);
        return maxSum;
    }

    //函数返回的 int[]{isBST，min,max,sum}
    //int[0]  ---  是否是BST 1：是BST  0：不是BST
    // min    ---  所有节点中的最小值
    // max    ---  所有节点中的最大值
    // sum    ---  所有节点值之和
    int[] traverse(TreeNode root){
        if (root == null){
            return new int[]{1,Integer.MAX_VALUE,Integer.MIN_VALUE,0};
        }
        int[] left = traverse(root.left);
        int[] right = traverse(root.right);

        int[] result = new int[4];
        //要左子树是个BST 右子树也是个BST  而且 root.val 要大于左子树的最大值 小于 右子树的最小值
        if (left[0] == 1 && right[0]==1 && root.val > left[2] && root.val < right[1]){
            //以root为根的二叉树 是 BST
            result[0] = 1;
            //以root为根的BST的最小值是多少
            result[1] = Math.min(left[1],root.val);
            //以root为根的BST的最大值是多少
            result[2] = Math.max(right[2],root.val);
            //以root为根的节点值之和是多少
            result[3] = left[3] + right[3] + root.val;
            //更新 最大键值和
            maxSum = Math.max(maxSum,result[3]);
        }else{
            result[0] = 0;//不满足 上述判断条件的 就不是BST
        }
        return result;
    }
}
