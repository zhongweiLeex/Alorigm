package leetcode.binarytree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class TraversWithStack {

    List<TreeNode> result = new LinkedList<>();//存储遍历结果
    Stack<TreeNode> stack = new Stack<>();

    public List<TreeNode> traverse(TreeNode root){
        //记录最近一次遍历完的子树根节点，（最近一次pop出栈的节点）
        TreeNode visited = new TreeNode(-1);//定义一个 记录最后一次遍历的节点的 功能性节点


        if (root == null){
            return null;
        }
        //左子树节点入栈
        pushLeftBranch(root);

        while(!stack.isEmpty()){
            TreeNode p = stack.peek();

            //visited == p.left  说明 左子树已经遍历完成
            //visited == p.right 说明 左右子树都已经遍历完成

            if ((p == null || p.left == visited) && p.right!= visited){
                /*****************************/
                /*        中序遍历位置         */
                // result.add(p);
                /*****************************/
                pushLeftBranch(p.right);//右子树入栈
            }

            if (p.right == null || p.right == visited){
                /*****************************/
                /*        后序遍历位置         */
                // result.add(p);
                /*****************************/
                visited = stack.pop();//记录最后一次 遍历完子树根节点
            }
        }

        return result;
    }
    private void pushLeftBranch(TreeNode p){
        if (p !=null){
            /*******************/
            /*  前序遍历位置代码  */
            // result.add(p);
            /*******************/
            stack.push(p);
            p = p.left;
        }
    }
}

/*
public List<Integer> traverse(TreeNode root) {
    pushLeftBranch(root);

    while (!stk.isEmpty()) {
        TreeNode p = stk.peek();

        if (p 的左子树被遍历完了) {
            /----------------/
            / 中序遍历代码位置 /
            /----------------/
            // 去遍历 p 的右子树
            pushLeftBranch(p.right);
}

        if (p 的右子树被遍历完了) {
                /------------------------------/
                / 后序遍历代码位置 /
                /------------------------------/
                // 以 p 为根的树遍历完了，出栈
                stk.pop();
            }
        }
   }

private void pushLeftBranch(TreeNode p) {
        while (p != null) {
        /------------------------------/
        / 前序遍历代码位置 /
        /------------------------------/
        stk.push(p);
        p = p.left;
    }
*/