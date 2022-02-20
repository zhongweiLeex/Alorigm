package graph2;
/*
* 二分图问题
* 双色问题
* */
public class TwoColor {
    private boolean[] marked;
    private boolean[] colors;
    private boolean isTwoColorable = true;
    public TwoColor(Graph G){
        marked = new boolean[G.V()];
        colors = new boolean[G.V()];
        for (int s = 0;s<G.V();s++){
            if (!marked[s])
                dfs(G,s);
        }
    }

    private void dfs(Graph G,int v){
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]){//如果w没有被遍历过
                colors[w] = !colors[v];//上色不同的颜色
                dfs(G,w);//继续以 w 为开始节点继续深度优先遍历
            }else if (colors[w] == colors[v]){//若是响铃的两个节点是同色的 则直接设置 isTwoColorable = false
                isTwoColorable = false;
            }
        }
    }
    public boolean isBipartite(){
        return isTwoColorable;
    }
}
