package leetcode.stockquestions;

/*
* 给定一个数组 prices ，其中prices[i] 表示股票第 i 天的价格。
* 在每一天，你可能会决定购买和/或出售股票。你在任何时候最多只能持有 一股 股票。你也可以购买它，然后在 同一天 出售。
* 返回 你能获得的 最大 利润。
* 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
* */

public class MaxProfit122 {
    //此处的交易次数 为无限次数
// k = +infinity  无限次数 说明k不是一个状态 因为他的值任意 无法从一个状态转移到另一个状态
    public int maxProfit(int[] prices){
        int n = prices.length;//交易日的数量

        int[][] dp = new int[n][2];//此处没有k是因为 k是无限的 任意值  不是状态
        for (int i = 0; i < n; i++) {
            if (i-1 == -1){
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            //状态转移开始
            dp[i][0] = Math.max( dp[i-1][0] ,dp[i-1][1] + prices[i] );
            dp[i][1] = Math.max( dp[i-1][1],dp[i-1][0] - prices[i]);
        }
        return dp[n-1][0];
    }


    public int maxProfit1(int[] prices){
        int n = prices.length;
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;//第 0 天就持有股票不可能
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 =  Math.max(dp_i_0,dp_i_1 + prices[i]);
            dp_i_1 =  Math.max(dp_i_1,temp - prices[i]);
        }
        return dp_i_0;
    }



}
