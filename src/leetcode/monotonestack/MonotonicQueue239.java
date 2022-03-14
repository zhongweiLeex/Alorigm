package leetcode.monotonestack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
*

给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
返回 滑动窗口中的最大值 。

链接：https://leetcode-cn.com/problems/sliding-window-maximum

* */

public class MonotonicQueue239 {
    //双向队列
    LinkedList<Integer> q = new LinkedList<>();
    //       队头   <---->   队尾
    /*
    * 队尾添加元素 n
    * */
    private void push(int n){
        while(!q.isEmpty() && q.getLast()< n){
            q.pollLast();
        }
        //在队尾添加新扫描到的元素 n
        q.addLast(n);
    }

    /*
    * 返回当前队列中 最大的元素
    * */
    private int max(){
        return q.getFirst();//队头元素最大
    }
    /*
    * 删除元素 n ，首先看看 n在不在 队列中 如果不在 说明已经在之前的  push的比较中被删除了
    * 如果不是 则说明 在之前已经被删除了
    * */
    private void pop(int n){
        //
        if (n == q.getFirst()){
            q.pollFirst();
        }
    }
    /*
    * k --> 滑动窗口规定大小
    * nums  --> 需要滑动窗口 滑动的数组
    * */
    private int[] maxSlideWindows(int[] nums,int k){
        MonotonicQueue239 window = new MonotonicQueue239();//构建一个滑动窗口 对象
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (i < k-1){
                //入队
                //窗口容量  是 k  先将 k-1 装满
                window.push(nums[i]);
            }else{
                //窗口向前滑
                window.push(nums[i]);
                result.add(window.max());//记录最大数字
                window.pop(nums[i-k+1]);//当前窗口的 队头元素出队  因为要向前滑动
            }
        }
        int[] arr = new int[result.size()];
        for (int i = 0 ; i< result.size();i++) {
            arr[i] = result.get(i);
        }
        return arr;
    }
}


































