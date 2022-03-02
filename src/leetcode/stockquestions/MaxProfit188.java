package leetcode.stockquestions;

/*
*
* 给定一个整数数组prices ，它的第 i 个元素prices[i] 是一支给定的股票在第 i 天的价格。
设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）
*
*
* 【选择】：每天都有三种选择：买入，卖出，误操作
* 要求：
*       1. sell 在 buy之后
*       2. buy 在 sell之后  （再次购买之前必须出售掉之前的股票）
*       3. rest 分为两种状态：
*                       3.1 buy之后 rest  （持有股票）
*                       3.2 sell 之后 rest （手中为空 不持有股票）
*
* 【状态】：
*        1. 天数：数组长度
*        2. 交易允许的最大次数：k
*        3. 当前持有状态：1. 持有 0.没有持有
*
* 【状态转移】：
*            1.第i天没有持有的状态 ： dp[i][k][0] = max(dp[i-1][k][0],dp[i-1][k][1] + prices[i])     第 i 天没有持有股票  有两种可能 第一种 前一天就没有持有， 第二种就是前一天持有的 但是 今天卖掉了
*                                 dp[i][k][0] = max(前一天没有持有且今天选择reset ，  前一天持有且今天卖掉了 )
*                                 交易限制k：昨天没有持有则 交易限制为 k，    昨天持有了，但是今天卖出了 交易限制重置为 k
*
*            2.第i天持有的状态： dp[i][k][1] = max(dp[i-1][k][1] , dp[i-1][k-1][0] - prices[i]) )
*                              第i天持有   = max（前一天持有且今天没有动 ， 前一天没有且今天买入了 ）
*                              交易限制k：昨天持有，今天没有动 则 交易限制为 k ，   前一天没有持有 但是今天买入了 则 前一天的交易限制最大是 k-1 （因为最少要留一次给今天）
*
*

* base case：
dp[-1][...][0] = dp[...][0][0] = 0
dp[-1][...][1] = dp[...][0][1] = -infinity

状态转移方程：
dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])

*
*
*
*
*
*
* */
public class MaxProfit188 {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;//交易日
        //0 <= prices.length <= 1000 题目中标出的限制条件
        if (n<=0){
            return 0;
        }
        //由于一次交易 由买入和卖出组成 由于不能同时参与多笔交易 意思就是交易日不能存在交易 
        //所以 交易次数不能 高于 n/2 
        //如果 交易次数 高于n/2 就是 无限交易次数了
        if (k > n/2){
            return maxProfitKIsInfinity(prices);
        }
        //创建 dp数组
        int[][][] dp  = new int[n][k+1][2];

        //k=0 没有交易量了 不可以交易了
        for (int i = 0; i < n; i++) {
            dp[i][0][0] = 0;
            dp[i][0][1] = Integer.MIN_VALUE;
        }


        for (int i = 0; i < n; i++) {
            for (int j = k; j >=1 ; j++) {
                if (i-1 == -1){//base case
                    dp[i][j][0] = 0;
                    dp[i][j][1] = -prices[i];
                    continue;
                }
                dp[i][j][0] = Math.max(dp[i-1][j][0],dp[i-1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i-1][j][1],dp[i-1][j-1][0] - prices[i]);
            }
        }
        return dp[n-1][k][0];
    }
    private int maxProfitKIsInfinity(int[] prices){
        int n = prices.length;
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0,dp_i_1+prices[i]);
            dp_i_1 = Math.max(dp_i_1,temp - prices[i]);
        }
        return dp_i_0;
    }

}














































