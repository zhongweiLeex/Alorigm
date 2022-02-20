package leetcode;

/*
*
*367. 有效的完全平方数
* 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
* */
public class isPerfectSqure367 {
    public boolean isPerfectSquare(int num) {
        int left = 1;
        int right = num;

        while(left <= right){
            int middle = left + (right - left)/2;
            if((long) middle * middle < num){
                left = middle + 1;
            }else if((long) middle*middle > num){
                right = middle - 1;
            }else{
                return true;
            }
        }
        return false;
    }
}
