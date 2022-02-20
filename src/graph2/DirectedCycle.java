package graph2;

import java.util.Stack;
/*
* 有向环
* */
public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;//有向环中的所有顶点
    private boolean[] onStack;//保存递归调用期间栈上的所有顶点
    /*
    * 有向环构造方法
    * */
    public DirectedCycle(Digraph G){
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        for (int v= 0;v <G.V();v++){
            if(!marked[v])//如果没有被遍历过 需要以v为根节点深度优先遍历
                dfs(G,v);
        }
    }


/*    public DirectedCycle(EdgeWeightedDigraph G){
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        for (int v= 0;v <G.V();v++){
            if(!marked[v])//如果没有被遍历过 需要以v为根节点深度优先遍历
                dfs(G,v);
        }
    }*/

    private void dfs(Digraph G,int v){
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (this.hasCycle())//如果存在环 直接返回 不进行以下操作
                return;

            else if (!marked[w]){//如果没有被遍历过
                edgeTo[w] = v;//将到达w的最后一个节点 加入到 edgeTo 数组中
                dfs(G,w);//以w为根节点开始深度优先搜索
            }

            else if (onStack[w]) {
                //如果w节点 在栈中  说明 w 节点 和  输入的 参数节点 v  是相同的  则说明已经形成一个环了  则直接向cycle中添加这个环中的所有节点
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
            onStack[v] = false;//调用结束后 将 onStack[v] 设置为 false
        }
    }
    /*
    * 判断是否存在环
    * */
    public boolean hasCycle(){
        return cycle != null;
    }
    /*
    * 返回环
    * */
    public Iterable<Integer> cycle(){
        return cycle;
    }
}
