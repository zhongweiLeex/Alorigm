package leetcode.binarytree;

public class Connect116 {
    public Node connect(Node root) {
        if (root == null){
            return null;
        }

        connectTwoNodes(root.left,root.right);

        return root;
    }
    /*
    * 将两个节点 连接
    * 包括 同一个根节点的左右节点  也包括不在一个根节点的两个节点
    *
    * */
    private void connectTwoNodes(Node node1, Node node2){
        if (node1 == null || node2 == null){
            return;
        }
        //前序位置 在刚进入节点的时候就将两个节点连接起来
        node1.next = node2;

        //递归遍历并连接 相同父结点   节点的左右子树
        connectTwoNodes(node1.left,node1.right);
        connectTwoNodes(node2.left,node2.right);

        //递归遍历并连接  不同父结点的 节点的左右子树
        //而且 都是 左树的 右子节点 连接 右树的 左子节点
        connectTwoNodes(node1.right,node2.left);
    }
}
