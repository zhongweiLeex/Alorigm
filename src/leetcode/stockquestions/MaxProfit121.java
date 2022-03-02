package leetcode.stockquestions;
/*
* 给定一个数组 prices ，它的第i 个元素prices[i] 表示一支给定股票第 i 天的价格。
* 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
* 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
*
* */

//次数的 交易次数 只能是 1 次  题目中写道： 只能选择 某一天买入 并在未来卖出   然后算出利润
public class MaxProfit121 {
    public int maxProfit(int[] prices) {
        int n = prices.length;//总共有多少交易日
        int[][] dp = new int[n][2];//两种状态 第二维度表示买卖两种状态  总共有 n个交易日
        //在n个交易日中交易
        for (int i = 0; i < n; i++) {
            //因为当 i = 0 的时候 i-1 是不合法的 所以要处理  边界溢出的情况
            if (i-1 == -1){
                dp[i][0] = 0;//第0天就卖出的话  本来就是没有股票 所以最后的收益 只能是 0
                dp[i][1] = -prices[i];//第 0 天就买入的话 相当于 获利  -prices[i]  换句话说 就是花了 -prices[i]
                continue;
            }
            //如果今天不持有的话  ， 前一天就有两种情况  第一种： 前一天也没有持有 ； 第二种：前一天持有，但是今天卖出了
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
            //如果今天持有的话， 两种情况 第一种：前一天就已经持有了 ;第二种情况 ：前一天没有持有，但是今天买入了
            dp[i][1] = Math.max(dp[i-1][1],- prices[i]);//因为只有一次买卖机会 所以今天买入 说明前面没有动过，换言之就是没有交易过 没有赚过钱 也没有亏过钱
        }
        return dp[n-1][0];
    }

/*
* 空间优化版本
* */
    public int maxProfit1(int[] prices){

        int n = prices.length;
        int dp_i_0 = 0;                //第i个交易日 没有 持有股票             但是在外面初始化 说明这里表示的 第 0 天的没有持有股票的情况下手里的钱
        int dp_i_1 = Integer.MIN_VALUE;//第i个交易日 有   持有股票             但是在外面初始化  说明 这里表示 第0 天的持有股票的情况下 但是 还没开始的情况下 是不可能持有的
        for (int i = 0; i < n; i++) {
            dp_i_0 = Math.max(dp_i_0,dp_i_1+prices[i]);//这里括号里的是 未更新前的值 表示前一天的
            dp_i_1 = Math.max(dp_i_1,-prices[i]);
        }
        return dp_i_0;//全部卖出去 也比 买入的时候赚钱  所以卖出
    }
}
