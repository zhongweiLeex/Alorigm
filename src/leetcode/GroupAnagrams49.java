package leetcode;

import java.util.*;

public class GroupAnagrams49 {
    public static List<List<String>> groupAnagrams(String[] strs){
//        int[] charnums = new int[strs.length];
        if (strs.length == 1){
            List<List<String>> result1 = new ArrayList<>();
            result1.add(Arrays.asList(strs));
            System.out.println(result1);
            return result1;
        }
        int nullnums = 0;
        HashMap<String,Integer> map = new HashMap<>();
        for (String s : strs) {
            int charnums=0;

            for (int i = 0;i<s.length();i++){
                if (s.charAt(i) == 0){
                    nullnums++;
                }
                charnums += (int)s.charAt(i);
            }
            map.put(s,charnums);
        }
        System.out.println(map);
        HashMap<Integer,List<String>> map1 = new HashMap<>();

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            List<String> results = new ArrayList<>();

            if (!map1.containsKey(entry.getValue())){
                 results.add(entry.getKey());
                 map1.put(entry.getValue(),results);
             }else{
                 String entryString = entry.getKey();
                 List<String> listValues = map1.get(entry.getValue());
                 if (entryString.equals("")){
                     for (int i = 0; i < nullnums ; i++) {
                         listValues.add(entryString);
                     }
                 }else{
                     listValues.add(entryString);
                     map1.put(entry.getValue(),listValues);
                 }
            }
        }
        System.out.println(map1);
        List<List<String>> result = new ArrayList<>(map1.values());
//        System.out.println(map1.values());

//        result.add(map1.values());
        System.out.println(result);

        return result;
        
    }

    public static void main(String[] args) {
//        String[] strs =  new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        String[] strs = new String[]{"","","tea"};
        groupAnagrams(strs);

    }
}
