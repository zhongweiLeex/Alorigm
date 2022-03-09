package leetcode.graph;

import java.util.List;

/*
给定从 0 到 n-1 标号的 n 个结点，和一个无向边列表（每条边以结点对来表示），请编写一个函数用来判断这些边是否能够形成一个合法有效的树结构。

注意：你可以假定边列表 edges 中不会出现重复的边。由于所有的边是无向边，边 [0,1] 和边 [1,0] 是相同的，因此不会同时出现在边列表 edges 中。

链接：https://leetcode-cn.com/problems/graph-valid-tree
* */


public class ValidTree261 {

    /*
    * 方法1 ： 使用并查集  这题的 中心思想 就是  图和树的不同          图 中可以存在环   树中不能存在环     且最后 树 中只能有一个连通分量
    *
    * 判定条件：
    *           1. 联通后不能 存在环  也就是已经在联同分量中的 节点 不能再次加入连通分量
    *           2. 树到最后 只能有一个连通分量
    *
    * */
    int[] parent;
    int count;
    public boolean validTree(int n, int[][] edges) {
        count = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        for (int[] edge : edges) {
            if (isConnected(edge[0], edge[1])) {
                return false;
            }
            union(edge[0], edge[1]);//将同一个无向边的两个节点   再在并查集中合并
        }
        return count()==1;
    }
    private void union(int x,int y){
        if (findParent(x)!=findParent(y)){
            parent[findParent(x)] = findParent(y);
        }
        count--;
    }
    private int findParent(int p){
        while(p != parent[p]){
            parent[p] = parent[parent[p]];//路径压缩
            p = parent[p];
        }
        return p;
    }
    private boolean isConnected(int x , int y){
        return findParent(x) == findParent(y);
    }
    private int count(){
        return count;
    }
}
