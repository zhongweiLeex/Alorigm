package leetcode.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CanFinish207BFS {

/*
* 1. 构建邻接表
* 2. 构建indegree数组记录每个节点的入度
* 3. 对bfs 队列初始化，将入度为0 的节点首先装入队列
* 4. 开始BFS循环 不断弹出队列中节点，减少相邻节点的入度，将入度变为0 的节点加入队列
* 5. 如果最终 队列中 入度为0 的节点  等于  count 说明不存在环
*
* */

    public boolean canFinish(int numCourse,int[][] prerequisites){
        List<Integer>[] graph = buildGraph(numCourse, prerequisites);
        //入度数组
        int[] indgree = new int[numCourse];
        for (int[] edge : prerequisites) {
            int to = edge[0];
            indgree[to]++;//入度+1
        }

        Queue<Integer> q = new LinkedList<>();//初始化队列中的节点
        for (int i = 0; i < numCourse; i++) {
            if (indgree[i] == 0){//如果i位置的节点的入度为 0 说明没有依赖的节点，可以作为
                //可以作为拓扑排序的起点，加入队列
                q.offer(i);
            }
        }

        int count = 0;//记录遍历节点个数
        while(!q.isEmpty()){
            int current = q.poll();//弹出节点
            count++;
            for (int next : graph[current]) {

                indgree[next]--;//将当前节点的指向的节点 入度减一

                if (indgree[next] == 0){
                    //如果指向的节点入度为0 说明 next这个节点所有依赖的节点多已经被遍历
                    q.offer(next);
                }
            }
        }
        return count== numCourse;//如果所有节点都被遍历过，说明不成环
    }




    /*
    * 构建图函数
    * */
    private List<Integer>[] buildGraph(int n, int[][] edges){

        List<Integer>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int[] edge : edges){
            int from = edge[1];
            int to = edge[0];
            graph[from].add(to);
        }
        return graph;

    }
}
