package fourthchapter;

import java.util.*;

public class BTree<K extends Comparable<K>,V> {
    public BTNode<K, V> root;
    public int degree;//度
    public int number;//节点数量

    public BTree(int degree) {
        if (degree < 2) {
            throw new IllegalArgumentException("degree must >= 2");
        }
        root = null;
        number = 0;
        this.degree = degree;
    }

    class BTNode<K extends Comparable<K>, V> {
        BTNode<K, V> parent;
        List<Entry<K, V>> children; //存放键值对的链表
        List<BTNode<K, V>> pointers; //子树指针链表

        /*
         * BTNode构造方法
         * */
        public BTNode() {
            parent = null;
            children = new LinkedList<Entry<K, V>>();
            pointers = new LinkedList<BTNode<K, V>>();
        }

        /*
         * 返回键值对的数量
         * */
        public int getKeyNum() {
            return children.size();
        }

        /*
         * 判断当前节点是否已经满了
         * */
        public boolean isFull() {
            return this.getKeyNum() == 2 * degree - 1;
        }

        public boolean isLeafNode() {
            return this.pointers.size() == 0;
        }

        /*
         * 判断当前节点的键值对数目是否符合约束条件
         * */
        public boolean isQualified() {
            int keyNum = this.getKeyNum();
            if (this != root) {
                if (keyNum < (degree - 1)) {
                    throw new IllegalArgumentException("The least keyNum is (degree - 1)");
                }
                if (keyNum > (2 * degree - 1)) {
                    throw new IllegalArgumentException("The most keyNum is (2*degree -1)");
                }
            }
            return true;
        }

        @Override
        public String toString() {
            return "BTNode [children=" + children + "]" + " count:" + this.getKeyNum();
        }
    }

    /*
     * 判断树是否为空
     * */
    public boolean isEmpty() {
        return size() == 0;
    }

    /*
     * 返回容量大小
     * */
    private int size() {
        return number;
    }

    /*
     * 内部类 键值对类
     * */
    private static class Entry<K extends Comparable<K>, V> {
        K k;
        V v;

