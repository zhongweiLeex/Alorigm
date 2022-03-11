package leetcode.lru;

public class DoubleList {
    //头尾虚结点
    private Node head,tail;
    //链表元素数量
    private int size;
    public DoubleList(){
        //构造头尾虚结点
        head = new Node(0,0);
        tail = new Node(0,0);
        //初始化  一开始没有真实节点的时候
        head.next = tail;
        tail.prev = head;
        size = 0;
    }
    public void addLast(Node x){
        x.prev = tail.prev;
        x.next = tail;
        tail.prev.next = x;
        tail.prev = x;
        size++;
    }
    public void addFirst(Node x){
        x.next = head.next;
        x.next.prev = x;
        head.next = x;
        x.prev  = head;
        size++;
    }
    // 删除链表中的节点x （节点x 一定存在）
    public void remove(Node x){
        x.prev.next = x.next;
        x.next.prev = x.prev;
        size--;
    }
    public Node removeLast(){
        //说明链表中没有真实节点  所以 直接返回空
        if (head.next == tail){
            return null;
        }
        Node last = tail.prev;
        remove(last);
        return last;
    }
    public Node removeFirst(){
        if (head.next == tail){
            return null;
        }
        Node first = head.next;
        remove(first);
        return first;
    }
    public int getSize(){
        return size;
    }
}
