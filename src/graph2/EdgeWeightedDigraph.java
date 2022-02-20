package graph2;

import java.util.Vector;

/*
* 加权有向图   的   数据类型
* */
public class EdgeWeightedDigraph {
    private final int V;                //顶点数量
    private int E;                      //边数量
    private Vector<DirectedEdge>[] adj; //邻接表
    /*
    * 构造函数
    * */
    public EdgeWeightedDigraph(int V){
        this.V = V;
        this.E = 0;
        adj = (Vector<DirectedEdge>[]) new Vector[V];
        for (int v = 0;v<V;v++){
            adj[v] = new Vector<>();
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }
    /*
    * 添加边
    * 将 e 添加到 有向图中
    * */
    public void addEdge(DirectedEdge e){
        adj[e.from()].add(e); //e这条边的起点 的 邻接表 加入 e这条边
        E++;
    }

    /*
    * 从v开始的所有的边
    * */
    public Vector<DirectedEdge> adj(int v){
        return adj[v];
    }

    /*
    * 有向图中的所有边
    * */
    public Iterable<DirectedEdge> edges(){
        Vector<DirectedEdge> vector = new Vector<>();
        for (int v = 0; v<V;v++){
            vector.addAll(adj[v]);
        }
        return vector;
    }



}
