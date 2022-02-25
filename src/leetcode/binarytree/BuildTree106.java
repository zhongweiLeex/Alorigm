package leetcode.binarytree;

public class BuildTree106 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return build(inorder,0,inorder.length-1,postorder,0, postorder.length-1);
    }
    public TreeNode build(int[] inorder,int inStart,int inEnd,int[] postorder,int postStart,int postEnd){
        if (inStart > inEnd){
            return null;
        }

        int rootVal = postorder[postEnd];//根节点的值 是后序遍历的最后位置的值
        TreeNode root = new TreeNode(rootVal);//构造根节点

        int index=0;//根节点在中序遍历数组中的位置
        for (int i = inStart; i <=inEnd ; i++) {
            if (inorder[i] == rootVal){
                index = i;//找到了 rootVal对应的在中序遍历数组中的位置
                break;
            }
        }

        int leftSize = index - inStart;//左子树的size

        //构造左子树
        root.left = build(inorder,inStart,index-1,postorder,postStart,postStart+leftSize-1);
        //构造右子树
        root.right = build(inorder,index+1,inEnd,postorder,postStart+leftSize,postEnd-1);
        return root;
    }
}
