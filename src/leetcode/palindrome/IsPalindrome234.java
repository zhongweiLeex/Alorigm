package leetcode.palindrome;

import java.util.Stack;

public class IsPalindrome234 {
    ListNode left;
    public boolean isPalindrome(ListNode head) {
        left = head;
        return traverse(head);
    }
    private boolean traverse(ListNode right){
        if(right == null){//到最后了没有受到阻挡
            return true;
        }
        boolean result = traverse(right.next);
        result = result && (right.val == left.val);
        left = left.next;
        return result;
    }



    public boolean isPalindrome1(ListNode head){
        if (head == null){
            return true;
        }
        Stack<Integer> stack = new Stack<>();
        ListNode cur = head;
        while(cur != null){
            stack.push(cur.val);
            cur = cur.next;
        }
        cur = head;
        while(!stack.isEmpty()){
            if (stack.pop()!= cur.val){
                return false;
            }
            cur = cur.next;
        }
        return true;
    }



    public boolean isPalindrome2(ListNode head){

        ListNode slow,fast;
        slow = fast = head;//快慢指针

        //找到链表中点
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        //如果fast没有指向null 说明 链表长度为 奇数  如果slow要作为被反转俩表的头节点 就要多走一步
        if (fast !=null){
            slow = slow.next;
        }

        ListNode right =  reverseList(slow);//反转后面的链表
        ListNode left  = head;

        while (right!=null){
            if (left.val != right.val){
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }


    private ListNode reverseList(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        ListNode last = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

}
