package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/*
* 最长定差子序列
* 使用 hashmap
* */
public class Longest_Arithmetic_Subsequence_of_Given_Difference {
    public static int longestSubSequence(int[] arr,int difference){
        int length = arr.length;
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            if (map.containsKey(arr[i]-difference)){//判断是否包含对应值的键 如果有则进行以下操作
                map.put(arr[i],map.get(arr[i]-difference)+1);
                ans = Math.max(ans,map.get(arr[i]));
            }
            if (!map.containsKey(arr[i]))//如果不包含对应的键
                map.put(arr[i],0);//在对应键 置 0
        }
        return ans+1;
    }

    public static void main(String[] args) {
        int[] arr = {-11,8,8,-13,-4,6,7,-3,8,4,-9,-7,13,-15,9};

        int count = longestSubSequence(arr,9);
        System.out.println(count);
    }
}
/*
class Solution {
    public int longestSubsequence(int[] arr, int difference) {
                    int length = arr.length;
        int count=1;
        int max = 1;
        int[] arr1 = new int[length];
        //反转 arr
        for (int i = length-1; i >= 0; i--) {
             arr1[length-i-1] = arr[i];
        }
        //System.out.println(Arrays.toString(arr1));
        int i=0;
        int j;
        int n = i;
        while(i<length){
            //int count = 0;
//            int n = i;
            for (j = n+1;j<length;j++){
                if ((arr1[n] - arr1[j]) == difference){
                    count++;
                    n=j;//如果找到了 等差的 就将i放到j的位置上
                    break;
                }
            }
            if (n!=j){
              i = i + 1;
              count=1;
              n=i;
            }
            if (max < count){
                max = count;
            }
        }
        for (int i = 0; i < length; ) {
            for (j = i+1; j < length; j++) {
                if ((arr1[i] - arr1[j]) == difference){
                    count++;
                    i=j;//如果找到了 等差的 就将i放到j的位置上 因为 有 i++ 所以 需要j-1
                    break;
                }
            }
            if (j== length-1){
                i++;
            }

            if (max < count){
                max = count;
            }
        }

        return max;
                }
                }
*/