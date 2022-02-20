package sort;

public class quickSortTest{
    public static <T extends Comparable<? super T>> void quickSort(T[] a, int left, int right){
        //quickSort(a,0,a.length-1);
        if(right <= left) return;
        int i = partition(a,left,right);
        quickSort(a,left, i -1);//将切分位置的前半部分排序
        quickSort(a, i +1,right);//将切分位置的后半部分排序

    }
    private static <T extends Comparable<? super T>> int partition(T[] a, int left, int right){
        int i = left;
        int j = right - 1;
//        int mid = left + (right - left)/2;
//        exchange(a,mid,right);
        pivot(a,left,right);//获取枢纽元 并将a[right-1]存储枢纽元
        T pivot = a[right-1];
//        int index = right-1;
        while (true){
            while(a[i].compareTo(pivot) < 0) i++;
            while(a[j].compareTo(pivot) > 0 && j>left) j--;
            if (i >= j){
                break;
            }
            exchange(a, i, j);
        }
        if (i<right)
            exchange(a, i,right-1);//交换i和暂存的pivot位置
        return i;//返回切分位置
    }
    private static <T extends Comparable<? super T>> void pivot(T[] a, int left, int right){
        //保持左边小 右边大的  枢纽值在中间
        int mid = left + (right - left)/2;
        if (a[left].compareTo(a[mid])>0)
            exchange(a,left,mid);
        if (a[left].compareTo(a[right])>0)
            exchange(a,left,right);
        if (a[right].compareTo(a[mid])<0)
            exchange(a,right,mid);
        exchange(a,right-1,mid);//将中间的枢纽值放到数组 right -1 位置
    }
    private static <T> void exchange(T[] a, int i, int j){
        T temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
//    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Integer[] arr = {9, 8};
        quickSort(arr,0,arr.length-1);
        for (Integer i :
                arr) {
            System.out.println(i);
        }
    }
}
