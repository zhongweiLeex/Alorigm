package graph2;
/*
* 判断 G 是否是 无环图
* 假设不存在 自环 和平行边
* */
public class Cycle {
    private boolean[] marked;
    private boolean hasCycle;
    public Cycle(Graph G){
        marked = new boolean[G.V()];
        for (int s = 0;s<G.V();s++){
            if (!marked[s]){
                dfs(G,s,s);
            }
        }
    }
    private void dfs(Graph G,int v,int u){
        marked[v]=true;
        for (int w : G.adj(v)) {
            if (!marked[w]){//如果没有遍历过 这个节点 以 w为开始节点往下遍历
                dfs(G,w,v);
            }else if(w!=u){//如果遍历过了 并且 开始节点并不是原来的 w  而是 u 则存在环
                hasCycle = true;
            }
        }
    }
}
