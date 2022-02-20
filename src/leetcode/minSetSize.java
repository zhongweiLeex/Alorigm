package leetcode;

import java.util.*;

public class minSetSize {
    public static int minSetSizeSolution(int[] arr){
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int j : arr) {
            Integer num = map.get(j);
            map.put(j,num == null ? 1 : num + 1);
        }
//        int length = arr.length;//获取arr的长度
//        System.out.println(map);
        List<Map.Entry<Integer,Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());//降序排序
            }
        });
        List<Integer> list1 = new ArrayList<>();
//        HashMap<Integer, Integer> map1 = new HashMap<>();
        for (Map.Entry<Integer, Integer> mapping : list) {
//            map1.put(mapping.getKey(),mapping.getValue());
//            System.out.println("key:" + mapping.getKey() + " value:" + mapping.getValue());
            list1.add(mapping.getValue());
        }
//        System.out.println(map1);
//        System.out.println(list1);
        int len = list1.get(0);
        int i;
        for (i = 1; i < list1.size(); i++) {
            if (len < arr.length/2){
                len += list1.get(i);
            }else{
                break;
            }
//            if (list1[i])
        }

//        map.values().stream().sorted().collect(Collectors.toList()).forEach(System.out::println);

//        HashSet<Integer> set = new HashSet<>();
//        for (int j : arr) {
//            set.add(j);
//        }
//
//        Iterator<Integer> iterator = set.iterator();
//
//        for (int i = 0; i < arr.length; i++) {
//
//            map.put(set.iterator().next(),)
//        }
//        List<Integer>[] list =(ArrayList<Integer>[]) new ArrayList[arr.length];
//
//
//
//


        return i;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9,10};
        System.out.println(minSetSizeSolution(arr));
    }
}
