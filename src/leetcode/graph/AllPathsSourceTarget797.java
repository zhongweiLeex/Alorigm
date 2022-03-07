package leetcode.graph;

import java.util.LinkedList;
import java.util.List;

/*
 给你一个有 n 个节点的 有向无环图（DAG），请你找出所有从节点 0 到节点 n-1 的路径并输出（不要求按特定顺序）
 graph[i] 是一个从节点 i 可以访问的所有节点的列表（即从节点 i 到节点 graph[i][j]存在一条有向边）。
链接：https://leetcode-cn.com/problems/all-paths-from-source-to-target
* */
public class AllPathsSourceTarget797 {
    List<List<Integer>> result = new LinkedList<>();//记录结果
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        //boolean[] visited;//记录节点是否被遍历过
        LinkedList<Integer> path = new LinkedList<>();//记录起点到当前节点的 路径

        traverse(graph,0,path);
        return result;

    }
    /*
    * graph  邻接表
    * s   开始节点
    * 邻接表遍历
    * */
    private void traverse(int[][] graph,int s,LinkedList<Integer> path){
//        boolean[] visited;//记录节点是否被遍历过
//        boolean[] onPath;//记录起点到当前节点的路径
        //进入
        path.addLast(s);//记录路过的节点
        int n = graph.length;//计算输入的graph邻接表的长度 计算图中总共有多少个节点

        if (s == n-1){
            result.add(new LinkedList<>(path));
            path.removeLast();
            return;
        }
        //递归所有相邻节点
        for (int a : graph[s]) {
            traverse(graph,a,path);
        }
        path.removeLast();
    }
}
