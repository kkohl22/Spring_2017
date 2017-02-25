import javafx.scene.layout.FlowPane;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Ken Kohl on 2/22/2017.
 */
public class NearestPoints {
    //TODO - mostly everything
    private ArrayList<Float> pointSet;
    private HashTable hashTable;

    /**
     * @param dataFile holds the absolute path of the file that contains the set of points S
     */
    public NearestPoints(String dataFile) {
        File file = new File(dataFile);

        try {
            pointSet = new ArrayList<>();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextFloat()) {
                pointSet.add(scanner.nextFloat());
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        buildDataStructure();
    }


    /**
     * @param pointSet contains the set of points S.
     */
    public NearestPoints(ArrayList<Float> pointSet) {
        this.pointSet = pointSet;
        buildDataStructure();
    }

    /**
     * Returns an array list of points (from the set S) that are close
     * to p
     *
     * @param p
     */
    public ArrayList<Float> naiveNearestPoints(float p) {
        ArrayList<Float> naivePoint = new ArrayList<>();
        for (Float q : pointSet) {
            if (Math.abs(p - q) <= 1) {
                naivePoint.add(q);
            }
        }
        return naivePoint;
    }

    /**
     * Builds the data structure that enables to quickly answer nearest point
     * queries. Your data structure must use the notion of neighbor preserving hashing and along with
     * the class HashTable. Otherwise, you will receive zero credit.
     */
    public void buildDataStructure() {
        int size = (int) (1.5 * pointSet.size());
        hashTable = new HashTable(size);
        for (Float point : pointSet) {
            int tmpKey = (int) Math.floor(point);
            Tuple t = new Tuple(tmpKey, point);
            hashTable.add(t);
        }
    }

    /**
     * @param p
     * @return an array list of points (from the S) that are close to p
     */
    public ArrayList<Float> npHashNearestPoints(float p) {
        ArrayList<Float> nearPoints = new ArrayList<>();
        for (int i = -1; i < 2; i++) {
            int searchValue = (int) Math.floor(p+i);
            ArrayList<Tuple> tuples = hashTable.search(searchValue);
            for (Tuple t : tuples) {
                if (Math.abs(p - t.getValue()) <= 1) {
                    nearPoints.add(t.getValue());
                }
            }
        }
        return nearPoints;
    }

    /**
     * For every point p ∈ S, compute the list of all points from S that
     * are close to p by calling the method NaiveNearestPoints(p). Write the results to a file named
     * NaiveSolution.txt
     */
    public void allNearestPointsNaive() {
        try {
            PrintWriter writer = new PrintWriter("NaiveSolution.txt", "UTF-8");
            for (Float p : pointSet) {
                ArrayList<Float> f = naiveNearestPoints(p);
                writer.print(p + ": ");
                for (Float point : f) {
                    writer.print(point + " ");
                }
                writer.println("");

            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    /**
     * For every point p ∈ S, compute the list of all points from S that
     * are close to p by calling the method NPHashNearestPoints(p). Write the results to a file named
     * HashSolution.txt. The expected time of this method must be O(n+P p∈S N(p)); otherwise you will receive zero credit.
     */
    public void allNearestPointsHash() {
        try {
            PrintWriter writer = new PrintWriter("HashSolution.txt", "UTF-8");
            for (Float f : pointSet) {
                ArrayList<Float> points = npHashNearestPoints(f);
                writer.print(f);
                for (Float point : points) {
                    writer.print(" " + point);
                }
                writer.println();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void printHashTable() {
        try {
            PrintWriter writer = new PrintWriter("hashTable.txt", "UTF-8");
            for (int i = 0; i < 100001; i++) {
                writer.print(i + ":");
                ArrayList<Tuple> t = hashTable.search(i);
                for (Tuple m : t) {
                    writer.print(" " + m.getValue());
                }
                writer.println();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
