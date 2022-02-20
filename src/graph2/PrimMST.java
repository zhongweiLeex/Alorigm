package graph2;

import heap.IndexMinPQ;

import java.util.LinkedList;
import java.util.Queue;
/*
* 不保存所有相邻边的 最小生成树
* */
public class PrimMST {
    private Edge[] edgeTo;          //离树最近的边
    private double[] distTo;       // distTo[w] = edgeTo[w].weight  distTo[] 只存储
    private boolean[] marked;       //如果v在树中 则为 true
    private IndexMinPQ<Double> pq;  //有效的横切边

    public PrimMST(EdgeWeightGraph G){
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        for (int v=0;v<G.V();v++){
            distTo[v]  = Double.POSITIVE_INFINITY;//定义无穷大的权值
        }
        pq = new IndexMinPQ<>(G.V());

        distTo[0] = 0.0;
        pq.insert(0,0.0);//用顶点 0 和权重 0 初始化 pq

        while(!pq.isEmpty())
            visit(G,pq.delMin());
    }
    private void visit(EdgeWeightGraph G,int v){
        marked[v] = true;

        for (Edge edge : G.adj(v)) {
            int w = edge.other(v);
            if (marked[w]) continue;//如果访问过了 就不存储了

            /*
            * 没有访问过 且 将权值最小的放到
            * */
            if (edge.weight() < distTo[w]) { //比如 当 v == 7 的时候 w == 2 的时候  应该比较 7-2 这条边权值 和 distTo[2] 即   0-2 的权值  很明显 0-2 小 所以 直接不参加比较
                edgeTo[w] = edge;//离树最近的边

                distTo[w] = edge.weight();//如果 7-2边的权值 小于 distTo[2] 0-2 的权值 则更新  distTo[2]中的值 为 7-2的值
                //将 所有 w相邻的节点的边按照 weight顺序放入 pq优先队列中
                if (pq.contains(w)) pq.changeKey(w,distTo[w]);//如果 优先队列中已经有了 对应的w 只需要更新其权值
                else                pq.insert(w,distTo[w]);//如果没有 直接加入到 优先队列中
            }
        }
    }
    public Iterable<Edge> edges(){
        Queue<Edge> mst = new LinkedList<>();
        for (int v = 0; v<edgeTo.length;v++){
            Edge e = edgeTo[v];
            if (e!= null){
                mst.add(e);
            }
        }
        return mst;
    }
}
