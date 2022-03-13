package leetcode.lru;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

/*
* least frequently used  最少使用的缓存淘汰算法
* */
public class LFUCache {
    //key 到value 的映射
    HashMap<Integer,Integer> keyToValue;

    //key 到frequency 的映射
    HashMap<Integer, Integer> keyToFrequency;

    // frequency 到 key 的映射
    // 1. 因为可能存在 多个不同的key对应 相同的frequency 的情况， 一个frequency 对应一个 key的列表
    // 2. frequency 对应的key的列表是存在时序的，便于快速查找到最旧的key
    // 3. 快速删除key列表的 任何一个 key  但是不能访问 这个 key 而是一次性 定位 key 因为是不能顺序访问其他的
    HashMap<Integer, LinkedHashSet<Integer>> frequencyToKeys;
//  HashMap<Integer, int[]> freq2Keys;


    //记录最小的 频次
    int minFrequency;
    //记录 LFU缓存的最大容量
    int capacity;

    public LFUCache(int capacity) {

        keyToValue = new HashMap<>();
        keyToFrequency = new HashMap<>();
        frequencyToKeys = new HashMap<>();//相同频率 对应 不同的 key     key 要求 1. 按照时序排列  2. 快速删除key列表中的任何一个key

//        freq2Keys = new HashMap<>();

        this.capacity = capacity;
        this.minFrequency = 0;//记录最小频次
    }
    /*
    * 1. 缓存中查询 key ，如果key存在，则返回key对应的val，如果不存在则返回 -1
    * */
    public int get(int key) {
        if (!keyToValue.containsKey(key)){
            return -1;
        }
        // 增加 key对应的 frequency
        increaseFreq(key);
        //返回 对应的value
        return keyToValue.get(key);
    }
    /*
     * 1. 插入或修改缓存，如果key存在，则修改value
     * 2. 如果key不存在，则插入键值对 (key,value)
     * */
    public void put(int key, int value) {
        if (this.capacity <= 0) return;
        //如果keyToValue存在 这个key 则直接更新value
        if (keyToValue.containsKey(key)){
            keyToValue.put(key,value);
            //对应的 frequency 也要+1
            increaseFreq(key);
            return;
        }

        //如果当前的cache 已经满了 则需要删除 frequency 最小的key
        if (this.capacity <= keyToValue.size()){
            removeMinFreqKey();
        }

        //插入 键值对
        keyToValue.put(key,value);
        // 插入 key - frequency
        keyToFrequency.put(key,1);//key到这里了 key 原本是不存在的
        //插入 frequency-key 表

        /*
        freq2Keys.putIfAbsent(1,new int[capacity]);
        int[] temp = freq2Keys.get(1);
        temp[key]++;*/


        frequencyToKeys.putIfAbsent(1,new LinkedHashSet<>());
        frequencyToKeys.get(1).add(key);
        //插入新的额key之后，最小的frequency 肯定是 1
        this.minFrequency = 1;

    }

    private void increaseFreq(int key) {
        int frequency = keyToFrequency.get(key);
        keyToFrequency.put(key,frequency+1);

        //将 frequency2keys 更新  key 到 更高频率
        frequencyToKeys.get(frequency).remove(key);
        frequencyToKeys.putIfAbsent(frequency+1,new LinkedHashSet<>());
        frequencyToKeys.get(frequency+1).add(key);



        //如果之前的那个frequencyToKeys中的keys列表为空了 则直接删除那个
        if (frequencyToKeys.get(frequency).isEmpty()){
            frequencyToKeys.remove(frequency);

            //如果对应的 这个key对应的 frequency是 minFrequency 则最小的frequency ++
            if (frequency  == this.minFrequency){
                this.minFrequency++;
            }
        }
    }

    private void removeMinFreqKey(){
        LinkedHashSet<Integer> keyList = frequencyToKeys.get(this.minFrequency);//freq最小的key列表

        //头部最先被插入的那个key就是该被淘汰的key
        int deletedKey = keyList.iterator().next();
        keyList.remove(deletedKey);

        //如果删除了那个key之后 那个key空了 就直接删除 这个位置得键值对
        if (keyList.isEmpty()){
            frequencyToKeys.remove(this.minFrequency);//将这个 frequency 对应的 那个键值对 直接删除  因为这个frequency对应的key列表为空了
        }
        keyToValue.remove(deletedKey);
        keyToFrequency.remove(deletedKey);
    }


}
