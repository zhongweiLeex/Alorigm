package leetcode.binarytree;

public class DeleNode {
    /*
    * BST 删除节点方法
    * */
    public TreeNode deleteNode(TreeNode root, int key){

        if (root == null){
            return null;
        }
        //如果当前节点 符合要找的目标值 则做以下分类
        if (root.val == key){

            //处理 左节点为空 非叶子节点 只有 右子树不为空
            if (root.left == null){
                return root.right;//使用右子树代替 原来被删除的节点位置
            }
            //处理 右节点为空 非叶子节点 只有  左子树不为空
            if (root.right == null){
                return root.left;//使用左子树代替 原来被删除的节点位置
            }


            //第三种情况 非叶子节点 左右两个子结点双全  使用右子树中最小的节点来代替 被删除的root节点
            TreeNode rightMinNode = getMin(root.right);//获取右子树中最小的节点
            root.right = deleteNode(root.right, rightMinNode.val);//删除右子树中最小节点 拿到目标删除的节点位置

            //左右子树指向原来 root的左右子树
            rightMinNode.left = root.left;
            rightMinNode.right = root.right;
            root = rightMinNode;

        }else if (root.val > key){//去左子树中寻找
            root.left = deleteNode(root.left,key);//如果目标值 小于当前root值 则在左子树中寻找并删除
        }else {//去右子树中寻找
            root.right = deleteNode(root.right,key);//如果目标值 大于当前 root值 则在 右子树中寻找并删除
        }
        return root;
    }
    //获取最小节点
    private TreeNode getMin(TreeNode root){
        while(root.left != null){
            root = root.left;//一直往左边遍历  最左边的 就是 BST的最小节点
        }
        return root;
    }
}
