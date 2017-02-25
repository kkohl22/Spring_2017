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
    protected int hash(int x) {
        long tmpX = (long) x;
        long tmpA = (long) getA();
        long tmpB = (long) getB();
        long tmpP = (long) getP();
        long result;
        result = (tmpA * tmpX + tmpB) % tmpP;
        return (int) result;
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
    public void setP(int start) {
        if (start < 0 || start == 2) {
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
        setA(getRandom());
        setB(getRandom());
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
     * @return A random value between 0 and max.
     */

    private int getRandom() {
        return (int) (Math.random() * (getP() - 1) + 1);
    }

    private int mod(int x, int y) {
        int result = x % y;
        if (result < 0) {
            result += y;
        }
        return result;
    }
}


