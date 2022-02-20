package maxpq;

//import java.security.Key;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;                               //基于堆的完全二叉树
    private int N = 0;                              //存储于 pa[1..N] pq[0] 没有使用
    /*
    * 描述： 构造二叉堆方法1
    * 参数： int maxN  二叉堆中有多少元素
    * */

    public MaxPQ(int maxN){                         //有参构造方法
        pq = (Key[]) new Comparable[maxN + 1];
    }
    /*
    * 描述：构造二叉堆方法2
    * 参数：
    * */

    /*
    * 描述：判断二叉堆是否是空
    * */
    public boolean isEmpty(){
        return N == 0;
    }

    /*
    * 描述：二叉堆元素个数
    * */
    public int size(){
        return N;
    }
    /*
    * 描述：插入元素
    * 参数： Key v
    * */
    public void insert(Key v){
        pq[++N] = v;
        swim(N);//排序
    }
    /*
    * 描述：比较第i个元素 和 第 j个 元素的大小
    * 参数1：int i 第i个元素
    * 参数2：int j 第j个元素
    * 返回值： boolean
    * */
    private boolean less(int i,int j){
        return pq[i].compareTo(pq[j])<0;
    }
    /*
    * 描述：交换方法
    * 参数1： int i 第i个元素
    * 残数2：int j 第j个元素
    * */
    private void exch(int i,int j){
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
    /*
    * 描述：由下至上的堆有序化  上浮
    *
    * 堆的有序化状态因为某个节点比 其父结点 更大 （堆有序化状态应该是 父结点 大小 比 左右子结点大）
    * 参数： int k  位置k的节点
    *
    * */
    private void swim(int k){
        while(k>1 && less(k/2,k)){//   k/2 是 父结点位置   k是节点位置   如果k不是根节点  且 当前节点比其父结点大 则打破了 堆有序化规则
            //迭代交换当前节点与其父结点位置  即上浮操作
            exch(k/2,k);
            k = k/2;
        }
    }




}
