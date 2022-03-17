package leetcode.arrayquestions;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/*
给定一个整数 n 和一个 无重复 黑名单整数数组 blacklist 。设计一种算法，从 [0, n - 1] 范围内的任意整数中选取一个 未加入 黑名单 blacklist 的整数。
任何在上述范围内且不在黑名单 blacklist 中的整数都应该有 同等的可能性 被返回。

优化你的算法，使它最小化调用语言 内置 随机函数的次数。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/random-pick-with-blacklist
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
public class BlackList710 {
    private Random random;
    private Map<Integer,Integer> map;//映射 黑名单元素 到 正常元素后面的 索引位置
    private int normalLength;//正常元素长度

    public BlackList710(int n, int[] blacklist) {
        random = new Random();
        normalLength = n-blacklist.length;
        map = new HashMap<>();

        for(int i : blacklist){
            map.put(i,-1);//将一开始的黑名单元素 的索引 初始化为其他位置（-1）
        }
        int lastPosition = n -1;
        //需要处理的是 n - blacklist.length  到 0 位置之间的 黑名单元素  需要将其 映射到后面正常元素
        for(int blackElement : blacklist){
            if( blackElement >= normalLength){// 黑名单元素  本来就是在非正常位置
                continue;
            }
            //已经映射到后面的黑名单元素 需要跳过
            while(map.containsKey(lastPosition) ){
                lastPosition--;
            }

            map.put(blackElement,lastPosition);
            lastPosition--;
        }
    }

    public int pick() {
        int value = random.nextInt(normalLength);

        return map.getOrDefault(value,value);

        // if(map.containsKey(value)){
        //     return map.get(value);//如果是黑名单中元素 取到他映射到后面的非黑名单元素
        // }

        // return value;//如果不是黑名单元素 直接返回


    }
}
