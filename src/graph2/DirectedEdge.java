package graph2;
/*
* 加权有向边  的  数据类型
*
* */
public class DirectedEdge {
    private final int v;//边的起点
    private final int w;//边的终点
    private final double weight;//边的权重
    /*
    * 构造方法
    * */
    public DirectedEdge(int v,int w,double weight){
        this.w = w;
        this.v = v;
        this.weight = weight;
    }
    public double weight(){
        return weight;
    }
    /*
    * 获取边的起点
    * */
    public int from(){
        return v;
    }

    /*
    * 获取边的终点
    * */
    public int to(){
        return w;
    }

    public String toString(){
        return String.format("%d -> %d %.2f",v,w,weight);
    }


}
