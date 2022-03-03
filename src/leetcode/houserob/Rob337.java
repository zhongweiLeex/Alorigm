package leetcode.houserob;

import java.util.HashMap;

public class Rob337 {
    HashMap<TreeNode,Integer> memo = new HashMap<>();//创建备忘录
    public int rob(TreeNode root) {
        if (root == null){
            return 0;
        }

        if (memo.containsKey(root)){//如果出现重复  则使用备忘录中的值
            return memo.get(root);
        }

        int robCurrent = root.val +
                ( root.left == null ? 0:
                        rob(root.left.left) + rob(root.left.right))
                + ( root.right == null ? 0:
                        rob(root.right.left) + rob(root.right.right));
        int noRobCurrent = rob(root.left) + rob(root.right);
        int result = Math.max(robCurrent , noRobCurrent);
        memo.put(root,result);
        return result;
    }

    public int rob2(TreeNode root) {
        int[] result = dp(root);
        return Math.max(result[0],result[1]);
    }
    private int[] dp(TreeNode root){
        if(root == null){
            return new int[]{0,0};
        }
        // int[] result = new int[2];
        int[] left = dp(root.left);
        int[] right = dp(root.right);

        int RobCurrent = root.val + left[0] + right[0];
        int noRobCurrent = Math.max(left[0],left[1]) + Math.max(right[0]+right[1]);
        return new int[]{noRobCurrent,RobCurrent};

    }
