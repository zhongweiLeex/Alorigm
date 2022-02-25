package leetcode.binarytree;

public class IsValidBST98 {
    boolean result;
    public boolean isValidBST(TreeNode root) {
        return valid(root,null,null);
    }
    public boolean valid(TreeNode root,TreeNode min,TreeNode max){
        if (root == null){
            return true;
        }
        if (min !=null  && root.val <= min.val){
            return false;
        }
        if (max !=null && root.val >= max.val){
            return false;
        }
        boolean res1 = valid(root.left,min,root);//对左子树进行验证， 则 最大的 是 root
        boolean res2 = valid(root.right,root,max);//对右子树进行验证  则 最小的是  root
        return res1 && res2;
    }

    TreeNode max;
    public boolean isValidBST2(TreeNode root){
        if (root == null){
            return true;
        }
        boolean left = isValidBST2(root.left);
        if (!left){
            return false;
        }
        //左边检查通过了 说明当前  root确实是目前最大的节点
        //但是接下来是检查右边了
        //右边的 都比左边的 还大  如果 右边的 当前节点 小于 max的话 说明顺序是错的
        if (max !=null && root.val <= max.val){
            return false;
        }
        max = root;

        boolean right = isValidBST2(root.right);
        if (!right){
            return false;
        }
        //如果右边也通过了 则说明全部通过了
        return true;
    }

}
