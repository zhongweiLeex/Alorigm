package leetcode.binarytree;



import java.util.LinkedList;
import java.util.Queue;

public class MinDepth111 {
    //使用 bfs搜索 广度优先搜索
    public int minDepth(TreeNode root){
        if (root == null) return 0;//null条件
        Queue<TreeNode> q = new LinkedList<>();//使用队列 实现bfs

        q.offer(root);//首先将 root节点加入到 队列中
        int depth =1; //本身的深度 就是 1

        while (!q.isEmpty()){
            int size = q.size();//算出当前队列中有多少个元素
            for (int i = 0; i < size; i++) {
                TreeNode current = q.poll();//将队列中的元素 依次出队 并操作

                assert current != null;
                //对当前节点的周围节点 进行判断和操作
                if (current.left == null && current.right == null){//当前广度搜索的终止条件就是找到了叶子节点 说明结束了
                    return depth;
                }
                if (current.left != null){
                    q.offer(current.left);//将当前节点的左子节点入队
                }
                if (current.right!=null){
                    q.offer(current.right);//将当前节点的 右子节点 入队
                }
            }
            depth++;//深度加1  说明 到了下一个层
        }

        return depth;
    }

/*
* 使用递归方法 DFS 深度优先搜素
* */
    public int minDepth2(TreeNode root) {
        if (root == null){
            return 0;
        }
        if(root.left == null && root.right!=null){
            return 1+minDepth2(root.right);
        }
        if(root.right == null && root.left !=null){
            return 1+minDepth2(root.left);
        }
        int leftMinDepth = minDepth2(root.left);
        int rightMinDepth = minDepth2(root.right);
        return 1+Math.min(leftMinDepth,rightMinDepth);
    }
}
