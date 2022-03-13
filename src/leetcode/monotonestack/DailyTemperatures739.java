package leetcode.monotonestack;

import java.util.Stack;
/*
*
* 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，
* 其中 answer[i] 是指在第 i 天之后，
* 才会有更高的温度。如果气温在这之后都不会升高，请在该位置用 0 来代替。

链接：https://leetcode-cn.com/problems/daily-temperatures

* */
//因为是温度  ，所以 必须存储的是下标
public class DailyTemperatures739 {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];//创建结果数组
        Stack<Integer> stack = new Stack<>();//存储下标 因为题目要返回的就是下标
        for (int i = temperatures.length-1; i >= 0; i--) {
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i] ){
                stack.pop();
            }
            if (stack.isEmpty()){
                result[i] = 0;
            }else {
                result[i] = stack.peek() - i;
            }
            //索引入栈 而不是元素
            stack.push(i);
        }
        return result;
    }
}
