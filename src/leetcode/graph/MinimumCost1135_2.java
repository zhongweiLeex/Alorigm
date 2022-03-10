package leetcode.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/*
* Prim 算法实现
* */
public class MinimumCost1135_2 {

    //存放边
    PriorityQueue<int[]> pq;
    boolean[] visited;
    List<int[]>[] graph;
    int weightSum = 0;
    public int minimumCost(int n ,int[][] connections){
        pq = new PriorityQueue<>( (a,b) -> a[2]-b[2] );
        graph = buildGraph(connections,n);//构造出一副加权无向图
        visited = new boolean[graph.length];

//        int weightSum = 0;
        cut(0);
        visited[0] = true;
        while (!pq.isEmpty()){
            int[] edge = pq.poll();
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            if (visited[to]){
                continue;
            }
            //如果to没有被拜访过
            weightSum = weight + weightSum;
            visited[to] = true;
            cut(to);
        }
        //如果最小生成树无法覆盖所有节点 则失败
        if (!allConnected()){
            return -1;
        }
        return weightSum;
    }

    private List<int[]>[] buildGraph(int[][] connections,int n){
        List<int[]>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int[] edge : connections) {
            int from = edge[0] - 1;//注意 题目中给出的城市编号是从1开始
                                    //但是 我们实现的prim是从0 开始的
            int to = edge[1] - 1;
            int weight = edge[2];
            //无向图 其实就是双向图
            graph[from].add(new int[]{from, to, weight});
            graph[to].add(new int[]{to, from, weight});
        }
        return graph;
    }

    private void cut(int n){
        for (int[] edge : graph[n]) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            if (!visited[to]){//如果  to这个点没有被拜访过  就将这个横切边加入pq优先队列中
                pq.offer(edge);
            }
        }

    }
    private boolean allConnected(){
        for (boolean b : visited) {
            if (!b){
                return false;
            }
        }
        return true;
    }
}
