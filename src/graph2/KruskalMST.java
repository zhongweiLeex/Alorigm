package graph2;

import heap.MinPQ;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/*
* Kruskal 最小生成树算法
* */
public class KruskalMST {
    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightGraph G){
        mst = new LinkedList<Edge>();
        MinPQ<Edge> pq = new MinPQ<Edge>((Comparator<Edge>) G.edges());//将所有 有权重信息的 边 v-w 加入到 优先队列中

        UF uf = new UF(G.V());//创建UF对象
        /*
        * 如果 最小生成树不是空的 且 mst 的容量 小于 G.V() - 1
        * */
        while(!pq.isEmpty() && mst.size() < G.V() - 1){
            Edge e = pq.delMin();//每次从 pq 取出最小的边
            int v = e.either(), w = e.other(v);//取出权重最小的边的 两个顶点
            if (uf.connected(v,w)) continue;//如果v w 已经在同一个连通分量中 了 就不进行操作
            uf.union(v,w);//如果 v w 没有在同一个连通分量中 则进行归并操作 某一个节点作为另一个的父节点 连接
            mst.add(e);//向最小生成树的队列中 添加上述 权重最小 且 顶点不在同一个连通分量中的 边 即 跨集合的两个顶点形成的边
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
