package leetcode;
/*
* 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，
* 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
* https://leetcode-cn.com/problems/surrounded-regions/
* */

/*
输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。
如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。


链接：https://leetcode-cn.com/problems/surrounded-regions


*/
/*
* 使用dfs
* */
public class Solve130 {
    public void solve(char[][] board) {
        if(board == null || board.length == 0) return;
        for(int i = 0; i<board.length;i++){
            for(int j = 0;j<board[0].length;j++){
                //从边界O开始dfs  同时 将 边界O 变为 除了 X O以外的其他字符
                if(i == 0 || j == 0 || i==board.length-1 || j == board[0].length-1){
                    if(board[i][j] == 'O'){
                        dfs(board,i,j);//先从边界 O 搜索
                    }
                }
            }
        }

        for(int i = 0; i< board.length;i++){
            for(int j = 0;j<board[0].length;j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
                if(board[i][j] == '#'){
                    board[i][j] = 'O';
                }
            }
        }
    }
    private void dfs(char[][] board,int i, int j){
        int m = board.length;
        int n = board[0].length;

        if(i<0 || j<0 || i>=m || j>=n || board[i][j] == '#' || board[i][j] == 'X'){
            return;//已经处理过了
        }

        // if(i == 0 || j==0 || i==m-1 || j==n-1){
        //     board[i][j] = 'Y';//将边界
        // }

        board[i][j] = '#';
        dfs(board,i+1,j);
        dfs(board,i,j+1);
        dfs(board,i-1,j);
        dfs(board,i,j-1);
    }
}
