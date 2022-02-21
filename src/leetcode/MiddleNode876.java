package leetcode;

public class MiddleNode876 {
    public ListNode middleNode(ListNode head){
//        ListNode dummy = new ListNode(-1);
//        dummy.next = head;

        ListNode fast = head;
        ListNode slow = head;

        while (fast!=null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
    public ListNode middleNode2(ListNode head){
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode p = dummy;
        ListNode node = dummy;
        int n = 0;//节点个数

        while (p.next != null){
            p = p.next;
            n++;
        }
        for (int i = 0; i < n/2; i++) {
            node = node.next;
        }
        return node;
    }
}
