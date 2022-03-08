package leetcode.graph;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CountComponents323 {
 /*给定编号从 0 到 n-1 的 n 个节点和一个无向边列表（每条边都是一对节点），
 请编写一个函数来计算无向图中连通分量的数目。
 * */


 /*
 * 示例 1:

 输入: n = 5 和 edges = [[0, 1], [1, 2], [3, 4]]

  0          3
  |          |
  1 --- 2    4

 输出: 2
 * */

 /*
 * 输入: n = 5 和 edges = [[0, 1], [1, 2], [2, 3], [3, 4]]

  0           4
  |           |
  1 --- 2 --- 3

  输出:  1*/

    /*
    * 方法1：使用dfs
    * */
    boolean[] visited;

    public int countComponent(int n,int[][] edges){
        //DFS
        visited = new boolean[n];
        int count = 0;
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        //构造一个图
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
        }

        for (int i=0; i<n;i++) {
            if (!visited[i]){
//                visited[i] = true;
                count++;
                dfs(graph,i);
//                bfs(graph,i);
            }
        }

        return 0;
        
    }
    private void dfs(List<Integer>[] graph,int current){
        if (visited[current]){
            return;
        }
        for (int j : graph[current]) {
            visited[j] = true;
            dfs(graph, j);
        }
    }


    private void bfs(List<Integer>[] graph,int i){
        Queue<Integer> q = new LinkedList<>();
        visited[i] = true;
        q.offer(i);
        while(!q.isEmpty()){
            int current = q.poll();
            for (int j: graph[current]) {
                if (!visited[j]){
                    visited[j] = true;
                    q.offer(j);
                }
            }
        }
    }

    int[] parent;
    public  int countComponent1(int n,int[][] edges){
        int count = n;//初始化连通分量数量
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;//根节点初始化 为自己
        }
        for (int[] edge : edges) {
            union(find(parent,edge[0]),find(parent,edge[1]),parent);
            count--;
        }
        return count;
    }
    private int find(int[] parents ,int x){
        while(x != parents[x]){
            parents[x] = parents[parents[x]];//压缩路径
            x = parents[x];
        }
        return x;
    }
    private void union(int p,int q,int[] parents){
        int pRoot = find(parents,p);
        int qRoot = find(parents,q);

        //不分轻重
        if (pRoot != qRoot){
            parents[pRoot] = qRoot;//将 p所在的树 连接到  q所在的树重
        }
    }



}
