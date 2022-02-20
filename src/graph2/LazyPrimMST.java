package graph2;

import heap.MinPQ;

import java.util.LinkedList;
import java.util.Queue;
/*
*
* 保存所有相邻边
* 最小生成树
* */
public class LazyPrimMST {
    private boolean[] marked; //最小生成树的顶点
    private Queue<Edge> mst;//最小生成树的边
    private MinPQ<Edge> pq;//横切边存储的数据结构 使用 最小优先队列

    /*
    * 构造方法
    * */
    public LazyPrimMST(EdgeWeightGraph G){
        pq = new MinPQ<Edge>();
        marked = new boolean[G.V()];
        mst = new LinkedList<Edge>();

        visit(G,0);
        while(!pq.isEmpty()){
            Edge e = pq.delMin();//从pq中得到权重最小的边

            int v = e.either(),w = e.other(v);
            if (marked[v] && marked[w]) continue;//如果已经遍历过了不做任何操作
            mst.add(e);
            if (!marked[v]) visit(G,v);//如果v没有遍历过 对 v节点相邻的结果节点组成的边 遍历
            if (!marked[w]) visit(G,w);
        }
    }

    private void visit(EdgeWeightGraph G,int v){
        marked[v] = true;

        for (Edge edge : G.adj(v)) { //遍历和v所有边
            if (!marked[edge.other(v)])//如果和v之间有边的顶点没有被遍历则 将这个两个顶点之间的边插入到优先队列中
                pq.insert(edge);
        }
    }

    public Iterable<Edge> edges(){
        return mst;
    }

    public double weight(){
        double result = 0;
        for (Edge edge : mst) {
            result += edge.weight();
        }
        return result;
    }
}
