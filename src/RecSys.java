import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Ken Kohl on 2/22/2017.
 */
public class RecSys {
    private HashMap hashMap;
    private ArrayList<Float> pointSet = new ArrayList<>();
    private ArrayList<Integer>[] ratings;
    private NearestPoints nearestUsers;

    // TODO - everything
    public RecSys(String mrMatrix) {
        ArrayList<Float> pointSet = new ArrayList<>();
        File file = new File(mrMatrix);
        ArrayList<Integer>[] ratings;
        int numUsers;
        int numMovies;
        int index = 0;

        try {
            Scanner scanner = new Scanner(file);
            float currentUser = 0.0f;
            numUsers = scanner.nextInt();
            numMovies = scanner.nextInt();
            ratings = new ArrayList[numUsers];
            hashMap = new HashMap(numUsers);
            while (scanner.hasNext()) {
                if (scanner.hasNextFloat()) {
                    currentUser = scanner.nextFloat();

                    pointSet.add(currentUser);
                } else if (scanner.hasNextInt()) {
                    while (scanner.hasNextInt()) {
                        if (ratings[index] == null) {
                            ratings[index] = new ArrayList<>(numMovies);
                        }
                        ratings[index].add(scanner.nextInt());
                    }
                    hashMap.put(index, ratings);
                }
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        nearestUsers = new NearestPoints(pointSet); // creates the checker for getting close users
    }

    public int ratingOf(int u, int m) {
        int rating = ratings[u].get(m);
        if(rating == 0) {
            float user = (float)hashMap.get(u);
            ArrayList<Float> nearbyUsers = nearestUsers.npHashNearestPoints(user);
        }
        else {
            return rating;
        }
    }

    private boolean hasRated(float user, int movie) {
        if()
        return false;
    }
}
