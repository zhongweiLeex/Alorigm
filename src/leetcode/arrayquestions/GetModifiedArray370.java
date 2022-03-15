package leetcode.arrayquestions;

public class GetModifiedArray370 {
    private int[] diff;//定义差分数组

    public int[] getModifiedArray(int length,int[][] updates){
        int[] nums = new int[length];
//        int[] result = new int[length];
        diff = diffArray(nums);//构造一个差分数组
        for (int[] update : updates) {
            int startIndex = update[0];
            int endIndex = update[1];
            int value = update[2];
            increment(startIndex,endIndex,value);
//            result = getResultArray();
        }
        return getResultArray();
    }
    //构造一个差分数组
    private int[] diffArray(int[] nums){
        diff = new int[nums.length];
        diff[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            diff[i] =  nums[i] - nums[i-1];
        }
        return diff;
    }
    //给差分数组 的 startIndex 到 endIndex 增加 value
    private void increment(int startIndex,int endIndex,int value){

        if (startIndex > endIndex){
            return;
        }
        diff[startIndex] = diff[startIndex] + value;

        if (endIndex + 1 < diff.length){
            diff[endIndex +1]  = diff[endIndex + 1] - value;
        }

    }

    private int[] getResultArray(){
        int[] result = new int[diff.length];
        result[0] = diff[0];
        for (int i = 1; i < diff.length; i++) {
            result[i] = result[i-1] + diff[i];
        }

        return result;
    }

}
