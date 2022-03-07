package leetcode.graph;
/*
* 邻接矩阵遍历
* */
public class GraphTraverse {
    //记录被遍历过的节点
    boolean[] visited;
    //记录从起点到当前节点的路径
    boolean[] onPath;

    void traverse(Graph graph,int s){
        if (visited[s]) return;

        visited[s] = true;//经过节点s ， 标记为 已经遍历了

        onPath[s] = true;//做选择 标记节点 s 在路径上

        for (int neighbor : graph.neighbors[s]) {//遍历与当前节点s邻接的所有节点
            traverse(graph, neighbor);
        }
        onPath[s] = false;//撤销选择 节点s 离开路径
    }

}
/*
//邻接表遍历
boolean[] visited;//防止遍历同一个节点
void traverse(List<Integer>[] graph,int s) {
       if(visited[s]) return;


       // 前序遍历位置
       visited[s] = true;//将这个节点 置为 已经遍历过了

       for(int t: graph[s]){
        traverse(graph,t);
       }

       // 后序遍历位置

}








*/
