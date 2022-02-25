package leetcode.binarytree;

public class DeleteNode50 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null){
            return null;
        }

        //找到了 当前 root节点为需要被删除的节点
        if(root.val == key){
            //处理 叶子节点 和 只有左子结点的 中间节点 或者 只有 右子节点的 中间节点
            if(root.left == null){
                return root.right;
            }
            if(root.right == null){
                return root.left;
            }
            //处理有两个子结点的内部节点
            TreeNode rightMinNode = getMin(root.right);
            root.right = deleteNode(root.right,rightMinNode.val);//删除右子树中的最小节点

            rightMinNode.left = root.left;
            rightMinNode.right = root.right;
            root = rightMinNode;

        }else if(root.val > key){
            root.left = deleteNode(root.left,key);
        }else{
            root.right = deleteNode(root.right,key);
        }
        return root;
    }
    //找到最小节点
    private TreeNode getMin(TreeNode root){
        while (root.left !=null){
            root = root.left;
        }
        return root;
    }
}
