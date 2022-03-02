package leetcode.stockquestions;

/*
* 最佳买卖股票时机含冷冻期
  给定一个整数数组prices，其中第 prices[i] 表示第 i 天的股票价格 。
  设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
  卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
  注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
  链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
* */
public class MaxProfit309 {
    //尽可能多的完成交易  说明 交易次数  k 为 不限次数
    public int maxProfit(int[] prices) {
        int n = prices.length;//交易日的天数
        int[][] dp = new int[n][2];//创建dp数组

        for (int i = 0; i < n; i++) {
            //第 0 天
            if (i == 0){
                dp[0][0] = 0;
                dp[0][1] = -prices[i];//花钱买股票了
                continue;
            }
            //第 1 天
            if (i == 1){
                dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i-1][1],-prices[i]);//手里没钱的时候 只有花钱买
                continue;
            }
            //除了上面的两种情况
            //买 - 卖  才是一个交易操作
            //卖出股票后 无法在第二天买入
            //
            //第一天  第二天 第三天
            //i-2    i-1    i
            //买入, 卖出, 冷冻期, 买入, 卖出
//            解释：第 i 天选择 buy 的时候，要从 i-2 的状态转移，而不是 i-1 。
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1],dp[i-2][0] - prices[i]);
        }
        return dp[n-1][0];
    }
    /*
    * 空间优化版本
    * */
    public int maxProfit1(int[] prices){
        int n = prices.length;
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;
        int dp_i_pre_0 = 0;//代表 dp[i-2][0];
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0,dp_i_1+prices[i]);
            dp_i_1 = Math.max(dp_i_1,dp_i_pre_0-prices[i]);
            dp_i_pre_0 = temp;
        }
        return dp_i_0;
    }
}



















