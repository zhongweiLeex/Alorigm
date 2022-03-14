package leetcode.queue22stack;

import java.util.LinkedList;
import java.util.Queue;

/*
请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。

实现 MyStack 类：

void push(int x) 将元素 x 压入栈顶。
int pop() 移除并返回栈顶元素。
int top() 返回栈顶元素。
boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。


注意：

你只能使用队列的基本操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。
你所使用的语言也许不支持队列。 你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/implement-stack-using-queues
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
public class Queue2Stack {
    private Queue<Integer> q;
    private int recordTop;//记录队尾元素 （这个位置其实是 栈的 栈顶元素 需要一个位置 特意记录一下）
    public Queue2Stack(){
        q = new LinkedList<>();
        recordTop = 0;
    }
    public void push(int x) {
        //队列的队尾  栈的栈顶
        q.offer(x);
        recordTop = x;  //队尾元素 就是 栈栈顶元素
    }

    public int pop() {
        for (int i = 0; i < q.size()-2; i++) {
            q.offer(q.poll());
        }
        recordTop = q.peek();//新的队列队头元素
        q.offer(q.poll());
        return q.poll();//将之前的队尾 就是  栈的栈顶元素删除并返回
    }

    public int top() {
        return recordTop;
    }

    public boolean empty() {
        return q.isEmpty();
    }
}
