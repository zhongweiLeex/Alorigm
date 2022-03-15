package leetcode.arrayquestions;

public class CarPooling1904 {
    //方案可行 说明  在所有站点的时候 乘客数量都不应该超过 capacity 这样才可以
    //就是要 比较 所有站点（检查点）的时候 容量不应该比 加起来的人 小  至少要等于 或者大于
    int[] diff = new int[1001];//差分数组  最多 1001个车站
    public boolean carPooling(int[][] trips, int capacity) {
        for(int[] trip : trips){
            int startIndex = trip[1];
            int endIndex = trip[2] - 1;//乘客在车上的站点的区间是   trip[1] 到 trip[2] -1;
            int value = trip[0];
            getDiff(startIndex,endIndex,value);
        }
        return getResult(capacity);
    }
    //构造差分数组
    private void getDiff(int startIndex,int endIndex,int value){
        if(endIndex < startIndex){
            return;
        }
        diff[startIndex] = diff[startIndex] + value;
        if(endIndex < diff.length){
            diff[endIndex + 1] = diff[endIndex + 1] -value;
        }
    }

    private boolean getResult(int capacity){
        int[] result = new int[diff.length];
        result[0] = diff[0];
        for(int i = 1; i< diff.length;i++){
            result[i] = result[i-1] + diff[i];
        }
        for (int j : result) {// 在所有站点都不应该超载
            if (capacity < j) {
                return false;
            }
        }
        return true;
    }
}
