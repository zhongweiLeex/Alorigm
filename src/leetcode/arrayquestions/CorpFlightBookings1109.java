package leetcode.arrayquestions;

public class CorpFlightBookings1109 {
    private int[] diff;
    public int[] corpFlightBookings(int[][] bookings, int n) {
        diff = new int[n];
        int[] nums = new int[n];
        getDiff(nums);//构造一个差分数组
        for(int[] booking : bookings){
            int startIndex = booking[0]-1;//索引需要减去1
            int endIndex = booking[1]-1;
            int value = booking[2];
            increment(startIndex,endIndex,value);
        }
        return getResult();
    }

    private void increment(int startIndex, int endIndex, int value){
        if(startIndex > endIndex){
            return;
        }
        diff[startIndex] = diff[startIndex] + value;
        if(endIndex+1 < diff.length){//检查 末尾索引是否超出了差分数组长度  如果超出说明所有的都要+value
            diff[endIndex + 1] = diff[endIndex + 1] - value;
        }
    }

    //构造一个 差分数组diff
    private void getDiff(int[] nums){
        diff[0] = nums[0];
        for( int i = 1 ; i< nums.length ;i++){
            diff[i] = nums[i] - nums[i-1];
        }
    }

    private int[] getResult(){
        int[] result = new int[diff.length];
        result[0] = diff[0];
        for(int i = 1; i<diff.length;i++){
            result[i] = result[i-1] + diff[i];
        }
        return result;
    }
}
