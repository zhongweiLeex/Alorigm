package leetcode;
//35题 搜索插入位置
//要求 时间复杂度为 O(log n )
//给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
public class SearchInsertPosition35 {
    public int searchPosition(int[] nums,int target){
        int left = 0;
        int right = nums.length-1;
        while(right >=left){
            int middle = left + (right - left) / 2;
            if (nums[middle] < target){
                left = middle + 1;
            }else if (nums[middle] > target){
                right = middle -1;
            }else{
                return middle;
            }
        }
        return right+1;
    }
}
