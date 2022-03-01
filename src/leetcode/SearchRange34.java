package leetcode;
/*
* 二分搜索 左右边界问题
* 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
* 如果数组中不存在目标值 target，返回[-1, -1]。
* 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
* */
public class SearchRange34 {
    /*
    * 二分搜索找右侧边界
    * */
    private int rightBound(int[] nums,int target){
        //左闭区间  右闭区间
        //[left,right]
        //如果是 左闭区间 右边开区间 应该是  left = 0,right = nums.length   [left,right)
        //本处 使用的 左闭区间  右边 闭区间 [left,right]
        int left = 0;
        int right = nums.length-1;
        while (left <=right){//左边 闭区间 右边 闭区间 所以 循环退出条件 必须是 left > right 才会退出
            int mid = left + (right-left)/2;//防止溢出
            //到右半边寻找
            if (nums[mid] < target){
                left = mid +1;
            }else if (nums[mid] > target){//到 左半边寻找
                right = mid -1;
            }else if (nums[mid] == target){//如果找到了  不返回 因为是要找到 右边边界
                left = mid +1;//收缩左边界  直到与right指针交叉
            }
        }
        //两种情况
        //第一种情况：右边的指针 到了左边边界的 左边   left没动  right一直向右移  实际上就是 target比左边界还小
        //第二种情况：left 和 right 都动过了 但是 数组中间没有 target目标值
        //为什么这里 是 nums[right] != target 因为上面的循环已经判定了 left > right 了 也就是意味着 right指针比left指针还要小了
        if (right<0 || nums[right] != target){
            return -1;
        }
        return right;//排除上面的-1情况后 直接返回 目标值的 右边界
    }



    /*
    * 二分查找 寻找 target值的 左侧边界
    * */
    private int leftBound(int[] nums,int target){
        int left = 0;//定义左指针
        int right = nums.length -1;//定义右指针
        while(left <= right){
            int mid = left + (right - left)/2;
            if (nums[mid] < target){//到右边寻找
                left = mid +1;
            }else if (nums[mid] > target){
                right = mid -1;
            }else if (nums[mid] == target){
                right = mid -1;//收缩right  直到 与left交叉
            }
        }
        //边界非法情况
        if (left >= nums.length || nums[left] != target){
            return -1;
        }
        return left;//target值的左边界
    }



    //寻找目标值的位置
    private int findTarget(int[] nums,int target){
        //[left,right]
        int left = 0;
        int right = nums.length-1;
        int mid = left +(right-left)/2;//中间位置
        while (left <= right){
            if (nums[mid] == target){
                return mid;
            }else if (nums[mid] < target){
                left = mid +1;
            }else if (nums[mid] > target){
                right = mid - 1;
            }
        }
        return -1;
    }
    /*
    * 寻找左右边界
    *
    * */
    public int[] searchRange(int[] nums, int target) {
        return new int[]{leftBound(nums,target),rightBound(nums, target)};
    }




}
















