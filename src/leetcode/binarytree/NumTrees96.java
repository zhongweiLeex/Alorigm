package leetcode.binarytree;
/*
* 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
* */
// BST 结构 不一定合法
public class NumTrees96 {
    /*
    * 二维计算 分别分为左右子树 分别有多少种组合可能
    * */
    int[][] memo;//备忘录
    public int numTrees(int n) {
        memo = new int[n+1][n+1];
        return count(1,n);//闭区间1到n之间的组成的bst个数

    }
    private int count(int low , int high){
        //当low>high的时候 显然是空节点 直接null 这是一种情况
        if (low > high) return 1;

        if (memo[low][high]!=0){
            return memo[low][high];
        }
        int result = 0;
        //计算以i为根节点的 子树 有多少种
        for (int i = low; i <= high ; i++) {
            int left = count(low,i-1);
            int right = count(i+1,high);
            result += left * right;//左右子树的组合数乘积就是整个bst的综述
        }
        memo[low][high] = result;//存入备忘录
        return result;
    }

    /*
    * 一维 计算多少节点 能有多少种可能 其实也是分为左右子树  但是没有显式的左右划分
    * */
    int[] memo1;
    public int numTree1(int n){
        if (n==0 || n==1){
            return 1;
        }
        memo1 = new int[n+1];
        memo1[0] = memo1[1] = 1;
        return count1(n);
    }
    private int count1(int n){
        if (memo1[n]!=0)
            return memo1[n];
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += count1(i)* count1(n-i-1);//最后的数量就是 多少个节点的组合
        }
        memo1[n] = result;
        return result;
    }

}
