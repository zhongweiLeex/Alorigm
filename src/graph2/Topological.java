package graph2;

import java.nio.file.DirectoryStream;

/*
* 有向无环图的拓扑排序
*
* 使用深度优先搜索中 的 逆后序排序   stack
*
* */
public class Topological {

    private Iterable<Integer> order = null; //顶点的拓扑顺序 初始化 order 为 null
    /*
    * 有向无权图 拓扑排序构造方法
    * */
    public Topological(Digraph G){
      DirectedCycle cycleFinder = new DirectedCycle(G);
      //判断G中是否有环
      if (!cycleFinder.hasCycle()){//如果G中没有环 则进行以下工作 如果有环 则直接order不进行以下工作
                                    // 就是 null
          //只有  有向无环图 才能拓扑排序

          DepthFirstOrder dfs = new DepthFirstOrder(G);//深度优先遍历 得到
          // 先序/后序/逆后序 三种遍历拓扑序列

          order = dfs.reversePost();//逆后序排列
      }
    }
    /*
    * 加权有向图  拓扑排序构造方法
    * */
    public Topological(EdgeWeightedDigraph G){
         EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(G);
         if (!finder.hasCycle()){
             DepthFirstOrder dfs = new DepthFirstOrder(G);
             order = dfs.reversePost();
         }
    }

/*    public Topological(EdgeWeightedDigraph G){
        DirectedCycle cycleFinder = new DirectedCycle(G);
        //判断G中是否有环
        if (!cycleFinder.hasCycle()){//如果G中没有环 则进行以下工作 如果有环 则直接order不进行以下工作
            // 就是 null
            //只有  有向无环图 才能拓扑排序

            DepthFirstOrder dfs = new DepthFirstOrder(G);//深度优先遍历 得到
            // 先序/后序/逆后序 三种遍历拓扑序列

            order = dfs.reversePost();//逆后序排列
        }
    }*/
    public Iterable<Integer> order(){
        return order;
    }
    /*
    * 判断G是否是有向无环图 如果是 返回 true
    * */
    public boolean isDAG(){
        return order != null;
    }
}
