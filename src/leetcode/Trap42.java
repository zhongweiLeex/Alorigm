package leetcode;

public class Trap42 {
    //使用备忘录 计算每个的左右两边的最高
    public int trap(int[] height){
        int result = 0;
        int len = height.length;
        int[] leftMax = new int[len];//leftMax[i] 记录i位置的左边最高的高度
        int[] rightMax = new int[len];//rightMax[i] 记录i位置的右边最高的高度


        //初始化
        leftMax[0] = height[0];
        rightMax[0] = height[len -1];

        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i-1]);
        }
        for (int i = len-2;i>=0;i--){
            rightMax[i] = Math.max(height[i],rightMax[i+1]);
        }
        for (int i = 1; i < len - 1; i++) {//此处从1开始 len-1 结束  因为 左右两块 并不蓄水
            result += Math.min(leftMax[i],rightMax[i]) - height[i];//减去当前的高度 就是
        }
        return result;
    }



    public int trap1(int[] height){
        int left = 0;
        int right =height.length -1;
        int leftMax = 0;
        int rightMax = 0;
        int result = 0;
        while(left<right){
            leftMax = Math.max(leftMax,height[left]);//左指针及左指针以左的最高高度
            rightMax = Math.max(rightMax,height[right]);//右指针及右指针以右最高高度

            if (leftMax<rightMax){
                result += leftMax -height[left];
                left++;
            }else{
                result += rightMax - height[right];
                right--;
            }
        }
        return result;
    }
}
