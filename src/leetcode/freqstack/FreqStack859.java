package leetcode.freqstack;

import java.util.HashMap;
import java.util.Stack;

public class FreqStack859 {
    //记录FreqStack 中元素的最大频率
    int maxFreq = 0;
    //记录 每个 val 对应的频率    value --> frequency
    HashMap<Integer, Integer> val2Freq = new HashMap<>();

    //记录 每个 frequency 对应的  value 列表  且 必须按照插入时间 排序
    //最新插入到放入栈顶
    HashMap<Integer, Stack<Integer>> freq2Val = new HashMap<>();

    public FreqStack859(){
    }
    /*
    * 在栈中加入一个元素 val
    * */
    public void push(int val){
        int freq  = val2Freq.getOrDefault(val,0)+1;
        val2Freq.put(val,freq);

        freq2Val.putIfAbsent(freq,new Stack<>());
        freq2Val.get(freq).push(val);//向值栈中推入 新的val
        maxFreq = Math.max(maxFreq,freq);//更新最大频率
    }
    /*
    * 从栈中删除并返回 出现频率最高的  元素
    * 如果频率最高的 元素 不止一个
    * 返回最近添加的那个元素
    * */
    public int pop(){
        Stack<Integer> vals = freq2Val.get(maxFreq);//获得最大频率 的值栈
        int value = vals.pop();//值栈栈顶元素出栈 ， 即  最大频率的 值栈的 最近插入的元素 出栈

        //更新  value2Frequency 这个map
        int freq = val2Freq.get(value) -1;//出栈了 对应的 频率 -1
        val2Freq.put(value,freq);

        //如果 freq2Val 的值栈 如果为空 说明  最大频率对应的那个 value 没了
        // 但是  频率减一的 value还在
        //换句话说 就是  如果有 3freq 的 val
        //肯定就有  2freq的val
        //因为是  freq 2 value
        //举例：
        //freq == 3的 对应 一个 5    ------------> 出栈 ，则 值栈为空   只要将 freq --就可以了
        //freq == 2的 对应一个 5 4 3
        if(vals.isEmpty()){
            maxFreq--;
        }
        return value;
    }

}
