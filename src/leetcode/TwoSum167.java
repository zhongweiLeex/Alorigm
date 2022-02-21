package leetcode;

public class TwoSum167 {
    public int[] twoSum(int[] numbers,int target){

        int left = 0;//左指针
        int right = numbers.length-1;//右指针
        while (left < right){
            int sum = numbers[left] + numbers[right];
            if (sum == target){
                return new int[]{left+1,right+1};//题目要求需要从下标1开始
            }else if (sum < target){
                left++;
            }else {
                right--;
            }
        }

        return new int[]{-1,-1};
    }
}
