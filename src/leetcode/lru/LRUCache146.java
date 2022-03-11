package leetcode.lru;

import java.util.LinkedHashMap;

public class LRUCache146 {
    //hash链表
    LinkedHashMap<Integer,Integer> cache = new LinkedHashMap<>();
    private int capacity;

    public LRUCache146(int capacity) {
        this.capacity = capacity;//构造方法
    }

    public int get(int key) {
        if (!cache.containsKey(key)){
            return -1;
        }
        //如果存在 则直接 将这个节点放到最新的位置
        makeRecently(key);
        return cache.get(key);
    }

    public void put(int key, int value) {
        //如果cache中已经包含了这个key 和 value
        //更新这个节点
        if (cache.containsKey(key)){
            cache.put(key,value);
            makeRecently(key);
            return;
        }
        if (cache.size() == capacity){
            cache.remove(removeOldest());//删除头部最久没有使用的key
        }
        cache.put(key,value);//在尾部添加
    }

    private  void makeRecently(int key){
        int value = cache.get(key);
        cache.remove(key);
        cache.put(key,value);//默认在最末尾添加节点
    }
    private int removeOldest(){
        return cache.keySet().iterator().next();//获取链表头部这个最久没有使用的节点

    }
}
