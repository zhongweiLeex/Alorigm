package firstchapter;

import java.util.Iterator;
import java.util.LinkedList;

public class Linkedlistitratorremove {
    private LinkedList<Integer> ll;

    public Linkedlistitratorremove(LinkedList<Integer> ll) {
        this.ll = ll;
    }
    public static void removeEvensVer2(LinkedList<Integer> lst){
        Iterator<Integer> itr = lst.iterator();
        while (itr.hasNext()){
            if (itr.next() %2 == 0){
                itr.remove();
            }
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            linkedList.add(i);
        }
        Linkedlistitratorremove linkedlistitratorremove = new Linkedlistitratorremove(linkedList);
        removeEvensVer2(linkedlistitratorremove.ll);
        System.out.println(linkedlistitratorremove.ll);

    }

}
