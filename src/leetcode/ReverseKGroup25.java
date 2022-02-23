package leetcode;

import leetcode.ListNode;

public class ReverseKGroup25 {
    public ListNode reverseKGroup(ListNode head, int k) {

/*
        只是反转了前k个节点
        ListNode dummy = new ListNode(-1);//虚拟头节点
        ListNode pre = null;
        ListNode nxt = head;
        ListNode current = head;
        int n = k;
        while(n!=0){
            nxt = current.next;
            current.next = pre;
            pre = current;
            current = nxt;
            n--;
        }
        nxt = pre;
        while (nxt.next !=null){
            nxt = nxt.next;
        }
        nxt.next = current;

        return pre;
        */

        //每k个节点一组
        if (head == null){
            return null;
        }
        ListNode start=head,end=head;
        for (int i = 0; i < k; i++) {
            if (end == null) return head;//当在k次移动次数以内 发现 end变成了null 说明这一组不足k个了 直接返回 不需要反转了
            end = end.next;//end向后移动 当结束的时候  end已经移动到 下一组的开头作为新的head
        }
        ListNode newHead = reverse(start,end);//对start 到 end的list进行反转
        //递归反转后续节点组
        start.next = reverseKGroup(end,k);
        return newHead;
    }
    private ListNode reverse(ListNode start,ListNode end){
        ListNode pre = null;
        ListNode nxt = start;
        ListNode current = start;

        while (current != end){
            nxt = current.next;
            current.next = pre;
            pre =  current;
            current = nxt;
        }
        return pre;

    }
}
