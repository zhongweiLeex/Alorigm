package leetcode;

import java.util.Arrays;
/*
* 从后往前  如果 # 个数为 0 则说明当前的字符可以添加到 如果使 >= 0 的 说明当前字符会被删除
*
* */
public class BackSpaceCompare844 {
    public boolean backSpaceCompare(String s,String t){
        return convert(s).equals(t);
    }
     public String convert(String s){
        char[] s1 = s.toCharArray();
        int numOfShape=0;
        StringBuffer constructor = new StringBuffer();
         for (int s1Pointer = s1.length -1;s1Pointer>=0;s1Pointer--){
             if (s1[s1Pointer] == '#'){
                 numOfShape++;
             }else if (numOfShape == 0){
                 constructor.append(s1[s1Pointer]);
             }else{
                 numOfShape--;
             }
         }
         return String.valueOf(constructor);
     }
}

