package leetcode.binarytree;

/*

给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，
并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~2h 个节点。
链接：https://leetcode-cn.com/problems/count-complete-tree-nodes

* */
public class CountNodes222 {
//    int result = 0;
    public int countNodes(TreeNode root) {

        /*
        * 普通二叉树 计算节点个数 直接遍历就行
        if (root == null){
            return 0;
        }
        int left = countNodes(root.left);
        int right = countNodes(root.right);
        result = left + right+1;
        return result;
         */


        /*
        * 满二叉树 ： 节点要么是叶子节点 要么有两个子结点
        * 满二叉树的节点个数与  高度相关    2*height -1
        int h = 0;
        //计算树的高度
        while (root !=null){
            root = root.left;
            h++;
        }
        return (int)Math.pow(2,h) +1
        */

        /*
        * 完全二叉树  ： 深度为h   除了第h层以外  其他各层节点都达到最大数， 第h层 所有的节点都连续集中在最左边
        * */
        TreeNode left = root,right = root;
        int hl = 0;//记录左子树高度
        int hr = 0;//记录右子树高度

        while (left!=null){
            left = left.left;
            hl++;
        }
        while (right!=null){
            right = right.right;
            hr++;
        }
        // 如果左右子树高度 相同  则说明 这个是个 满二叉树
        if (hl == hr){
            return (int)Math.pow(2,hl) -1;
        }else{//如果不是 左右子树高度相同  则说明这是个 完全二叉树   可以直接按照普通二叉树 统计节点方法统计节点
            return 1+countNodes(root.left) + countNodes(root.right);
        }



    }


}
