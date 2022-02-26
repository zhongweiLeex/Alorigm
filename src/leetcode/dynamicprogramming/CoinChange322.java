package leetcode.dynamicprogramming;

import java.util.Arrays;

public class CoinChange322 {
    /*
    *
    * 暴力递归 指数时间
    * */
    /**
     * @param coins 钱币种类
     * @param amount 目标金额  发生变化的状态 导致状态发生变化的原因是 选择钱币的动作 减少了目标金额
     * @return 最少的硬币数量
     * */
    public int coinChange(int[] coins, int amount) {
        //base case  当目标金额完成时  也就是 不用凑钱的时候 凑好了 就返回0
        if (amount == 0){ return 0; }
        if (amount < 0){
            //不能凑出 返回 -1
            return -1;
        }
        //硬币总个数
        int result = Integer.MAX_VALUE;
        //遍历 硬币种类
        for (int coin : coins) {

            //子问题的最少硬币数量
            int subNum = coinChange(coins,amount-coin);//当前一步选择 了 coin这个面额的时候  子问题的金额总数变成了  amount - coin
            if (subNum == -1){ continue; }//说明没有凑出来 就进行对下一种硬币

            result = Math.min(result,subNum+1);//比较 当前的硬币总数  和 新得出的硬币总数 哪个大
        }
        return result == Integer.MAX_VALUE ? -1:result;//如果当前的result没有得到更新  说明 根本没找到 直接返回 -1
    }


/*------------------------------------------------------------------------------------------------------------------*/
/*------------------------------------------------------------------------------------------------------------------*/


    /**
     * @apiNote 使用备忘录 避免重复计算 直接取用 算好的 在amount金额下需要的硬币个数
     *
     * */
    int[] memo;//备忘录  amount 为下标  amount位置 记录当需要凑钱的金额为amount的时候 需要的最少硬币个数
    public int coinChange1(int[] coins,int amount){
        memo = new int[amount +1];
        Arrays.fill(memo,-2);//将备忘录 全部初始化为 特殊值
        return dp(coins,amount);
    }

    private int dp(int[] coins,int amount){
        if (amount == 0){
            return 0;
        }
        if (amount < 0){
            return -1;
        }
        if (memo[amount] != -2){//如果当前的amount 需要凑得金额 已经之前又过了 则直接返回
            return memo[amount];
        }


        int result = Integer.MAX_VALUE;//硬币总个数
        //如果没有则需要继续遍历数组 来找到最小的
        for (int coin : coins) {
            int subNum = dp(coins,amount - coin);//假设之前已经选了coin 那递归计算剩下的金额 最少需要多少个数的硬币才能凑齐
            if (subNum == -1) continue;//如果当前的子问题 凑不出 说明 选择 的这个coin不是个好选择 直接下一个coin
            result = Math.min(result,subNum+1);//比较当前的硬币总数result  和 新的出的硬币总数subNumb+1 选最小的硬币总数
        }

        memo[amount] = (result == Integer.MAX_VALUE) ? -1:result;//将得出的 amount 这种情况的 存储到 备忘录的对应位置种
        return memo[amount];
    }


/*------------------------------------------------------------------------------------------------------------------*/
/*------------------------------------------------------------------------------------------------------------------*/


    /**
     * @apiNote 动态规划数组迭代解法  dp数组迭代解法
     * @param amount 表示 需要凑得钱
     * */
    public int coinChange2(int[] coins,int amount){
        //dp数组的定义 是 当目标金额 是 i的时候 需要 dp[i]枚硬币凑出
        //dp[i] 就是硬币的总个数
        int[] dp = new int[amount+1];//创建长度为 amount+1的dp数组 因为 要凑的钱最少是 1
        Arrays.fill(dp,amount+1);//特殊值为什么设置 amount+1 因为 即使全部选 面额为 1 的硬币 都不会凑到amount+1的钱的
        //base case
        dp[0] =0;
        for (int i = 0; i < dp.length; i++) {
            for (int coin : coins) {
                if (i < coin){
                    continue;//i是需要凑的钱  coin 是目前硬币面额  如果需要凑的钱都比 硬币面额小 则 直接跳过 当前硬币不适合
                }
                dp[i] = Math.min(dp[i],1+dp[i-coin]);//更新 要凑 i 块钱的时候 需要的最少硬币数量
            }
        }
        return (dp[amount] == amount +1) ? -1 : dp[amount];//看看是否更新了 如果值没有更新 则直接返回 -1
    }
}




























