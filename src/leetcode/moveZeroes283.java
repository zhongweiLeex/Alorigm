package leetcode;
//移动0 快慢指针
public class moveZeroes283 {
    public void moveZeroes(int[] nums) {
        int slowPointer = 0;
        for (int fastPointer = 0; fastPointer < nums.length;fastPointer++){
            if(nums[fastPointer]!=0){
                int temp;
                temp = nums[slowPointer];
                nums[slowPointer] = nums[fastPointer];
                nums[fastPointer] = temp;
                slowPointer++;
            }
        }
    }
}
