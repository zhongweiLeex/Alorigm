package leetcode.islands;

public class CountSubIslands1905 {
    //1 --> 陆地
    //0 --> 海洋
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length;
        int n = grid1[0].length;
        for (int i = 0; i < m ; i++) {
            for (int j = 0; j < n; j++) {
                if (grid1[i][j] != grid2[i][j]){
                    dfs(grid2,i,j);
                }
            }
        }

        int result = 0;//图2中的子岛数量
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == 1){
                    result++;
                    dfs(grid2,i,j);
                }
            }
        }
        return result;
    }

    //从（i，j）开始 将与之响铃的 陆地全部变成海水
    public void dfs(int[][] grid,int i,int j){
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || i>=m || j<0 || j>= n){
            return;
        }
        if (grid[i][j] == 0){
            return;
        }
        grid[i][j] = 0;//淹没现在的这个陆地
        dfs(grid, i+1, j);
        dfs(grid, i-1, j);
        dfs(grid, i, j-1);
        dfs(grid, i, j+1);
    }
}
