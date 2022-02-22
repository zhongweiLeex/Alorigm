package leetcode.binarytree;

import java.util.LinkedList;
import java.util.List;

public class PreOrderTraversal144 {
    List<Integer> results = new LinkedList<>();
    public List<Integer> preorderTraversal(TreeNode root){
        traverse(root);
        return results;
    }
    private void traverse(TreeNode root){
        if (root == null){
            return;
        }
        results.add(root.val);//前序遍历位置

        traverse(root.left);
        traverse(root.right);
    }

    public List<Integer> preorderTraversal1(TreeNode root){
        List<Integer> result = new LinkedList<>();
        if (root == null){
            return null;
        }
        result.add(root.val);
        result.addAll(preorderTraversal1(root.left));
        result.addAll(preorderTraversal1(root.right));
        return result;
    }
}
