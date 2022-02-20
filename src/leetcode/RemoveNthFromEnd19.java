package leetcode;

public class RemoveNthFromEnd19 {
    /*
    * 描述：  使用节点移动规则 快慢双指针方法 找到需要删除节点位置
    * 参数1： 表示链表的头节点
    * 参数2：表示需要删除的倒数第N个节点
    * */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;//将虚头节点指向投节点
        ListNode fastNode = dummyNode;//定义一个快指针
        ListNode slowNode = dummyNode;//定义一个慢指针
        while (fastNode!=null && n!=0){
            fastNode = fastNode.next;
            n--;
        }
        assert fastNode != null;
        fastNode = fastNode.next;//快指针多走一步 使慢指针删除的时候可以直接指向删除位置的下一个位置

        //慢指针开始行动
        //移动结束标志为 快指针到达末尾null位置
        while (fastNode!=null){
            assert slowNode!=null;
            slowNode = slowNode.next;//慢指针开始出发
            fastNode = fastNode.next;//快指针与慢指针同步移动
        }
        assert slowNode != null;
        slowNode.next = slowNode.next.next;//开始删除指定位置得节点
        return dummyNode.next;
    }


    /*
    * 描述： 使用节点数量统计方法
    * 参数1： ListNode head 表示链表投节点
    * 参数2： int n  表示需要删除的倒数第n个节点
    * */
    public ListNode removeNthFromEnd2(ListNode head,int n){
        //排除空链表影响
        if (head == null || head.next == null){
            return null;
        }
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;//将虚拟头节点next指向头节点
        ListNode currentNode = dummyNode;
        ListNode node = dummyNode;
        int count = 0;//链表中节点个数
        while (currentNode.next != null){
            count++;//链表个数+1
            currentNode = currentNode.next;//向后移动
        }
        //找到需要删除的节点位置
        for (int i = 0; i < count - n; i++) {
            node = node.next;
        }
        node.next = node.next.next;
        return dummyNode.next;
    }
}
