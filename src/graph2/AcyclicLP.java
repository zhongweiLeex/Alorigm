package graph2;

import java.util.Stack;

/*
*
* 无环 加权 有向 图 的 最长路径 算法  使用 拓扑排序
*
* 无环
* 有向
* 加权
* 最长路径
*
* 拓扑排序
* */
public class AcyclicLP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public AcyclicLP(EdgeWeightedDigraph G, int start){

        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];

        for (int v = 0; v<G.V();v++){
            distTo[v] = Double.NEGATIVE_INFINITY;// -1.0 / 0.0 表示负无穷  此处的 是 负无穷 不是正无穷
        }
        distTo[start] = 0.0;//初始化 开始节点的 权重和

        Topological top = new Topological(G);

        for (int v : top.order()) {//逆后序排序
            relax(G, v);
        }

    }
    /*
    * 反向松弛操作
    * */
    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (distTo[w] < distTo[v] + e.weight()) {//此处的 不等号 和 顶点松弛操作 不同  取反
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
            }
        }
    }

    public double distTo(int v){
        return distTo[v];
    }
    public boolean hasPathTo(int v){
        return distTo[v] > Double.NEGATIVE_INFINITY;//如果大于 负无穷 说明存在到 v 存在路径
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
