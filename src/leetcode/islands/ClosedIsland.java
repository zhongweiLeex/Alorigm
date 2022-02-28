package leetcode.islands;

import java.util.LinkedList;
import java.util.Queue;

public class ClosedIsland {
    public int closedIsland(int[][] grid) {
        int result = 0;//记录岛屿数量

        int m = grid.length;//整个海域的多少行
        int n = grid[0].length;//整个海域多少列


        for (int i = 0; i < n; i++) {
            dfs(grid,0,i);//把靠在上面的岛屿淹没
            dfs(grid,m-1,i);//把海域边的下面边上的岛屿淹没
        }
        for (int i = 0; i < m; i++) {
            dfs(grid,i,0);//将靠在左边的岛屿淹没 第0列
            dfs(grid,i,n-1);//将靠在右边的岛屿淹没 第n-1列
        }



        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0){// 0 代表陆地，如果发现一块陆地 则 说明这里有 岛屿 接下来就是往这块陆地的上下左右 深度优先搜索 dfs 淹没
                    result++;
                    dfs(grid,i,j);//对这块陆地dfs
                }
            }
        }
        return result;
    }
    //i 表示   上下  +1表示向下一个
    //j 表示   左右  +1表示向右一个
    //深度优先搜索 将上下左右 四个方向的陆地全部淹没
    private void dfs(int[][] grid,int i,int j){
        int m = grid.length;//海域横向长度
        int n = grid[0].length;//海域纵向长度
        //边界条件判断  如果越界了  则直接结束 本次 dfs
        if (i < 0 || j<0 || i >= m || j >=n)
            return;
        //如果dfs的那块土地已经是海水了 则直接结束 本次 dfs
        if (grid[i][j] == 1){
            return;
        }
        //如果 既没有越界 也没有被海水淹没 则进行 以下操作
        grid[i][j] = 1;//淹没陆地
        dfs(grid,i+1,j);//向下淹没
        dfs(grid,i,j+1);//向右淹没
        dfs(grid,i-1,j);//向上淹没
        dfs(grid,i,j-1);//向左淹没
    }



    private int[][] DIRECTIONS = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};//方向数组 方向的四种组合 顺序是随便的
    private int rows;//行数
    private int cols;//列数

    //陆地 ---> 0
    //海洋 ---> 1
    public int closedIsland2(int[][] grid){
        rows = grid.length;
        cols = grid[0].length;
        int result = 0;
        //维护一个访问数组
        //没有被拜访过   ---> 0
        //被拜访过      ---> 1
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0 && !visited[i][j]){//当前是陆地 且 没有被拜访过
                    if (bfs(grid,visited,i,j)){//对当前位置的陆地 为 开始节点  进行广度优先搜索
                        result++;
                    }
                }
            }
        }
        return result;
    }
    private boolean bfs(int[][] grid,boolean[][] visited,int startX,int startY){
        boolean result = true;//指示 这一片 是否是封闭式陆地
        Queue<int[]> queue = new LinkedList<>();//放的是一个点的横纵坐标
        queue.offer(new int[]{startX,startY});//将当前的点先放进来
        visited[startX][startY] = true;//拜访过了
        //开始遍历
        while(!queue.isEmpty()){
            int[] cur =  queue.poll();//当前拿到手的点

            //边界值
            if (cur[0] == 0 || cur[1] == 0 || cur[0] == rows -1 || cur[1] == cols -1){
                result = false;
            }
            //对当前点的四个方向进行遍历
            for (int[] d : DIRECTIONS){

                int newx = cur[0] + d[0];
                int newy = cur[1] + d[1];

                if (newx >=0 && newx<rows && newy>=0 && newy<cols && grid[newx][newy] == 0 && !visited[newx][newy]){
                    queue.offer(new int[]{newx,newy});
                    visited[newx][newy] = true;
                }

            }

        }
        return result;
    }
}
























