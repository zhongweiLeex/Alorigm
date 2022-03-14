package leetcode.arrayquestions;
// 303

/*
给定一个整数数组  nums，处理以下类型的多个查询:

计算索引 left 和 right （包含 left 和 right）之间的 nums 元素的 和 ，其中 left <= right
实现 NumArray 类：

NumArray(int[] nums) 使用数组 nums 初始化对象
int sumRange(int i, int j) 返回数组 nums 中索引 left 和 right 之间的元素的 总和 ，包含 left 和 right 两点（也就是 nums[left] + nums[left + 1] + ... + nums[right] )



链接：https://leetcode-cn.com/problems/range-sum-query-immutable


*/

public class NumArray {
    //sumRange 会多次被调用
    // 所以 不能在sumrange中写循环
    //提前计算好 各个位置的前缀和
    int[] preNums;
    public NumArray(int[] nums) {
        preNums = new int[nums.length+1];
        preNums[0] = 0;
        for(int i = 1; i<=nums.length;i++){
            preNums[i] = preNums[i-1] + nums[i-1];
            //i位置的前缀和 等于 i-1 的前缀和 + nums[i-1] 但是注意不包含当前位置num[i]的
            //计算的只是num[i] 之前的和
        }
    }

    public int sumRange(int left, int right) {
        return preNums[right+1] - preNums[left];//left - right 的闭区间的累加和
    }
}
