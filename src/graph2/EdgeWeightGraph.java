package graph2;

import java.util.Vector;
/*
*     加权无向图数据类型
* */
public class EdgeWeightGraph {
    private final int V;// number of vertices
    private int E;//number of edges
    private Vector<Edge>[] adj;//adjacent table

    public EdgeWeightGraph(int V){
        this.V = V;
        this.E = 0;
        adj = (Vector<Edge>[]) new Vector[V];
        for (int v= 0;v<V;v++){
            adj[v] = new Vector<Edge>();
        }
    }
//    public EdgeWeightGraph(In in)
    public int E(){
        return E;
    }
    public int V(){
        return V;
    }

    public void addEdge(Edge edge){
        int v = edge.either(), w = edge.other(v);
        adj[v].add(edge);
        adj[w].add(edge);
        E++;
    }

    public Iterable<Edge> adj(int v){
        return adj[v];
    }
    /*
    * 遍历图的所有边
    * */
    public Iterable<Edge> edges(){
        Vector<Edge> b = new Vector<>();
        for (int v = 0; v<V;v++){
            for (Edge e : adj[v]) {
                if (e.other(v) > v)//此判断用来忽略自环
                    b.add(e);
            }
        }
        return b;
    }
}
