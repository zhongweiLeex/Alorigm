package leetcode.houserob;

import java.util.Stack;

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
            stack.push(i);
        }
        return result;
    }
}
