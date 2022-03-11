package leetcode.lru;

public class Node {
    public int key,value;
    public Node next,prev;//后向指针 前向指针
    //构造方法
    public Node(int key,int value){
        this.key = key;
        this.value = value;
    }
}
