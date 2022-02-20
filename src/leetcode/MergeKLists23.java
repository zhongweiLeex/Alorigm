package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;
//优先队列的特点是 最大的 在 根节点  且 遵循二叉堆规律
public class MergeKLists23 {
    public ListNode mergeKLists(ListNode[] lists){
        //非空判断
        if (lists.length == 0){
            return null;
        }
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        //优先队列  最小堆
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, (a, b)->(a.val - b.val));//比较的是值

        //将所有链表的头节点陆续加入优先队列中
        for (ListNode head : lists) {
            if (head != null) {
                pq.add(head);
            }
        }

        while (!pq.isEmpty()){
            //获取最小节点  接到结果链表中
            ListNode node = pq.poll();
            cur.next = node;

            if (node.next!=null){
                pq.add(node.next);
            }
            cur = cur.next;
        }
        return dummy.next;

    }
}
