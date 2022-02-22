package hashimpl;
/**
 * @author Zhongwei Li
 * @version  1.0
 * @apiNote 线性探测法hash  迭代版本 添加 二次hash 以及 平方探测法
 *
 * */
public class LinearProbingHashST<Key,Value> {
    private Key[] keys;
    private Value[] values;
    private int M;//线性探测表的大小
    private int N;//线性探测表中键值对的数量

    /*
    * 构造方法
    * */
    public LinearProbingHashST() {
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];
    }

    /*
    * 计算hash值
    * */
    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % M;
    }

    /*
    * 重新调整数组大小
    * */
    private void resize(int M){
        this.M = M;
        Key[] tempKey = (Key[]) new Object[M];
        Value[] tempValue = (Value[]) new Object[M];
        //将原来数组内容复制到临时数组中
        for (int i = 0; i < M; i++) {
            tempKey[i] = keys[i];
            tempValue[i] = values[i];
        }
        keys = tempKey;
        values = tempValue;
    }
    /*
    * 找到指定元素
    * */
    public Value get(Key key){
        int hashValue = hash(key);//计算hashcode
        if (keys[hashValue]!=null && !keys[hashValue].equals(key)){//如果当前hashValue满足要求
            while (keys[hashValue]!=null && keys[hashValue]!=key){//如果没找到 因为是线性探测法 所以要向数组后面寻找
                hashValue = (hashValue+1)/M;
            }
        }
        return values[hashValue];
    }
    /*
    * 存入元素
    * */
    public void put(Key key,Value value){
        //如果当前数组中的元素个数 大于 M/2 则需要扩容
        if (N >= M/2){
            resize(M*2);
        }

        int hashValue = hash(key);//计算key的hash值
        //如果当前hashValue位置是空的 则直接存入
        if (values[hashValue] == null){
            keys[hashValue] = key;
            values[hashValue] = value;
            N++;
        }else{//当前位置不是空的 则需要向后探测
            while(values[hashValue] !=null){
                hashValue = (hashValue+1)%M;//向后探测
            }
            keys[hashValue] = key;
            values[hashValue] = value;
            N++;
        }
    }
    /*
    * 删除键值对
    * */
    public Value delete(Key key){
        int hashValue = hash(key);//计算hash值
        if (keys[hashValue] == null){//如果当前key计算得到的hashValue值空的 则直接返回null
            return null;
        }else if (keys[hashValue].equals(key)){
            //直接置空
            keys[hashValue] = null;
            values[hashValue] = null;
            N--;
            return values[hashValue];
        }else {
            while (keys[hashValue]!=null && !keys[hashValue].equals(key)){
                hashValue = (hashValue+1)%M;//向后探测
            }
            keys[hashValue] = null;
            values[hashValue] = null;
            N--;
            return values[hashValue];
        }
    }

}
