package leetcode.arrayquestions;
/*
给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。
设计一个算法使得这 m 个子数组各自和的最大值最小。
https://leetcode-cn.com/problems/split-array-largest-sum/
*/
public class SplitArray410 {
    public int splitArray(int[] nums, int m) {
        //求最后的 最小的 max值  这不就是 二分搜索里面找到左边界值的做法吗
        //max 的取值范围 从 nums.max  到最后的 sum(nums[])
        int low = getMax(nums);
        int high = getSum(nums);


        //超出时间限制
        // for(int max = low; max <= high;max++){
        //     int n = split(nums,max);//根据子数组的和的max值 划分子数组
        //     if(n<=m){// 一旦能划分成 比 m小的 n ， 必定能将 n 个子数组继续划分成 m 个
        //     // 一个数组能划分成 2 个  就必定能继续划分成 3 个
        //         return max;
        //     }
        // }
        // return -1;

        //左闭右开
        //二分搜索边界值
        //结果必定落在【max（nums）， sum（nums）】这个区间内
        while(low < high){
            int mid = low + (high-low)/2;//计算中间值  使用二分搜索
            //mid 指的是 遍历用的 max和
            int n = split(nums,mid);
            if(n == m){
                high=mid;
            }else if(n > m){//划分得到的子数组数量比 规定的m个多 说明 max值要大一点
                low = mid+1;
            }else if(n < m){
                high = mid;
            }
        }
        return low;//左侧边界值
    }

    //限制最大数组和为 max
    // 计算nums至少可以被分割成几个数组
    private int split(int[] nums,int max){
        int sum = 0;
        int resultCount = 1;//记录最多能分成几个最大数组和为 Max的子数组数量
        for (int num : nums) {
            if (sum + num > max) {
                resultCount++;
                sum = num;//从 nums[i] 开始重新划分后面的
            } else {
                sum += num;
            }
        }
        return resultCount;
    }


    private int getMax(int[] nums){
        int max = nums[0];
        for(int i = 1; i<nums.length;i++){
            if(max < nums[i]){
                max = nums[i];
            }
        }
        return max;
    }
    private int getSum( int[] nums){
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }
}
