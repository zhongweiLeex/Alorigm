package leetcode.dp;

public class RegExpMatch10 {
/*
使用动态规划的方法：

状态矩阵：dp[i][j] 表示s的前i个字符是否可以被p的前j个字符匹配.
转移方程：
2.1 如果p[j] == s[j]，那么dp[i][j] = dp[i-1][j-1]
2.2 如果p[j] == '.'，那么dp[i][j] = dp[i-1][j-1]
2.3 如果p[j] == '*'：
2.3.1 如果p[j - 1] != s[i]，那么dp[i][j] = dp[i][j-2] // 也就是说*匹配了个寂寞，让前面的字符无效了
2.3.2 如果p[j - 1] == s[i]或者p[j-1] == '.'：
- 要么dp[i][j] = dp[i-1][j] // * 匹配到了多个字符
- 要么dp[i][j] = dp[i][j-1] // * 匹配了一个字符
- 要么dp[i][j] = dp[i][j-2] // * 匹配了个寂寞


*
*/
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;

        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        // 和p的匹配关系初始化，a*a*a*a*a*这种能够匹配空串
        for (int i = 2; i <= n; i += 2) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i - 2];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);
                if (sc == pc || pc == '.') {
                    //当前字符 两个位置相等 则当前结果依赖之前的结果
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc == '*') {
                    if (dp[i][j - 2]) {//当前的字符不相等  但是有个 * 可以覆盖掉 还是要依赖之前的比较结果
                        dp[i][j] = true;
                    } else if (sc == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        //s的i个字符能和p的i-1 个字符匹配  或者 p的第 i-1 个字符是 任意字符 那么 s就回退
                        // 这样回退之后的结果才是 现在的结果
                        dp[i][j] = dp[i - 1][j];//？？？？？？看不懂
                    }
                }
            }
        }
        return dp[m][n];
    }
}


