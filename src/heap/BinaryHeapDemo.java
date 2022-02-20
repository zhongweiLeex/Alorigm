package heap;

import java.util.Arrays;
/*
* 二叉堆
* */
public class BinaryHeapDemo<T extends Comparable<? super T>> {
    private static final int DEFAULT_CAPACITY = 10;
    private int currentSize;//堆中中的元素个数
    private T[] array; //堆存储列表
    /*
    * 构造方法
    * */
    public BinaryHeapDemo(){
        this(DEFAULT_CAPACITY);
    }
    /*
    * 构造方法
    * */
    @SuppressWarnings("unchecked")
    public BinaryHeapDemo(int capacity){
        currentSize = 0;
        array = (T[]) new Comparable[capacity + 1];//开辟一个新的存储空间
    }
    /*
    * 将无序的二叉树调整形成 堆
    * */
    @SuppressWarnings("unchecked")
    public BinaryHeapDemo(T[] items){
        currentSize = items.length;
        array = (T[]) new Comparable[(currentSize + 2)*11/10];//???
        int i = 1;

        for (T item : items) {//将传入的 数组调整成为 二叉堆  按照位置放入 但是没有顺序
            array[i++] = item;
        }
        buildHeap();//使用下滤将无序二叉树进行调整
    }
    /*
    * 建立堆 将无序的二叉树调整形成堆 进行排序
    * */
    private void buildHeap() {
        for (int i = currentSize/2; i > 0 ; i--) { //因为有 percolateDown 需要hole*2 所以只需要对一半长度操作就行
            percolateDown(i);
        }
    }
    /*
    * 将 第hole位置的元素 放入适当位置
    *
    * 先将hole位置的元素单独保存一个副本
    * 下滤 将当前位置 hole 空穴位置 从上往下 进行比较和交换位置 如果当前位置比孩子节点的元素小 则进行交换
    * 具体过程类似于  一个气泡慢慢向下冒  如果按照大小是正确位置  就相当于啥都没干就交换了 空泡和字节点的位置
    * */
    private void percolateDown(int hole) {
        int child;
        T temp = array[hole];//得到当前位置的元素

        for (;hole*2<=currentSize;hole = child){//hole*2 是子节点位置 如果当前还有左孩子的时候
            child = hole*2;//得到左孩子的位置
            if (child!= currentSize && array[child+1].compareTo(array[child])<0){//仍旧有右孩子 比较左右孩子
                child++;
            }
            if (array[child].compareTo(temp)<0){//如果孩子元素比当前hole位置的元素小 就将当前孩子元素上移到hole位置
                array[hole] = array[child];
            }else{
                break;
            }
        }
        array[hole] = temp;
    }

    /*
    * 使用上滤策略进行插入 从底层到顶层 使用 array[0]（空的） 作为哨兵
    * */
    public void insert(T x){
        if (currentSize == array.length - 1){//如果堆中元素个数最大了 则 新建一个原来数组长度两倍的新数组
            enlargeArray(array.length*2 -1);
        }
        int hole = ++currentSize;//建立的空穴 空穴位置位于最大的位置 最底端位置
        //寻找插入位置  将当前的要插入的元素 x 和当前空穴位置的父节点比较 如果比父节点小 则交换空穴位置和父节点位置 一步一步向上冒泡
        for(array[0] = x;x.compareTo(array[hole/2])<0;hole = hole/2){
            array[hole] = array[hole/2];//设定新的空穴位置位于 父节点位置
        }
        array[hole] = x;//经过循环找到了对应的位置  插入即可
    }
    /*
    * 扩展数组
    * */
    @SuppressWarnings("unchecked")
    public void enlargeArray(int newSize) {
        T[] old = array;
        array = (T[]) new Comparable[newSize+1];//新建一个新的数组
        System.arraycopy(old, 0, array, 0, old.length);//将旧数组内容复制到新建大数组中
    }
    /*
    * 找到最小的
    * */
    public T findMin(){
        if (isEmpty()){
            return null;
        }
        return array[1];
    }

    public T deleteMin(){
        if (isEmpty()){
            return null;
        }
        T minItem = findMin();
        array[1] = array[currentSize--];//将最低端的数据放到根节点
        percolateDown(1);//从最根部位置开始调整大小顺序
        return minItem;
    }
    private boolean isEmpty() {
        return currentSize <= 0;
    }
    public void makeEmpty(){
        currentSize = 0;
        Arrays.fill(array, null);
    }

    public static void main(String[] args) {
        BinaryHeapDemo<Integer> binaryHeapDemo = new BinaryHeapDemo<>();
        binaryHeapDemo.insert(1);
        binaryHeapDemo.insert(5);
        binaryHeapDemo.insert(4);
        binaryHeapDemo.insert(2);
        binaryHeapDemo.insert(7);
        while(!binaryHeapDemo.isEmpty()){
            System.out.println(binaryHeapDemo.deleteMin());
        }
        binaryHeapDemo.makeEmpty();
        System.out.println(Arrays.toString(binaryHeapDemo.array));
    }
}
