        //构造方法
        public Entry(K k, V v) {
            this.k = k;
            this.v = v;
        }

        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof Entry) {
                Entry<?, ?> e = (Entry<?, ?>) o;
                if (Objects.equals(k, e.k) && Objects.equals(v, e.v))
                    return true;
            }
            return false;
        }

        public K getK() {
            return k;
        }

        public V getV() {
            return v;
        }

        @Override
        public String toString() {
            return "Entry[k=" + k + ",v=" + v + "]";
        }
    }

    /*
     * 查找方法:
     * 首先从当前节点查找
     * B树的节点有多个KEY 需要每个都进行比较，所以需要使用二分查找法加速节点中的查找
     *
     * @param BTNode<K,V> x  ---  某棵子树的 根节点
     * @param K key  --- 需要查找的key
     * */
    public Entry<K, V> search(BTNode<K, V> x, K key) {
        int i = 0;
        Entry<K,V> entry,next;
        //当i位置指定器 仍然在本节点内部
        while(i<x.getKeyNum()){
            entry = x.children.get(i);
            next = (i==x.getKeyNum()-1) ? null:x.children.get(i+1);


            //找到数组内的元素包含了目标元素
            if (key.equals(entry.getK())){
                return entry;
            }

            //如果键 比 本节店内最大的键还大 就放到子节点中 递归寻找
            if (key.compareTo(x.children.get(x.getKeyNum()-1).getK())>0){
                i = x.getKeyNum();
                break;
            }else{
                //如果比目前的节点的key还小 在子树中递归寻找
                if (key.compareTo(entry.getK())<0){
                    i = 0;
                    break;
                    //如果在目前节点中 且夹在目前的节点和下一个节点之间 则在之后的子结点中寻找
                }else if (key.compareTo(entry.getK()) > 0 && key.compareTo(next.getK())<0){
                    i = i+1;
                    break;
                }
            }
            //如果当前节点中不是目前这个节点，则向后遍历
            i++;
        }
        if (x.isLeafNode()){
            return null;
        }else {
            return search(x.pointers.get(i),key);
        }
    }
    /*
    * 插入
    * 1. 先判断当前节点的Entry是否已满
    * 2. 如果已经满了，就分裂
    * 3. 如果是叶子节点 则进行插入曹祖， 不是叶子节点 则向下递归
    * */
    private void insertAction(BTNode<K,V> current,Entry<K,V> entry){
        if (root == null){
            root = new BTNode<>();
            current = root;
        }
        //判断是否当前节点是否已经满了 如果满了 则分裂
        if (current.isFull()){
            current = split(current);
        }
        assert current != null;
        if (current.isLeafNode()){//如果是叶子节点
            int index = getPosition(current,entry);
            current.children.add(index,entry);
            number++;
            return;
        }else{ //如果不是叶子节点 则向下面的节点递归
            int index = getPosition(current,entry);
            insertAction(current.pointers.get(index),entry);
        }
    }
    ////待分析
    /*
     * 用于定位在链表中，新增Entry节点适合插入的位置
     * @param BTNode current    ---> 需要查找的节点
     * @param Entry<K,V> entry  ---> 需要定位的键值对
     * @return int index        ---> 键值对的索引值
     * */
    private int getPosition(BTNode<K, V> current, Entry<K, V> entry) {
        List<Entry<K,V>> list = current.children;
        int index = 0;
        Entry<K,V> entry1,next;
        for (int i = 0; i < list.size(); i++) {
            entry1 = current.children.get(i);
            next = (i ==current.getKeyNum()-1)?null:current.children.get(i+1);
            //如果需要找的entry的key比最大的那个key还大
            if (entry.k.compareTo(list.get(list.size()-1).k)>0){
                //返回最大的key索引
                index = list.size();
                break;
            }else{
                //如果在左边 则从最小的索引开始 因为getPosition() 是用来查看大概位置的 如果需要找的键值对是在子树中 需要从最左边的子树寻找
                if (entry.k.compareTo(entry1.k)<=0){
//                    if (i == 0){
//                        index = 0;
//                    }else{
//                        index = i-1;
//                    }
//                    break;
                    index = 0;
                    break;
                }
                //如果在右边则从当前位置的右边一个开始寻找 在中间
                else if (entry.k.compareTo(next.k)<=0){
                    //index = i+1;
                    index = i+1;
                    break;
                }
            }
        }
        return index;
    }


    public void insert(K key,V value){
        Entry<K,V> entry = new Entry<>(key,value);
        //从 root节点开始插入
        insertAction(root,entry);
    }

    /*
    * 节点分裂
    * 节点约束：
    * 1. 最多有 2t-1 key
    * 2. 最多有 2t 孩子节点
    * 分裂将其分为 t-1个key
    * 内部节点各t个孩子
    * 多余的一个节点插入到父节点中，作为分裂后两个节点的分割 key
    *
    *
    *
    * 实现：将当前节点的中间Entry节点插入到父结点中，将剩余的BTNode拆分
    *
    * 难点：
    * 1.绑定父节点和子节点的关系
    * 2.定义pointer 指针
    * */

    private BTNode<K, V> split(BTNode<K, V> current) {
        BTNode<K,V> parent;
        BTNode<K,V> left = new BTNode<>();
        BTNode<K,V> right = new BTNode<>();
        int len = current.getKeyNum();
        Entry<K,V> mid = current.children.get(len/2);
        for (int i = 0; i < len; i++) {
            //拆开的节点分别放入左右儿子节点中
            if (i<len/2){
                left.children.add(current.children.get(i));
            }
            if (i>len/2){
                right.children.add(current.children.get(i));
            }
        }
        if (current.isLeafNode()){
            if (current == root){ //如果当前节点是根节点
                parent = new BTNode<>();//新建一个空的parent
                parent.children.add(mid);//将拆开的中间节点直接插入到父节点中,作为新的父节点
                //子节点放到 pointers 中
                parent.pointers.add(left);
                parent.pointers.add(right);
                //设置子节点的父节点
                left.parent  = parent;
                right.parent = parent;

                root = parent; //设置root为parent
                current = root;//根节点为current

            }else{//当前节点不是根节点
                parent = current.parent;//当前parent为current的parent
                int position = this.getPosition(parent,mid);//找到当前mid在parent中的位置

                parent.pointers.remove(position);
                parent.pointers.add(position,left);
                parent.pointers.add(position+1,right);

                left.parent = parent;
                right.parent = parent;
                current= parent;
            }
        }else{//不是叶子节点
            if (current == root){//不是叶子节点的根节点
                parent = new BTNode<>();
                parent.children.add(mid);
                parent.pointers.add(left);
                parent.pointers.add(right);


                left.parent = parent;
                right.parent = parent;

                root = parent;

                //绑定子节点
                for (int i = 0; i < current.pointers.size(); i++) {
                    BTNode<K,V> node = current.pointers.get(i);
                    if (i<len/2){
                        left.pointers.add(node);
                        node.parent = left;
                    }else{
                        right.pointers.add(node);
                        node.parent = right;
                    }
                }
                current = root;
            }else{ //不是叶子节点的内部节点


                //绑定父节点
                parent = current.parent;
                //right.pointers.add(node);
                int position = this.getPosition(parent,mid);
                parent.children.add(position,mid);
                parent.pointers.remove(position);
                parent.pointers.add(position,left);
                parent.pointers.add(position+1,right);
                left.parent = parent;
                right.parent = parent;

                //绑定子节点
                for (int i = 0; i < current.pointers.size(); i++) {
                    BTNode<K,V> node = current.pointers.get(i);
                    if (i<len/2){
                        left.pointers.add(node);
                        node.parent = left;
                    }else{
                        right.pointers.add(node);
                        node.parent = right;
                    }
                }
                current = parent;
            }
        }
        return current;
    }

    public Entry remove(K key){
        Entry<K,V> entry = search(root,key);
        if (entry == null){
            return null;
        }
        return removeAction(root,entry);
    }

    /*
    * 删除动作
    *
    * step1：判断根节点是否只有一个元素，并且根节点的左右子节点的keyNum =? MIN 如果是 则执行 合并操作 否则step2
    * step2：通过key定位到当前节点的索引位，根据索引位置得到左子节点和右子节点
    *
    *     例如： C    G    N     --------- BT_Node1
    *
    *        AB   DE   JK  OP   --------- BT_Node2
    *     在BT_Node1中，通过key(G)找到Entry G所在的位置为1，然后得到G的左右子结点分别为：DE(左) 和 JK(右
    *
    * step3：如果删除的节点是父节点，判断当前节点是左子节点还是右子节点
    *     case1：如果左子节点和右子节点的keyNum都大于最小数 Min(degree-1)  取出左子树   最大元素 替换待删除的元素
    *     case2：如果左子节点的keyNum > min  右子节点 keyNum = Min       取出左子树  最大元素  替换待删除元素
    *     case3：如果左子节点的keyNum = Min  右子节点 keyNum > Min       取出右子树  最小元素  替换待删除元素
    *     case4：如果左右子节点keyNum < Min  执行merge（合并）操作
    * step4：递归到叶子节点，如果叶子节点的KeyNum > Min 直接删除，相反需要 执行merge合并操作
    *
    * */
    private Entry<K, V> removeAction(BTNode<K, V> parent, Entry<K, V> entry) {
        BTNode<K,V> left,right;
        Entry<K,V> replace;
        int index;
        //如果当前节点是 root节点 并且当前节点的只有一个元素
        if (parent == root && parent.getKeyNum()==1){
            left = parent.pointers.get(0);
            right = parent.pointers.get(1);
            //判断左右自己的keyNum是否 < Min 如果小于 则进行合并
            if (left.getKeyNum()==degree-1&& right.getKeyNum()==degree-1){
                parent = merge(root,entry,0);
            }
        }
        //判断是否是叶子节点
        //如果是叶子节点 且 keyNum > Min 则直接删除
        //如果是叶子节点 且 keyNum = Min 则需进行merge操作

        if (parent.isLeafNode()){                               //判断当前节点是否是叶子节点
            if (parent.getKeyNum() > degree -1){                //如果是叶子节点 且 KeyNum > MIN直接删除
                parent.children.remove(entry);;
            }else {                                            //如果是叶子节点  且 keyNum == Min 需要对其父节点进行merge操作
                BTNode<K,V> grand = parent.parent;             //将当前节点的父节点拿出来
                index = grand.pointers.indexOf(parent);        //获取当前节点在父结点中的索引
                System.out.println("index is " + index);
                Entry<K,V> entry1 = grand.children.get(index); //当前节点在其父结点中的 键值对
                grand = merge(grand,entry1,index);             //合并将 grand中的entry1 也合并到子结点中
                parent = grand==root?root:grand.pointers.get(index);
                parent.children.remove(entry);
            }
            return entry;
        }

        if (!parent.isLeafNode() && parent.children.contains(entry)){ //当前节点不是叶子节点 并且当前节点包含 需要删除的entry
            index = parent.children.indexOf(entry);
            left = parent.pointers.get(index);
            right = parent.pointers.get(index + 1);
            //左子节点和右子节点的 KeyNum都大于 Min ---> 取出左子树的最大元素替换需要删除的元素
            if (left.getKeyNum()>degree-1 && right.getKeyNum()>= degree -1){
                replace = findLeftReplaceEntry(left);
                parent.children.set(index,replace);
                left.children.remove(replace);
                return entry;
            }else if (left.getKeyNum() == degree -1 && right.getKeyNum()>degree -1){
                replace = findRightReplaceEntry(right);
                parent.children.set(index,replace);
                right.children.remove(replace);
                return entry;
            }else{
                parent = merge(parent,entry,index);
            }
        }
        //如果删除的不是中间父节点  则逐步向下递归
        index = getPosition(parent,entry);
        return removeAction(parent.pointers.get(index),entry);
    }
    /*
    * 找到右子树中最小的元素来替代上面需要删除的元素
    * @param BTNode<K,V> node  当前父节点
    * */
    private Entry<K, V> findRightReplaceEntry(BTNode<K, V> node) {
        BTNode<K,V> current = node;
        int keyNum;
        //如果当前节点不是叶子节点 意味着存在左子树和右子树
        while(!current.isLeafNode()){
            keyNum = current.getKeyNum();//获得当前节点的keyNum
            current = current.pointers.get(keyNum);//
        }
        return current.children.get(0);//
    }
    /*
    * 找到左子树中最大的元素
    * */
    private Entry<K,V> findLeftReplaceEntry(BTNode<K,V> node){
        BTNode<K,V> current = node;
        int keyNum;
        while (!current.isLeafNode()){
//            keyNum = current.getKeyNum();
            current = current.pointers.get(0);
        }
        return current.children.get(current.getKeyNum()-1);
    }

    /*
    * merge：entry回从parent中移动到merge后的新结点中去
    * @param BTNode<K,V> parent  ---> 当前节点
    * @param Entry<K,V>  entry   ---> 需要删除的元素
    * @param int index           ---> entry在parent中的索引位置
    * 例如：    C G L

     *       /  / \ \

     *     AB  DE JK NO

     * 对DE和JK做merge操作，结果：

     * 结果：   C   L

     *       /  /  \

     *     AB DEGJK NO
    * */
    private BTNode<K, V> merge(BTNode<K, V> parent, Entry<K, V> entry, int index) {
        BTNode<K,V> node; //合并后的子节点/ 如果当前节点是root 直接合成成为 root节点   如果当前节点不是root节点 则将其作为当前节点的子节点
        BTNode<K,V> left = parent.pointers.get(index);
        BTNode<K,V> right = parent.pointers.get(index + 1);
        //如果当前节点是根节点
        if (parent == root){
            node = new BTNode<>();
            //绑定children键值对
            node.children.addAll(left.children);
            node.children.add(entry);
            node.children.addAll(right.children);
            //如果左右子节点不是叶子节点
            if (!left.isLeafNode() && !right.isLeafNode()){
                //绑定pointers指针引用
                //意为合并当前节点parent的左右子节点
                node.pointers.addAll(left.pointers);
                node.pointers.addAll(right.pointers);
                //重新定义父子关系
                //左右父节点都设置为 node
                for (int i = 0;i<left.getKeyNum();i++){
                    left.pointers.get(i).parent = node;
                }
                for (int i = 0; i < right.getKeyNum(); i++) {
                    right.pointers.get(i).parent = node;
                }
            }
            //清空所有引用
            parent = null;
            left = null;
            right = null;
            //重新赋值root
            root = node;
            return root;
        }else{//如果当前节点不是根节点
            node = new BTNode<>();
            //重新绑定children键值对
            node.children.addAll(left.children);
            node.children.add(entry);
            node.children.addAll(right.children);

            //如果不是左右子节点不是叶子节点
            if (!left.isLeafNode() && !right.isLeafNode()){
                //绑定pointers指针引用
                node.pointers.addAll(left.pointers);
                node.pointers.addAll(right.pointers);
                //重新定义父子关系
                for (int i = 0;i<left.getKeyNum();i++){
                    left.pointers.get(i).parent = node;
                }
                for (int i = 0;i<right.getKeyNum();i++){
                    right.pointers.get(i).parent = node;
                }
            }
            //删除parent中的entry
            parent.children.remove(entry);
            parent.pointers.remove(left);
            parent.pointers.remove(right);
            //重新设置当前节点的子树连接 将  node安插到 index位置
            parent.pointers.add(index,node);
            return parent;
        }
    }
    public void frontIterator(BTNode<K,V> node){
        if (node.isLeafNode()){
            System.out.print(node);
            System.out.println();
        }else{
            System.out.print(node);
            for (int i = 0; i < node.pointers.size(); i++) {
                frontIterator(node.pointers.get(i));
            }
        }
    }

}
























