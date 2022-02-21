package leetcode;

public class DetectCycle142 {
    public ListNode detectCycle(ListNode head){
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next !=null){
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow){
                break;
            }
        }
        //如果遇到null 说明没有环
        if (fast == null || fast.next == null){
            return null;
        }
//      fast = head;
        slow = head;//将slow 或者fast 任意一个重置到 head位置
        while (fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
