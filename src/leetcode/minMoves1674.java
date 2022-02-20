package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
/*
* 1674 数组互补的最少操作次数
* 给你一个长度为 偶数 n 的整数数组 nums 和一个整数 limit 。每一次操作，你可以将 nums 中的任何整数替换为 1 到 limit 之间的另一个整数。

如果对于所有下标 i（下标从 0 开始），nums[i] + nums[n - 1 - i] 都等于同一个数，则数组 nums 是 互补的 。例如，数组 [1,2,3,4] 是互补的，因为对于所有下标 i ，nums[i] + nums[n - 1 - i] = 5 。

返回使数组 互补 的 最少 操作次数。

* 算法：差分数组
*我们设初始操作次数为两次。令`target`从数轴最左端开始向右移动，我们会发现：

在`1+a`处，操作次数减少一次；
在`a+b`处，操作次数减少一次；
在`a+b+1`处，操作次数增加一次；
在`b+limit+1`处，操作次数增加一次。
因此，我们可以遍历数组中的所有数对，求出每个数对的这四个关键位置，然后更新差分数组。最后，我们遍历（扫描）差分数组，就可以找到令总操作次数最小的`target`，同时可以得到最少的操作次数。
*
*
* */
public class minMoves1674 {
    public static int minMoves(int[] nums,int limit){
        int[] options = new int[2*limit + 2];
        int n = nums.length;
        for (int i = 0; i < nums.length / 2; i++) {
            int a = Math.min(nums[i],nums[n-i-1]);
            int b = Math.max(nums[i],nums[n-i-1]);
            options[a + 1]--;//在`1+a`处，操作次数减少一次；
            options[a + b]--;//在`b+a`处，操作次数减少一次；
            options[a + b + 1]++;//在`b+a + 1`处，操作次数减少一次；
            options[limit + b + 1]++;//在`limit + a + 1`处，操作次数减少一次；
        }
        int current = n/2 * 2;//初始化都要操作两次  n/2 有这么多需要操作的位置  *2 表示操作两次
        int res = n;//
        //数组范围为 2 到 2 * limit + 2
        for (int i = 2; i <=limit * 2; i++) {
            current += options[i];
            res = Math.min(res,current);//最终的操作次数取所有的操作数的最小值
        }
        return res;

//        int[] nums2 = new int[nums.length/2];
//        for (int i = 0; i <= (nums.length - 1) / 2; i++) {
//            nums2[i] = nums[i] + nums[nums.length-1-i];
//        }
//        System.out.println(Arrays.toString(nums2));
//
//        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
//        for (int i = 0; i < nums2.length; i++) {
//            if (!map.containsKey(nums2[i])){
//                map.put(nums2[i],1);
//            }else{
//                map.put(nums[i], map.get(nums2[i] + 1));
//            }
//        }
////        for (int j : nums2) {
////            if (!map.containsKey(j)) {
////                map.put(j, 1);
////            } else {
////                map.put(j, map.get(j) + 1);
////            }
////        }
//        System.out.println(map);
//        System.out.println(Arrays.toString(nums2));
//        Map.Entry<Integer,Integer> maxEntry = null;
//        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
//            if (maxEntry == null){
//                maxEntry = entry;
//            }
////            System.out.println(maxEntry);
//            if (maxEntry.getValue() < entry.getValue()){
//                maxEntry = entry;
//            }else if (maxEntry.getValue().equals(entry.getValue())){
//                maxEntry = (maxEntry.getKey() > entry.getKey() ? maxEntry : entry);
//            }
////            max = entry.getKey();
//        }
//
//        assert maxEntry != null;
//        int maxValue = maxEntry.getKey();
//        System.out.println(maxValue);
//        int count = 0;
//        for (int i = 0; i <= (nums.length-1)/2; i++) {
//            if (nums2[i] < maxValue){
//                if (nums[i] <= nums[nums.length-1-i]){
//                    int change = nums[nums.length-1-i] + limit;
//                    count = count + 1;
//                    while(change < maxValue){
//                        count++;
//                        change = limit + limit;
//                    }
//                }else{
//                    int change = nums[i] + limit;
//                    count = count + 1;
//                    while(change < maxValue){
//                        count++;
//                        change = limit + limit;
//                    }
//                }
//            }
///*            else if (nums2[i] > maxValue){
//                if (nums[i] >= nums[nums.length-1-i]){
//                    int change = nums[nums.length-1-i] + limit;
//                    count = count + 1;
//                    while(change > maxValue){
//                        count++;
//                        change = limit+limit;
//                    }
//                }else{
//                    int change = nums[i] + limit;
//                    count = count + 1;
//                    while(change > maxValue){
//                        count++;
//                        change = limit + limit;
//                    }
//                }
//            }*/
//        }
//
//
//
//        System.out.println(map);
//        System.out.println(Arrays.toString(nums2));
//        return count;
    }

    public static void main(String[] args) {

        int[] nums = new int[]{37,2,9,49,58,57,48,17};
        System.out.println(minMoves(nums,58));
    }
}
