package leetcode.graph;

import java.util.Arrays;

public class MinimunCost1135 {
    int count;
    int[] parent;
    public int minimunCost(int n,int[][] connections){
        count = n+1;
        parent = new int[n+1];
        int minCost = -1;
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        Arrays.sort(connections,(a,b)->(a[2]- b[2]));//使用 comparable 比较接口
        for(int[] edge:connections){
            if (isConnected(edge[0],edge[1])){
                continue;
            }
            minCost += edge[2];
            union(edge[0],edge[1]);

        }
        // 节点 0 没有使用 原本应该是  count ==1 但是 这里0没有使用 所以是 count ==2
        if (count==2){
            return minCost;
        }else {
            return -1;
        }
    }
    private int findParent(int x){
        while(x!= parent[x]){
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
    private void union(int a, int b ){
        if (findParent(a) != findParent(b)){
            parent[findParent(a)] = findParent(b);
        }
        count--;
    }
    private int count(){
        return count;
    }
    private boolean isConnected(int a,int b){
        return findParent(a) == findParent(b);
    }
}
