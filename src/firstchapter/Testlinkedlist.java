package firstchapter;

import java.util.LinkedList;

public class Testlinkedlist {
    public static LinkedList<Integer> ll = new LinkedList<>();
    public void fill() {
        for (int i = 0; i < 10;i++){
            ll.add(i);
        }
        //return ll;
    }
    public void removeEvensVer1(){
        //int i = 0;
        //ll.removeIf(i -> i % 2 != 0);
        try {
            ll.removeIf(x -> x % 2 == 0);//其底层实现是 Iterator
        }catch (Exception e){
            System.out.println("this is an exception");
        }
        //System.out.println(ll);
    }

    public static void main(String[] args) {
        Testlinkedlist tll = new Testlinkedlist();
        tll.fill();
        tll.removeEvensVer1();
        System.out.println(ll);
    }
}
