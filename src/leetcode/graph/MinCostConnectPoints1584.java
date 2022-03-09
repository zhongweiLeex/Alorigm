package leetcode.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinCostConnectPoints1584 {
    int count ;
    int[] parent;
    public int minCostConnectPoints(int[][] points) {
        // int count = points.length;
        int result = 0;
        List<int[]> edges = calculator(points);
        edges.sort((a, b) -> {
            return a[2] - b[2];
        });//函数式
        count = points.length;
        parent = new int[points.length];
        for(int i = 0; i< points.length;i++){
            parent[i] = i;
        }
        for(int[] edge : edges){
            if(isConnected(edge[0],edge[1])){
                continue;
            }else {
                union(edge[0],edge[1]);
                result = result + edge[2];
                count--;
            }

        }
        return result;
    }


    //计算 各个点之间边的权重 并排序
    private List<int[]> calculator(int[][] points){
        // int[][] edges = new int[points.length][points.length];
        //存储各个节点之间的权重 权重就是曼哈顿距离
        List<int[]> edges = new ArrayList<>();//存储所有节点之间的 距离 也就是权重
        for(int i= 0;i< points.length; i++){
            for(int j = i+1 ; j < points.length;j++){//这是个无向边  前面的权重不用算了
                int ix = points[i][0];//第 i 个点的  x坐标
                int iy = points[i][1];//第  i个点的 y坐标
                int jx = points[j][0];//第 j个点的 x 坐标
                int jy = points[j][1];//第 j 个点的 y坐标
                edges.add(new int[]{i,j,Math.abs(ix - jx)+Math.abs(iy-jy)});//全部节点的 权重构造完成
            }
        }
        return edges;
    }

    private boolean isConnected(int a,int b){
        return findParent(a) == findParent(b);
    }

    private int findParent(int p){
        while(p != parent[p]){
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }
    private void union(int x, int y){
        if(findParent(x)!=findParent(y)){
            parent[findParent(x)] = findParent(y);
        }
        count--;
    }



}
