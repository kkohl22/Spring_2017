import java.util.ArrayList;

/**
 * Created by Ken Kohl on 2/22/2017.
 */
public class nearestPoints {

    /**
     * @param dataFile holds the absolute path of the file that contains the set of points S
     */
    public nearestPoints(String dataFile) {

    }

    /**
     * @param pointSet contains the set of points S.
     */
    public nearestPoints(ArrayList<Float> pointSet) {

    }

    /**
     * Returns an array list of points (from the set S) that are close
     * to p
     *
     * @param p
     */
    public ArrayList<Float> naiveNearestPoints(float p) {
        return null;
    }

    /**
     * Builds the data structure that enables to quickly answer nearest point
     * queries. Your data structure must use the notion of neighbor preserving hashing and along with
     * the class HashTable. Otherwise, you will receive zero credit.
     */
    public void buildDataStructure() {

    }

    /**
     *
     * @param p
     * @return  an array list of points (from the S) that are close to p
     */
    public ArrayList<Float> npHashNearesetPoints(float p) {
        return null;
    }

    /**
     * For every point p ∈ S, compute the list of all points from S that
     * are close to p by calling the method NaiveNearestPoints(p). Write the results to a file named
     * NaiveSolution.txt
     */
    public void allNearestPointsNaive() {

    }

    /**
     * For every point p ∈ S, compute the list of all points from S that
     * are close to p by calling the method NPHashNearestPoints(p). Write the results to a file named
     * HashSolution.txt. The expected time of this method must be O(n+P p∈S N(p)); otherwise you will receive zero credit.
     */
    public void allNearestPointsHash() {

    }

}
