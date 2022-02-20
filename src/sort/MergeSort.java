package sort;

import java.util.Arrays;
/*
* 归并排序
*
* */
public class MergeSort {
    //将数据都放在num1中 实现归并
    public void merge(int[] nums, int left,int middle,int right){
        int[] temp = new int[nums.length];
        int pointer1=left;//组1指针
        int pointer2=middle+1;//组2指针
        int pointer=left;//临时数组的指针
        int t = left;
        //将两个数组中比较小的数字移动到新的数组中
        while(pointer1<=middle && pointer2<=right){
            if (nums[pointer1]<=nums[pointer2]){
                temp[pointer++] = nums[pointer1++];
            }else{
                temp[pointer++] = nums[pointer2++];
            }
        }
        //左边剩余的数移动到数组中
        while(pointer1<=middle){
            temp[pointer++] = nums[pointer1++];
        }
        //右边剩余的数移动到数组中
        while(pointer2 <=right){
            temp[pointer++] = nums[pointer2++];
        }
        //将信数组覆盖到nums数组后面中
        //即nums前面排序的那部分
        while(t<=right){
            nums[t] = temp[t];
            t++;
        }
    }

    public void mergeSort(int[] array,int left,int right){
//        int left = 0;
//        int right = array.length-1;
        int middle = left + (right - left)/2;
        if (left < right){
            //归并排序左边
            mergeSort(array, left, middle);
            //归并排序右边
            mergeSort(array,middle+1,right);
            //左右合并
            merge(array,left,middle,right);
        }
    }

    public static void main(String[] args) {
        int[] nums = {-1,2,3,1,0,9,-5};
        MergeSort ms = new MergeSort();
        ms.mergeSort(nums,0,nums.length-1);
        System.out.println(Arrays.toString(nums));
    }

}
