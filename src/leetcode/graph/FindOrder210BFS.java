package leetcode.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindOrder210BFS {

    /*
     * 1. 构建邻接表
     * 2. 构建indegree数组记录每个节点的入度
     * 3. 对bfs 队列初始化，将入度为0 的节点首先装入队列
     * 4. 开始BFS循环 不断弹出队列中节点，减少相邻节点的入度，将入度变为0 的节点加入队列
     * 5. 如果最终 队列中 入度为0 的节点  等于  count 说明不存在环
     *
     * */

    public int[] findOrder(int numCourses,int[][] prerequisites){
        int[] indgrees = new int[numCourses];//入度统计数组
        List<Integer>[] graph = buildGraph(numCourses,prerequisites);//构建图
        //统计各个节点的入度
        for (int[] edge : prerequisites) {
            int from = edge[1];
            int to = edge[0];
            indgrees[to]++;//to这个节点的入度+1
        }


        Queue<Integer> q = new LinkedList<>();//将所有入度为0 的节点入队
        for (int i = 0; i < numCourses; i++) {
            if (indgrees[i] == 0){
                q.offer(i);//将入度为0 的节点入队
            }
        }
        int[] result = new int[numCourses];//记录拓扑排序
        int count = 0;//记录遍历节点的索引
        //队列为空 == 入度为0 的节点没有了
        while (!q.isEmpty()){
            int current = q.poll();
            result[count]=current;
            count++;
            //遍历当前节点的相邻节点
            for (int next : graph[current]) {
                indgrees[next]--;//被遍历到的节点的 入度 --
                if (indgrees[next] == 0){
                    q.offer(next);//如果当前节点的入度为0 加入队列
                }
            }
        }
        if (count != numCourses){
            return new int[]{};//如果存在环 则拓扑排序不存在
        }
        return result;

    }



    /*
    *
    * 构建图*/
    private List<Integer>[] buildGraph(int n,int[][] edges){
        List<Integer>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : edges) {
            int from = edge[1];
            int to = edge[0];
            graph[from].add(to);
        }
        return graph;
    }
}
