package leetcode.binarytree;

/*
* https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
* */
public class Flatten114 {
    public void flatten(TreeNode root) {
        if (root == null){
            return;
        }
        flatten(root.left);
        flatten(root.right);

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;//根节点的左子节点指向null 但是不用怕 因为 root.left  已经暂存到 left中
        root.right = left;//将根节点的右子结点 指向left

        TreeNode p = root;
        while (p.right != null){
            p = p.right;
        }
        p.right = right;//将原来右子树的 节点全部连接到 现在的 右子树（也就是原来的左子树）右边
    }
}
