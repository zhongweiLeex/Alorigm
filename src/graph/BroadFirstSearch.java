package graph;

import java.util.LinkedList;
import java.util.Stack;

public class BroadFirstSearch {
    private int[] edgeTo;           //从起点到一个顶点的当前的路径的最后一个顶点
    private boolean[] marked;       //标志是否一个顶点已经被 visited 了
    private int start;              //起点

    /*
     * 邻接表生成器类
     *
     * @param start  标志广度优先搜索的开始点
     * @param graphIterator  邻接矩阵迭代器
     * 顶点下标
     * */
    public BroadFirstSearch(SparseGraphIterator graphIterator, int start) {
        //Queue<Integer> queue = new Queue<Integer>();
        marked = new boolean[graphIterator.getVertex()];
        this.start = start;
        edgeTo = new int[graphIterator.getVertex()];
        bfs(graphIterator, start);
    }

    private void bfs(SparseGraphIterator graphIterator, int start) {
       LinkedList<Integer> queue = new LinkedList<>();//使用linkList 模拟队列

        marked[start] = true;       //标记起点
        queue.add(start);           //将v节点添加到队列中
        while (!queue.isEmpty()) {
            int v = queue.pop();    //出队
            for (int w : graphIterator.adj(start)){//遍历所有邻接节点
                if (!marked[w]){                //对于每个没有遍历过的相邻节点
                    edgeTo[w] = v;              //保存最短路径的最后一条鞭
                    marked[w] = true;           //标记 w 顶点已经遍历过了
                    queue.add(w);               //将遍历过的顶点加到队列中
                }
            }
        }
    }

    /*
     * 检测是否有到达vertex顶点的路径
     * */
    public boolean hasPathTo(int vertex) {
        return marked[vertex];
    }
    /*
    * 功能：找到start 到 任意节点 vertex的路径
    * */
    public Iterable<Integer> pathTo(int vertex) {
        if (!hasPathTo(vertex)) return null;
        Stack<Integer> path = new Stack<>();//创建存储路劲的栈
        //path.push(vertex);
//        vertex = edgeTo[vertex];
//        while (vertex != start) {
//            path.push(vertex);
//            vertex = edgeTo[vertex];
//        }
        for (int x = vertex; x != start;x = edgeTo[x]){
            path.push(x);
        }
        path.push(start);
        return path;
    }
}
