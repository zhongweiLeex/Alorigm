package graph;

import java.util.Vector;

/*
* 邻接表
* */
public class SparseGraph {
    private int vertexCount;//节点数
    private int edgeCount;//边数量
    private boolean isDirected;//判断是否是有向图

    private Vector<Integer>[] graphTables;//使用邻接表 每个向量中的元素表示相邻节点

    /*
    * 构造方法
    * @param vertexCount 节点数量
    * @param isDirected  判断是否是有向图
    * */
    @SuppressWarnings("unchecked")
    public SparseGraph(int vertexCount,boolean isDirected){
        this.vertexCount = vertexCount;
        this.edgeCount = 0;
        this.isDirected = isDirected;
        graphTables = (Vector<Integer>[]) new Vector[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            graphTables[i] = new Vector<>();
        }

    }

    public int getVertexCount(){
        return vertexCount;
    }
    public int getEdgeCount(){
        return edgeCount;
    }
    public void addEdge(int v,int w){
        graphTables[v].add(w);
        //如果不是有向图 就要两边都要加上
        if (v!=w&&!isDirected){
            graphTables[w].add(v);
        }
        edgeCount++;
    }
    boolean hasEdge(int v,int w){
        for (int i = 0; i < graphTables[v].size(); i++) {
            if (graphTables[v].elementAt(i) == w){
                return true;
            }
//        return false;
        }
        return false;
    }
}
