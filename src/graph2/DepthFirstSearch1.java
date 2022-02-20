package graph2;

public class DepthFirstSearch1 {
    private boolean[] marked;//标记点是否被遍历过
    private int count;//遍历节点的个数

    public DepthFirstSearch1(Graph G,int s){
        marked = new boolean[G.V()];
        dfs(G,s);
    }
    private void dfs(Graph G,int v){

        marked[v] = true;
        count++;

        for (int w : G.adj(v)) {//返回v点的所有邻接点
            if (!marked[w])//如果某个节点的所有邻接点中其中一个没有被遍历过 则往下递归遍历 将这个 w作为新的起点 往下遍历
                dfs(G, w);
        }
    }
    /*
    * @param w 某个点的下标
    * @return w点的是否被遍历过
    * */
    public boolean marked(int w){
        return marked[w];
    }
    /*
    * @return 深度优先遍历遍历的多个个节点个数
    * */
    public int count(){
        return count;
    }

}
