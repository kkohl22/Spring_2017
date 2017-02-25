import java.util.ArrayList;

/**
 * Created by Ken Kohl on 2/18/2017.
 */
public class Tests {
    public static void main(String[] args) {
        /*HashTable hashTable = new HashTable(5);
        Tuple a = new Tuple(1, 1.2f);
        Tuple b = new Tuple(2, 2.3f);
        Tuple c = new Tuple(3, 3.3f);
        hashTable.add(a);
        hashTable.add(b);
        hashTable.add(c);
        System.out.println("size is : "+hashTable.size());
        System.out.println("numElements: " + hashTable.numElements());
        Tuple x = new Tuple(1, 1.35f);
        System.out.println(hashTable.remove(x));
        System.out.println("size is now : "+hashTable.size());
        System.out.println("numElements now is : " + hashTable.numElements());*/





        String path = "C:\\Users\\Ken Kohl\\IdeaProjects\\Spring_2017\\src\\points.txt";
        NearestPoints foo = new NearestPoints(path);
        //ArrayList<Float> floats = foo.naiveNearestPoints(771218.f);
/*        long start1 = System.currentTimeMillis();
        foo.allNearestPointsNaive();
        long end1 = System.currentTimeMillis();
        System.out.println("time for naive points is : " + (end1-start1));*/

        ArrayList<Float> bar = foo.npHashNearestPoints(475800.8f);
        for (Float f : bar) {
            System.out.println(f);
        }
        //foo.printHashTable();
        long start2 = System.currentTimeMillis();
        foo.allNearestPointsHash();
        long end2 = System.currentTimeMillis();
        System.out.println("time for hash points is : " + (end2 - start2));
    }

    public int getHashValue(float f) {
        int tmp = (int) Math.floor(f);
        int val = ((60984 * tmp) + 28135) % 100003;
        return val;
    }
}
