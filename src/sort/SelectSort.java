package sort;
/*
* 直接选择排序
*
*
* */
public class SelectSort {
    public int[] selectSort1(int[] array){
        for (int i = 0; i < array.length - 1; i++) {
            //外部循环用来遍历数组中元素
            int min = i;
            //内部循环用来对比当前最小和剩余所有元素的大小 如果找到比当前小的元素 直接交换
            for (int j = i+1; j < array.length; j++) {
                if (array[min]>array[j]){
                    min = j;
                }
            }
            //将当前最小与之前的最小i位置元素 进行交换
            if (min!=i){
                int temp = array[min];
                array[min] = array[i];
                array[i] = temp;
            }
        }
        return array;
    }
}
