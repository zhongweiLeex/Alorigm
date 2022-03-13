package leetcode.monotonestack;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class NextGreaterElement496 {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];//创建一个数组
        Stack<Integer> stack = new Stack<>();
        Map<Integer,Integer> map = new HashMap<>();//使用的 是 map 因为是两个数组  要在 第一数组中对比 第二个数组

        for (int i = nums2.length-1 ;i >=0;i--) {
            while (!stack.isEmpty() && stack.peek() <= nums2[i]) {//必须倒着扫描 因为是栈结构
                stack.pop();
            }
            map.put(nums2[i],stack.isEmpty()?-1:stack.peek());
            stack.push(nums2[i]);
        }
        for (int i = nums1.length-1; i >=0; i--) {
            result[i] = map.get(nums1[i]);
        }
        return result;
    }


}

