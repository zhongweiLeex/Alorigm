package leetcode.dynamicprogramming;

/*
* https://leetcode-cn.com/problems/fibonacci-number/submissions/
* */
public class Fib509 {
    /*
    * 暴力递归
    * */
    public int fib(int n){
        if (n == 1 || n == 2){
            return 1;
        }else {
            return fib(n-1) + fib(n-2);
        }
    }

/*-----------------------------------------------------------------------------------*/
    /*
    * 备忘录 递归
    * */

    public int fib2(int n){
        int[] memo = new int[n+1];
        return fibHelper(n,memo);
    }

    private int fibHelper(int n,int[] memo){
        if (n==0 || n==1) return n;
        if (memo[n] != 0) return memo[n];
        memo[n] = fibHelper(n-1,memo) + fibHelper(n-2,memo);
        return memo[n];
    }

/*----------------------------------------------------------------------------------*/
    /*
    *  使用dp table
    * */
    public int fib3(int n){
        if (n == 0) return 0;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n ; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
/*-------------------------------------------------------------------------------------*/
    /*
    * 简化上述 dp table
    * */
    public int fib4(int n){
        if (n == 0 || n==1) return n;
        int dp_i_1 = 1;//dp[i-1]
        int dp_i_2 = 0;//dp[i-2]
//        int dp_i = 0;
        for (int i = 2; i <=n ; i++) {
            int dp_i = dp_i_1 + dp_i_2;
            //滚动更新
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i_1;
    }
}






























