package leetcode.houserob;

/*
*
*https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484525&idx=1&sn=3d2e63694607fec72455a52d9b15d4e5&chksm=9bd7fa65aca073734df90b45054448e09c14e6e35ad7b778bff62f9bd6c2b4f6e1ca7bc4f844&scene=21#wechat_redirect
* */


import java.util.LinkedList;
import java.util.Stack;
/*
* 使用单调栈 解决 nextGreaterElement
* */
public class NextGreaterElement {
    public LinkedList<Integer> nextGreatElement(LinkedList<Integer> nums){
        LinkedList<Integer> result = new LinkedList<>();//创建结果list
        Stack<Integer> stack = new Stack<>();
        for (int i = nums.size()-1; i >=0 ; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums.get(i)){//要求 栈底元素最大  栈顶元素最小
                stack.pop();//单调栈  要求 栈底元素 最大  栈顶元素最小 如果不符合  将 栈顶元素出栈

            }

            if (stack.isEmpty()){//如果最终栈还是不是单调栈 但是 元素都被全部出栈了
                result.add(-1);
            }else{
                result.add(stack.peek());//如果不是因为 栈空 而结束比较  就是 元素 符合单调栈的大小规则了 则
            }
//            result.add(stack.isEmpty() ? -1: stack.peek());
            stack.push(nums.get(i));//符合单调栈的大小规则了 则 将当前的 比栈内peek元素小的 符合单调栈规则的元素 入栈
        }
        return result;
    }

    /*
    * 单调递增栈（从栈顶 到 栈底 递增） 实现
    * */
    private Stack<Integer> monotoneStack(LinkedList<Integer> nums){
        Stack<Integer> stack = new Stack<>();//创建单调栈
        for (int i = 0; i < nums.size(); i++) {//倒着入栈
            while (!stack.isEmpty() && nums.get(i) >= stack.peek()){//如果是单调递减栈 则需要将 >= 改成 <=
                stack.pop();//当前栈顶元素 比 nums中的 元素小 要将大的加入到 stack
            }
            stack.push(nums.get(i));
        }
        return stack;
    }
}
