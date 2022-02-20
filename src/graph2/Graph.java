package graph2;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

public class Graph {
    private int V;                  //顶点数目
    private int E;                  //边数
    private Vector<Integer>[] adj;  //邻接表
    /*
    * 构造方法
    * */
    @SuppressWarnings("unchecked")
    public Graph(int V){
        this.V = V;
        this.E = 0;
        adj = (Vector<Integer>[]) new Vector[V];
        for(int v = 0;v<V;v++){
            adj[v] = new Vector<>();
        }
    }
    public Graph(DataInputStream in) throws IOException {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();//读取一个顶点
            int w = in.readInt();//读取另一个顶点
            addEdge(v,w);//添加一条连接两个顶点的边
        }
    }
    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    /*
    * 返回某个顶点所有邻接点
    * */
    public Iterable<Integer> adj(int v){
        return adj[v];
    }
}
