import java.util.*;
import java.math.*;

/**
*	CS4355 Cryptanalysis Final Project
*	Question 2 - part b
*	Dec.2/19
*	Avery Briggs
*	3471065
*
*	Java program to implement the Miller-Rabin primality test
*
*/

public class MillerRabin{

	private static Scanner scan = new Scanner(System.in);
	private static final BigInteger TWO = new BigInteger("2");
	private static int iterations;
	
	private static boolean checkInt(String text) {
		try {
			BigInteger i = new BigInteger(text);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	private static BigInteger getInputInteger() {
		String input = scan.next();
		if (checkInt(input)) {
			return new BigInteger(input);
		}
		else {
			return BigInteger.ZERO;
		}
	}
	
	private static BigInteger power(BigInteger a, BigInteger d, BigInteger n) {
		BigInteger res = BigInteger.ONE;
		a = a.mod(n);
		while(d.compareTo(BigInteger.ZERO) > 0) {
			if (d.mod(TWO).compareTo(BigInteger.ONE) == 0) {
				res = res.multiply(a).mod(n);
			}
			
			d = d.divide(TWO);
			a = a.multiply(a).mod(n);
		}
		return res;
	}
	
	private static boolean millerTest(BigInteger d, BigInteger n) {
		BigInteger a = TWO;
		BigInteger x = power(a, d, n);
		BigInteger m = n.subtract(BigInteger.ONE);
		
		if (x.compareTo(BigInteger.ONE) == 0 || x.compareTo(m) == 0) {
			return true;
		}
		
		while (d.compareTo(m) != 0) {
			x = x.multiply(x).mod(n);
			d = d.multiply(TWO);
			if (x.compareTo(BigInteger.ONE) == 0) {
				return false;
			}
			if (x.compareTo(m) == 0) {
				return true;
			}
		}
		return false;
	}
	
	private static int isPrime(BigInteger n) {		
		if (n.compareTo(BigInteger.ONE) <= 0 || n.compareTo(new BigInteger("4")) == 0) {
			return 0;
		}
		if (n.compareTo(new BigInteger("3")) <= 0) {
			return 1;
		}
		BigInteger d = n.subtract(BigInteger.ONE);
		while(d.mod(TWO).compareTo(BigInteger.ZERO) == 0) {
			d = d.divide(TWO);
		}
		
		for (int i = 0; i < iterations; i++) {
			if (!millerTest(d, n)) {
				return 0;
			}
		}
		return 1;
	}

	public static void main(String[] args) {
		iterations = 9; // 1024;
		System.out.println("Enter an integer:\n");
		BigInteger input = getInputInteger();
		System.out.println("\n\tInput: " + input);
		System.out.println("\tIs prime? " + isPrime(input));
	}
}