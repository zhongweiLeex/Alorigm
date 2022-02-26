package leetcode;

import java.util.Arrays;
import java.util.Scanner;


/**
 * 某比赛已经进入了淘汰赛阶段,已知共有n名选手参与了此阶段比赛，他们的得分分别是a_1,a_2….a_n,小美作为比赛的裁判希望设定一个分数线m，
 * 使得所有分数大于m的选手晋级，其他人淘汰。
 * 但是为了保护粉丝脆弱的心脏，小美希望晋级和淘汰的人数均在[x,y]之间。
 * 显然这个m有可能是不存在的，也有可能存在多个m，如果不存在，请你输出-1，如果存在多个，请你输出符合条件的最低的分数线。
 *
 * 输入描述:
 * 输入第一行仅包含三个正整数n,x,y，分别表示参赛的人数和晋级淘汰人数区间。(1<=n<=50000,1<=x,y<=n)
 * 输入第二行包含n个整数，中间用空格隔开，表示从1号选手到n号选手的成绩。(1<=|a_i|<=1000)
 *
 * 输出描述:
 * 输出仅包含一个整数，如果不存在这样的m，则输出-1，否则输出符合条件的最小的值。
 *
 * 输入例子1:
 * 6 2 3
 * 1 2 3 4 5 6
 * 输入例子1:
 * 3
 */

public class MeiTuanTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();//所有参加比赛的人
        int x = scanner.nextInt();//晋级的最少人
        int y = scanner.nextInt();//晋级的最多人

        int[] score = new int[50000];
        for (int i = 0; i < n; i++) {
            score[i] = scanner.nextInt();
        }
        //排序
        Arrays.sort(score);
        int notOk = n-y;//最少没过线的人数   也就意味着  最多人晋级
        if (notOk > y ){//淘汰的人比 y多 说明不符合要求 连最少淘汰的 都比最多淘汰人数多 不可以
            System.out.println(-1);
        }else if( notOk >= x && notOk <=y ){//淘汰的人在x和y之间  晋级的人数 就是 y 那分数线应该是 n-y-1
            System.out.println(score[n-y-1]);
        }else{//淘汰的人比x人还要少 说明 淘汰了 x个人 只有前面 x个人被淘汰 则分数 应该是 从零开始 则 是 score[x-1]
            System.out.println(score[x-1]);
        }
    }
}