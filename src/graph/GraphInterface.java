package graph;

public interface GraphInterface {
    public int getVertex();
    public int getEdge();
    public void addEdge(int vertex1,int vertex2);
    boolean hasEdge(int vertex1,int vertex2);
    void show();
    public Iterable<Integer> adj(int vertex);//邻接表/邻接矩阵迭代器
}
