package leetcode.slidingwindow;

import java.util.HashMap;
import java.util.HashSet;

public class LengthOfLongestSubstring3 {
    public int lengthOfLongestSubstring(String s) {
        int length = 0;//长度
        HashMap<Character,Integer> window = new HashMap<>();
        int left = 0;
        int right = 0;
        while(right <= s.length()){
            char c = s.charAt(right);//取出单独的字符
            right++;
//            if (window.containsKey(c))
            window.put(c,window.getOrDefault(c,0)+1);//将当前的字符加入到window中
            //如果当前的字符在window中个数大于1了 就是有重复了
            while (window.get(c)>1){
                char d = s.charAt(left);
                left++;//left右移
                window.put(d,window.get(d)-1);//更新移除的字符的个数
            }
            length = Math.max(length,right-left);
//            length = Math.max(length,window.size());
        }
        return length;
    }
}
