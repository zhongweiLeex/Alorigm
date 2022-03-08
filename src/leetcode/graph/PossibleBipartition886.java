package leetcode.graph;

import java.util.LinkedList;
import java.util.List;

/*

给定一组 n 人（编号为 1, 2, ..., n）， 我们想把每个人分进任意大小的两组。每个人都可能不喜欢其他人，那么他们不应该属于同一组。
给定整数 n 和数组 dislikes ，其中 dislikes[i] = [ai, bi] ，表示不允许将编号为 ai 和  bi的人归入同一组。当可以用这种方法将所有人分进两组时，返回 true；否则返回 false
链接：https://leetcode-cn.com/problems/possible-bipartition

* */
public class PossibleBipartition886 {
    boolean[] visited;
    boolean[] color;
    boolean result = true;
    public boolean possibleBipartition(int n, int[][] dislikes) {
        visited = new boolean[n+1];
        color = new boolean[n+1];
        List<Integer>[] graph = buildGraph(dislikes,n);//创建图

        for(int i = 1; i<=n; i++){
            if(!visited[i]){
                traverser(graph,i);//对节点i的相邻节点开始遍历
            }

        }
        return result;
    }

    private void traverser(List<Integer>[] graph, int i){
        if(!result) return;
        // if(visited[i]  == true){
        //     return;
        // }
        visited[i] = true;
        for(int j:graph[i]){//对节点i的相邻节点开始遍历
            if(!visited[j]){
                color[j] = !color[i];
                traverser(graph,j);
            }else{
                if(color[i] == color[j]){
                    result = false;
                }
            }
        }
    }

    //构建图
    private List<Integer>[] buildGraph(int[][] dislikes,int n){
        List<Integer>[] graph = new LinkedList[n+1];
        for(int i  = 1; i<=n;i++){//编号从1 开始
            graph[i] = new LinkedList<>();
        }

        for(int[] edges : dislikes){
            int from = edges[0];
            int to = edges[1];
            graph[from].add(to);
            graph[to].add(from);
        }
        return graph;
    }
}
