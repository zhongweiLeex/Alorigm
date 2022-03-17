package leetcode.arrayquestions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
/*
实现RandomizedSet 类：

RandomizedSet() 初始化 RandomizedSet 对象
bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1)

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/insert-delete-getrandom-o1
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
public class RandomSet380 {
    //存储值
    private List<Integer> nums;
    // 值 - 索引   键值对 存储
    private HashMap<Integer,Integer> val2Index;
    private Random random;
    public RandomSet380() {
        nums = new ArrayList<>();
        val2Index = new HashMap<>();
        random = new Random();
    }
    //如果 val 不存在于集合中 插入并返回 true  否则返回 false
    //在末尾插入
    public boolean insert(int val) {
        if(val2Index.containsKey(val)){
            return false;
        }
        nums.add(val);
        val2Index.put(val,nums.size()-1);
        return true;
    }
    // 如果 val 在集合中， 则删除并返回 true 否则返回 false
    //将val 和最后一个元素互换位置 ，然后删除最后一个元素
    public boolean remove(int val) {
        if(!val2Index.containsKey(val)){
            return false;
        }
        int index = val2Index.get(val);//取到 val 对应的 数组索引
        int lastVal = nums.get(nums.size()-1);//记录获取数组最后的值

        nums.set(index,lastVal);//将数组中val对应的索引位置的值设置为最后的值
        nums.remove(nums.size()-1);//最后的值删掉

        val2Index.put(lastVal,index);//更新 val2Index 这个map中的键值对
        val2Index.remove(val);
        return true;
    }
    //从集合中等概率的随机获取一个元素
    public int getRandom() {
        return nums.get(random.nextInt(nums.size()));//随机数  随机种子是 nums 长度
    }
}
