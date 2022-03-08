package leetcode.graph;

import java.util.LinkedList;
import java.util.Queue;

/*
存在一个 无向图 ，图中有 n 个节点。其中每个节点都有一个介于 0 到 n - 1 之间的唯一编号。给你一个二维数组 graph ，
其中 graph[u] 是一个节点数组，由节点 u 的邻接节点组成。形式上，对于 graph[u] 中的每个 v ，
都存在一条位于节点 u 和节点 v 之间的无向边。该无向图同时具有以下属性：
不存在自环（graph[u] 不包含 u）。
不存在平行边（graph[u] 不包含重复值）。
如果 v 在 graph[u] 内，那么 u 也应该在 graph[v] 内（该图是无向图）
这个图可能不是连通图，也就是说两个节点 u 和 v 之间可能不存在一条连通彼此的路径。
二分图 定义：如果能将一个图的节点集合分割成两个独立的子集 A 和 B ，
并使图中的每一条边的两个节点一个来自 A 集合，一个来自 B 集合，就将这个图称为 二分图 。
如果图是二分图，返回 true ；否则，返回 false 。

链接：https://leetcode-cn.com/problems/is-graph-bipartite
*/
public class IsBipartite785 {
    //双色图问题
    boolean[] color ;
    boolean[] visited;
    boolean ok = true;
    public boolean isBipartite(int[][] graph) {

        color  = new boolean[graph.length];//记录每个节点的颜色
        visited = new boolean[graph.length];//记录每个节点是否被遍历过了

        for(int i = 0; i< graph.length;i++){
            if(!visited[i]){//如果这个节点没有被访问过 则 看看 这个节点的相邻节点是否被访问过了
                traverse(graph,i);
            }
        }
        return ok;
    }
    private void traverse(int[][] graph,int i){
        if(!ok){
            return;
        }
        for(int j : graph[i]){
            //如果没有被访问过  这个节点应该涂相邻节点不一样的颜色
            if(!visited[j]){
                color[j] = !color[i];
                visited[j] = true;//标志j节点被访问过了
                traverse(graph,j);//对图继续遍历
            }else{
                //如果i相邻接节点被访问过了 则检查是否和相邻节点的颜色是否一样  如果相同 则不是二分图
                if(color[j] == color[i]){
                    ok = false;
                }
            }
        }
    }
    /*
    * BFS算法
    * */
    boolean result = true;
    public boolean isBipartiteBFS(int[][]  graph){
        visited = new boolean[graph.length];
        color = new boolean[graph.length];

        for(int i = 0;i<graph.length;i++){
            if(!visited[i]){
                bfs(graph,i);
            }
        }
        return result;
    }
    private void bfs(int[][] graph,int i){
        Queue<Integer> q = new LinkedList<>();//创建队列
        visited[i] = true;
        q.offer(i);
        while(!q.isEmpty()&&result){
            int current = q.poll();//从队列中拿出一个
            for(int j:graph[current]){
                if(!visited[j]){
                    color[j] = !color[current];
                    visited[j] = true;
                    q.offer(j);

                }else{
                    if(color[j] == color[current]){
                        result = false;
                    }
                }
            }
        }
    }


}
