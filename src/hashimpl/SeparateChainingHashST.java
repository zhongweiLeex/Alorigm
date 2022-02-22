package hashimpl;

/**
 * @author Zhongwei Li
 * @version 1.0
 * @apiNote 拉链法简单实现hash
 * */

public class SeparateChainingHashST<Key,Value> {
    private int M;
    private SeparateChainingHashST<Key,Value>[] st;//拉出来的链 使用数组实现

    public SeparateChainingHashST(int M){
        this.M = M;
        st = (SeparateChainingHashST<Key,Value>[]) new SeparateChainingHashST[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SeparateChainingHashST<>();
        }
    }

    public SeparateChainingHashST() {
        this(997);
    }
    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % M;//&0x7fffffff 是为了保证没有负数
    }
    public Value get(Key key){
        return st[hash(key)].get(key);
    }
    public void put(Key key,Value val){
        st[hash(key)].put(key,val);
    }
}
