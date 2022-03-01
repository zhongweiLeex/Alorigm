package leetcode.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
* 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
* 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
* 链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
* */
public class FindAnagrams438 {
    public List<Integer> findAnagrams(String s, String p) {
        HashMap<Character,Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();

        for (int i = 0; i < p.length(); i++) {
            need.put(p.charAt(i),need.getOrDefault(p.charAt(i),0)+1);
        }

        int left=0;
        int right = 0;
        int valid = 0;


        List<Integer> result = new ArrayList<>();//记录开始位置


        while (right < s.length()){
            char c = s.charAt(right);//获取当前right所在位置的字符
            right++;//窗口 向右扩展
            if (need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);
                if (window.get(c).equals(need.get(c))){
                    valid++;
                }
            }
            //窗口收缩时间
            while (right-left >= p.length()){
                if (valid == need.size()){//此处一定要写need.size() 因为存在重复的字符
                    result.add(left);//将left起使位置加入到 result中
                }
                char d = s.charAt(left);
                left++;//右移
                if (need.containsKey(d)){
                    if (window.get(d).equals(need.get(d))){
                        valid--;
                    }
                    window.put(d,window.get(d)-1);//更新window中d字符的个数
                }
            }
        }
        return result;
    }
}
