import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by Ken Kohl on 2/18/2017.
 */
public class HashTable {
    private int numElements = 0;
    private int size; // number of buckets
    private HashFunction h;
    private ArrayList<Tuple>[] table;

    /**
     * Finds the smallest prime integer p whose value is at least size. Creates a hash table of size p where each cell initially is NULL
     *
     * @param size Starting index of finding p
     */
    HashTable(int size) {
        h = new HashFunction(size);
        this.size = h.getP();
        table = new ArrayList[size()];
    }

    /**
     * @return The max load of this hash table
     */
    public int maxLoad() {
        int max = 0;
        for (int i = 0; i < size(); i++) {
            int count = 0;

            if (table[i] == null) {
                continue;
            }

            if (table[i].size() > max) {
                max = table[i].size();
            }
        }
        return max;
    }

    /**
     * @return the average load of the hash table
     */
    public float averageLoad() {
        int buckets = 0;
        for (int i = 0; i < size(); i++) {
            if (table[i] == null || table[i].isEmpty()) {
                continue;
            }
            buckets++;
        }
        int temp = (int)(numElements() / buckets);
        return (numElements() / buckets);
    }

    /**
     * @return the current size of the hash table
     */
    public int size() {
        return h.getP();
    }

    /**
     * @return the number of Tuples that are currently stored in the hash table.
     */
    public int numElements() {
        return numElements;
    }

    /**
     * @return the load factor which is numElements()/size()
     */
    public int loadFactor() {
        return numElements() / size();
    }

    /**
     * @param t tuple to be added
     * @return True if the tuple added correctly. False otherwise.
     */
    public boolean add(Tuple t) {
        if (loadFactor() > 0.7) {
            table = resize();
        }
        int index = h.hash(t.getKey());
        if (table[index] == null) {
            table[index] = new ArrayList<>();
        }
        if (table[index].add(t)) {
            numElements++;
            return true;
        }
        return false;
    }

    /**
     * @param k Searches for the tuple with key k
     * @return an array list of Tuples (in the hash table) whose key equals k. If no such Tuples exist, returns an empty list
     */
    public ArrayList<Tuple> search(int k) {
        int index = h.hash(k);
        if (table[index] == null) {
            ArrayList<Tuple> temp = new ArrayList<>();
            return temp;
        } else return table[index];
    }

    /**
     * @param t Tuple to be removed
     * @return True if the tuple was successfully removed. False otherwise.
     */
    public boolean remove(Tuple t) {
        int index = h.hash(t.getKey());
        ArrayList<Tuple> temp = table[index];
        for(Tuple tuple : temp) {
            if(tuple.equals(t)) {
               if(table[index].remove(tuple)) {
                   numElements--;
                   return true;
               }
            }
        }
        return false;
      /*
        if(table[index].remove(t);) {
            numElements--;
            return true;
        }
        return false;*/
    }


    /**
     * doubles the size of the hash table and rehashes all the elements (tuples) to the new hash table.
     *
     * @return The new hashtable
     */
    private ArrayList<Tuple>[] resize() {
        int newSize = size * 2;
        HashFunction newHash = new HashFunction(newSize);
        ArrayList<Tuple>[] newTable = new ArrayList[newHash.getP()];
        for (int i = 0; i <= size(); i++) {
            for (Tuple t : table[i]) {
                newTable[newHash.hash(t.getKey())].add(t);
            }
        }
        h = newHash;
        return newTable;
    }

}
