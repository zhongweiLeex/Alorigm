package leetcode;
//34 在排序数组中查找元素的第一个和最后一个位置
public class FindStartAndEndPosition {
/*
* 先使用二分查找找到想要找到的位置
* 之后分别向左向右找到和target相同的 并记录下更新边界位置
* 如果target不存在 则直接返回 -1,-1
* */
    public int[] searchRange(int[] nums,int target){
        int index = binarySearch(nums,target);

        if (index == -1){
            return new int[] {-1,-1};
        }
        int left = index;
        int right = index;
        while(left - 1 >= 0 && nums[left-1]==nums[index]){
            left--;
        }
        while(right + 1< nums.length && nums[right + 1] == nums[index]){
            right++;
        }
        return new int[] {left,right};
    }
    public int binarySearch(int[] nums,int target) {
        int left = 0;
        int right = nums.length - 1;
        while (right >= left) {
            int middle = left + (right - left) / 2;
            if (nums[middle] < target) {
                left = middle + 1;
            } else if (nums[middle] > target) {
                right = middle - 1;
            } else {
                return middle;
            }
        }
        return -1;
    }
}
