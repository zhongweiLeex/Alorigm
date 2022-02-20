package leetcode;

import java.util.*;

public class FindMinHeightTrees310 {

    @SuppressWarnings("unchecked")
    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
        boolean[] marked = new boolean[n];
        int[] edgeTo = new int[n];//记录每个路径最后的一个顶点
        int num = n;

        LinkedList<Integer> queue = new LinkedList<>();
        if (n==1){
            List<Integer> adj = new ArrayList<>();
            adj.add(0);
            return adj;
        }
        if (n==2){
            List<Integer> adj = new ArrayList<>();
            adj.add(edges[0][0]);
            adj.add(edges[0][1]);
            return adj;
        }
        List<Integer> list = new ArrayList<>();
//        List<Integer> list2 = new ArrayList<>();
//        boolean[] marked= new boolean[n];//创建标志数组 查看是否 已经遍历过
//        int[] edgeTo = new int[n];

        List<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];//创建邻接表
        int[] adj2 = new int[n];//创建邻接表
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }



        //构建邻接表
        for (int i = 0; i < edges.length; i++) {
            //Set<Integer> set1 = new HashSet<>();
            adj[edges[i][1]].add(edges[i][0]);
            adj[edges[i][0]].add(edges[i][1]);
        }
        for (int i = 0; i < adj.length; i++) {
            adj2[i] = adj[i].size();//构建入度表
        }
        System.out.println(Arrays.toString(adj));
        System.out.println(Arrays.toString(adj2));

        for (int i = 0; i < n; i++) {
            if (adj2[i] == 1)
                queue.offer(i);//将叶子节点加入队列 将所有入度为1 的节点入对
        }
        System.out.println(queue);
        while(num>2){
            int count = queue.size();
            num = num - count;
            while(count>0){
                int item = queue.pop();
                int size = adj[item].size();
                //将所有叶子节点去掉 然后其他所有的节点入度减1 并将新的叶子节点加入到队列中
                for (int i = 0;i<size;i++){
                    --adj2[item];
                    --adj2[adj[item].get(i)];

                    if (adj2[adj[item].get(i)] == 1){
                        queue.offer(adj[item].get(i));
                    }
                }
                count--;
            }
        }
        System.out.println(queue);
        while(!queue.isEmpty()){
            list.add(queue.pop());
        }
        return list;
    }
    /*private static void dfs(List<Integer>[] adj, int v){
        marked[v] = true;
        count++;
        for (int w : adj[v]) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(adj, w);
            }
        }

    }
*/





    public static void main(String[] args) {
        int[][] edges = {{3,0},{3,1},{3,2},{3,4},{5,4}};
//        System.out.println(edges.length);
//        System.out.println(edges[0][1]);

        System.out.println(findMinHeightTrees(6,edges));

    }
}
