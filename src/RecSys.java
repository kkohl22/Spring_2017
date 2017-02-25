import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Ken Kohl on 2/22/2017.
 */
public class RecSys {
    // hashmap to store the user number with the associated float value
    private HashMap numToFloat;
    // the reverse of the other
    private HashMap floatToNum;
    // Arraylist
    private ArrayList<Float> pointSet = new ArrayList<>();
    private ArrayList<Integer>[] ratings;
    private NearestPoints nearestUsers;

    /**
     * Creates a a hash table of the closest users along with and arraylist of user ratings and two hashmaps to associate the index of a user with its float value.
     * @param mrMatrix contains the absolute path name of the file that contains the mapped ratings matrix.
     */
    public RecSys(String mrMatrix) {
        ArrayList<Float> pointSet = new ArrayList<>();
        File file = new File(mrMatrix);
        int numUsers;
        int numMovies;
        int index = 1;

        try {
            Scanner scanner = new Scanner(file);
            float currentUser = 0.0f;
            numUsers = scanner.nextInt();
            numMovies = scanner.nextInt();
            ratings = new ArrayList[numUsers+1];
            //**NOTE** having two hashes be the reverse of eachother probably sounds real dumb but the run time is still O(n). Plus it works.
            numToFloat = new HashMap(numUsers);
            floatToNum = new HashMap(numUsers);
            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    while (scanner.hasNextInt()) {
                        if (ratings[index] == null) {
                            ratings[index] = new ArrayList<>(numMovies);
                        }
                        ratings[index].add(scanner.nextInt());
                    }
                    numToFloat.put(index, currentUser);
                    floatToNum.put(currentUser, index);
                    index++;
                } else if (scanner.hasNextFloat()) {
                    currentUser = scanner.nextFloat();
                    pointSet.add(currentUser);
                }
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        nearestUsers = new NearestPoints(pointSet); // creates the checker for getting close users
    }

    /**
     * returns that users rating; otherwiseit will predict the rating based on the approach described above, and returns the predicted rating.
     * @param u index of user tog et rating
     * @param m movie of whose rating we want.
     * @return either the users rating or a predicted one if the user has not yet rated this movie.
     */
    public float ratingOf(int u, int m) {
        if(isUIllegal(u) | isMIllegal(m)) {
            throw new IndexOutOfBoundsException();
        }
        int ratingSum = 0;
        int numUsefulUsers = 0;
        int rating = ratings[u].get(m-1);
        if (rating == 0) {
            float user = (float) numToFloat.get(u); //gets float value associated.
            ArrayList<Float> nearbyUsers = nearestUsers.npHashNearestPoints(user);
            for (float rater : nearbyUsers) {
                int index = (int) floatToNum.get(rater);
                int userRating = ratings[index].get(m-1);
                if (userRating != 0) {
                    ratingSum += userRating;
                    numUsefulUsers++;
                }
            }
          //  Math.round(d * 1e6) / 1e6;
            double roundNumber = Math.round(((double)ratingSum / (double) numUsefulUsers) * 1e2) / 1e2;
            return (float) roundNumber;

        } else {
            return (float) rating;
        }
    }

    /**
     * checks if the value is within the constraints
     * @param u User index to check
     * @return True if index is illegal. False otherwise.
     */
    private boolean isUIllegal(int u) {
        return (u < 0 || u > ratings.length) ;
    }
    private boolean isMIllegal(int m) {
        return (m < 0 || m > ratings[1].size()) ;
    }
}
