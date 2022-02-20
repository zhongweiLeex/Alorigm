package graph2;

/*
*      加权无向边  数据类型
* */
public class Edge implements Comparable<Edge>{
    private final int v;//vertex
    private final int w;//another vertex
    private final double weight;//weight of edge

    public Edge(int v,int w,double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /*
    * return the edge of weight.
    * */
    public double weight(){
        return weight;
    }

    /*
    * return the vertex of the edge.
    * */
    public int either(){
        return v;
    }
    /*
    * return the other vertex except the argument vertex
    *
    * */
    public int other(int vertex){
        if (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new RuntimeException("Inconsistent edge");
    }
    /*
    * compare the weight of his.edge to that.edge
    * the order of the vertices using Comparable to realize
    * */
    public int compareTo(Edge that){
        if      (this.weight()< that.weight())  return -1;
        else if (this.weight() > that.weight()) return +1;
        else                                    return 0;
    }
    public String toString(){
        return String.format("%d-%d %.2f" , v,w,weight);
    }




}
