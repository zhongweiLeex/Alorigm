package fourthchapter.cockshash;

public interface HashFamily<T> {
    //根据which来选择散列函数 并返回hash值
    int hash(T x, int which);

    //返回集合中散列函数的个数
    int getNumberOfFunctions();

    //获取新的散列函数
    void generateNewFunctions();
}
