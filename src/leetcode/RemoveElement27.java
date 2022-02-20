package leetcode;
//27
public class RemoveElement27 {
    //暴力做法
    public int removeElementSimple(int[] nums,int val){
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == val){
                for (int j = i+1; j < len; j++) {
                    nums[j-1] = nums[j];
                }
                i--;//为了使i保持原地位置不变
                len--;
            }
        }
        return len;
    }
    //双指针做法
    public int removeElementDoublePointer(int[] nums,int val){
        int slowPointer = 0;
        for (int fastPointer = 0; fastPointer < nums.length; fastPointer++) {
            if (nums[fastPointer] != val){
                //当快指针没有遇到val 就 将快指针赋值给 慢指针
                //如果遇到了 这个if直接没有执行
                // 意味着 slowPointer 没有动
                // 这样之后没有予到的 则直接执行if 把快指针的东西就覆盖到慢指针上了
                nums[slowPointer] = nums[fastPointer];
                slowPointer++;
            }
        }
        return slowPointer;
    }

}
