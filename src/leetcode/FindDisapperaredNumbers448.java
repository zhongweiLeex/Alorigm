package leetcode;

import java.util.*;
/*
* ***************************************************************************************************************************
* 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。

* 来源：力扣（LeetCode）
* 链接：https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array
* 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*
* ***************************************************************************************************************************
* */
public class FindDisapperaredNumbers448 {
    public static List<Integer> findDisapperedNumbers(int[] nums){
/*        List<Integer> results = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;

            if (nums[index]<0){
                continue;
            }
            nums[index] = -nums[index];
        }
        System.out.println(Arrays.toString(nums));
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0){
                results.add(i+1);
            }
        }*/
        List<Integer> results = new LinkedList<>();
        TreeSet<Integer> set = new TreeSet<Integer>();
        for (int i : nums) {
            set.add(i);
        }
        //        System.out.println(iterator.next());
        for (Integer integer : set) {
            nums[integer - 1] = -1;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != -1){
                results.add(i+1);
            }
        }
        return results;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,3,2,7,8,2,3,1};


        System.out.println(findDisapperedNumbers(nums));

    }
}
