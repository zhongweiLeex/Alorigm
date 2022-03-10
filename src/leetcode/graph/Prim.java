package leetcode.graph;

import java.util.List;
import java.util.PriorityQueue;

public class Prim {
    private boolean[] visited;//记录节点是否被使用过了
    // 存储横切边的的优先级队列 里面其实 可以设置成动态排好序了
    private PriorityQueue<int[]> pq;//存储横切边 三个部分 横切边起点  横切边终点 权重
    private int weightSum = 0;//最小树的权重和
    private List<int[]>[] graph;//表示图  邻接表表示


    public Prim(List<int[]>[] graph) {
        this.graph = graph;
        this.pq = new PriorityQueue<>((a,b) -> a[2] - b[2]);//lambda表达式 优先队列 按照权重大小排序

        int n = graph.length;//图中有多少个节点

        this.visited = new boolean[n];

        cut(0);//从第0 个划分
        visited[0] = true;
        while (!pq.isEmpty()){
            int[] edge = pq.poll();
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            if (visited[to]){
                continue;
            }
            visited[to] = true;
            weightSum = weightSum + weight;//总权重
            cut(to);//从下一个终点开始切分

        }


    }
    private void cut(int x){
        for (int[] edge : graph[x]) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            if (!visited[to]){//如果相邻节点没有被拜访过  就说明这条边可以加入pq中
                //如果被拜访过了  再加入的话 会产生环
                pq.offer(edge);
            }
        }
    }
    public int getWeightSum(){
        return weightSum;
    }
    //判断最小生成树是否覆盖所有节点
    public boolean allConnected(){
        for (boolean b : visited) {
            if (!b) {
                return false;
            }
        }
        return true;
    }
}
