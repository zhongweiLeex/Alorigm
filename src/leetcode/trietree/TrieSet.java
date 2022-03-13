package leetcode.trietree;

import java.util.List;

// 对  TrieMap进行封装 实现 TrieSet
public class TrieSet {
    private final TrieMap<Object> map = new TrieMap<>();

    public void add(String key){
        map.put(key,new Object());
    }
    public void remove(String key){
        map.remove(key);
    }

    public boolean contains(String key){
        return map.containStr(key);
    }

    public String shortestPrefixOf(String query){
        return map.shortestPrefixOf(query);
    }
    public String longestPrefixOf(String query){
        return map.longestPrefixOf(query);
    }
    public List<String> keysWithPrefix(String prefix){
        return map.strWithPrefix(prefix);
    }
    public boolean hasKeyWithPrefix(String prefix){
        return map.hasStrWithPrefix(prefix);
    }
    public List<String> keysWithPattern(String pattern){
        return map.strsWithPattern(pattern);
    }
    public boolean hasKeyWithPattern(String pattern){
        return map.hasStrWithPattern(pattern);
    }
    public int size(){
        return map.size();
    }

}
