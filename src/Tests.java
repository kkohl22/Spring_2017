import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by Ken Kohl on 2/18/2017.
 */
public class Tests {
    public static void main(String[] args) {
        Tuple a = new Tuple(2, 1.5f);
        Tuple b = new Tuple(4, 3.2f);
        Tuple c = new Tuple(8, 7.3f);
        Tuple d = new Tuple(16, 4.56f);

        HashTable hashTable = new HashTable(4);

      //  System.out.println(hashTable.toString());

        hashTable.add(a);

        hashTable.add(b);
        hashTable.add(c);
        hashTable.add(d);

        System.out.println(hashTable.maxLoad());
        System.out.println(hashTable.averageLoad());
    }
}
