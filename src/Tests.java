import java.util.ArrayList;

/**
 * Created by Ken Kohl on 2/18/2017.
 */
public class Tests {
    public static void main(String[] args) {

        String pathToPoints = "C:\\Users\\Ken Kohl\\IdeaProjects\\Spring_2017\\src\\points.txt";
        NearestPoints foo = new NearestPoints(pathToPoints);

        //ArrayList<Float> floats = foo.naiveNearestPoints(771218.f);
        long start1 = System.currentTimeMillis();
        foo.allNearestPointsNaive();
        long end1 = System.currentTimeMillis();
        System.out.println("time for naive points is : " + (end1-start1));

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
