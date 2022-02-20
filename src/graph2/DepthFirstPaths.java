package graph2;

import java.util.Stack;
/*
* 使用深度优先搜索查找图中的路径
* */
public class DepthFirstPaths {
    private boolean[] marked;//是否遍历过这个顶点 即 这个顶点是否调用过 dfs()
    private int[] edgeTo;//edgeTo 数组的作用就是找到从每个与start连通的顶点回到s的路径
                         // 每次添加的都是目前顶点到上一个顶点的路径
    private final int start;
    /*
    * 构造方法
    * */
    public DepthFirstPaths(Graph G,int start){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.start = start;
        dfs(G,start);//构造函数中遍历了 将marked[]数组已经标记了
    }

    /*
    * 深度优先搜索
    * */
    private void dfs(Graph G,int v){
        marked[v] = true;
        for (int w : G.adj(v)) {//w是 v邻接点中的一个 遍历v所有邻接点
            if (!marked[w]){//如果没有遍历过
                edgeTo[w] = v;//记录到达w位置的最后一个节点 v
                dfs(G,w);//递归继续遍历
            }
        }
    }
    /*
    * 检查是否到v是否有路径
    * */
    public boolean hasPathTo(int v){
        return marked[v];
    }
    public Iterable<Integer> pathTo(int v){
        if (!hasPathTo(v)) return null;
        //使用栈 先进后出
        Stack<Integer> path = new Stack<>();
        for (int x = v;x!=start;x = edgeTo[x])
            //从后往前 以知道start 依次放入栈中
            path.push(x);//将所有 edgeTo 中的节点 放入栈中
        path.push(start);//将start也放到栈中
        return path;//返回路径栈
    }
//
//    public static void main(String[] args) {
//        Graph G = new Graph(new )
//    }
}
