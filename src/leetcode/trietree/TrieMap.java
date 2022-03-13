package leetcode.trietree;

import java.util.LinkedList;
import java.util.List;
/*
* 1. 增加 put(String ,V)    put(TrieNode<V>,String,V, int)
* 2. 删除 remove(String)   remove(TrieNode<V>,String,int)
* 3. 判断
*       3.1 判断是否在前缀为 prefix的键        hasKeyWithPrefix(String prefix)
*       3.2 判断是否存在                      containsKey(String key)
 * 4. 查
*       4.1 查key 返回对应值                    V  get(String key)
*       4.2 在所有键中 查找 query 的最长前缀     String longestPrefixOf(String query)
*       4.3 在所有键中 查找 query 的最短前缀     String shortestPrefixOf(String query)
*       4.4 查所有键中 查找 所有前缀为 query的键  List<String> keysWithPrefix(String prefix)
*       4.5 查找与所有通配符匹配的键           List<String> keysWithPrefix(String pattern)    traverse
* */
public class TrieMap<V> {
    private static final int R = 256;//定义 ASCII码数量
    //当前map中的键值对个数
    private int size = 0;
    //Trie根节点
    private TrieNode<V> root = null;

    //内部静态节点类
    private static class TrieNode<V>{
        V val = null;
        TrieNode<V>[] children = new TrieNode[R];
        //此处的索引才表示字符  不是索引下的数组元素表示字符
    }

    //从节点 node 开始搜索str
    //getNode 这个函数只能说明 以node为开始搜索的节点 的树中包含这个str
    //但是无法判断是否 是一个大的字符串的前缀子串  还是 真正的一个完整的字符串（在末尾添加一个val的字符串）
    //如果只是前缀  ， 中间存储的那个 val 是 null  没有 准确的值得
    // 这里的 有两种可能
    //      1. 真实存在的键 或者 完整的 字符串
    //      2. 真实目前map中的 键的前缀 或者  前缀字符串 不是完整的真实存在的  字符串
    //从node 结点开始搜索 key  如果存在 则返回对应节点 否则返回 null
    //返回的值 不管是不是 val 或者 null  都直接返回
    private TrieNode<V> getNode(TrieNode<V> node , String str){
        TrieNode<V> p = node;
        for (int i = 0; i < str.length(); i++) {
            //如果p等于null 了 则无法向下子结点搜寻了
            if (p == null){
                return null;
            }
            //向下搜索子结点
            char c = str.charAt(i);
            p = p.children[c];
        }
        return p;
        //返回的是 最终连接的那个节点的 children[] 这个数组中对应的c索引位置的值
        //有可能是 null null说明这个str 这个串 在树中只是一个前缀（一个完整字符串的一部分）
        //如果是一个完整字符串 则返回最终的那个 非空val的值
    }

    //搜索str对应的值 ，不存在 则返回 null
    //返回的值只有一种可能
    //  1.  真实的整个树
    //搜索 key对应的的值  就是最后的 那个value
    //最后的返回的value 肯定不可能是 null
    public V get(String str){
        //从 root 节点开始搜索 str
        TrieNode<V> node = getNode(root,str);//str 表示 字符串
        if (node == null || node.val == null){
            return null;
            //如果node为空 说明根本没有这个str （连子串都不存在）
            //如果node 不为空，但是 node.val == null 说明只是一个大的字符串的一部分
            // （因为如果存储了一个完整的大的字符串，则必须要在叶子节点位置放一个整数 val）
        }
        return node.val;
        //排除上面的情况  node  ！=null  && node.val != null
        // 才真正算是一个完整的字符串存储进去了
    }

    // 查看是否包含指定 键str
    //注意：这里判断 是 树中是否包含一个完完整整的 树 不是一个大的字符串的前缀
    //而是 一个在叶子节点处 挂着一个 val的完整的字符串枝条
    public boolean containStr(String str){
        return get(str) != null;
    }


    /*   查看所有键中是否 存在前缀为  prefix的键   */
    public boolean hasStrWithPrefix(String prefix){
        return getNode(root,prefix) !=null;
    }


    /*
    * 在所有键中寻找 输入字符串 query 的最短前缀
    * */
    public String shortestPrefixOf(String query){
        TrieNode<V> p = root;

        for (int i = 0; i < query.length(); i++) {
            if (p==null){
                return "";
            }
            //只要 val 不是空 就说明找到了实体键
            if (p.val != null){
                return query.substring(0,i);
            }
            char c = query.charAt(i);
            p = p.children[c];
        }

        if (p!=null && p.val != null){
            //query本身就是一个键
            return query;
        }
        return null;
    }

    /*在前缀树中搜索 query的最长前缀*/
    public String longestPrefixOf(String query){
        TrieNode<V>  p = root;
        int max_len = 0;//记录当前前缀子串的最大长度 ， 这个前缀子串要真正的出现在前缀树中
        for (int i = 0; i < query.length(); i++) {
            if (p == null){
                break;//如果当前p指针是个 空的 说明根本找不到
            }
            if (p.val != null){//说明找到了一个完整的前缀子串 更新当前的 max_len;
                max_len = i;
            }
            //继续向下搜索
            char c = query.charAt(i);
            p = p.children[c];
        }

        if (p != null  && p.val!=null){
            return query;//遍历完了  自己本身也是自己的一个子串 而且是最大的子串
        }
        return query.substring(0,max_len);
    }


    //在map中添加
    public void put(String str,V val){
        if (!containStr(str)){
            //如果map中已经存在了str 这个键， 则增加一个键值对个数
            size++;
        }
        //如果已经包含了 则只要使用put方法修改就行
        root = put(root,str,val, 0);
    }

