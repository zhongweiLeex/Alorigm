package disjSets;

import java.util.ArrayList;
import java.util.Arrays;

public class DisjSets {
    private int length;//定义数组长度
    private int[] array;

    /*
    * 构造方法 初始化数组元素为 -1
    *
    * @param length   数组长度
    * */
    public DisjSets(int length) throws Exception {
        if (length <= 0)
            throw new Exception("初始化失败，长度非法");

        array = new int[length];
        this.length = length;
        Arrays.fill(array, -1);
    }

    /*
    * 获取一个下标所在的整个集合的值
    *
    * @param index 下标值
    * @return 整个集合的list
    * */
    public ArrayList<Integer> getDisjMember(int index){
        int count = array[index] < 0 ? (array[index]*-1) : (array[find(index)] * -1);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(index);
        findAll(list,index,count);
        return list;
    }
    /*
    *
    * 找到所有 以 parentRoot 为父节点的子节点
    * @param list  需要返回的list 对象
    * @param  parentRoot  父节点
    * @param count  这个集合的 节点个数
    * @return 以parentRoot为父节点的集合
    * */
    private ArrayList<Integer> findAll(ArrayList<Integer> list,int parentRoot,int count){

        if (count == 0)
            return list;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == parentRoot){//如果当前节点是父节点  往list中添加
                list.add(i);
                findAll(list,i,count--);//找到所有以i为父节点的子节点
            }
        }
        return list;
    }
    /*
    * 获取数组长度
    * @return
    * */
    public int length(){
        return this.length;
    }
    /*
    * 判断是否全部合并
    * */
    public boolean isUnionAll(){
        boolean isUnionAll = false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0){
                if (isUnionAll){
                    isUnionAll = !isUnionAll;
                    break;
                }
                isUnionAll = !isUnionAll;
            }
        }
        return isUnionAll;
    }
    /*
    * 通过比较两个集合的个数    个数小的 合并到个数大的 树中
    * */
    public void unionBySize(int root1,int root2){
        int sumSize = array[root1] + array[root2];//将
        if (array[root1] > array[root2]){
            array[root2] = sumSize;
            array[root1] = root2;//将节点下标赋值给对应的array[root1]位置 表示指向连接到这个地方
        }else{
            array[root1] = sumSize;
            array[root2] = root1;
        }
    }
    /*
    * 通过比较比较两个集合高度 高度小的结合归并到 高度大的 树中
    *
    * */

    public void unionByHeight(int root1,int root2) {
        if (array[root1] < array[root2]) {//root2 更深
            array[root1] = root2;//设置 root1 的父节点标志为  root2  谁的高度高谁是父节点
        } else {
            if (array[root1] == array[root2])
                array[root1]--;//如果相同则更新高度
            array[root2] = root1;//设置root1 作为新的root
        }
    }


    /*
    * 使用 路径压缩  查找方式 通过根的下标数组标识树集合
    * 查找包含 index 位置的元素的 集合
    * 使用 根节点的index 表示这个树的位置
    *
    * path compression 路径压缩  与 按照大小求并完全兼容
    * path compression 路径压缩  与 按照高度求并 不完全 兼容
    *
    * */
    public int find(int index) {
        if (array[index] < 0 ){
            return index;
        }else{
            return array[index] = find(array[index]);//使用 最终返回的是树的根节点
        }
    }

}
