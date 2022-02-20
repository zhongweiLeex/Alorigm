package leetcode;

public class SortedSquares977 {
    public int[] sortedSquares(int[] nums){
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]<0){
                nums[i] = nums[i]*(-1);
            }
        }
        return countingSort(nums);
    }
    /*
    * 计数排序方法
    *
    * */
    public int[] countingSort(int[] nums){
        if (nums.length==1){
            nums[0] = nums[0]*nums[0];
            return nums;
        }
        int len = nums.length;
        int max = nums[0];
        int min = nums[0];

        for (int i = 0; i < len; i++) {
            if (max<nums[i]){
                max = nums[i];
            }
            if (min > nums[i]){
                min = nums[i];
            }
        }
        //区间长度 offset
        int offset = max - min + 1;
        //计算频率
        int[] count = new int[offset+1];
        for (int i = 0; i < len; i++) {
            count[nums[i] - min + 1]++;
        }
        for (int i = 0; i < offset; i++) {
            count[i+1] += count[i];
        }
        int[] aux = new int[len];
        for (int i = 0; i < len; i++) {
            aux[count[nums[i]-min]++] = nums[i];
        }
        for (int i = 0; i < len; i++) {
            nums[i] = aux[i]*aux[i];
        }
        return nums;
    }

    /*
    *
    * 快速排序方法
    *
    * */
    public void quickSort(int[] nums,int start,int end){
        //递归出口
        if(start > end){
            return ;
        }
        int temp = nums[start];

        int left = start;//左指针定义
        int right = end;//右指针定义
        while(left<right){
            while(nums[right] >= temp && left < right){
                right--;
            }
            nums[left] = nums[right];
            while(nums[left]<=temp && left < right){
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = temp;
        quickSort(nums,start,left-1);
        quickSort(nums,left+1,end);
    }
    public int[] sqrt1(int[] nums){
        for(int i = 0;i<nums.length;i++){
            nums[i] = nums[i]*nums[i];
        }
        return nums;
    }

    /*
    * 双指针方法
    * */
    public int[] sortedSquares1(int[] nums){
        int right = nums.length -1;
        int left = 0;
        int[] result = new int[nums.length];
        int pointer = result.length-1;
        while(left<=right){
            if (nums[right]*nums[right] < nums[left]*nums[left]){
                result[pointer--] = nums[left]*nums[left];
                ++left;
            }else{
                result[pointer--] = nums[right]*nums[right];
                --right;
            }
        }
        return result;
    }




}










































