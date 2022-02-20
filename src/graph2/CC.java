package graph2;

import graph.SparseGraphIterator;

/*
* 寻找无向图的连通分量
* */
public class CC {
    private boolean[] marked;//用来寻找一个顶点作为每个连通分量中深度优先搜索的起点
    private int[] id;//以顶点作为索引的数组 将同一个连通分量中的顶点和连通分量的标识符关联
    private int count;
    /*
    * 构造函数
    * */
    public CC(Graph G){
        marked = new boolean[G.V()];
        id = new int[G.V()];

        for (int s = 0;s<G.V();s++){
            if (!marked[s]){//如果没有遍历过这个节点就以这个节点为开始节点深度优先遍历 每次遍历一个 都将其深度+1
                            //并且将count 放到id数组中 如果两个节点在同一个连通分量中 则 id[v] == id[count] == count
                dfs(G,s);//递归出来之后
                count++;//递归出来之后 连通分量的个数才会增加
            }
        }
    }

    private void dfs(Graph G,int v){
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean connected(int v,int w){
        return id[v] == id[w];
    }

    public int id(int v){
        return id[v];
    }

    public int count(){
        return count;
    }
}
