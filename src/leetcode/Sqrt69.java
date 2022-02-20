package leetcode;

import java.util.ArrayList;
import java.util.List;
/*
* 二分查找
*
* 或者牛顿迭代法
*
* */
public class Sqrt69 {
    public int mySqrt(int x) {
        int left = 0;
        int right = x;
        int result = -1;
        while(left <= right){
            int middle = left + (right - left) /2;
            if ((long) middle*middle <= x){
                result = middle;
                left = middle + 1;
            }else{
                right = middle -1;
            }
        }
        return result;
    }
}
