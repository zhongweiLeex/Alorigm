package leetcode.graph;
/*


现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，
其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。

例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
链接：https://leetcode-cn.com/problems/course-schedule-ii

*/

import java.util.*;

/*
* 图连通性   使用拓扑排序
*
* 拓扑排序 前提是没有图中没有环存在
* 拓扑排序 就是  后序遍历的逆序
* */
public class FindOrder210 {
    boolean[] visited;
    boolean[] onPath;
    boolean hasCycle = false;//判断是否存在环的标志位
    List<Integer> postOrder = new ArrayList<>();//记录后序遍历结果

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses,prerequisites);
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];

        //遍历图
        for (int i = 0; i < numCourses; i++) {
            traverse(graph,i);
        }
        //有环图 无法进行拓扑排序
        if (hasCycle){
            return new int[]{};//存在环 的图无法拓扑排序 直接返回 空数组
        }
        int[] result = new int[numCourses];

        //逆后序遍历结果就是拓扑排序结果
        Collections.reverse(postOrder);//逆转后序遍历的数组
        for (int i = 0; i < numCourses; i++) {
            result[i] = postOrder.get(i);
        }
        return result;

    }

    /*
    * 根据给出的构建图
    * */
    private List<Integer>[] buildGraph(int numCourses,int[][] prerequisites){
        List<Integer>[] graph = new LinkedList[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int[] edge : prerequisites) {
            int from = edge[1];
            int to = edge[0];//先修完 edge[1] 才能修  edge[0]
            graph[from].add(to);
        }
        return graph;
    }


    /*
    * 深度优先遍历
    * */
    private void traverse(List<Integer>[] graph,int s){
        if (onPath[s]){//如果当前节点之前就已经加入到 路劲中  则直接存在环
            hasCycle = true;
        }
        if (onPath[s] || visited[s]){ //如果这个节点被访问过了 或者被加入过路径 直接退出
            return;
        }

        //需要的节点没有加入过路径中  且 没有被访问过
        //深度优先遍历

        //前序遍历位置
        onPath[s] = true;
        visited[s] = true;
        for (int t : graph[s]) {
            traverse(graph, t);
        }
        //后序遍历位置
        postOrder.add(s);
        onPath[s] = false;
    }


}
