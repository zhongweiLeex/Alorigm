package leetcode.islands;

public class MaxAreaOfIsland695 {
    //1 ---> 陆地
    //0 ---> 海洋
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1){
                    result = Math.max(result,dfs(grid,i,j));
                }
            }
        }
        return result;
    }
    public int dfs(int[][] grid, int i, int j){

        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || i >= m || j<0 || j>=n){
            return 0;
        }


        if (grid[i][j] == 0){
            return 0;
        }
        grid[i][j] = 0;//淹没

        return dfs(grid, i-1, j) + dfs(grid,i+1,j) + dfs(grid,i,j-1)+dfs(grid,i,j+1) + 1;//为什么要 +1 因为还有自己站的那一块 不能忽略
    }
}
