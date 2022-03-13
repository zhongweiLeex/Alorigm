package leetcode.medianfinder;

import java.util.PriorityQueue;

public class MedianFinder295 {
//        int mid;

    private PriorityQueue<Integer> large;// 梯形  底部大  顶部小
    private PriorityQueue<Integer> small;//三角 底部小 顶部大
    public MedianFinder295() {
        large = new PriorityQueue<>((a,b) -> (a-b));// 小的在上面  大的在底下
        small = new PriorityQueue<>((a,b) -> (b-a));// 小的在底下  大的在上面
    }
    /*
    * 1. 维护 large 和 small 的元素个数 相差不能超过 1
    * 2. 维护 large 堆的堆顶元素 要大于等于  small 堆的堆顶元素
    * */
    public void addNum(int num) {
        if (small.size() >= large.size()){
            small.offer(num);//插入之后   优先队列 会自动排序  大元素在上面 小元素在下面
            large.offer(small.poll());// 为了维护 大的始终在 large中 小的始终在small中 且两边个数不能相差1   将 small 堆顶的（small中最大的 加入到 large中）
        }else{
            large.offer(num);
            small.offer(large.poll());
        }
    }

    public double findMedian() {
        if (large.size() > small.size()){
            return large.peek();
        }else if (large.size() < small.size()){
            return small.peek();
        }else{
            return (large.peek() + small.peek()) / 2.0;
        }
    }


}
