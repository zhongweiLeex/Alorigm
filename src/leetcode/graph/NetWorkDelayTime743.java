package leetcode.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class NetWorkDelayTime743 {
    private List<int[]>[] graph;//构建的图
    private int weight;//最短路径

    // k - start 出发节点
    // n 节点个数
    public int networkDelayTime(int[][] times, int n, int k) {
        graph = buildGraph(times,n);//构造一个图

        // 从k节点开始  使用 dijkstra 对graph这个图进行遍历 找到 k 到所有节点的 最短路径
        int[] distTo = dijkstra(graph,k);
        int result = 0;
        for(int i = 1; i< distTo.length ;i++){
            if(distTo[i] == Integer.MAX_VALUE){
                return -1;//不可到达
            }
            result = Math.max(result,distTo[i]);
        }
        return result;



    }
    //构建图
    private List<int[]>[] buildGraph(int[][] times,int n){
        List<int[]>[] graph = new LinkedList[n+1];
        for(int i = 1; i<= n;i++){
            graph[i] = new LinkedList<>();
        }
        for(int[] edge: times){
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            // from : {to,weight}   存储邻接节点  和 from 到 to 的之间的 权重
            graph[from].add(new int[]{to,weight});
        }
        return graph;
    }
    private int[] dijkstra(List<int[]>[] graph,int start){
        int[] distTo = new int[graph.length];//记录到所有节点的距离

        //pq中每个节点存储的 是  int[2]   int[0]--> 节点编号   int[1] --->start 到这个节点的距离
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->a[1]-b[1]);

        Arrays.fill(distTo,Integer.MAX_VALUE);
        distTo[start] = 0;
        pq.offer(new int[]{start,0});

        while (!pq.isEmpty()){
            int[] curNode = pq.poll();
            int curNodeId = curNode[0];
            int curNodeDistFromStart = curNode[1];

            if(curNodeDistFromStart > distTo[curNodeId]){//如果当前节点的距离比 distTo数组中记录的还要大 则不做任何动作
                continue;
            }

            for(int[] neighbor : graph[curNodeId]){//获取邻接节点
                int nextNodeId = neighbor[0];
                int nextNodeDistFromStart = neighbor[1] + distTo[curNodeId];
                if(distTo[nextNodeId] > nextNodeDistFromStart){
                    distTo[nextNodeId] = nextNodeDistFromStart;//更新 distTo[nextNodeId]
                    pq.offer(new int[]{nextNodeId,nextNodeDistFromStart});
                }

            }
            // distTo[curNodeId] = curNodeDistFromStart;

        }
        return distTo;

    }



}
