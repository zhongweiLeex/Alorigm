package sort;
/*
* 直接插入排序
* */
public class InsertSort {
    public int[] insertSort1(int[] array){
        int len = array.length;
        for (int i = 1; i < len; i++) {
            if (array[i]<array[i-1]){//先和左边比较 如果确实小于左边 说明没有排好 接下来工作
                int temp = array[i];//将当前没有拍好的数字取出
                int j;
                for (j = i;array[j-1]>temp; j--) {
                    array[j] = array[j-1];//将比temp大的元素依次后移
                }
                array[j] = temp;//插入
            }
        }
        return array;
    }
}
