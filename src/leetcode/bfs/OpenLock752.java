package leetcode.bfs;

import java.util.*;

/*
* https://leetcode-cn.com/problems/open-the-lock/submissions/
* BFS 广度优先搜索
*
* */
public class OpenLock752 {
    //j位置的密码位 向上拨动一次
    private String upOne(String s,int j){
        char[] ch = s.toCharArray();
        if (ch[j] == '9'){
            ch[j] = '0';
        }
        else
            ch[j] += 1;
        return new String(ch);
    }
    /*
    * j位置的密码位 向下拨动一次
    * */
    private String downOne(String s, int j){
        char[] ch = s.toCharArray();
        if (ch[j] == '0'){
            ch[j] = '9';
        }
        else
            ch[j] -= 1;
        return new String(ch);
    }
    /*
    * 传统 BFS 单项的 广度优先搜索
    * */
    public int openLock(String[] deadends, String target) {
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();//用来记录这个密码是否被使用过
        Set<String> deads = new HashSet<>(Arrays.asList(deadends));//因为原来的 是 deadEnds 是一个String数组 比较起来不方便
        int step = 0;//记录 改变的步数


        q.offer("0000");//初始状态
        visited.add("0000");//将初始状态添加到 以访问的set中


        while (!q.isEmpty()){//如果当前队列不为空
            int size = q.size();//当前队列的容量
            for (int i = 0; i < size; i++) {//对当前队列中 所有节点 做逐个的操作
                String current = q.poll();///出队

                if (deads.contains(current)){//如果当前的deadsEnd种包含当前这个密码组合 则直接跳过 不进行任何操作
                    continue;
                }
                if (Objects.equals(current, target)){//如果当前的密码组合 和 目标组合相同 则直接返回 步数
                    return step;
                }
                //所有  相邻节点  实际上 此处 相邻节点有  8个
                for (int j = 0; j<4 ; j++){
                    assert current != null;

                    String up = upOne(current,j);

                    if (!visited.contains(up)){//如果当前调整之后的 密码组合 没有出想过 则直接加入
                        q.offer(up);//将当前节点的 相邻节点 添加到 队列中
                        visited.add(up);
                    }

                    String down = downOne(current,j);

                    if (!visited.contains(down)){
                        q.offer(down);
                        visited.add(down);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    public int openLock2(String[] deadends, String target){
        Set<String> visited = new HashSet<>();
        Set<String> deads = new HashSet<>(Arrays.asList(deadends));
        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();
        int steps = 0;//返回的步数值

        q1.add("0000");//从开始想目标开始推导
        q2.add(target);//从目标向前推导

        while (!q1.isEmpty() && !q2.isEmpty()){
            //hashset在遍历过程中不能被修改 因为 结果是唯一的 如果修改了 就不能说清楚 是哪一个找到了的
            Set<String> temp = new HashSet<>();
//            int size1 = q1.size();
//            int size2 = q2.size();
            //用节点少的 做运算 这样比较快
            if (q1.size() > q2.size()){
                temp = q1;
                q1 = q2;
                q2 = temp;
            }
            for (String current : q1) {
                //q1队列中的String
//                String current = q1.iterator().next();

                if (deads.contains(current)){
                    continue;
                }
                if (q2.contains(current)){
                    return steps;
                }
                visited.add(current);///记录被 拜访过了

                for (int j = 0; j < 4; j++) {
                    String up = upOne(current,j);
                    if (!visited.contains(up)){
                        temp.add(up);
                    }
                    String down = downOne(current,j);
                    if (!visited.contains(down)){
                        temp.add(down);
                    }
                }
            }
            steps++;
            q1 = q2;
            q2 = temp;
        }
        return -1;
    }
    /*
    * 直接将 deadends 合并到 visited中的版本
    * */
    public int openLock3(String[] deadends, String target) {
        Set<String> visited = new HashSet<>(Arrays.asList(deadends));
        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();
        int steps = 0;//返回的步数值

        q1.add("0000");//从开始想目标开始推导
        q2.add(target);//从目标向前推导

        while (!q1.isEmpty() && !q2.isEmpty()){
            //hashset在遍历过程中不能被修改 因为 结果是唯一的 如果修改了 就不能说清楚 是哪一个找到了的
            Set<String> temp = new HashSet<>();
//            int size1 = q1.size();
//            int size2 = q2.size();
            for (String current:q1) {
                //q1队列中的String

                if (visited.contains(current)){
                    continue;
                }
                if (q2.contains(current)){
                    return steps;
                }
                visited.add(current);///记录被 拜访过了

                for (int j = 0; j < 4; j++) {
                    String up = upOne(current,j);
                    if (!visited.contains(up)){
                        temp.add(up);
                    }
                    String down = downOne(current,j);
                    if (!visited.contains(down)){
                        temp.add(down);
                    }
                }
            }
            steps++;
            q1 = q2;
            q2 = temp;
        }
        return -1;
    }
}



























