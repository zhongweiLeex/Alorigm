package leetcode.stockquestions;

/*
* 给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
* 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
* 返回获得利润的最大值。
* 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
* 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee
* */
public class MaxProfit714 {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;//交易日天数

        int[][] dp = new int[n][2];//dp数组 存储获利
//        dp[0][0]  = 0;
//        dp[0][1] = -prices[0]-fee;//初始化
        for (int i = 0; i < n; i++) {
            if (i==0){
                dp[i][0]= 0;
                dp[i][1] = -prices[i] -fee;
                continue;
            }
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]-prices[i]-fee);//减去手续费
        }
        return dp[n-1][0];
    }

    public int maxProfit1(int[] prices,int fee){
        int n = prices.length;

        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0,dp_i_1+prices[i]);
            dp_i_1  = Math.max(dp_i_1,temp-prices[i]-fee);
        }
        return dp_i_0;
    }
}
