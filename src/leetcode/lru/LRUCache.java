package leetcode.lru;
/*
请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
实现 LRUCache 类：
LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。
如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/lru-cache
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

import java.util.HashMap;

//哈希链表 ：
//      1. cache中的元素需要具有 时序
//      2. cache中的元素需要支持 任意位置的快速插入和删除元素
//cache 中代替元素 需要的是 删除最早添加的 元素
public class LRUCache {
    // key:Integer -> value: Node(key,value)
    private HashMap<Integer,Node> map;//一个整数键 对应 一个 链表中的节点
    private DoubleList cache;//双向链表
    private int capacity;//最大容量
//    private int[] frequency;//记录所有缓存的使用频率 下标==key
    //构造方法
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
//        frequency = new int[capacity];//默认初始化为1
    }
    //获取某个缓存
    public int get(int key) {
        //如果具有缓存 则直接将缓存的优先级变为最优先
        if (map.containsKey(key)){
            makeRecently(key);
//            frequency[key]++;
            //返回这个缓存
            return map.get(key).value;
        }
        //否则直接返回不存在
        return -1;
    }
    // 加入缓存
    //如果这个缓存已经存在  将这个缓存的原先的key，value 并且添加最近的缓存的key,value
    public void put(int key, int value) {
        if (map.containsKey(key)){
            deleteKey(key);
            addRecently(key,value);
//            makeRecently(key);
            return;
        }
        //如果当前的缓存容量已经满了
        //删除最久没有使用的节点
        if (cache.getSize() == capacity){
            removeLeastRecently();
        }
        //缓存不存在 且 容量没有满 直接在最近的位置 添加这个缓存
        addRecently(key, value);
    }
    // 将某个key变为最近使用的
    //最近使用的 会被插入到结尾
    //最新被使用的节点会被放到最后位置
    private void makeRecently(int key){
        Node x = map.get(key);
        cache.remove(x);
        cache.addLast(x);
    }
    //添加这个缓存到最近的位置
    //              1. 链表在尾部添加节点
    //              2. map中也要添加 对应的(key,node)这个键值对
    private void addRecently(int key,int value){
        Node x = new Node(key, value);//根据键值对 构造一个双向链表中的节点
        cache.addLast(x);
        map.put(key,x);
    }
    //删除 key对应的 缓存
    private void deleteKey(int key){
        Node x = map.get(key);
        cache.remove(x);
        map.remove(key);
    }

    //删除最久没有使用的节点
    private void removeLeastRecently(){
        Node deletedNode = cache.removeFirst();//删除双向链表中的第一个节点
        int deletedKey = deletedNode.key;//获取最久没有使用的节点中的key
        map.remove(deletedKey);//将map中的映射节点删除
    }



}

























