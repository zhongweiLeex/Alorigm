package leetcode.binarytree;

public class BuildTree105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder,0, preorder.length-1,inorder,0,inorder.length-1 );
    }
    private TreeNode build(int[] preorder,int preStart,int preEnd,
                           int[] inorder,int inStart,int inEnd){
        if (preEnd < preStart){
            return null;
        }
        //目前的根节点就是 前序遍历的 起始节点
        int rootVal = preorder[preStart];
        //index 为根据 rootVal 在中序遍历中找到的 root的索引
        int index = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal){
                index = i;
                break;
            }
        }

        int leftSize = index-inStart;//左子树的size  确定了 preEnd就能确定为 preStart+leftSize
        TreeNode root = new TreeNode(rootVal);//根据rootVal 构造一个根节点
        //递归构造左子树
        root.left = build(preorder,preStart+1,preStart + leftSize,inorder,inStart,index-1);
        //递归构造右子树
        root.right = build(preorder,preStart+leftSize+1,preEnd,inorder,index+1,inEnd);

        return root;
    }
}
