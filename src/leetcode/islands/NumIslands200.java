package leetcode.islands;

import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;

/*
* 岛屿题
* https://leetcode-cn.com/problems/number-of-islands/
* */
public class NumIslands200 {
    public int numIslands(char[][] grid) {
        int result = 0;//记录结果中 岛屿数量
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1'){
                    result++;
                    dfs(grid,i,j);
                }
            }
        }
        return result;
    }
    /*
    * gird[][] 海域
    * i 横向坐标
    * j 纵向坐标
    * */
    private void dfs(char[][] gird,int i, int j){
        int m = gird.length;//整个海域的 横向大小
        int n = gird[0].length;//整个海域的 纵向大小

        if (i<0 ||j<0 || i>=m || j>=n){//超出了左右边界   超出了上下边界
            return;
        }
        if (gird[i][j] == '0'){//如果当前位置 已经是海水了 直接返回
            return;
        }
        gird[i][j] = '0';   //将当前位置 变为海水  直接免去了 维护 visited[][] 数组的麻烦
        dfs(gird,i-1,j);//对 当前位置的 左边进行遍历
        dfs(gird,i+1,j);//对 当前位置的 右边进行遍历
        dfs(gird,i,j-1);//对 当前位置的 上面进行遍历
        dfs(gird,i,j+1);//对 当前位置的 下面进行遍历
    }

/*
*
* 方法2  bfs
*
* */
    public int numIslands2(char[][] grid){
        if (grid == null || grid.length == 0 ){
            return 0;
        }
        int result = 0;
//        int[] pair1 = new int[2];
        Queue<int[]> q = new LinkedList<>();//创建存储队列
//        int result = 0;//统计岛屿数量

        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1'){
                    result++;
                    grid[i][j] = '0';
//                    pair1 =
                    q.offer(new int[]{i,j});
//                    q.offer(new int[]{startX,startY});//起点入队
//                    grid[startX][startY] = '0';//起点陆地改为水
                    while(!q.isEmpty()){
//            int size = q.size();
                        int[] temp = q.poll();//出队一个队列中的节点

                        if(temp[0]-1 >=0 && grid[temp[0]-1][temp[1]] == '1'){
                            q.offer(new int[]{temp[0]-1,temp[1]});
                            grid[temp[0]-1][temp[1]] = '0';
                        }
                        //temp的下邻接点存在的话
                        if(temp[0]+1 <m && grid[temp[0]+1][temp[1]] == '1'){
                            q.offer(new int[]{temp[0]+1,temp[1]});
                            grid[temp[0]+1][temp[1]] = '0';
                        }
                        //temp的左邻接点存在的话
                        if(temp[1]-1 >=0 && grid[temp[0]][temp[1]-1] == '1'){
                            q.offer(new int[]{temp[0],temp[1]-1});
                            grid[temp[0]][temp[1]-1] = '0';
                        }
                        //temp的右邻接点存在的话
                        if(temp[1]+1 <n && grid[temp[0]][temp[1]+1] == '1'){
                            q.offer(new int[]{temp[0],temp[1]+1});
                            grid[temp[0]][temp[1]+1] = '0';
                        }
                    }
                }
            }
        }
        return result;
    }





}
