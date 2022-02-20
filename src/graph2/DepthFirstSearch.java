package graph2;
/*
* 深度优先搜索
* */
public class DepthFirstSearch {
    private boolean[] marked;
    private int count;
    public DepthFirstSearch(Graph G,int start){
        marked = new boolean[G.V()];
        dfs(G,start);
    }
    private void dfs(Graph G,int v){
        marked[v] = true;
        count++;
        for (int w : G.adj(v)) {
            if (!marked[w])
                dfs(G, w);//使用递归 进行深度优先遍历
        }
    }
    public boolean marked(int w){
        return marked[w];
    }
    public int count(){
        return count;
    }
}
