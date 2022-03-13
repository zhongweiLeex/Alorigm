package leetcode.trietree;

public class TrieNode<V> {
    V val = null;//存储键对应的值
    //存储指向子节点的指针
    //此处 256 表示只处理 ascii码
    TrieNode<V>[] children = new TrieNode[256];//此处的索引是有意义的  不同于 多叉树的孩子节点的索引
}
