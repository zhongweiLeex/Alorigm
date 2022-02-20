package leetcode;
//26
public class RemoveDuplicates26 {
    public int removeDuplicates(int[] nums){
        int len = nums.length;
        int slowPointer = 0;
        for (int fastPointer = 0;fastPointer < nums.length;fastPointer++){
            if (nums[fastPointer] != nums[slowPointer]){
                nums[slowPointer+1] = nums[fastPointer];
                slowPointer++;
            }
        }
        return slowPointer+1;
    }

}
