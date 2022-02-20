package graph2;
/*
*
* 任意定点对之间的最短路径
* */
public class DijkstraAllPairsSP {

    private DijkstraSP[] all;

    DijkstraAllPairsSP(EdgeWeightedDigraph G){

        all = new DijkstraSP[G.V()];

        for (int v = 0; v<G.V();v++){
            //构造一个 在 加权有向图 G 中 ， 以每个顶点v为起点的所有的 Dijkstra最短路径 的 数组
            //比如 all[v] 就是 在G图中 以 v为起点的一个Dijkstra最短路径类对象
            all[v] = new DijkstraSP(G,v);
        }
    }
    Iterable<DirectedEdge> path(int s, int t){
        return all[s].pathTo(t);
    }
    /*
    * 从 起点s 到 终点 t 的最短路径
    * */
    double dist(int s,int t){
        return all[s].distTo(t);
    }


}
