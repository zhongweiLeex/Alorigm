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
        pq[++N] = v;//先将元素插入到末尾
        swim(N);// 然后从末尾位置开始swim上浮调整
    }

    /*
    * 二叉堆（最大堆）删除最大元素
    * */
    public Key delMax(){
        Key max = pq[1];//根据二叉堆的性质 从根节点处获得最大元素
        exch(1,N--);//将最大的与最小的元素交换位置  并 在交换位置之后 将 元素个数 -1
        pq[N+1] = null;//将原本最后的元素 置空 防止越界
        sink(1);//自下而上的 恢复堆的有序性
        return max;
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
            k = k/2;//持续向上调整 层数向上
        }
    }
    /*
    * 描述： 由上至下的堆有序化  下沉
    *
    * 如果堆有序化状态 因为 某个节点比两个子结点或是其中之一更小 打破
    * 可以通过 将 此节点 与 节点的两个子结点中较大者交换来恢复堆
    *
    * 参数：int k 第k个节点
    * */
    private void sink(int k){
        while(2*k <= N){//如果 k 的左子节点还没有 超过最大个数
            int j = 2*k;//左子结点 持续向下寻找应该调整的位置  层数向下

            if (j<N && less(j,j+1)){ j++; }//持续向右寻找应该调整的位置   个数向右

            if (!less(k,j)){// 如果 当前节点 比 子结点大了 则停止向下寻找  找到插入位置了
                break;
            }
            exch(k,j);//将当前节点与子结点较小的节点交换
            k = j;
        }
    }




}
