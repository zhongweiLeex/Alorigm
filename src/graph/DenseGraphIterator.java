package graph;

import java.util.Vector;

/*
* 邻接矩阵迭代
* */
public class DenseGraphIterator implements GraphInterface {
    private int vertexCount;//节点数
    private int edgeCount;//边数
    private boolean directed;//指示是否为有向图
    private boolean[][] dense;//邻接矩阵
    /*
    * 构造方法
    * @param vertexCount 节点数量
    * @param directed   指示是否是有向图
    * */
    public DenseGraphIterator(int vertexCount,boolean directed){
         this.vertexCount = vertexCount;
         this.edgeCount = 0;
         this.directed = directed;

         dense = new boolean[vertexCount][vertexCount];//邻接矩阵初始化 都是 false默认量
    }
    @Override
    public int getVertex() {
        return vertexCount;
    }

    @Override
    public int getEdge() {
        return edgeCount;
    }

    @Override
    public void addEdge(int vertex1, int vertex2) {
        if (hasEdge(vertex1,vertex2))
            return;
        dense[vertex1][vertex2] = true;
        if (!directed)//如果是无向图
            dense[vertex2][vertex1] = true;
        edgeCount++;
    }

    @Override
    public boolean hasEdge(int vertex1, int vertex2) {
        return dense[vertex1][vertex2];//返回是否邻接矩阵相对位置的值是否为 true
    }

    @Override
    public void show() {
        //TODO
    }
    /*
    * 返回图中一个顶点的所有邻边
    * */
    @Override
    public Iterable<Integer> adj(int vertex) {
//        return null;
        Vector<Integer> adjVertex = new Vector<>();
        for (int i = 0; i < vertexCount; i++) {
            if (dense[vertex][i]){
                adjVertex.add(i);
            }
        }
        return adjVertex;
    }

}
