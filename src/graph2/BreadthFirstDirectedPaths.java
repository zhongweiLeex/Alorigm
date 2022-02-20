package graph2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BreadthFirstDirectedPaths {
    private boolean[] marked;//标记是否被遍历过了
    private int[] edgeTo;//到达某个顶点的最后一个顶点
    private final int start;//开始节点

    /*
     * 构造函数
     * */
    public BreadthFirstDirectedPaths(Graph G, int start){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.start = start;
        bfs(G,start);
    }

    private void bfs(Graph g, int start) {
        Queue<Integer> queue = new LinkedList<>();
        marked[start] = true;
        queue.offer(start);//将开始节点放入栈中
        //如果栈不空 则 继续循环
        while(!queue.isEmpty()){
            int v = queue.poll();//出队
            for (int w : g.adj(v)) {
                //如果没有遍历过 将所有和 v相邻的节点w为下标的edgeTo[]数组元素变为 v
                if (!marked[w]){
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.offer(w);//将w添加到队列中
                }
            }
        }
    }
    public boolean hashPathTo(int v){
        return marked[v];
    }
    public Iterable<Integer> pathTo(int v){
        if (!hashPathTo(v)) return null;

        Stack<Integer> path = new Stack<>();

        for(int x = v;x!=start;x = edgeTo[x]){
            path.push(x);
        }

        path.push(start);
        return path;
    }
}
