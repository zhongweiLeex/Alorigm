package graph2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
* 基于队列的 Bellman-Ford 算法
*
* 一般加权有向图中的最短路径问题 （具有负权重的图的最短路径）
*
* 当 加权有向图中 至少存在一条 从 s 到 v 的有向路径
* 并且 s 到 v 的有向路径上的 所有顶点
* 所有顶点都不在 负权重环 中  则
* s 到 v 的最短路径才存在
* */
public class BellmanFordSP {
    private double[] distTo;            //从起点到某个顶点的路径长度
    private DirectedEdge[] edgeTo;      //从起点到某个顶点的最后一条边
    private boolean[] onQueue;          //该顶点是否存在于队列中 onQueue 指示 某顶点是否在 放松顶点队列中
    private Queue<Integer> queue;       //正在被放松的顶点  就是 与 某个顶点相邻的几个顶点在队列中
    private int cost;                   //relax() 松弛方法 被调用的次数
    private Iterable<DirectedEdge> cycle;//edgeTo[] 中 是否有 负权重环 如果有 则返回环  没有 直接return null
    /*
    * 构造方法
    *
    * */
    public BellmanFordSP(EdgeWeightedDigraph G, int start){
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onQueue = new boolean[G.V()];
        queue = new LinkedList<Integer>();

        for (int v=0;v<G.V();v++){
            distTo[v] = Double.POSITIVE_INFINITY;//初始化所有的节点距离为  无穷大
        }
        distTo[start] = 0.0;

        queue.add(start);//开始节点 加入 放松队列中
        onQueue[start] = true;//设置 start 节点在 放松队列中
        /*
        * 当 队列 不为空
        *
        * 并且
        *
        * start 节点 到 当前节点  的路径上的所有节点  都不在  负权重环 上的时候
        * */
        while (!queue.isEmpty() && !this.hasNegativeCycle()){
            //如果从queue出来的顶点 放松操作后 存在负权值环 则说明不可以继续放松该操作了
            int v= queue.remove();//将队列中的 最顶部的 元素 出队
            onQueue[v] = false;//出队 之后 将 对应的 指示位置设置未 false
            relax(G,v);//对 v顶点进行 松弛操作  最开始 就是 对  start 节点 做 relax操作
        }
    }

    /*
    * 边松弛操作
    * */
    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {//遍历 以 v作为开始节点的 边
            int w = e.to();//去到  与 v 节点形成边 的  另一个顶点 w
            //对 w 顶点进行松弛操作
            if (distTo[w] > e.weight() + distTo[v]) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;

                //如果 w 顶点 不在 松弛顶点队列中 则将 w 顶点放入queue 队列中
                if (!onQueue[w]) {
                    queue.add(w);
                    onQueue[w] = true;
                }

            }

            if (cost++ % G.V() == 0) {//如果当前顶点被松弛操作的个数 是 顶点数量的 整数倍
                findNegativeCycle();//周期性的检查  到 w 顶点的路径 是否存在 负权值环
            }
        }
    }


    public double distTo(int v){
        return distTo[v];
    }
    public boolean hasPathTo(int v){
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
    /*
    * 返回到v节点的路径
    * */
    public Iterable<DirectedEdge> pathTo(int v){
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<>();//path 这条 Stack中 包含的是
        for (DirectedEdge e = edgeTo[v]; e!=null; e = edgeTo[e.from()]){
            path.push(e);
        }
        return path;
    }
    /*
    * 返回负权有向环
    * */
    public Iterable<DirectedEdge> negativeCycle(){
        return cycle;
    }
    /*
    * 检测是否含有 负权重环
    * */
    public boolean hasNegativeCycle(){
        return cycle !=null;
    }
    /*
    *
    * 找到 负权重环
    * 如果没有 则返回 null
    *
    * 负权值有向环  只要最终的 权重和 是负的 才是 负权值有向环
    *
    *
    * 首先  先找到环
    * 之后  判断环的权重和 是否是 负的
    * */
    private void findNegativeCycle(){
        int V = edgeTo.length;//从起点到 某个指定节点的 中间的节点个数
        EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);//创建 有 V 个节点的 加权有向边
        for (int v = 0; v<V;v++){
            if (edgeTo[v] != null){
                spt.addEdge(edgeTo[v]);
            }
        }
        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(spt);//构建一个 加权有向环对象
        cycle = finder.cycle();//找到当前 加权有向图中的加权有向环

        Iterator<DirectedEdge> iterator = cycle.iterator();
        double weight = 0.0;
        while(iterator.hasNext()){
            weight += iterator.next().weight();
        }
        if (weight >= 0){//如果最后的权值是 非负  就不存在负权值有向环
            cycle = null;
        }
    }

}
