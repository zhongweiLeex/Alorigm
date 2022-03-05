package leetcode.palindrome;
/*
* 最长回文数组
* */
public class LongestPalindrome5 {
    public String longestPalindrome(String s) {
        String result = "";//结果字符串
        for (int i = 0; i < s.length()-1; i++) {
            String s1 = palindrome(s,i,i);//找到回文子串 元素个数为 奇数的
            String s2 = palindrome(s,i,i+1);//找到元素个数为 偶数的 回文子串

            result = result.length() > s1.length() ? result : s1;
            result = result.length() > s2.length() ? result : s2;
        }
        return result;
    }

    private String palindrome(String s,int left, int right){
        while (left >=0 && right <s.length()-1 && s.charAt(left) == s.charAt(right)){//处理越界 和比较元素
            left--;
            right++;
        }
        return s.substring(left+1,right);//返回以 left开始 right结束的 子串
    }
    /*
    * 使用动态规划算法
    * 状态： 结果字符串里的字符
    * 选择： 后面一个字符加还是不加入结果
    * 动态规划五部曲：
    *               1. 确定dp数组以及下标含义
    *               2. 确定递推公式
    *               3. 确定dp数组如何初始化
    *               4. 确定遍历顺序 （便于取得前期计算的结果）
    *               5. 举例推导dp数组
    * */
    public String longestPalindrome1(String s){
        int n = s.length();
        boolean[][] dp = new boolean[n][n];//建立dp数组 下标表示  第几个字符
        int maxLen = 0;//记录回文子串的最大长度
        int left = 0;
        int right = 0;
        //需要确定正确的遍历顺序
        for (int i = n-1; i >= 0 ; i--) { //i为右指针  j为左指针  因为是 j-1  和 i +1 来判断  所以必须是 两个位置的信息 提前计算出来了
            for (int j = i; j < n ; j++) {
                if (s.charAt(i) == s.charAt(j)){//当两个指针指向的 字符相等 的时候
                    if (j - i <=1) {            //且当 两个指针 举例 <= 1 的时候  指针重合 或者 指针相邻  且 两个字符相等的时候
                        dp[i][j] = true;
                    }else if (dp[i+1][j-1] == true){
                        //当两个指针 距离 > 1的时候
                        //但是两个指针的位置的元素确实之前计算过的是相等的
                        dp[i][j] = true;
                    }
                }
                if (dp[i][j] == true && j-i+1 > maxLen){
                    maxLen = j-i +1;
                    left = i;
                    right = j;
                }
            }
        }
        return s.substring(left,right);
    }
}
