package leetcode.stockquestions;
/*
* 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
* 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
* 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
* 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii
* */
public class MaxProfit123 {
/*
* k是两次
* */
    public int maxProfit(int[] prices){
        int n = prices.length;
        int max_k = 2;//最大交易次数
        int[][][] dp = new int[n][max_k+1][2];//此处max_k + 1 因为是数组定义的时候 必须大一个
        for (int i = 0; i < n; i++) {
            dp[i][0][0] = 0;
            dp[i][0][1] = Integer.MIN_VALUE;

        }
        for (int i = 0; i < n; i++) {
            for (int k = max_k; k >= 1 ; k--) {
                if (i-1 == -1){
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i-1][k][0],dp[i-1][k][1]+prices[i]);
                dp[i][k][1] = Math.max(dp[i-1][k][1],dp[i-1][k-1][0]-prices[i]);

            }
        }
        return dp[n-1][max_k][0];
    }

    public int maxProfit1(int[] prices){
        int n = prices.length;
        int max_k = 2;//最多两次交易
        // i_剩余交易量_持有状态（0-不持有）
        int dp_i10 = 0;
        int dp_i11 = Integer.MIN_VALUE;
        int dp_i20 = 0;
        int dp_i21 = Integer.MIN_VALUE;

        for (int price : prices) {
            dp_i20 = Math.max(dp_i20,dp_i21+price);
            dp_i21 = Math.max(dp_i21,dp_i10-price);
            dp_i10 = Math.max(dp_i10,dp_i11+price);
            dp_i11 = Math.max(dp_i11,-price);
        }
        return dp_i20;
    }
}
