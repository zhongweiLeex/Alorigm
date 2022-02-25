package leetcode.binarytree;
/**
 * @author Zhongwei Li
 * @apiNote https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
 * */
public class ConstructFromPrePost889 {
    /*
    * 只给了前序和后序遍历数组 只能得到根节点 但是无法确定 左右子树有哪些节点
    * */
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {

        return construct(preorder,0, preorder.length-1,postorder,0, postorder.length-1);
    }
    private TreeNode construct(int[] preorder,int preStart,int preEnd,
                               int[] postorder,int postStart,int postEnd){
        //递归终止条件1
        if (preStart > preEnd){
            return null;
        }
        //递归终止条件2 只有一个元素的时候
        if (preStart == preEnd){
            return new TreeNode(preorder[preStart]);
        }

        int leftRootVal = preorder[preStart+1];//根据前序遍历数组的特性 找到当前root值
        int rootVal = preorder[preStart];//根节点值

        //根据当前root值找到 root在后序遍历数组中的 root位置
        int index = 0;
        for (int i = postStart; i <postEnd ; i++) {//因为是后序遍历数组  所以最后的postEnd位置 是 root节点的位置 只消检索前面的就行
            if (postorder[i] == leftRootVal){
                index = i;//找到了 rootVal在后序遍历数组中的 index位置
                break;
            }
        }
        int leftSize = index - postStart +1;//左子树的size
        TreeNode root = new TreeNode(rootVal);//构造当前root节点

        //递归构造左子树
        root.left = construct(preorder,preStart+1,preStart+leftSize,postorder,postStart,index);//index所指的节点是在左子树中的
        //递归构造右子树
        root.right = construct(preorder,preStart+leftSize+1,preEnd,postorder,index+1,postEnd-1);



        return root;
    }
}
