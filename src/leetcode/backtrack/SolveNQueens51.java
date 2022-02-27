package leetcode.backtrack;

import java.util.LinkedList;
import java.util.List;

public class SolveNQueens51 {

    List<List<String>> result = new LinkedList<>();//存放所有结果

    public List<List<String>> solveNQueens(int n) {
        //新建一个棋盘
        List<String> board = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();
        //初始化棋盘 全部置空
        stringBuilder.append(".".repeat(n));
        for (int i = 0; i < n; i++) {
            board.add(stringBuilder.toString());
        }
        backTrack(0,board);
        return result;


    }
    /**
     * @param board 棋盘情况
     * @param row 当前游标处于第几行
     * */
    private void backTrack(int row,List<String> board){
        //回溯终止条件
        if (row == board.size()){//终止条件 递归到了最后一个行 直接跳出
            result.add(new LinkedList<>(board));//将目前的结果中的添加到result中
            return;
        }
        int n = board.get(row).length();//获取当前游标所在的行的 String长度
        //开始穷举 从 当前行的 第0 列开始 处理节点
        for (int col = 0; col < n ; col++) {
            //处理节点 （检查节点合法性 ， 如果合法 ）
            if (!isValid(row,col,board)){//检查 当前行 的 所有列的节点是否合法
                continue;//如果不合法直接跳过
            }
            String str = board.get(row).substring(0,col) + 'Q' + board.get(row).substring(col+1);

            board.set(row,str);//将上述的替换后的String 代替到 游标所在的row

            //递归 深入子树
            backTrack(row+1,board);//对深入的下一行进行递归操作

            //回溯 ，撤销处理结果
            str = board.get(row).substring(0,col) + '.' + board.get(row).substring(col+1);
            board.set(row,str);
        }
    }
    /**
     * @param row  // 检查游标 目前 所有在的行位置
     * @param column //检查游标 目前的 所在的列位置
     * @param board //当前的棋盘状态
     * @apiNote 判断是否当前位置是否合法
     * */
    private boolean isValid(int row,int column,List<String> board){
        int n = board.size();
        //判断列位置是否合法
        for (int i = 0; i < n; i++) {
            if (board.get(i).charAt(column) == 'Q'){//第 i行 第 column 列 的字符
                return false;
            }
        }
        //判断是否与右上方发生冲突
        for (int i = row -1,j = column + 1; i >= 0 && j < n; i--,j++ ) {
            if (board.get(i).charAt(j) == 'Q'){
                return false;
            }
        }
        //判断 是否与 左上方冲突
        for (int i = row -1,j = column -1 ; i >=0 && j>=0 ; i--,j--) {
            if (board.get(i).charAt(j) == 'Q'){
                return false;
            }
        }
        return true;
    }
}






















