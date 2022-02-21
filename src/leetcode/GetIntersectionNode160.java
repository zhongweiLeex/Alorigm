package leetcode;

public class GetIntersectionNode160 {
    public ListNode getIntersectionNode(ListNode headA,ListNode headB){
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1!=p2){

            if (p1 == null){
                p1 = headB;
            }else{
                p1 = p1.next;
            }

            if (p2 == null){
                p2 = headA;
            }else{
                p2 = p2.next;
            }
        }
        return p1;
    }

    public ListNode getIntersectionNode1(ListNode headA,ListNode headB){
        ListNode p1 = headA;
        ListNode p2 = headB;

        int n1 = 0;
        int n2 = 0;

        while (p1!=null){
            p1 = p1.next;
            n1++;
        }

        while (p2!=null){
            p2 = p2.next;
            n2++;
        }
        //归位
        p1 = headA;
        p2 = headB;

        if (n1 > n2){
            for (int i = 0; i < n1 - n2; i++) {
                p1 = p1.next;
            }
        }else{
            for (int i = 0; i < n2 - n1; i++) {
                p2 = p2.next;
            }
        }
        while (p1 != p2){
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;

    }
}
