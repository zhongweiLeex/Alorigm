package leetcode.arrayquestions;
/*
给定一个二维矩阵 matrix，以下类型的多个请求：

计算其子矩形范围内元素的总和，该子矩阵的 左上角 为 (row1, col1) ，右下角 为 (row2, col2) 。
实现 NumMatrix 类：

NumMatrix(int[][] matrix) 给定整数矩阵 matrix 进行初始化
int sumRegion(int row1, int col1, int row2, int col2) 返回 左上角 (row1, col1) 、右下角 (row2, col2) 所描述的子矩阵的元素 总和 。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/range-sum-query-2d-immutable
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
public class NumMatrix304 {
    int[][] preMatrixSum;
    public NumMatrix304(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        if(m == 0 || n==0) return;

        preMatrixSum = new int[m+1][n+1];


        for(int i = 1 ; i<= m; i++){
            for(int j = 1; j<= n;j++){
                //需要减去重复的
                preMatrixSum[i][j] = preMatrixSum[i-1][j] + preMatrixSum[i][j-1] + matrix[i-1][j-1] - preMatrixSum[i-1][j-1];
            }
        }
    }
    // 左上角起使点 row1 col1
    // 右下角结束点 row2 col2
    //  子矩阵 长度  row2 - row1
    //  子矩阵 宽度  col2 - col1
    public int sumRegion(int row1, int col1, int row2, int col2) {
        //这里需要 +1 因为是前缀和  如果想要计算  row2,col -1 的总体的和  需要写 preMatrixSum[row2+1][col]这个值
        return preMatrixSum[row2+1][col2+1] - preMatrixSum[row1][col1] - preMatrixSum[row2+1][col1] - preMatrixSum[row1][col2+1] + 2* preMatrixSum[row1][col1];
    }
}
