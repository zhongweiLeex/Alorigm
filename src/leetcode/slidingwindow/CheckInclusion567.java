package leetcode.slidingwindow;

/*
* 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
* 换句话说，s1 的排列之一是 s2 的 子串 。
* 链接：https://leetcode-cn.com/problems/permutation-in-string
* */

import java.util.HashMap;

public class CheckInclusion567 {
    public boolean checkInclusion(String s1, String s2) {
        HashMap<Character, Integer> need = new HashMap<>();//目标 s1
        HashMap<Character, Integer> window = new HashMap<>();//滑动窗口记录

        int left =0;//左指针
        int right =0;//右指针
        int valid = 0;//滑动窗口中的符合的字符个数

        //符合条件1：窗口中包含所有s1中的字符
        //符合条件2：窗口中包含的s1的字符的数量都和s1中的一样

        //将s1中的字符hash化
        for (int i = 0; i < s1.length(); i++) {
            need.put(s1.charAt(i),need.getOrDefault(s1.charAt(i),0)+1);
        }
        //停止标志 right指针超出s2的右边界
        while (right < s2.length()){
            char c = s2.charAt(right);//获取right位置的字符
            right++;//right指针右移

            //窗口内数据更新
            if (need.containsKey(c)) {//need中确实包含当前right指针指向的这个字符
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }
                //判断window收缩 左移条件是滑动窗口大小 大于等于字串长度
            while(right - left >= s1.length()){
                if (valid==need.size()){
                    return true;//符合的字符种类和数量相同
                }
                //如果还没符合 left右移  缩小滑动窗口
                char d = s2.charAt(left);
                left++;
                if (need.containsKey(d)){
                    if (window.get(d).equals(need.get(d))){
                        valid--;
                    }
                    window.put(d, window.get(d)-1);
                }
            }
        }
        return false;
    }
}
