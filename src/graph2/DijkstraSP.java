package graph2;

import heap.IndexMinPQ;

import java.util.Iterator;
import java.util.Stack;
/*
*
*  Dijkstra  有环 有向加权图的 最短路径算法
*  有环
*  有向
*  加权
*  最短路
* */
public class DijkstraSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;//索引优先队列  存放  键 - 权值 对



    /*
    * Dijkstra 最短路算法 的构造方法
    * */
    public DijkstraSP(EdgeWeightedDigraph G,int start){
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];

        pq = new IndexMinPQ<>(G.V());
        //使用 索引优先队列的原因是
        // 索引位置  指代 某个节点  索引的后面的 键 是 到达 这个节点的权重


        for (int v = 0; v<G.V(); v++){
            distTo[v] = Double.POSITIVE_INFINITY;//除了开始节点之外的所有节点初始化 为 无穷大
        }

        distTo[start] =0.0;//只有开始节点的未知 权重设置为  0

        pq.insert(start,0.0);//将 IndexMinPriorityQueue 的 start 索引未知 插入 0.0 键值

        while (!pq.isEmpty()){
            relax(G,pq.delMin());//节点松弛 将 权重最小的 顶点 进行节点松弛
        }
    }




    /*
    * 加权有向图的 点松弛 操作
    * */
    private void relax(EdgeWeightedDigraph G,int v){
        //说明 ： v 当前局部边的 起点  w 是以 v 为 起点 的边的终点
        //当前 从 v 到 w 可能不是走的e 这条边 可能是其他路径
        //但是如果distTo[v] + e(v-w)这条边 比原有路径短  就直接执行松弛操作
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();//获取 e 这条边的 终点 w
            //具体松弛操作  如果 start - w 的路径长度  大于
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;

                if (pq.contains(w)){
                    pq.changeKey(w,distTo[w]);//更新到达 w 节点的  路径长度
                }else{
                    //如果优先队列中没有w 节点相关的  则直接插入相关值
                    pq.insert(w,distTo[w]);
                }
            }
        }
    }

    /*
    * 返回 到达 start 到 v 的最短路径长度
    * */
    public double distTo(int v){
        return distTo[v];
    }
    /*
    * 检测start 到 v 是否具有可达性
    * */
    public boolean hasPathTo(int v){
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
    /*
    * 将存储在 edgeTo[] 数组中的所有路径 e 添加到 path中
    * */
    public Iterable<DirectedEdge> pathTo(int v){
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e!=null; e= edgeTo[e.from()]){
            path.push(e);
        }
        return path;
    }



}
