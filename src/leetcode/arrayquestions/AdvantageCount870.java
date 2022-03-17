package leetcode.arrayquestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
/*
给定两个大小相等的数组 A 和 B，A 相对于 B 的优势可以用满足 A[i] > B[i] 的索引 i 的数目来描述。

返回 A 的任意排列，使其相对于 B 的优势最大化。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/advantage-shuffle
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
public class AdvantageCount870 {
    public int[] advantageCount(int[] nums1, int[] nums2) {



        PriorityQueue<int[]> maxpq = new PriorityQueue<>( (int[] pair1,int[] pair2) -> (pair2[1] - pair1[1]) );
        Arrays.sort(nums1);//对 nums1 从小到大排序

        for(int i = 0; i< nums2.length; i++){
            //将 索引和 索引对应的值 加入优先队列 （加入后就已经排好序了）
            maxpq.offer(new int[]{i,nums2[i]});
        }
        int[] result = new int[nums2.length];//结果数组
        int left = 0;
        int right = nums1.length-1;

        while(!maxpq.isEmpty()){
            int[] temp = maxpq.poll();//从优先队列中拿出一个index-maxval 对
            int maxVal = temp[1];
            int index = temp[0];
            if(maxVal < nums1[right]){
                //比得过 就加入
                result[index] = nums1[right];
                right--;
            }else{
                //比不过就是用小的 糊弄过去 小的就是靠左边的元素
                result[index] = nums1[left];
                left++;
            }
        }
        return result;
    }


}
