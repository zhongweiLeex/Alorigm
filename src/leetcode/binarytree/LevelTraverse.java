package leetcode.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class LevelTraverse {
    /*
    * 使用迭代 + 队列数据结构 层序遍历
    * */
    public List<List<Integer>> levelTraverse(TreeNode root){
        if (root == null){
            return new ArrayList<>();
        }

        List<List<Integer>> result = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);//先将根节点入队

        while (!queue.isEmpty()){
            int size = queue.size();

            List<Integer> tempList = new LinkedList<>();

            for (int i = 0; i < size; i++) {

                TreeNode currentNode = queue.poll();//目前遍历的节点出队

                assert currentNode != null;
                tempList.add(currentNode.val);//将结果存入tempList中

                if (currentNode.left !=null){//将目前节点的左子节点入队
                    queue.offer(currentNode.left);
                }
                if (currentNode.right !=null){//将目前节点的右子节点入队
                    queue.offer(currentNode.right);
                }
            }
            result.add(tempList);
        }
        return result;
    }
}
