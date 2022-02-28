package leetcode.islands;

public class NumEnclaves1020 {
    //0 --> 海洋
    //1 --> 陆地
    public int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n= grid[0].length;
        for (int i = 0; i < m; i++) {
            dfs(grid,i,0);
            dfs(grid,i,n-1);
        }
        for (int i = 0; i < n; i++) {
            dfs(grid,0,i);
            dfs(grid,m-1,i);
        }
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1){
                    result++;
                }
            }
        }
        return result;
    }
    public void dfs(int[][] grid,int i,int j){
        int m = grid.length;
        int n = grid[0].length;
        if (i<0 || i >=m || j<0 || j>=n){
            return;
        }
        if (grid[i][j]  == 0){//已经是海洋了
            return;
        }
        grid[i][j] = 0;
        dfs(grid,i-1,j);
        dfs(grid,i+1,j);
        dfs(grid,i,j-1);
        dfs(grid,i,j+1);
    }
}
