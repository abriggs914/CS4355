package sample;

import java.math.BigInteger;
import java.util.Random;

public class RSAEncryption {

    public RSAEncryption() {

    }

    public int totient(int n) {
        int count = 0;
        boolean isprime = true;
        for (int i = 2; i < (Math.pow(n, 0.5) + 1); i++) { // in range(2, int(math.sqrt(count)) + 1):
            if (count % i == 0) {
                isprime = false;
                break;
            }
            count++;
        }
        System.out.println("New totient: " + count);
        return count;
    }

    public BigInteger encrypt(BigInteger m, BigInteger e, BigInteger n) {
        return m.pow(e.intValue()).mod(n);
    }

    public synchronized BigInteger decrypt(BigInteger c, BigInteger d, BigInteger n) {
        return c.pow(d.intValue()).mod(n);
    }

    public BigInteger modInverse(BigInteger n, BigInteger m) {
        return n.modInverse(m);
    }

    public BigInteger gcd(BigInteger a, BigInteger b) {
        if (b.compareTo(new BigInteger("0")) == 0) {
            return a;
        }
        else {
            return gcd(b, a.mod(b));
        }
    }

    public boolean checkPrime(BigInteger n) {
        return n.isProbablePrime(4);
    }

    public boolean checkPrime(int n) {
        for (int i = 2; i < (Math.pow(n, 0.5) + 1); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int generatePrimeNumber(int start, int end) {
        // list of primes
//        ArrayList<Integer> primes = new ArrayList<>();
        int rnd_num = generateRandomNumber(start, end);
        boolean isprime = checkPrime(rnd_num);
        while (!isprime) {
            rnd_num = generateRandomNumber(start, end);
            isprime = checkPrime(rnd_num);
        }
        return rnd_num;
    }

    public int generateRandomNumber(int start, int end) {
        Random rnd = new Random();
        if (start >= end || start < 0) {
            // adjusting bad input
            start = 0;
            end = 1000;
        }
        double rdnN = (rnd.nextDouble() * (end - start)) + start;
        int roundN = (int) rdnN;
//        System.out.println("rndN (double):\t" + rdnN + "\troundN (int):\t" + roundN);
        return roundN;
    }

//    private ArrayList<Integer> generatePrimesList(int start, int end) {
//        /*
//        *   import math
//            import itertools
//            def primes():
//                count = 2
//                while True:
//                    isprime = True
//                    for i in range(2, int(math.sqrt(count)) + 1):
//                        if count % i == 0:
//                            isprime = False
//                            break
//                    if isprime:
//                        yield count
//
//                    count += 1
//
//            lst = list(itertools.takewhile(lambda x : x <= 31, primes()))
//            print(lst)
//        * */
//    }

}
