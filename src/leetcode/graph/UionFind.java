package leetcode.graph;
/*
* 并查集算法
* */
public class UionFind {


    /* 返回图中有多少连通分量 */
    public int count(){
        return count;
    }

    private int count; // 记录连通分量
    private int[] parent;//记录 当前节点的父结点
    private int[] size;//记录每个连通分量的节点个数

    /*
    * 构造方法
    * */
    public UionFind(int count) {
        this.count = count;//一开始 连通分量 就是节点的个数
        parent = new int[count];//初始化  所有节点的父节点都是自己
        size = new int[count];
        for (int i = 0; i < count; i++) {
            parent[i] = i;
            size[i] =1;//一开始的时候  所有连通分量的 节点个数 都是1
        }
    }



    /*
    * 找到当前节点的最终的根节点 用来连接到 另个一个根节点上
    * */
    public int findParent(int x){
        /*
         * 路径压缩：用既然是同一个连通分量  将自己直接连接到 祖父节点上 比 先连接到父结点再连接到祖父节点更快
         * */
        while (x != parent[x]){
            parent[x] = parent[parent[x]];
            x = parent[x];
      }
      return x;
    }

    /*
    * 判断 p q是否在同一个联通分量重
    * */
    public boolean connected(int p,int q){
        return findParent(p) == findParent(q);
    }


    /*
    * 连通两个节点  的优化方法 将 节点数小的 连接到 节点数大的根节点上面
    * */
    public void unionBetter(int p, int q){
        int pRoot = findParent(p);
        int qRoot = findParent(q);

        if (pRoot == qRoot){
            return;
        }
        if (size[pRoot] > size[qRoot]){
            parent[qRoot] = pRoot;//小的连接到大的上面
            size[pRoot] = size[pRoot]+size[qRoot];

        }else{
            parent[pRoot] = qRoot;
            size[qRoot] = size[qRoot]+size[pRoot];
        }
        count--;
    }

    //如果两个节点被联通  其中任意一个节点的根节点 连接到 另一个节点的根节点上
    public void union(int p,int q){
        int pRoot = findParent(p);
        int qRoot = findParent(q);
        if (pRoot == qRoot){
            return;
        }
        parent[pRoot] = qRoot;//将p节点根节点 连接掉q 节点的根节点上
        count--;//连通分量减少一
    }


}
