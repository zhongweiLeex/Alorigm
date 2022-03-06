package leetcode.binarytree;

import java.util.Arrays;
import java.util.LinkedList;

/*
序列化是将一个数据结构或者对象转换为连续的比特位的操作，
进而可以将转换后的数据存储在一个文件或者内存中，
同时也可以通过网络传输到另一个计算机环境，
采取相反方式重构得到原数据。

请设计一个算法来实现二叉树的序列化与反序列化。
这里不限定你的序列 / 反序列化算法执行逻辑，
你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

链接：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
* */
public class Serialize297 {
    // Encodes a tree to a single string.
    // 编码  （序列化）
    String SEP = ",";
    String NULL = "#";
//    StringBuffer
    public String serialize(TreeNode root) {
        StringBuilder stringBuilder = new StringBuilder();
        //调用序列化函数
        serialize(root,stringBuilder);
        return stringBuilder.toString();
    }
    // 将 Tree中的元素 构造成 字符串 使用 ，分割
    // null 使用 # 表示
    private void serialize(TreeNode root,StringBuilder stringBuilder){
        //basecase
        if (root == null){
            stringBuilder.append(NULL).append(SEP);
            return;
        }
        //实际的序列化处理
        stringBuilder.append(root.val).append(SEP);

        serialize(root.left,stringBuilder);//递归对左子树序列化
        serialize(root.right,stringBuilder);//递归对右子树序列化
    }

    // Decodes your encoded data to tree.
    // 解码  （反序列化）
    public TreeNode deserialize(String data) {
        //先将String转变成 LinkedList
        LinkedList<String> linkedList = new LinkedList<>(Arrays.asList(data.split(SEP)));
        return deserialize(linkedList);
    }
    private TreeNode deserialize(LinkedList<String> linkedList){
        //basecase
        if (linkedList.isEmpty()){
            return null;
        }
        //实际的反序列化处理
        //最左侧就是根节点 因为前面是 先序遍历
        String val = linkedList.removeFirst();
        //从序列头部拿出一个元素 如果这个元素是 “#” 则直接退出  因为到这个子树 到底了
        if (val.equals(NULL)){
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(val));

        root.left = deserialize(linkedList);
        root.right = deserialize(linkedList);
        return root;
    }




    public String serialize_post(TreeNode root){
        StringBuilder stringBuilder = new StringBuilder();
        serialize_post(root,stringBuilder);
        return stringBuilder.toString();
    }
    private void serialize_post(TreeNode root,StringBuilder stringBuilder){
        if (root == null){
            stringBuilder.append(NULL).append(SEP);
            return;
        }

        stringBuilder.append(root).append(SEP);

        serialize_post(root.left,stringBuilder);
        serialize_post(root.right,stringBuilder);
    }
    public TreeNode deserialize_post(String data){

        LinkedList<String> linkedList = new LinkedList<>();
        for (String str : data.split(SEP)) {
            linkedList.addLast(str);
        }


        return serialize_post(linkedList);

    }
    private TreeNode serialize_post(LinkedList<String> linkedList){
        if (linkedList.isEmpty()){
            return null;
        }
        String last = linkedList.removeLast();

        if (last.equals(NULL)){
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(last));
        //由于是后序遍历的 结果  所以  在存储结构中的顺序 为    左子树节点-右子树节点-root
        root.right = serialize_post(linkedList);
        root.left = serialize_post(linkedList);
        return root;
    }


}


























