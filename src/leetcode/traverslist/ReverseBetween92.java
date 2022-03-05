package leetcode.traverslist;

/*
* 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
* */

import java.util.List;

public class ReverseBetween92 {

    /*
    * 双指针 定位算法
    * */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre  = dummy;

        for(int i = 1;i<left;i++){
            pre = pre.next;
        }
        // head = p.next;
        ListNode cur = pre.next;
        /*
        * 交换链表节点位置
        * */
        for(int i = left;i<right;i++){
            ListNode nex = cur.next;
            cur.next = nex.next;
            nex.next = pre.next;
            pre.next = nex;
            // p1 = p1.next;
        }
        return dummy.next;
    }
    /*
    * 递归算法
    * */

    public ListNode reverseBetween1(ListNode head,int left,int right){
        //base case
        if (left==1){
            return reverseN(head,right);
        }
        //如果 left 不是head位置 则 将 head向left位置推进  将推进之后的位置作为新的head 那直接就是left == 1的时候  反转这个链表 就是 后面位置的链表了
        head.next = reverseBetween1(head.next,left-1,right-1);
        return head;

    }

    /*
    * 反转前n个节点  从 head 到 第n个节点
    * */
    ListNode post = null;
    private ListNode reverseN(ListNode head,int n){
        //因为此时的 head不一定是最后一个节点   所以 head.next 保存到后驱节点
        if (n == 1){
            post = head.next;
            return head;//只有一个头节点  之后直接返回  记录头节点后驱位置
        }
        //以head.next 为开始节点  反转n-1个节点
        ListNode last = reverseN(head.next , n-1);

        head.next.next = head;
        head.next = post;

        return last;

    }


}
