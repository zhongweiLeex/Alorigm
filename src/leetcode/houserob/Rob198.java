package leetcode.houserob;

import java.util.Arrays;
/*
*
* 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
* 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，
* 系统会自动报警。
* 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，
* 一夜之内能够偷窃到的最高金额。
链接：https://leetcode-cn.com/problems/house-robber

* */
public class Rob198 {
    /*
    * 备忘录的 从上向下的方法
    * */
    private int[] memo;
    public int rob(int[] nums) {
        memo = new int[nums.length];//创建备忘录
        Arrays.fill(memo,-1);
        return dp(nums,0);
    }

    private int dp(int[] nums,int start){
        if (start >= nums.length){
            return 0;
        }
        if (memo[start] != -1){
            return memo[start];
        }
        int result = Math.max(dp(nums,start+1),dp(nums,start+2)+nums[start]);
        memo[start] = result;//将当前这个位置的结果计入结果
        return result;
    }

    /*
    * 使用 dp数组 从后往前 这样就省去了初始化 和特殊情况的处理
    * */
    public int rob1(int[] nums){
        int n = nums.length;
        int[] dp = new int[n+2];
        for (int i = n-1; i >=0 ; i++) {
            dp[i] = Math.max(dp[i+1],dp[i+2]+nums[i]);
        }
        return dp[0];
    }
    /*
    * 使用dp数组
    * */
    public int rob2(int[] nums){

        int n = nums.length;

        if (n == 1){
            return nums[0];
        }
        int[] dp = new int[n];
        //初始化
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], 0 + nums[1]);//初始化 在第二家的抢还是不抢  如果第2家抢 则 第一家就不能抢
        for (int i = 2 ; i < n ; i++) {
            dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        return dp[n-1];
    }
    /*
    * 逆向的 就不用处理 开头的位置初始化的问题了
    * */
    public int rob21(int[] nums){
        int n = nums.length;
        int[] dp = new int[n+2];//n +1  与 n都是0 虚构出来的  方便开始 真正的 n-1家
        for (int i = n-1; i >= 0  ; i--) {
            dp[i] = Math.max(dp[i+1], nums[i] + dp[i+2]);
        }
        return dp[0];
    }

    public int rob3(int[] nums){

        int n = nums.length;
        if (n == 1){
            return nums[0];
        }
        int dp_i_1 = Math.max(nums[0],nums[1]);//dp[i-1]
        int dp_i_2 = nums[0];//dp[i-2]
        int dp_i = 0;
        for (int i = 2; i < n; i++) {
            dp_i = Math.max(dp_i_1,dp_i_2+nums[i]);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i;
    }
    public int rob32(int[] nums){
        int n = nums.length;
        int dp_i_1 = 0;
        int dp_i_2 = 0;

        int dp_i = 0;
        for (int i = n-1; i >=0 ; i--) {
            dp_i = Math.max(dp_i_1,nums[i] + dp_i_2);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i;
    }




}
