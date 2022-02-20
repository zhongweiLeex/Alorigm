package graph2;

import java.util.Iterator;
import java.util.Vector;
/*
* 有向无权图
* */
public class Digraph {
    private final int V;
    private int E;
    private Vector<Integer>[] adj;

    public Digraph(int V){
        this.V = V;
        this.E = 0;
        adj =(Vector<Integer>[]) new Vector[V];
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
    * 从v向w 添加一条边
    * */
    public void addEdge(int v,int w){
        adj[v].add(w);
        E++;
    }

    /*
    * 返回v邻接的所有顶点
    * */
    public Iterable<Integer> adj(int v){
        return adj[v];
    }
    /*
    * 构建反向图
    * */
    public Digraph reverse(){
        Digraph R = new Digraph(V);
        for (int v = 0;v<V;v++){
            for (int w : adj(v)) {
                R.addEdge(w, v);
            }
        }
        return R;
    }
}
