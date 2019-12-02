import java.util.*;
import java.math.*;

/**
*	CS4355 Cryptanalysis Final Project
*	Question 1 - part a
*	Dec.2/19
*	Avery Briggs
*	3471065
*
*	Java program to implement the Extended-Euclidean algorithm for computing
*	modular inverses of two given numbers.
*
*/

class ExtendedEuclidean {
	
	private static Scanner scan = new Scanner(System.in);
	
	private static BigInteger getInputInteger() {
		String input = scan.next();
		if (checkInt(input)) {
			return new BigInteger(input);
		}
		else {
			return BigInteger.ZERO;
		}
	}
	
	private static boolean checkInt(String text) {
		try {
			BigInteger i = new BigInteger(text);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
    public static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }
	
	public static int gcd(int a, int b) {
	    if (b == 0) {
	        return a;
	    }
	    else {
	        return gcd(b, a % b);
	    }
	}
	
	// iterative version
	public static int modInverse(int a, int mod) {
		a = a % mod;
		for (int i = 1; i < mod; i++) {
			if ((a * i) % mod == 1) {
				return i;
			}
		}
		return 1;
	}
	
	// recursive version
	public static BigInteger[] extendedEuclidean(BigInteger a, BigInteger b, BigInteger s, BigInteger t) {
	    if (a.compareTo(BigInteger.ZERO) == 0) {
	        return new BigInteger[] {b, BigInteger.ZERO, BigInteger.ONE};
	    }
	    else {
            BigInteger[] res = extendedEuclidean(b.mod(a), a, BigInteger.ONE, BigInteger.ONE); 
	        BigInteger s1 = res[1], t1 = res[2];
            s = t1.subtract((b.divide(a)).multiply(s1));
            t = s1;
            res[1] = s;
            res[2] = t;
            return res;
	    }
	}
	
	public static boolean check(BigInteger a, BigInteger b, BigInteger[] res) {
	    return (a.multiply(res[1])).add(b.multiply(res[2])).compareTo(BigInteger.ONE) == 0;
	}
    
    public static String egcd(BigInteger a, BigInteger b, BigInteger s, BigInteger t) {
        BigInteger[] egcd = extendedEuclidean(a, b, s, t);
        String string = "\n\tegcd(" + a + ", " + b + "):\n";
		string += Arrays.toString(egcd) + "\n";
        if (check(a, b, egcd)) {
            string += "(" + a + " * " + egcd[1] + ") + (" +  b + " * " + egcd[2] + ") = 1";
        }
        else {
            string += "(" + a + " * " + egcd[1] + ") + (" +  b + " * " + egcd[2] + ") != 1";
            string += "\n=> " + ((a.multiply(egcd[1])).add(b.multiply(egcd[2])));
        }
        string += "\n";
        return string;
    }
	
	public static void main (String[] args) {
		
		System.out.println("Enter integer A:\n");
		BigInteger a = getInputInteger();
		System.out.println("Enter integer B:\n");
		BigInteger b = getInputInteger();
		System.out.println(egcd(a, b, BigInteger.ONE, BigInteger.ONE));
		
		//System.out.println("gcd:");
		//System.out.println(gcd(1769, 550));
		//System.out.println(gcd(1234, 4321));
		//System.out.println(egcd(1234, 4321, 1, 1));
		//System.out.println("\n" + egcd(1769, 550, 1, 1));
		//System.out.println("gcd(171,550):\t" + gcd(171, 550));
		//System.out.println("gcd(1082, 309):\t" + gcd(1082, 309));
		//System.out.println(egcd(19743119, 647783, 1, 1));
		//System.out.println(egcd(45, 15, 1, 1));
		//System.out.println(egcd(92400, 3696, 1, 1));
		//System.out.println("-------------------------------------------------------");
		//System.out.println("lcm(1769, 550):\t" + lcm(1769, 550));
		//System.out.println("modInverse(1234, 4321): " + modInverse(1234, 4321));
		//System.out.println("modInverse(1769, 550): " + modInverse(1769, 550));
	}
}