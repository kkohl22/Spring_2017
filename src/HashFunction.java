import java.util.Random;

/**
 * Created by Ken Kohl on 2/18/2017.
 */
public class HashFunction {
    private int a;
    private int b;
    private int p;

    HashFunction(int range) {
        setP(range);
        setA(Math.abs(getRandom(getP())));
        setB(Math.abs(getRandom(getP())));
    }

    /**
     * @return the value of a
     */
    public int getA() {
        return a;
    }

    /**
     * @return b the value of b
     */
    public int getB() {
        return b;
    }

    /**
     * @return the value of p
     */
    public int getP() {
        return p;
    }

    /**
     * @param x The value to be hashed
     * @return The hashed value of x
     */
    public int hash(int x) {
        return ((getA() * x + getB()) % getP());
    }

    /**
     * Sets the value of a
     *
     * @param x
     */
    public void setA(int x) {
        a = x % p;
    }

    /**
     * sets the value of b
     *
     * @param y
     */
    public void setB(int y) {
        b = y % p;
    }

    /**
     * Sets p to be the smallest prime number equal or greater than start
     *
     * @param start The first index to check if prime
     */
    private void setP(int start) {
        if (start < 0) {
            p = 2;
        } else if (isPrime(start)) {
            p = start;
        } else {
            boolean notFound = true;
            while (notFound) {
                start += 1;
                if (isPrime(start)) {
                    p = start;
                    notFound = false;
                }
            }
        }
    }

    /**
     * @param x Value to check if prime
     * @return True if x is prime. False otherwise.
     */
    private boolean isPrime(int x) {
        if (x % 2 == 0) return false;
        for (int i = 3; i * i <= x; i += 2) {
            if (x % i == 0)
                return false;
        }
        return true;
    }

    /**
     * @param max The max numebr the random number should be.
     * @return A random value between 0 and max.
     */
    private int getRandom(int max) {
        Random random = new Random(max);
        return random.nextInt();
    }
}


