package leetcode.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/*
你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row, col) 的高度。
一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。
你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。

一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。

请你返回从左上角走到右下角的最小 体力消耗值 。

链接：https://leetcode-cn.com/problems/path-with-minimum-effort

* */
public class MinimumEffortPath1631 {
        int result = 0;//记录最后消耗的体力值
        public int minimumEffortPath(int[][] heights) {
            int m = heights.length;
            int n = heights[0].length;
            int[][] efforts = new int[m][n];//存储00 到 ij 之间的 effort
            //初始化 邻接矩阵 权重值
            for(int i = 0; i < m ;i ++){
                Arrays.fill(efforts[i],Integer.MAX_VALUE);
            }
            //start
            efforts[0][0] = 0;
            // 0 --> 当前点的x坐标  1 ---> 当前点的 y坐标   2 ---> 当前点距离 start effort
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2]- b[2]);
            pq.offer(new int[]{0,0,0});

            while(!pq.isEmpty()){
                int[] currentNode = pq.poll();
                int curX = currentNode[0];
                int curY = currentNode[1];
                int curEffortFromStart = currentNode[2];

                //如果越过边界
                if(curX == m-1 && curY == n-1 ){
                    return curEffortFromStart;
                }
                //如果当前节点还是比数组中的大  就直接跳过了 因为我们要找最小的 effort
                if(curEffortFromStart > efforts[curX][curY]){
                    continue;
                }

                for(int[] neighbor : adj(heights,curX,curY)){
                    int nextX = neighbor[0];
                    int nextY = neighbor[1];
//一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
                    int effortsToNextNode = Math.max(efforts[curX][curY],Math.abs(heights[nextX][nextY] - heights[curX][curY]));
                    if(efforts[nextX][nextY] > effortsToNextNode){
                        efforts[nextX][nextY] = effortsToNextNode;
                        pq.offer(new int[]{nextX,nextY,effortsToNextNode});
                    }
                }
            }
            return -1;

        }
        //返回当前节点的邻接节点  坐标 x y
        int[][] directions = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        private List<int[]> adj(int[][] graph,int x,int y){
            List<int[]> result = new LinkedList<>();

            int m = graph.length;
            int n = graph[0].length;
            for(int[] dir : directions){
                int newx = x + dir[0];
                int newy = y + dir[1];
                if(newx >= m || newx <0 || newy <0 || newy >= n){
                    continue;
                }
                result.add(new int[]{newx,newy});
            }

            return result;
        }
}
