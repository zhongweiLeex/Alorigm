package leetcode.dp;

public class RegExpMatch10 {

    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;

        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        // 和p的匹配关系初始化，a*a*a*a*a*这种能够匹配空串
        for (int i = 2; i <= n; i+= 2) {
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
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }

        return dp[m][n];
    }
}


