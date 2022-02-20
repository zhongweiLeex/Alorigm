package leetcode;

import java.util.HashMap;

public class ContainRepeat2 {
    public static boolean containsNearbyDuplicate(int[] nums,int k){
        boolean result = false;
        if (nums.length == 1){
            return result;
        }else if (nums.length == 2 && nums[0]!=nums[1]){
            return result;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])){
                if (map.get(nums[i])!= i){
                    if (Math.abs(map.get(nums[i])-i)<=k){
                        result = true;
                    }else{
                        map.put(nums[i],i);//更新 键值对
                    }
                }
            }
            if (!map.containsKey(nums[i])){
                map.put(nums[i],i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,1,2,3};
//        containsNearbyDuplicate(nums,3);
        System.out.println(containsNearbyDuplicate(nums,2));
    }
}
