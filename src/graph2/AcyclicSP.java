package graph2;

import java.util.Stack;

/*
* 无环   加权有向图的最短路径算法   使用拓扑排序
*
* 无环
* 有向
* 加权
* 最短路
*
* 使用拓扑排序
*
* */
public class AcyclicSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public AcyclicSP(EdgeWeightedDigraph G,int s){

        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];

        for (int v = 0;v<G.V();v++){
            distTo[v] = Double.POSITIVE_INFINITY;
        }

        distTo[s] = 0.0;

        Topological top = new Topological(G);

        for (int v : top.order()) {
            relax(G, v);
        }
    }
    /*
    * 加权有向图的 点松弛操作
    * */
//    private void relax(EdgeWeightedDigraph G, int v) {
//
//    }
    /*
    * 带权
    * 有向
    * 无环
    * 点松弛 操作
    * */
    private void relax(EdgeWeightedDigraph G, int v){
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()){
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
            }
        }
    }
    /*
    * 从start 到 v 点 的路径长度
    * */
    public double distTo(int v){
        return distTo[v];
    }

    public boolean hasPathTo(int v){
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v){

        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e!=null;e = edgeTo[e.from()]){
            path.push(e);
        }
        return path;
    }

}