    //向 node为根的 前缀树中插入/更新 str[i...] 返回插入完成后的根节点
    private TrieNode<V> put(TrieNode<V> node, String str,V val , int i){
        if(node == null){
            //因为是递归插入  node 可能不存在 那就直接新建一个 作为根节点
            node  = new TrieNode<>();
        }

        if (i== str.length()){
            //插入完成将最后的val设置成一个 val值 不能是空值  表示键插入结束
            node.val = val;
            return node;
        }
        char c = str.charAt(i);
        //递归插入子结点 ， 并接收返回值
        node.children[c] = put(node.children[c], str,val,i+1);
        return node;
    }

    // 删除键 str 和对应的值
    public void remove(String str){
        if (!containStr(str)){
            return;
        }
        root = remove(root,str,0);
        size--;
    }
    //以 node为根的 Trie树中删除str[i..]  返回删除后的根节点
    private TrieNode<V> remove(TrieNode<V> node,String str,int i){
        //base case
        if(node == null){
            return null;//当前节点已经是空的了 则直接返回null
        }
        if (i == str.length()){
            node.val = null;//找到了str 对应的TrieNode 删除最后的 val
        }else{
            //找到对应的字符对应的节点
            char c = str.charAt(i);
            //递归向下删除
            node.children[c] = remove(node.children[c],str,i+1);
        }
        //这里是后序位置 证明前面都被删除结束了

        //检查除了输入的 str之外又没有了 其他分支但是不是str的子树
        //后序位置
        if (node.val !=null){
            return node;
        }
        for (int c = 0; c<R;c++){
            if (node.children[c] != null){
                return node;
            }
        }
        //没有存储 val  也没有后缀树枝  这个节点需要被清理
        return null;
    }







    //搜素所有前缀 为 prefix的键(所有真实子树)
    public List<String> strWithPrefix(String prefix){
        List<String> result = new LinkedList<>();
        //此处只能使用 getNode
        //因为 要找的是  prefix ***** 这样的字符串  ， 此处使用 getNode 找到以prefix为前缀的字符串的开始节点
        //往后开始 DFS 找到所有 以 node 开始节点的  prefix后面的串
        TrieNode<V> node = getNode(root,prefix);
        //如果当前节点 开始搜索的节点 == null
        //无法向下搜素子结点了 说明停止了根本不存这个
        if ( node == null){
            return null;
        }
        //如果当前找到了 以prefix 为前缀的 则以这个节点开始深度遍历
        //DFS 算法遍历 以node为根的子树
        traverse(node,new StringBuilder(prefix),result);
        return null;
    }
    private void traverse(TrieNode<V> node,StringBuilder path,List<String> result){
        if(node == null){
            return;//到达前缀树的底部叶子节点
        }
        if (node.val != null){
            //找到一个真实的串  （键）
            result.add(path.toString());
        }
        //存在隐式转换
        //回溯遍历框架
        for(char c  = 0; c<R;c++){
            path.append(c);//回溯做选择
            traverse(node.children[c],path,result);
            path.deleteCharAt(path.length()-1);//回溯撤销选择
        }
    }

    // 按照模式串 查找匹配模式串的 真实子串
    public List<String> strsWithPattern(String pattern){
        List<String> result = new LinkedList<>();
        traverse(root,new StringBuilder(),pattern,0,result);
        return result;
    }
    //方法重载  不是重写
    private void traverse(TrieNode<V> node , StringBuilder path, String pattern , int i,List<String> result){
        if (node ==null){
            //如果树枝不存在
            return;
        }
        //模式串匹配结束, 就意味着匹配结束
        if (i == pattern.length()){
            if (node.val != null){
                result.add(path.toString());
            }
            return;
        }
        char c = path.charAt(i);
        if (c == '.'){//如果c是通配符 则 需要遍历所有的子结点 并DFS

            for (char j = 0; j < R; j++) {
//                if (node.children[j] == null){
//                    return;
//                }
                path.append(j);
                //未证实优化
//                TrieNode<V> newNode = node.children[j];
//                traverse(newNode,path,pattern,i+1,result);
                traverse(node.children[j], path,pattern,i+1,result);
                path.deleteCharAt(path.length()-1);
            }
        }else{
            //如果c是普通字符 则 遍历指定的 子结点
            path.append(c);
            traverse(node.children[c],path,pattern,i+1,result);
            path.deleteCharAt(path.length()-1);
        }
    }
    // 判断是否存在与模式串匹配的键
    public boolean hasStrWithPattern(String pattern){
        return hasStrWithPattern(pattern,root,0);
    }
    //方法重载 overload
    //从node 节点 开始 从模式串 pattern  的第 i个位置开始 匹配
    //不需要计算所有匹配的字符串  只需要判断是否存在匹配模式串
    private boolean hasStrWithPattern(String pattern,TrieNode<V> node,int i){
        if (node == null){
            return false;
        }
        if ( i == pattern.length()){
            //如果模式串都被匹配完成了
            //在检测最后的节点是不是真正的完整子串
            return node.val !=null;
        }
        char c = pattern.charAt(i);
        if (c != '.'){
            return hasStrWithPattern(pattern,node.children[c],i+1);
        }
        for (int j = 0; j < R; j++) {
            if (hasStrWithPattern(pattern,node.children[j],i+1 )){
                return true;
            }
        }
        return false;
    }

    //返回map中键值对数量
    public int size(){
        return size;
    }





}


