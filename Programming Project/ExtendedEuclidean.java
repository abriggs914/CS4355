/*package whatever //do not write package name here */

import java.util.Arrays;

class ExtendedEuclidean {
	
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
	
	public static int[] extendedEuclidean(int a, int b, int s, int t) {
	    if (a == 0) {
	        return new int[] {b, 0, 1};
	    }
	    else {
            int[] res = extendedEuclidean(b % a, a, 1, 1); 
	        int s1 = res[1], t1 = res[2];
            s = t1 - ((b / a) * s1);
            t = s1;
            // System.out.println("a:\t" + a + "\tb:\t" + b + "\ts:\t" + s + "\tt:\t" + t + "\ts1:\t" + s1 + "\tt1:\t" + t1);
            res[1] = s;
            res[2] = t;
            return res;
	    }
	}
	
	public static boolean check(int a, int b, int[] res) {
	    return a * res[1] + b * res[2] == 1;
	}
    
    public static String egcd(int a, int b, int s, int t) {
        int[] egcd = extendedEuclidean(a, b, s, t);
        String string = "\n\tegcd(" + a + ", " + b + "):\n"; 
        if (check(a, b, egcd)) {
            string += "(" + a + " * " + egcd[1] + ") + (" +  b + " * " + egcd[2] + ") = 1";
        }
        else {
            string += "(" + a + " * " + egcd[1] + ") + (" +  b + " * " + egcd[2] + ") != 1";
            string += "\n=> " + ((a * egcd[1]) + (b * egcd[2]));
        }
        string += "\n" + Arrays.toString(egcd) + "\n";
        return string;
    }
	
	public static void main (String[] args) {
		
		System.out.println("gcd:");
		System.out.println(gcd(1769, 550));
		System.out.println(gcd(1234, 4321));
		System.out.println(egcd(1234, 4321, 1, 1));
		System.out.println("\n" + egcd(1769, 550, 1, 1));
		System.out.println("gcd(171,550):\t" + gcd(171, 550));
		System.out.println("gcd(1082, 309):\t" + gcd(1082, 309));
		System.out.println(egcd(19743119, 647783, 1, 1));
		System.out.println(egcd(45, 15, 1, 1));
		System.out.println("-------------------------------------------------------");
		System.out.println("lcm(1769, 550):\t" + lcm(1769, 550));
	}
}