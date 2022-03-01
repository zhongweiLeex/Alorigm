package leetcode.slidingwindow;


/*
*
* 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
*
* 注意：
* 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
* 如果 s 中存在这样的子串，我们保证它是唯一的答案。
* 链接：https://leetcode-cn.com/problems/minimum-window-substring
* */

import java.util.HashMap;

/*滑动窗口算法框架
void slidingWindow(string s, string t) {
        //s 目标串
        //t 匹配串

        // need 记录 匹配串t中 的所有的 字符出现的次数
        //window 记录 滑动窗口中 相应的字符出现的次数
        unordered_map<char, int> need, window;//使用不默认排序的 hashmap

        for (char c : t) need[c]++;

        int left = 0, right = 0;
        int valid = 0;
        while (right < s.size()) {//判断是否right指针还在数组中
        // c 是将移入窗口的字符
        char c = s[right];//取出right指针取出的元素
        // 右移窗口
        right++;//右边扩大窗口
        // 进行窗口内数据的一系列更新
        ...

        ----- debug 输出的位置 ---------------------
        printf("window: [%d, %d)\n", left, right);
        -----------------------------------------

        // 判断左侧窗口是否要收缩
        while (window needs shrink) {
        // d 是将移出窗口的字符
        char d = s[left];//将left指针指向的元素取出
        // 左移窗口
        left++;//left右移 缩小窗口
        // 进行窗口内数据的一系列更新
        ...
        }
        }
        }
*/
public class MinWindow76 {
    public String minWindow(String s, String t) {
        HashMap<Character,Integer> need = new HashMap<>();//用来存储匹配串的字符以及其频率
        HashMap<Character,Integer> window = new HashMap<>();//目前窗口中的字符和其出现的频率
        int t_length = t.length();//匹配串的长度
        /*
        * 统计 匹配串t的字符 和 其出现的频率
        * */
        for (int i = 0; i < t_length; i++) {
            need.put(t.charAt(i),need.getOrDefault(t.charAt(i),0)+1);//获取指定的key对应的 value 如果找不到key 则返回设置的默认值
        }
        int left =0;//初始化左指针
        int right = 0;//初始化右指针

        int valid = 0;//窗口中 满足need条件的字符个数，如果valid和need.size 大小相同 说明 窗口完全满足条件了 可以选择收缩了

        int start = 0;//记录最小覆盖子串的起使索引
        int len = Integer.MAX_VALUE;//记录最小覆盖字串的长度

        //右指针到达 目标串s的末尾的时候停止
        while(right < s.length()){

            char c = s.charAt(right);//获取右指针所在位置的char
            right++;//右指针右移 窗口扩大

            if (need.containsKey(c)){//如果need中包含当前指针所在的字符c
                window.put(c,window.getOrDefault(c,0)+1);//将当前char c 存入对应的window位置
                if (window.get(c).equals(need.get(c))){//如果目标map 中的 相对应的 字符数量符 和 当前window中的 对应的字符数量相同 则 符合的valid++
                    valid++;
                }
            }
            //判断左侧窗口是否需要收缩
            while (valid == need.size()){//如果 window中符合need中的char数量则说明确实找到了可行解
                if (right - left < len){//说明现在的子串的长度比 之前记录的长度还要小
                    len = right - left;//更新字串长度记录
                    start = left;//更新起使索引到left
                }
                //将当前left位置的字符移除窗口
                char d = s.charAt(left);
                //左移窗口
                left++;
                if (need.containsKey(d)){//如果当前移除的字符 存在于 need中 需要对窗口内的数据进行一系列更新
                    if (window.get(d).equals(need.get(d))){//如果当前需要移除的字符数量 和 need中需要移除的字符数量相同 则将 匹配的字符个数 -1
                        valid--;
                    }
                    window.put(d,window.get(d)-1);//将当前的window中的对应的字符数量 -1
                }
            }
        }
        //right到达了字符串的末尾
        return len == Integer.MAX_VALUE ? "" : s.substring(start,start+len);
    }
}









































