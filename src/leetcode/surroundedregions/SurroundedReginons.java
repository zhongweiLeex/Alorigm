package leetcode.surroundedregions;
/*
* 题目： leetcode.Solve130
* */

//使用并查集方法
/*
* 1.  将边界上 为 O的加入连通分量
* 2.  从边界 O 出发  上下左右扩散   将与边界O直接连接的 O加入到 联同分量
* 3.  将所有不与边界O 在同一个连通分量中的 O 全部变为 X
* */
public class SurroundedReginons {
    public void solve(char[][] board) {
        int m = board.length;//行数量
        int n = board[0].length;//列数
        int dummy  = m* n;//用一个永远用不到的 地方存储  最终的更节电

        UionFind uf = new UionFind(m*n +1);//并查集用的是 一维数组  所以 要将 这里的二维数组转换成一维数组    (x ,y) ---> x*n + y
        for (int x = 0 ; x < m; x++){

                //将 边界的 O 与  dummy 根节点连接
                if (board[x][0]  == 'O'){
                    uf.union(x*n,dummy);
                }
                if (board[x][n-1] == 'O'){
                    uf.union(x*n + (n-1),dummy);
                }

        }

        for (int y = 0;y < n;y++){
            if (board[m-1][y] == 'O'){
                uf.union((m-1)*n + y,dummy);
            }
            if (board[0][y] == 'O'){
                uf.union(y,dummy);
            }
        }



        int[][] directions = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};//左右上下
        for (int i = 1; i < m-1; i++) {
            for (int j = 1; j < n-1; j++) {
                if (board[i][j] == 'O'){
                    for (int k = 0; k < 4; k++) {
                        int x = i+directions[k][0];//左右扩散
                        int y = j+directions[k][1];//上下扩散

                        if (board[x][y] == 'O'){
                            uf.union(x*n + y,i*n+j);

                        }
                    }
                }
            }
        }


        for (int i = 1; i < m-1; i++) {
            for (int j = 1; j < n-1; j++) {
                if (uf.isConnected(dummy,i*n+j)){
                    //判断当前节点i，j 是否和 dummy 相连
                    board[i][j] ='X';//所有和 dummy 不连通的 内部的O 都要变为 x
                }
            }
        }

    }




}


class UionFind{
    private int[] parent;//
    private int count;//联同分量个数  初始值 为  节点个数
    public UionFind(int n){
        this.count = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;//初始化
        }
    }


    public int findParent(int x){
        while(parent[x] != x){
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    public void union(int p, int q){
        int rootX = findParent(p);
        int rootY = findParent(q);
        if (rootX == rootY){
            return;
        }
        parent[rootX] = rootY;//如果不是相同根节点  则连通成一个分量
        count--;
    }

    public boolean isConnected(int p,int q){
        return findParent(p) == findParent(q);
    }
    public int count(){
        return count;//返回连通分量的个数
    }


}
