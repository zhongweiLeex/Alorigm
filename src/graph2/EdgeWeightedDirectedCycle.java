package graph2;

import java.util.Stack;
/*
* 加权有向环
*
* */
public class EdgeWeightedDirectedCycle {
    private boolean[] marked;
    private DirectedEdge[] edgeTo;
    private boolean[] onStack;//指示 递归期间 对应位置的顶点是否被正在被深度优先搜索 或者已经被深度优先搜索过
    private Stack<DirectedEdge> cycle;//有向环中的所有顶点

    public EdgeWeightedDirectedCycle(EdgeWeightedDigraph G){
        marked = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        for (int v = 0; v<G.V(); v++){
            if (!marked[v])
                dfs(G,v);
        }
    }

    private void dfs(EdgeWeightedDigraph g, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (DirectedEdge e : g.adj(v)) {//遍历所有 以 v 为起点的 边
            int w = e.to();//边 e 的起点  因为 G中的 adj出的都是 边 e DirectedEdge类型

            if (cycle!=null)
                return;//如果已经存在了环 则直接退出dfs

            else if(!marked[w]){//e这条边的 终点 w 没有被遍历过 直接 以 w 作为起点 深度优先遍历
                edgeTo[w] = e;
                dfs(g,w);//以w为起点继续深度优先遍历
            }
            else if (onStack[w]){//如果 w 这个顶点正在被深度优先搜索 且 w 点已经被遍历过了
                //如果w节点 在栈中  说明 w 节点 和  输入的 参数节点 v  是相同的
                // 则说明已经形成一个环了  则直接向cycle中添加这个环中的所有节点
                cycle = new Stack<>();
                DirectedEdge f = e;

                while(f.from() !=w){//从环的最后一条边/开始边的前一条边 的开始结点开始遍历 将所有的点加入到环中
                    cycle.push(f);//将当前的边存入 cycle中
                    f = edgeTo[f.from()];//以当前f边的起点作为终点的边 赋值给f 就是f 的上一条边 赋值给f边
                }
                cycle.push(f);
                return;
            }
        }
        onStack[v] = false;
    }
    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> cycle(){
        return cycle;
    }
}
