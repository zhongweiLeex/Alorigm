package leetcode.monotonestack;

/*
*
*https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484525&idx=1&sn=3d2e63694607fec72455a52d9b15d4e5&chksm=9bd7fa65aca073734df90b45054448e09c14e6e35ad7b778bff62f9bd6c2b4f6e1ca7bc4f844&scene=21#wechat_redirect
* */

/*
* 循环的  和  490不同
* */
import java.util.LinkedList;
import java.util.Stack;
/*
* 使用单调栈 解决 nextGreaterElement
* 这个是  循环的   就要 使用 % 操作 做到 循环
* */
public class NextGreaterElement503 {
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[nums.length];
        for(int i = 2*nums.length-1;i>= 0 ;i--){
            while(!stack.isEmpty() && stack.peek() <= nums[i%nums.length]){
                stack.pop();
            }
            result[i%nums.length] = stack.isEmpty() ? -1:stack.peek();
            stack.push(nums[i%nums.length]);
        }
        return result;
    }

    /*
    * 单调递增栈（从栈顶 到 栈底 递增） 实现
    * */
    private Stack<Integer> monotoneStack(LinkedList<Integer> nums){
        Stack<Integer> stack = new Stack<>();//创建单调栈
        for (int i = nums.size()-1; i >=0; i--) {//倒着入栈
            while (!stack.isEmpty() && nums.get(i) >= stack.peek()){//如果是单调递减栈 则需要将 >= 改成 <=
                stack.pop();//当前栈顶元素 比 nums中的 元素小 要将大的加入到 stack
            }
            stack.push(nums.get(i));
        }
        return stack;
    }
}
