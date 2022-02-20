package graph;

import java.util.Stack;

public class DepthFirstSearch {
    private boolean[] isVisited;
    private int[] edgeTo;
    private int count;
    private int start;

    public DepthFirstSearch(SparseGraphIterator sparseGraphIterator, int s){
        isVisited = new boolean[sparseGraphIterator.getVertex()];
        start = s;
        dfs(sparseGraphIterator, s);
    }

    private void dfs(SparseGraphIterator sparseGraphIterator, int indexOfVertex) {
        isVisited[indexOfVertex] = true;
        count++;
        for (int w : sparseGraphIterator.adj(indexOfVertex)) {//spareGraphIterator.getVertex(indexOfVertex) 获取的是 indexOFVertex的相邻节点
            
            if (!isVisited[w]){
                //如果没有被访问过 则访问w下标的索引
                dfs(sparseGraphIterator,w);
                edgeTo[w] = indexOfVertex;
            }
        }
    }
    public boolean visit(int indexOfVertex){
        return isVisited[indexOfVertex];
    }
    public int getCount(){
        return count;
    }

    public Iterable<Integer> pathTo(int vertex){
        Stack<Integer> stack = new Stack<>();
        if (!visit(vertex)) return null;//还没有被访问过的节点 直接返回 null 表示找不到
//        stack.push(vertex);//将经历过的路径上的节点
        Stack<Integer> path = new Stack<>();//创建存储路径的栈
        for (int x = vertex; x!= start;x = edgeTo[x])
            path.push(x);
        path.push(start);
        return path;
    }

}
