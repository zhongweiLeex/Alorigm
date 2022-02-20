package heap;
/*
*
* 使用链表数据结构存储
* */
public class LeftHeap<T extends Comparable<? super T>> {

    private HeapNode<T> root;
    /*
     * 私有内部类 堆节点类
     * */
    private static class HeapNode<T> {
        int npl;//零路径长
        HeapNode<T> left;
        HeapNode<T> right;
        T element;
        public HeapNode(T element){
            this(element,null,null);
        }
        public HeapNode(T element,HeapNode<T> left,HeapNode<T> right){
            this.element = element;
            this.left = left;
            this.right = right;
            npl = 0;
        }
        //LeftHeap heap = new LeftHeap();
    }

    /*
    *  左式堆构造方法
    * */
    public LeftHeap(T element){
        root = new HeapNode<T>(element);
    }

    /*
    * 合并h1 树和h2 树
    * */
    private HeapNode<T> internalMerge(HeapNode<T> h1, HeapNode<T> h2) {
        //如果h1是空得 直接返回 h2
        if (h1 == null){
            return h2;
        }
        //如果h2 是空  直接返回 h1
        if (h2 == null){
            return h1;
        }

        HeapNode<T> result = null;
        //以根值小得为主体树 合并之后 得新的树 作为根值大的树的右子树并且返回
        if (h1.element.compareTo(h2.element)<0){//h2 的根值大
            h1.right = internalMerge(h1.right,h2);//递归的合并  将返回的合并后的树 作为根值小的树的右子树
            result = h1;
        }else{
            h2.right = internalMerge(h2.right,h1);
            result = h2;
        }
        //如果不满足结构性，调整  大的npl的树 作为左子树
        int leftNPL = result.left == null ? -1:result.left.npl;
        int rightNPL = result.right == null? -1:result.right.npl;
        if (leftNPL < rightNPL){
            HeapNode<T> temp = result.right;
            result.right = result.left;
            result.left = temp;
        }

        result.npl = Math.min(leftNPL,rightNPL) + 1;
        return result;

    }
    /*
    * 插入就是一个合并过程
    * */
    /*
     *
     * */
    public void merge(LeftHeap<T> heap){
        if (heap!=null){
            root = internalMerge(root,heap.root);
        }
    }

    public void insert(T element){
        LeftHeap<T> heap = new LeftHeap<>(element);
        merge(heap);
    }
    //删除最小的元素 按照结构性质 则是删除 根节点
    public T deleteMin(){
        if (root == null){
            return null;
        }
        //获取最小的节点  root
        HeapNode<T> node = root;
        //删除之后进行合并 并调整堆序
        root = internalMerge(root.left,root.right);
        //返回元素
        return node.element;
    }
    /*
    * 判断是否为空
    * */
    public boolean isEmpty(){
        return root == null;
    }
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        //@SuppressWarnings("unchecked")
        LeftHeap heap = new LeftHeap(2);
        for (int i = 1; i < 10; i++) {
            heap.insert(i);
        }
        while(!heap.isEmpty()){
            System.out.println(heap.deleteMin());
        }
    }
}
