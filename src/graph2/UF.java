package graph2;

/*
*
* 连通分量的 Union-find 实现
*
* */
public class UF {
    private int[] id;//分量id
    private int count;//分量数量

    /*
    * 构造方法
    * */
    public UF(int N){
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public int count(){
        return count;
    }
    /*
    * 如果两个的根节点是相同的 则说明在同一个连通分量重
    * */
    public boolean connected(int p,int q){
        return find(p) == find(q);
    }

    public int find(int p){
        while(p!=id[p])
            p = id[p];//id[id[id[num]]]... 嵌套 直到找到根节点
        return p;
    }

    public void union(int p,int q){
        //统一 p 和 q的根节点统一
        //以qRoot为根节点
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return;

        id[pRoot] = qRoot;
        count--;
    }


/*    public int find(int p){
        return id[p];
    }

    public void union(int p,int q){
        int pID = find(p);
        int qID = find(q);

        if (pID == qID) return;
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID) id[i] = qID;
        }
        count--;
    }*/

}
