package graph;
/*
* 邻接矩阵
* */
public class DenseGraph {
    private static final int MAX_WEIGHT = 1000;

    private int vertexSize;//顶点个数
    private int edgeSize;//边的数量
//    private int[] vertexes;//顶点集合
    private boolean isDirected;//有向图标志位
    private boolean[][] matrix;//邻接矩阵

    /*
    * 构造方法
    * @param vertexSize  顶点个数
    * @param isDirected  有向图标志位
    * */
    public DenseGraph(int vertexSize, boolean directed){
        assert vertexSize>=0;//添加断言 顶点个数>= 0
        this.isDirected = directed;
        this.vertexSize = vertexSize;
        this.edgeSize = 0;//初始化 边数量为0
        matrix = new boolean[vertexSize][vertexSize];//初始化邻接矩阵

    }
    /*
    * 获取顶点数量
    * */
    public int getVertexSize(){
        return vertexSize;
    }
    /*
    * 获取边的数量
    * */
    public int getEdgeSize(){
        return edgeSize;
    }

    /*
    * 向图中添加一个边
    * @param v 节点下标1
    * @param w 节点下标2
    * */
    public void addEdge(int v,int w){
        if (hasEdge(v,w))
            return;
        matrix[v][w] = true;
        if (!isDirected){//如果不是有向图
            matrix[w][v] = true;
        }
        edgeSize++;
    }
    /*
    * 判断 v w 之间是否存在边
    * */
    boolean hasEdge(int v, int w){
        return matrix[v][w];
    }


}
























