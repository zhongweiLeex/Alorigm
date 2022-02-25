package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MeiTuanTest2 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = scanner.nextInt();
        }
        Arrays.sort(s);
        int count = 0;
        int newIndex = 1;
        for (int i = 0; i < s.length; i++) {
            count += Math.abs(newIndex-s[i]);
            newIndex++;
        }
        System.out.println(count);
    }
}
