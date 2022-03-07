package leetcode.graph;

import java.util.LinkedList;
import java.util.List;

/*
* 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。

在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程 bi 。

例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。

链接：https://leetcode-cn.com/problems/course-schedule
* */

/*
* 有向图中是否存在环
* */
public class CanFinish207 {
    boolean[] visited;
    boolean[] onPath;
    boolean hasCycle = false;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses,prerequisites);//构建一个图
        visited = new boolean[numCourses];//创建visited数组
        onPath = new boolean[numCourses];//创建路径数组
        for (int i = 0; i < numCourses; i++) {
            traverse(graph,i);//遍历 图
        }
        return !hasCycle;//只要没有环 就可以完成课程
    }
    /*
    * 根据 prerequisites 建图
    * */
    private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites){
        List<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : prerequisites) {
            //edge[1] --> 先修课程
            //edge[0] --> 后修课程
            int from = edge[1];
            int to = edge[0];//先修完 edge[1] 才能修  edge[0]
            graph[from].add(to);
        }
        return graph;
    }
    /*
    * 遍历图 （邻接表方法）
    * */
    private void traverse(List<Integer>[] graph,int s){
        if (onPath[s]){//发现现在的这个节点  已经在路径中了
            hasCycle = true;
        }
        if (visited[s] || hasCycle) return;

        visited[s] = true;
        onPath[s] = true;//做选择 将标记节点s 在路径上
        for (int t : graph[s]) {
            traverse(graph, t);
        }
        onPath[s] = false;//回溯
    }

}
