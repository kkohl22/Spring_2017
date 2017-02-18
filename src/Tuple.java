/**
 * Created by Ken Kohl on 2/18/2017.
 */
public class Tuple {
    private int key;
    private float value;

    Tuple(int keyP, float valueP) {
        key = keyP;
        value = valueP;
    }

    /**
     * @return returns key
     */
    public int getKey() {
        return key;
    }

    /**
     * @return returns value
     */
    public float getValue() {
        return value;
    }

    /**
     * @param t tuple to compare.
     * @return true if tuples are the same. False otherwise
     */
    public boolean equals(Tuple t) {
        if(t.getKey() == key && t.getValue() == value) {
            return true;
        }
        return false;
    }
}
