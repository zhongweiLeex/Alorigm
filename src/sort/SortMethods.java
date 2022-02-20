package sort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class SortMethods <T extends Comparable<? super T>>{
    /*
    * 比较元素 a 和 元素 b 的大小
    * */
    private boolean less(T a, T b){
        return a.compareTo(b) < 0;
    }
    /*
    * 交换 i j位置的元素
    * */
    private void exchange(T[] a, int i,int j){
        T temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    /*
    * 判断是否已经排序
    * */
    public boolean isSorted(T[] a){
        for (int i = 1; i < a.length; i++) {
            if (less(a[i],a[i-1])) return false;
        }
        return true;
    }
    /*
    * 选择排序
    * */
    public void selectSort(T[] a){
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i+1; j < N ; j++) {
                if (less(a[j], a[min]))
                    min = j;
            }
            exchange(a,i,min);//将最小的和当前的i位置元素进行交换
        }
    }

    /*
    * 插入排序
    * */
    public void insertSort(T[] a){
        //按照从小到大顺序排列
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j >0 && less(a[j],a[j-1]) ; j--) {
                exchange(a,j,j-1);
            }
        }
    }

    /*
    * 希尔排序
    * */
    public void hillSort(T[] a){
        int N = a.length;
        int h = 1;
        while(h < N/3 ){
            h = 3 * h + 1;
        }
        while( h >= 1){
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j = j - h) {
                    exchange(a, j, j - h);
                }
            }
            h = h/3;//这里的/3 可以换成其他除数 控制下一次的区间大小
        }
    }

    /*
    * 归并排序
    * */
    //@SuppressWarnings("unchecked")
    public void mergeSort(T[] a,int low,int high){
        if (high <= low) return;
        int mid = low + (high -low)/2;
        //左边
        mergeSort(a,low,mid);
        //右边
        mergeSort(a,mid+1,high);
        //归并成一个数组
        merge(a,low,mid,high);

    }
    /*
    * 将 a[low..mid] 和 a[mid+1..high] 归并
    * */
    private void merge(T[] a, int low, int mid, int high) {
        int i = low;//左指针
        int j = mid+1;//右指针
        int k = 0;
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Comparable[high-low + 1];
        while (i<=mid&&j<=high){
            if (a[i].compareTo(a[j])< 0){
                temp[k++] = a[i++];//将较小的存入temp中
            }else{
                temp[k++] = a[j++];
            }
        }
        //将左边剩余的存入temp中
        while(i<=mid){
            temp[k++] = a[i++];
        }
        while(j<= high){
            temp[k++] = a[j++];
        }
        //将temp 覆盖到 a数组的相应位置
        for (int k2 = 0;k2< temp.length;k2++){
            a[k2+low] = temp[k2];
        }
    }

    /*
    * ---------------------------------------------------------------------------***
    * */

    /*
    * 快速排序 quickSort
    * */
    public void quickSort(T[] a,int left,int right){
        //quickSort(a,0,a.length-1);
        if(right <= left) return;
        int i = partition(a,left,right);
        quickSort(a,left, i -1);//将切分位置的前半部分排序
        quickSort(a, i +1,right);//将切分位置的后半部分排序

    }
    private int partition(T[] a,int left,int right){
        int i = left;
        int j = right - 1;
//        int mid = left + (right - left)/2;
//        exchange(a,mid,right);
        pivot(a,left,right);//选出枢纽元并且将枢纽元存储到 right - 1 位置
        T pivot = a[right-1];//pivot 枢纽元元素
//        int index = right-1;
        while (true){
            while(a[i].compareTo(pivot) < 0) i++;
            while(a[j].compareTo(pivot) > 0 && j> left) j--;
            if (i >= j){
                break;
            }
            exchange(a, i, j);//如果i 在j左边则交换
        }
        exchange(a,i,right-1);//交换i和暂存的pivot位置
        return i;//返回切分位置
    }
    /*
    * 选取枢纽元
    * */
    private void pivot(T[] a,int left,int right){
        //保持左边小 右边大的  枢纽值在中间
        int mid = left + (right - left)/2;
        if (a[left].compareTo(a[mid])>0)
            exchange(a,left,mid);
        if (a[left].compareTo(a[right])>0)
            exchange(a,left,right);
        if (a[right].compareTo(a[mid])<0)
            exchange(a,right,mid);

        exchange(a,right-1,mid);//将中间的枢纽值放到数组 right - 1 位置
    }
