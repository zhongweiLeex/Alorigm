package graph;

import java.util.Vector;
/*
* 邻接表迭代
* */
public class SparseGraphIterator implements GraphInterface{
    private int vertexCount;//节点数量
    private int edgeCount;//边的数量
    private boolean directed;//判断是否是有向图
    private Vector<Integer>[] sparse;//邻接表

    /*
    * 构造方法
    * @param vertexCount  节点数量
    * @param directed     判断是否是有向图
    *
    * */
    @SuppressWarnings("unchecked")
    public SparseGraphIterator(int vertexCount,boolean directed){
        this.vertexCount = vertexCount;
        this.edgeCount = 0;
        this.directed = directed;
        sparse =(Vector<Integer>[]) new Vector[vertexCount];//初始化 邻接表
        for (int i = 0; i < vertexCount; i++) {
            sparse[i] = new Vector<>();
        }
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
        sparse[vertex1].add(vertex2);
        if (vertex1 != vertex2 && !directed){
            sparse[vertex2].add(vertex1);
        }
        edgeCount++;
    }

    @Override
    public boolean hasEdge(int vertex1, int vertex2) {
        for (int i = 0; i < sparse[vertex1].size(); i++) {
            if (sparse[vertex1].elementAt(i) == vertex2){
                return true;
            }
        }
        return false;
    }

    @Override
    public void show() {
        //TODO
    }

    @Override
    public Iterable<Integer> adj(int vertex) {

        return sparse[vertex];
    }
    /*
    * 向外提供一个获取构造的邻接表的接口
    * */
    public Vector<Integer> getSparse(int index){
        return sparse[index];
    }
}
