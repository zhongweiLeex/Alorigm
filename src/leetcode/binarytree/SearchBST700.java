package leetcode.binarytree;

/**
 * @author zhongwei li
 * @apiNote leetcode-cn.com/problems/search-in-a-binary-search-tree/
 *
 * */
public class SearchBST700 {
    public TreeNode searchBST(TreeNode root,int val){
        if (root == null){
            return null;
        }
        TreeNode left = searchBST(root.left,val);
        TreeNode right = searchBST(root.right,val);
        if (root.val == val){
            return root;
        }
        if (left == null){
            return right;
        }else {
            return left;
        }
    }
    public TreeNode searchBST2(TreeNode root,int val){
        if (root == null){
            return null;
        }
        //如果当前节点的值比 需要寻找的val的值要大  说明 需要寻找的节点 存在于 BST的左子树中
        if (root.val > val){
            return searchBST2(root.left,val);
        }else if(root.val < val){//如果 当前节点的值 比需要寻找的节点的值小 说明当前需要寻找的目标节点在 BST的右子树中
            return searchBST2(root.right,val);
        }else{
            return root;
        }
    }
}
