package graph;
/*
* 寻找连通分量
* */
public class CC {
    /*
    * id[] 下标是每个顶点的标号
    * 如果某个顶点属于第i个连通分量 则 id[v] == i
    * i <= count;
    * */
    private boolean[] marked;//数组下标表示每个顶点  true 表示被访问过了
    private int[] id;//以顶点为索引的数组
    private int count;//表示第几个连通分量 连通分量标号

    public CC(SparseGraphIterator sparseGraph){
        marked = new boolean[sparseGraph.getVertex()];
        id = new int[sparseGraph.getVertex()];
        for ( int s = 0; s< sparseGraph.getVertex();s++){
            if (!marked[s]){
                //如果没有访问过
                dfs(sparseGraph,s);//深度优先搜索
                count++;
            }
        }
    }
    private void dfs(SparseGraphIterator sparseGraphIterator, int vertex){
        marked[vertex] = true;
        id[vertex] = count;
        for (int w : sparseGraphIterator.adj(vertex)) {//遍历所有邻接点
            if (!marked[w])//如果没有被访问过 直接对这个进行深度优先搜索
                dfs(sparseGraphIterator,w);
        }
    }
    /*
    * 判断  v w 是否连通
    * */
    public boolean connected(int v,int w){
        return id[v] == id[w];
    }

    public int id(int v){
        return id[v];
    }
    public int getCount(){
        return count;
    }
}
