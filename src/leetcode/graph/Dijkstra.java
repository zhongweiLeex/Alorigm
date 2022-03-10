package leetcode.graph;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class State{
    int id;//图节点id
    int distFromStart;//从start 到当前节点的最短距离

    public State(int id, int distFromStart) {
        this.id = id;
        this.distFromStart = distFromStart;
    }
}
public class Dijkstra {
    List<Integer>[] graph;

    //输入一图，和 起点 start 计算 start 到其他所有节点的最短距离
    public int[] dijkstra(int start, List<Integer>[] graph){
//  求 start 到 指定的end 之间的最短路径长
//  public int[] dijkstra(int start, int end ,List<Integer>[] graph){
        int n = graph.length;//图中的节点个数

        //从 start 到各个节点的最短路径
        int[] distanceTo = new int[n];//定义最短路径的每条边的权重 从0 开始 到 n-1结束

        //存放节点  按照start 节点 到当前节点的 最短路径排序  小的排在前面
        PriorityQueue<State> pq = new PriorityQueue<>((a,b) -> a.distFromStart - b.distFromStart);

        //因为是求最小值  所以将最短路径的所有边初始化 最大值
        Arrays.fill(distanceTo,Integer.MAX_VALUE);


        //start 到 自己  start  的距离当然是  0 了
        distanceTo[start] = 0;//dijkstra 不允许出现负值


        //bfs
        pq.offer(new State(start,0));//从start 开始入队

        while (!pq.isEmpty()) {
            State node = pq.poll();//将队列中的一个节点出队

            //获取当前节点的id 和 distFromStart
            int currentNodeId = node.id;
            int currentNodeDistanceFromStart = node.distFromStart;

/*            if (currentNodeId == end){// 输入start 和 end 计算起点到终点的最短距离
                        return currentNodeDistanceFromStart;

                 }
*/


            if (currentNodeDistanceFromStart > distanceTo[currentNodeId]){
                //已经有 一条 更短的路径到达现在的节点了
                continue;
            }
            //如果现在最短 路径就是  start 到当前节点  currentNodeId
            //扩撒 bfs
            for (int nextNodeId : adj(currentNodeId)) {
                //获取 start 到  nextNode 节点的  最短路径
                int nextNodeDistanceFromStart = distanceTo[currentNodeId] + getWeight(currentNodeId,nextNodeId);
                //一个节点 可能被遍历多次  所以需要多次比较
                if (nextNodeDistanceFromStart < distanceTo[nextNodeId]){
                    distanceTo[nextNodeId] = nextNodeDistanceFromStart;
                    //将nextNode 这个节点加入到 队列中
                    pq.offer(new State(nextNodeId, nextNodeDistanceFromStart));
                }
            }
        }
        return distanceTo;//返回 start 节点 到 图中各个节点的最短路径长
    }

    // 获取 节点s 的所有相邻节点
    // 不同的图  存储邻接节点的方式也不一样  需要视实际情况而定
    private List<Integer> adj(int s){
        return graph[s];//输入节点s 输出s的相邻节点
    }


    /*
    * 返回两个节点之间的权重
    * 各个题目 对于两点之间的权重的存储方式不一样  所以 这里需要 按照实际情况 确定
    * */
    private int getWeight(int from , int to){
        //TODO
        return 0;
    }
}