/*
* -------------------------------------------------------------------------
* -------------------------------------------------------------------------
* */
    /*
    * 计数排序 countSort
    * int[] count  ---> 统计相同元素在数组中的个数
    * int[] sortedArray ---> 存储排好序的元素
    * sortedArray[count[(a[i]-min)--]] = a[i] 将对应位置的放到对应的位置 从后往前放 重复元素也可以按照顺序拍好
    * */
    public int[] countSort(int[] a){
        int max = a[0];
        int min = a[0];
        for (int i : a) {
            if (i>max){
                max = i;
            }
            if (i < min){
                min = i;
            }
        }
        int d = max - min;
        //创建统计数组 并且计算统计对应的元素个数
        int[] count = new int[d+1];//开辟内存空间
        //统计每个整数出现的次数
        for (int i = 0; i < a.length; i++) {
            count[a[i] - min]++;
        }
        int sum = 0;
        for (int i = 0; i< count.length; i++){
            sum += count[i];
            count[i] = sum;
        }
        //排好序的数组
        int[] sortedArray = new int[a.length];
        //从后向前遍历数组 将元素放到有序数组中的合适位置
        for (int i = a.length-1; i >0 ; i--) {
            //获取元素在counts 中的索引
            int index = a[i] - min;
            sortedArray[count[index]--] = a[i];
        }
        for (int i = 0; i < sortedArray.length; i++) {
            a[i] = sortedArray[i];
        }
        return a;
    }
    /*
    *
    * ------------------------------------------------------------------------------------
    * 基数排序
    * radix sort
    * */
    public void radixSort(int[] a){
//        int exp;
        int max = findMax(a);//找到最大的数
        for (int exp = 1;max/exp > 0;exp *= 10){
            countSort(a,exp);
        }
    }
    private int findMax(int[] a){

        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max)
                max = a[i];
        }
        return max;
    }
    /*
    * countSort
    * @param a 需要排序的数组
    * @param exp 指示用
    * exp =1 按照个位数排序
    * exp = 10 按照十位排序
    * exp = 100 按照百位排序
    * 依次类推
    * ...
    * */
    private void countSort(int[] a,int exp){
        int[] output = new int[a.length];//创建一个临时存储数组 大小等于 a
        int[] buckets = new int[10];
        Arrays.fill(buckets,0);
        //int i = 0;
        /*
        * 以下类似于计数排序核心步骤
        * */
        //将数据出现的  【次数】  存储在 buckets[] 中
        for (int i = 0; i < 10; i++) {
            buckets[ (a[i]/exp)%10 ]++;
        }
        //更改 buckets[i]  目的是让更改后的值 表示在output[]中的位置
        for (int i = 1; i < 10; i++) {
            buckets[i] += buckets[i-1];
        }
        //将数据存储到临时数组output[]中
        for (int i = a.length-1;i>=0;i--){
            output[buckets[ ( a[i] / exp ) % 10]-1 ] = a[i];
            buckets[ (a[i] /exp )%10]--;//对应位置上的数量减1
        }
        for (int i = 0;i<a.length;i++){
            a[i] = output[i];
        }
    }
    /*
    *
    * =======================================================================================
    * --------------------------------------桶排序--------------------------------------------
    * =======================================================================================
    * */
    /*
    * 桶排序
    * */
    public int[] bucketSort(int[] a,int bucketSize){
        if (a.length == 0)
            return a;
        int min = a[0];
        int max = a[0];
        for (int value : a) {
            if (value < min)
                min = value;
            else if (value > max)
                max = value;
        }

        int bucketCount = (int) Math.floor( (max - min) / bucketSize )+1;//计算桶的数量
        int[][] buckets = new int[bucketCount][0];

        for (int j : a) {
            int index = (int) Math.floor((j - min) / bucketCount);
            buckets[index] = aAppend(buckets[index], j);
        }
        int aIndex = 0;
        for (int[] bucket : buckets) {
            if (bucket.length <= 0)
                continue;
            insertSort(bucket);//对每个桶进行插入排序
            //将排好序的桶中元素存入
            for (int value : bucket) {
                a[aIndex++] = value;
            }
        }
        return a;
    }

    private void insertSort(int[] bucket) {
        int N = bucket.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j >0 && bucket[j] < bucket[j-1]; j--) {
                int temp = bucket[j];
                bucket[j] = bucket[j-1];
                bucket[j-1] = temp;
            }
        }
    }
    /*
    * 扩容方法  每次只阔一个
    * */
    private int[] aAppend(int[] a, int value) {
        a = Arrays.copyOf(a,a.length+1);
        a[a.length-1] = value;
        return a;
    }



    public static void main(String[] args) {
        //TODO
    }
}

















