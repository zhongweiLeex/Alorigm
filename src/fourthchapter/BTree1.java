package fourthchapter;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BTree1<K,V> {

    private class Entry<K,V> {
        private K key;
        private V value;

        public void setKey(K key) {
            this.key = key;
        }

        public K getKey() {
            return this.key;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public V getValue(){
            return this.value;
        }

        @Override
        public String toString() {
            return "key=" + key + " , ";
        }
    }

        /*
        * 内部类
        * 搜索结果 封装进入对象中
        * */
        private class SearchResult<V>{

            private boolean isExist;
            private V value;
            private int index;
            //构造方法 将查询结构封装入对象
            public SearchResult(boolean isExist,int index,V value){
                this.isExist = isExist;
                this.index = index;
                this.value = value;
            }

            public boolean isExist(){
                return isExist;
            }
            public V getValue(){
                return value;
            }
            public int getIndex(){
                return index;
            }

        }

        /*
        * 节点类 匿名内部类
        * */
        public class Node<K,V>{
            //节点内的项
            private List<Entry<K,V>> entries;
            //节点的孩子节点
            private List<Node<K,V>> sons;
            //指示是否是孩子节点
            private boolean isLeaf;
            //键值比较函数
            private Comparator<K> kComparator;

            //比较两个key 如果没有传入的自定义的排序方式 则采用默认的升序
            private int compare(K key1,K key2){
                return this.kComparator == null?((Comparable<K>) key2).compareTo(key1):kComparator.compare(key1,key2);
            }
            //节点构造方法
            Node(){
                this.entries = new LinkedList<>();
                this.sons = new LinkedList<>();
                this.isLeaf = false;
            }
            //自定义K排序方式的构造函数
            Node(Comparator<K>  kComparator){
                this();
                this.kComparator = kComparator;
            }
            //设置叶子节点标志位
            public void setIsLeaf(boolean isLeaf){
                this.isLeaf = isLeaf;
            }
            //获得是否是叶子节点
            public boolean getIsLeaf(){
                return this.isLeaf;
            }

            //返回本节点的项数
            public int nodeSize(){
                return this.entries.size();
            }

            /*
            * @param: K key  ---> 待查找元素的key值
            * @return：查找结果封装入 SearchResult
            * @Exception：
            * @Description：在本节店中查找元素，本质就是一个有序数组的二分查找
            * */
            public SearchResult<V> search(K key){
                int begin = 0;
                int end = this.nodeSize() -1;
                int mid = (begin + end)/2;
                boolean isExist = false;
                int index = 0;
                V value = null;

                while (begin < end){
                    mid = (begin + end) /2;
                    Entry<K,V> midEntry = this.entries.get(mid);
                    int compareRe = compare((K) midEntry.getKey(),key);
                    //找到了
                    if (compareRe == 0){
                        break;
                        //在中点右边
                    }else if (compareRe > 0){
                        begin = mid + 1;
                    }else{
                        end = mid -1;
                    }
                }
                //二分查找结束 判断结果， 需要考虑边界条件 一个节点中只有两个或者一个元素
                //找到了
                if (begin < end){
                    isExist = true;
                    index = mid;
                    value = this.entries.get(mid).getValue();
                }else if (begin == end){
                    K midKey = this.entries.get(begin).getKey();
                    int comRe = compare(midKey,key);
                    //找到了
                    if (comRe == 0){
                        isExist = true;
                        index = begin;
                        value = this.entries.get(mid).getValue();
                    }else if (comRe > 0){
                        isExist = false;
                        index = begin + 1;
                        value = null;
                    }else {
                        isExist = false;
                        index = begin;
                        value = null;
                    }
                }else {
                    isExist = false;
                    index = begin;
                    value = null;
                }
                return new SearchResult<V>(isExist,index,value);
            }
            //删除给定索引位置的项
            public Entry<K,V> removeEntry(int index){
                Entry<K,V> re = this.entries.get(index);
                this.entries.remove(index);
                return re;
            }
            //得到索引位置的项
            public Entry<K,V> entryAt(int index){
                return this.entries.get(index);
            }
            //将新项插入指定位置
            private void insertEntry(Entry<K,V> entry,int index){
                this.entries.add(index,entry);
            }
            //节点内插入项
            private boolean insertEntry(Entry<K,V> entry){
                SearchResult<V> result = search(entry.getKey());
                //如果已经存在则返回false 插入失败
                if (result.isExist()){
                    return false;
                }else {
                    insertEntry(entry,result.getIndex());
                    return true;
                }
            }
            //更新项 如果项存在 更新值并返回原值  否则直接插入
            public V putEntry(Entry<K,V> entry){
                SearchResult<V> re = search(entry.getKey());
                if (re.isExist){
                    Entry oldEntry = this.entries.get(re.getIndex());
                    V oldValue = (V) oldEntry.getValue();
                    oldEntry.setValue(entry.getValue());
                    return oldValue;
                }else {
                    insertEntry(entry);
                    return null;
                }
            }
            //获得指定索引的子节点
            public Node<K,V> childAt(int index){
                return this.sons.get(index);
            }
            //删除指定索引的子节点
            public void removeChild(int index){
                this.sons.remove(index);
            }
            //将新的子节点插入到指定位置
            public void insertChild(Node<K,V> child,int index){
                this.sons.add(index,child);
            }
        }

        //读属 不传入参数 默认是 2-3 树
        private int DEFAULT_T  = 2;
        private Node<K,V> root;
        private int t = DEFAULT_T;
        //非根节点的最小项数
        private int nodeMinSize = t-1;
        //节点的最大项数
        private int nodeMaxSize = 2*t -1;
        //比较函数对象
        private Comparator<K> kComparator;
        //构造方法
        BTree1(){
            Node<K,V> root = new Node<>();
            this.root = root;
            root.setIsLeaf(true);
        }
        //构造一个度为t的b树
        BTree1(int t){
            this();
            this.t = t;
            nodeMinSize = t-1;
            nodeMaxSize = 2*t -1;
        }
        //构造一个按照给定排序方式，度为t的b树
        BTree1(Comparator<K> com,int t){
            this(t);
            this.kComparator = com;
        }
        //在以root为根的树内搜索key项
        private V search(Node<K,V> root,K key){
            SearchResult<V> re = root.search(key);
            //如果直接找到返回 对应的key的值
            if (re.isExist){
                return re.value;
            }else {
                //尾递归回归条件
                if (root.isLeaf){
                    return null;
                }
                int index = re.index;
                //递归查找子节点
                return (V) search(root.childAt(index),key);
            }
        }
        public V search(K key){
            return search(this.root,key);
        }

        /*
        * @Param1：fatherNode   待分裂的父节点
        * @param2: splitNode    待分裂的节点
        * @param3: index        待分裂节点在父节点中的索引
        * @Description: 从中间断开，后半部分形成新的节点插入父节点，如果分裂节点不是叶子节点，将子节点一并分裂到新节点中
        * */
        private void splitNode(Node<K,V> fatherNode,Node<K,V> splitNode,int index){
            //分裂产生的新节点
            Node<K,V> newNode = new Node<>(this.kComparator);
            //如果源节点是叶子节点，新节点也是叶子节点
            newNode.setIsLeaf(splitNode.isLeaf);
            //将t到2*t-2 (从0 开始嘛 最后就是 -2)项迁移到新节点
            for (int i = t; i < this.nodeMaxSize; i++) {
                newNode.entries.add(splitNode.entries.get(i));
            }
            //中间节点向上融合到父节点的index+1
            Entry<K,V> midEntry = splitNode.entries.get(t-1);
            //删除节点中已经迁移的项，删除时注意从尾部向前删除
            for (int i = this.nodeMaxSize-1; i >= t; i--) {
                splitNode.entries.remove(i);
            }
            //如果分裂的节点不是叶子节点 子节点跟着一起分裂
            if (!splitNode.getIsLeaf()){
                //子节点要多一个 所以是 nodeMaxSize + 1
                for (int i = t; i < this.nodeMaxSize+1; i++) {
                    newNode.sons.add(splitNode.sons.get(i));
                }
                //从后往前删除原有节点的后面的内容
                for (int i = this.nodeMaxSize;i >= t;i--){
                    splitNode.sons.remove(i);
                }
                //父节点插入分裂的中间元素 分裂出的新节点加入父节点的sons
                fatherNode.insertEntry(midEntry);
                fatherNode.insertChild(newNode,index+1);
            }
        }

        /*
        * @Param current:当前节点
        * @Param entry:待插入的元素
        * @Description
        * 插入一个非满节点：一路向下找插入位置
        * 在寻找的路径上，如果大小时2t-1的节点，分裂并向上融合（因为如果插入节点导致下层节点的向上融合，如果是2t-1就会导致数据超过限制）
        * 知道查找到叶子节点直接插入
        * */
        private boolean insertNotFull(Node<K,V> current,Entry<K,V> entry){
            //如果当前节点是叶子节点则直接插入
            if (current.getIsLeaf()){
                return current.insertEntry(entry);
            }

            //根据输入的 entry的key寻找当前节点中是否存在
            SearchResult<V> re = current.search(entry.getKey());
            //如果已经存在key 直接返回false 插入失败
            if (re.isExist){
                return false;
            }
            //如果不存在 则返回当前需要超找的节点的entry的最接近插入位置的index
            int index = re.getIndex();
            //在当前节点的index位置的子节点
            Node<K,V> searchChild = current.childAt(index);
            //如果查询的子节点已经满了，则先分裂后再判断该搜索哪个子节点
            if (searchChild.nodeSize() == 2*t -1){
                //在当前节点从index作为中间节点放到父节点current中 ，其他子节点开始分裂
                splitNode(current,searchChild,index);
                //如果当前节点的index位置的key比想要寻找的entry的key大在从当前节点的index + 1位置的子节点中寻找插入位置
                if (current.compare(current.entryAt(index).getKey(),entry.getKey())>0){
                    searchChild = current.childAt(index+1);
                }
            }
            //递归到下个子结点中寻找并插入
            return insertNotFull(searchChild,entry);
        }
        //插入一个新结点
        public boolean insertNode(Entry<K,V> entry){
                //如果根节点满了，先分裂根节点
                if (root.nodeSize() == 2*t -1){
                    Node<K,V> newRoot = new Node<>();
                    newRoot.setIsLeaf(false);
                    //在newRoot节点的0位置索引插入子节点 root
                    newRoot.insertChild(root,0);
                    //父节点newRoot, 待分裂节点 root  带分裂节点在父节点的索引 0
                    splitNode(newRoot,root,0);
                    //设置根节点为 newRoot
                    this.root = newRoot;
                }
                //如果根节点没有满 从根节点开始寻找插入位置 如果遇到满了的 先分裂后在子结点中查找
                return insertNotFull(root,entry);
        }
        /*
         *  更新不满的节点的方法
         *  @Description: 如果key已经存在，更新value，否则直接插入entry
         * */
        private V putNotFull(Node<K,V> current,Entry<K,V> entry){
            assert current.nodeSize() < nodeMaxSize;
            //如果当前是叶子节点 直接更新/直接插入entry
            if (current.isLeaf){
                return current.putEntry(entry);
            }
            SearchResult<V> re = current.search(entry.getKey());
            //如果当前的entry的key已经存在 则直接在index位置直接更新值
            if (re.isExist){
                current.entryAt(re.index).setValue(entry.getValue());
                return re.value;
            }
            //如果不存在 则继续向下继续搜索， 但是需要先判断子节点是否需要分裂
            Node<K,V> searchChild = current.childAt(re.index);
            //如果向下搜索时 发现 子节点满了 需要分裂
            if (searchChild.nodeSize() == 2*t -1){
                //父节点设置为当前节点   需要搜索的子节点 searchChild   分裂位置  re.index
                splitNode(current,searchChild,re.index);
                if (current.compare(entry.getKey(),current.entryAt(re.index).getKey())>0){
                    searchChild = current.childAt(re.index + 1);
                }
            }
            return putNotFull(searchChild,entry);
        }
        //如果树中已经存在key 则更新并返回原value 否则插入并返回null
        //先考虑满节点 后针对不满的节点
        public V put(Entry<K,V> entry){
            //如果根节点已经满了，先分裂根节点
            if (this.root.nodeSize() == nodeMaxSize){
                Node<K,V> newRoot = new Node<>(kComparator);
                newRoot.setIsLeaf(false);
                newRoot.insertChild(root,0);
                splitNode(newRoot,root,0);
                this.root = newRoot;
            }
            //如果根节点不满 则直接从根节点开始 搜索并且更新
            return putNotFull(root,entry);
        }
        //删除内容
        private Entry<K,V> delete(Node<K,V> current,Entry<K,V> entry){
            SearchResult<V> re = current.search(entry.getKey());
            //如果找到了
            if (re.isExist()){
                //如果当前节点是叶子节点 直接删除
                if (current.getIsLeaf()){
                    //直接删除对应index位置的entry
                    return current.removeEntry(re.getIndex());
                }

                //如果不是叶子节点 判断应该删除的是左子节点还是右子节点
                //如果不是叶子节点  就将当前找到的位置的entry换到最后的叶子节点中并且删除
                //获取当前index位置的子节点

                //从左子树中删除
                Node<K,V> leftChild = current.childAt(re.getIndex());
                //如果左子节点包含多于t-1（最小个数）个项，转移到左子节点删除
                if (leftChild.nodeSize() >= t){
                    //删除过程为，将待删除项与其左子节点最后一项互换，并递归互换下去，直到将待删除节点换到叶子节点后删除
                    current.removeEntry(re.getIndex());
                    current.insertEntry(leftChild.entryAt(leftChild.nodeSize() - 1),re.getIndex());

                    leftChild.removeEntry(leftChild.nodeSize()-1);
                    leftChild.insertEntry(entry);
                    return delete(leftChild,entry);
                }

                //从右子树中删除
                Node<K,V> rightChild = current.childAt(re.getIndex() + 1);
                if (rightChild.nodeSize() >= t){
                    current.removeEntry(re.getIndex());
                    //从右子树中的最前面的一项开始互换 并且递归循环删除最后的子节点中的entry
                    current.insertEntry(rightChild.entryAt(0),re.getIndex());
                    rightChild.removeEntry(0);
                    rightChild.insertEntry(entry);
                    return delete(rightChild,entry);
                }


                //如果左右子节点均不能删除项
                //将左右子节点合并，并将删除项放到新结点的合并连接处
                Entry<K,V> deletedEntry = current.removeEntry(re.getIndex());//需要删除的项
                leftChild.insertEntry(deletedEntry);//讲删除的项放到左子节点的最后
                current.removeChild(re.getIndex()+1);//将指定索引的子节点删除 就是右边的子节点的删除 以便合并右子节点
                //将右子节点的所有节点合并到左子节点中
                for (int i = 0; i < rightChild.nodeSize(); i++) {
                    leftChild.insertEntry(rightChild.entryAt(i));
                }
                //右子节点存在子节点，则子节点也合并入左子节点子节点集合中
                if (!rightChild.getIsLeaf()){
                    for (int i = 0; i < rightChild.sons.size(); i++) {
                        leftChild.insertChild(rightChild.childAt(i),leftChild.sons.size());
                    }
                }
                //合并后继续向左递归 向左递归的原因是 将右子树合并到左子树中了
                 return delete(leftChild,entry);


            }else {//在本节点未找到删除元素 去子节点继续寻找

                //删除节点不在本节点
                //回归条件 搜索到叶节点依然没找到，待删除节点不在树中
                //如果当前是叶子节点  并且没有找到 直接返回没有找到
                if (current.getIsLeaf()){
                    for (int i = 0; i < current.nodeSize(); i++) {
                        System.out.println("+++++++++++++++++");
                        System.out.println(current.entryAt(i).getKey() + ", ");
                        System.out.println("+++++++++++++++++");
                    }
                    throw new RuntimeException(entry.key + " is not in this tree!");
                }


                //如果当前不是叶子节点
                //找到当前节点索引位置的儿子节点
                Node<K,V> searchChild = current.childAt(re.index);
                //子节点可删除项 递归删除
                //如果子节点的节点 尺寸大于 t 直接递归删除
                if (searchChild.nodeSize() >= t){
                    return delete(searchChild,entry);
                }

                //如果子节点的节点尺寸不支持删除
                //待旋转节点 子节点项数小于等于 t-1 不能删除项，准备左旋或右旋为其补充项数
                //尝试让子节点的左右兄弟节点旋转匀出一个元素给子节点
                Node<K,V> siblingNode = null;//待旋转节点
                int siblingIndex = -1;
                //存在右兄弟
                if (re.getIndex() < current.nodeSize() - 1){
                    //获取右子节点
                    Node<K,V> rightBrother = current.childAt(re.getIndex()+1);
                    //右子节点可以删除项
                    if (rightBrother.nodeSize() >= t){
                        siblingNode = rightBrother;
                        siblingIndex = re.getIndex() + 1;
                    }
                }
                //不存在右兄弟 尝试左兄弟
                if (siblingNode == null){
                    if (re.getIndex() > 0){
                        //尝试左兄弟节点
                        Node<K,V> leftBrother = current.childAt(re.getIndex() - 1);
                        if (leftBrother.nodeSize() >= t){
                            siblingNode = leftBrother;
                            siblingIndex = re.getIndex() -1;
                        }
                    }
                }

                //至少有一个兄弟 可以匀出一个元素
                if (siblingNode!=null){
                    //是左兄弟匀出的节点
                    if (siblingIndex < re.getIndex()){
                        //左节点最后一项右旋
                        searchChild.insertEntry(current.entryAt(siblingIndex),0); //将原有父节点的项插入到子节点的开头
                        current.removeEntry(siblingIndex);//删除父节点中被借的项
                        current.insertEntry(siblingNode.entryAt(siblingNode.nodeSize()-1),siblingIndex);//左兄弟的子节点中最大的项放入父节点想找的那个项的位置中
                        siblingNode.removeEntry(siblingNode.nodeSize()-1);//把原来左兄弟的借的项删除
                        //子节点跟着右旋
                        if (!siblingNode.getIsLeaf()){
                            searchChild.insertChild(siblingNode.childAt(siblingNode.sons.size()-1),0);
                            siblingNode.removeChild(siblingNode.sons.size()-1);
                        }
                    }else {//是右兄弟匀出的项
                        searchChild.insertEntry(current.entryAt(re.getIndex()),searchChild.nodeSize()-1);//将原有父节点的项插入到末尾
                        current.removeEntry(re.getIndex());//删除父节点中被借的项
                        current.insertEntry(siblingNode.entryAt(0),re.getIndex());//右兄弟的子节点中最小的节点放入想找的项的位置（父节点中想找的那个项）
                        siblingNode.removeEntry(0);//把右兄弟的子节点中借的项删除
                        //子节点跟着左旋
                        if (!siblingNode.getIsLeaf()){
                            searchChild.insertChild(siblingNode.childAt(0),searchChild.sons.size());
                            siblingNode.removeChild(0);
                        }
                    }
                    return delete(searchChild,entry);
                }
                //左右兄弟都运不出来项 直接由左右兄弟节点与父亲项合并为一个节点
                if (re.getIndex() <= current.nodeSize() - 1){//存在右兄弟
                    Node<K,V> rightSon = current.childAt(re.getIndex()+1);
                    searchChild.insertEntry(current.entryAt(re.getIndex()),searchChild.nodeSize());
                    current.removeEntry(re.getIndex());
                    current.removeChild(re.getIndex()+1);//将指定索引的子节点删除 就是右边的子节点的删除 以便合并右子节点
                    for (int i = 0; i < rightSon.nodeSize(); i++) {
                        searchChild.insertEntry(rightSon.entryAt(i));
                    }
                    if (!rightSon.getIsLeaf()){
                        for (int j = 0;j < rightSon.sons.size();j++){
                            searchChild.insertChild(rightSon.childAt(j),searchChild.sons.size());
                        }
                    }
                    if (current == this.root){
                        this.root = searchChild;
                    }


                }else{//没有右兄弟 尝试左兄弟
                    Node<K,V> leftSon = current.childAt(re.getIndex()-1);
                    searchChild.insertEntry(current.entryAt(re.getIndex()-1),0);
                    current.removeChild(re.getIndex()-1);
                    current.removeEntry(re.getIndex()-1);
                    for (int i = 0; i < leftSon.nodeSize(); i++) {
                        searchChild.insertEntry(leftSon.entryAt(i));
                    }
                    if (!leftSon.getIsLeaf()){
                        for (int i = leftSon.sons.size()-1; i >= 0 ; i--) {
                            searchChild.insertChild(leftSon.childAt(i),0);
                        }
                    }
                    if (current == this.root){
                        this.root = searchChild;
                    }
                }
                return delete(searchChild,entry);
            }
        }
        public Entry<K,V> delete(K key){
            Entry<K,V> en = new Entry<>();
            en.setKey(key);
            return delete(root,en);
        }

        public void output() {
            Queue<Node<K, V>> queue = new LinkedList<Node<K, V>>();
            queue.offer(this.root);
            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();
                for (int i = 0; i < node.nodeSize(); ++i) {
                    System.out.print(node.entryAt(i) + " ");
                }
                System.out.println();
                if (!node.getIsLeaf()) {
                    for (int i = 0; i <= node.sons.size() - 1; ++i) {
                        queue.offer(node.childAt(i));
                    }
                }
            }
        }
}





























