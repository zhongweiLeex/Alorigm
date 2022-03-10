package leetcode.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/*
给你一个由 n 个节点（下标从 0 开始）组成的无向加权图，该图由一个描述边的列表组成，其中 edges[i] = [a, b] 表示连接节点 a 和 b 的一条无向边，
且该边遍历成功的概率为 succProb[i] 。

指定两个节点分别作为起点 start 和终点 end ，请你找出从起点到终点成功概率最大的路径，并返回其成功概率。

如果不存在从 start 到 end 的路径，请 返回 0 。只要答案与标准答案的误差不超过 1e-5 ，就会被视作正确答案。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/path-with-maximum-probability
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
public class MaxProbability1514 {
    List<double[]>[] graph;
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        graph = buildGraph(n,edges,succProb);//构造一个图
        return dijkstra(start,end,graph);
    }

    //构造图
    public List<double[]>[] buildGraph(int n,int[][] edges,double[] succProb){
//        int m = edges.length;
//        int n = edges[0].length;
        List<double[]>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }
        int i = 0;
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            graph[from].add(new double[]{(double)to,succProb[i]});
            graph[to].add(new double[]{(double)from,succProb[i]});
            i++;
        }
        return graph;
    }
    private double dijkstra(int start,int end ,List<double[]>[] graph){
        double[] possibleTo = new double[graph.length];//存的是最大可能
        //将可能性大的排在前面
        PriorityQueue<double[]> pq = new PriorityQueue<>((a,b)->Double.compare(b[1],a[1]));
        Arrays.fill(possibleTo,-1);

        possibleTo[start] = 1;
        pq.offer(new double[]{(double) start,1});
        while (!pq.isEmpty()){
            double[] cur = pq.poll();
            int curId = (int)cur[0];
            double curPossibleValue = cur[1];

            if (curId == end){
                return curPossibleValue;
            }

            //要找大的
            if (curPossibleValue < possibleTo[curId]){
                continue;
            }
            for (double[] neighbor : graph[curId]) {
                int nextId = (int) neighbor[0];
                double nextPossibleValue = neighbor[1]*possibleTo[curId];
                if (nextPossibleValue > possibleTo[nextId]){
                    possibleTo[nextId] = nextPossibleValue;
                    pq.offer(new double[]{(double) nextId,nextPossibleValue});
                }

            }
        }
        return 0.0;
    }
}
