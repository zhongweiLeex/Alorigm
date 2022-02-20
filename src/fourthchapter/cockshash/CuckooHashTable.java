package fourthchapter.cockshash;

import java.util.Arrays;
import java.util.Random;

public class CuckooHashTable<T> {
    //定义最大装载因子
    private static final double MAX_LOAD = 0.4;

    private static final int ALLOWED_REHASHES = 1; //定义最大rehash次数
    private static final int DEFAULT_TABLE_SIZE = 101;//定义默认表大小
    private final HashFamily<? super T> hashFunctions;//定义散列函数集合

    private final int numHashFunctions;//定义散列函数个数

    private T[] array; //定义当前表
    private int currentSize;//定义当前表长度
    private int rehashes;//定义rehash的次数

    private Random random = new Random();


    /*
    * 构造方法
    * */
    public CuckooHashTable(HashFamily<? super T> hashFamily){
        this(hashFamily,DEFAULT_TABLE_SIZE);
    }
    /*
    * 构造方法
    * */
    public CuckooHashTable(HashFamily<? super T> hashFamily,int size){
        allocateArray(nextPrime(size));
        doClear();
        hashFunctions = hashFamily;
        numHashFunctions = hashFamily.getNumberOfFunctions();
    }

    /*
    * 清空
    * */
    public void makeEmpty(){
        doClear();
    }
    /*
    * 检查是否包含
    * */
    public boolean contains(T x){
        return findPosition(x)!= -1;
    }

    /*
    * 哈希函数
    * @param x 当前元素
    * @param which 选取散列函数对应的位置
    * */
    private int myHash(T x,int which){
        //调用散列函数集合中的hash方法获取hash值
        int hashVal = hashFunctions.hash(x,which);
        //线性探测法 生成hash值
        hashVal %=array.length;
        if (hashVal < 0){
            hashVal += array.length;
        }
        return hashVal;
    }

    private int findPosition(T x){
        for (int i = 0; i < numHashFunctions; i++) {
            //获取x使用第i个hash函数之后的hash值
            int position = myHash(x,i);
            //判断表中是否存在当前元素
            if (array[position]!=null && array[position].equals(x)){
                return position;
            }
        }
        return -1;
    }
    /*
    * 删除元素：先查询表中是否存在该元素 如果存在 则进行删除该元素
    * */
    public boolean remove(T x){
        int position = findPosition(x);
        if (position!=-1){
            array[position] = null;
            currentSize--;
        }
        return position!=-1;
    }

    /*
    * 插入：先判断该元素是否存在，如果不存在，在判断表的大小是否达到最大负载
    * 如果达到最大负载 则进行扩展 最后调用insertHelper方法进入插入元素
    * */
    public boolean insert(T x){
        if (contains(x)){//如果包含x 则返回失败
            return false;
        }

        //如果表的大小 达到最大负载 进行扩展
        if (currentSize >= array.length*MAX_LOAD){
            expand();
        }

        return insertHelper(x);
    }

    private boolean insertHelper(T x) {
        final int COUNT_LIMIT = 100;//设定循环的最大次数
        while (true){
            int lastPosition = -1;//记录上一个元素位置
            int position;
            //进行查找插入
            for (int count = 0;count <COUNT_LIMIT;count++){
                for (int i = 0; i < numHashFunctions; i++) {
                    position = myHash(x,i);
                    //查找成功，直接返回
                    if (array[position] == null){
                        array[position] = x;
                        currentSize++;
                        return true;
                    }
                }
                //查找失败 进行替换操作 产生随机数位置 产生的位置不能与原来的位置相同
                int i = 0;
                do {
                    position = myHash(x,random.nextInt(numHashFunctions));
                }while (position == lastPosition && i++ < 5);

                //替换原来位置的元素
                T temp = array[lastPosition = position];
                array[position] = x;
                x = temp;
            }

            //超过次数 还是插入失败 进行扩表 或者 rehash操作
            if (++rehashes > ALLOWED_REHASHES){//超过rehash次数了 进行扩表
                expand();
                rehashes = 0;
            }else {//没有超过rehash使用次数  使用rehash
                rehash();
            }
        }
    }
    /*
    * 创建一个更大的数组 但是保持使用同样的散列函数
    * */
    private void expand(){
        rehash((int) (array.length / MAX_LOAD));
    }
    /*
    * 保持数组规模不变 但是创建一个新的数组 用新选的散列函数填充
    * */
    private void rehash(){
        hashFunctions.generateNewFunctions();
        rehash(array.length);
    }

    private void rehash(int newLength){
        T[] oldArray = array;
        allocateArray(nextPrime(newLength));
        currentSize = 0;
        for (T str : oldArray) {
            if (str != null){
                insert(str);
            }
        }
    }

    private void allocateArray(int arraySize){
        array = (T[]) new Object[arraySize];
    }

    public void printArray(){
        System.out.println("当前散列表如下 ");
        System.out.println("表的大小为：" + array.length);
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null){
                System.out.println("current position:  " + i + "  current value:   " + array[i]);
            }
        }
    }


    private static int nextPrime(int n) {
        while(!isPrime(n)){
            n++;
        }
        return n;
    }

    /*
    * 判断是否为素数
    * */
    private static boolean isPrime(int n) {
        for (int i = 2; i <=Math.sqrt(n); i++) {
            if (n%i == 0 && n!=2){
                return false;
            }
        }
        return true;
    }

    /*
    * 清空操作
    * */
    private void doClear() {
        currentSize = 0;
        Arrays.fill(array, null);
    }

}
