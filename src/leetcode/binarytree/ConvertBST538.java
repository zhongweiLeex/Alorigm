package leetcode.binarytree;

public class ConvertBST538 {
    int sum=0;
    public TreeNode convertBST(TreeNode root) {
        if(root == null){
            return null;
        }
        convertBST(root.right);//注意此处 是先遍历了 右子树 这样就是 降序遍历  如此就能实现node的新值 等于原树种大于等于node.val的值之和
        sum = sum + root.val;//使用一个外部变量 sum来维护
        root.val = sum;
        convertBST(root.left);
        return root;
    }
}
