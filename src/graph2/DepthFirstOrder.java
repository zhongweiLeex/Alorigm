package graph2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
/*
* 有向图中基于深度优先搜索的顶点排序
*
* 1. 前序排列
* 2. 后序排列
* 3. 逆后序排列
*
* */
public class DepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> pre;//所有顶点的前序排列
    private Queue<Integer> post;//所有顶点的后序排列
    private Stack<Integer> reversePost;//所有顶点的逆后序排列
    /*
    *  有向无权图的 深度优先遍历 的 顶点排序构造方法
    * */
    public DepthFirstOrder(Digraph G){
        pre = new LinkedList<>();
        post = new LinkedList<>();
        reversePost = new Stack<>();

        marked = new boolean[G.V()];

        for (int v = 0;v <G.V();v++){
            if (!marked[v])
                dfs(G,v);
        }
    }
    /*
    *
    * 有向带权图的 深度优先遍历 的 顶点排序构造方法
    * */
    public DepthFirstOrder(EdgeWeightedDigraph G){
       pre = new LinkedList<>();
       post = new LinkedList<>();
       reversePost = new Stack<>();
       for (int v = 0; v <G.V();v++){
           if (!marked[v])
               dfs(G,v);
       }
    }
    /*
    * 有向无权图的 深度优先遍历
    * */
    private void dfs(Digraph G,int v){
        pre.add(v);//前序排列  在 递归dfs之前就 将当前节点加入到 队列   中  映射到 树中  就是 根节点-> 左节点 -> 右节点

        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w])//如果没有被遍历过
                dfs(G,w);
        }

        post.add(v);//后序排列  在 递归 dfs 之后 将最深处的当前节点加入到 队列  中  左 -> 根 -> 右
        reversePost.push(v);//逆后序排列  在递归 dfs 之后  将当前节点  加入到 stack中
    }
    /*
    * 有向带权图  深度优先遍历
    * */
    private void dfs(EdgeWeightedDigraph G, int v){
        pre.add(v);

        marked[v] = true;
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (!marked[w]){
                dfs(G,w);
            }
        }
        post.add(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre(){
        return pre;
    }
    public Iterable<Integer> post(){
        return post;
    }
    public Iterable<Integer> reversePost(){
        return reversePost;
    }

}
