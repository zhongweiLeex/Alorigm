package graph2;
/*
* 按照权重 进行快速 union-find
* */
public class WeightedQuickUnionUf {
    private int[] id;//父连接数组
    private int[]  sz;//根节点所对应的分量大小
    private int count;//连通分量的数量

    /*
    * 构造方法
    * */
    public WeightedQuickUnionUf(int N){
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            sz[i] = 1;//初始化根节点所有的对应分量
        }

    }
    public int count(){
        return count;
    }
    public boolean connected(int p,int q){
        return  find(q) == find(q);
    }
    private int find(int p){
        while(p!=id[p])
            p = id[p];
        return p;
    }

    public void union( int p,int q){
        int i = find(p);
        int j = find(q);
        if (i==j) return;
        if (sz[i]<sz[j]){
            id[i] = j;
            sz[j] += sz[i];
        }else{
            id[j] = i;
            sz[i] += sz[j];
        }
        count--;
    }
}
