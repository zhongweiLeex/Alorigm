package leetcode.houserob;
/*
你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，
这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，
如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，
今晚能够偷窃到的最高金额。
链接：https://leetcode-cn.com/problems/house-robber-ii
* */
public class Rob213 {
    public int rob(int[] nums) {
        /*
        * 首尾相连：
        *       1. 第一间 被抢   最后一间不抢
        *       2. 第一间 不抢   最后一间 被抢
        *       3. 第一间 不抢   最后一间 不抢
        * */
        int n = nums.length;
        if (n == 1){
            return nums[0];
        }
        return Math.max(rob2Situation(nums,0,n-2),rob2Situation(nums,1,n-1));

    }

    private int rob2Situation(int[] nums,int start,int end){
        int dp_i_1 = 0;
        int dp_i_2 = 0;
        int dp_i = 0;
        for (int i = end; i >= start ; i--) {
            dp_i = Math.max(dp_i_1,nums[i] + dp_i_2);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i;
    }
}
