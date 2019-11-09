/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;
import java.math.BigDecimal;

class DLPBrute {
    
    public static int calcM(double start, double end, int exp, double n, double ans) {
        double i = start;
        boolean calc = true;
        while (calc) {
            BigDecimal base = new BigDecimal(Double.toString(i));
            BigDecimal baseToPower = base.pow(exp);
            int modVal = baseToPower.remainder(new BigDecimal(n)).intValue();
            System.out.println("i: " + i + ", baseToPower: " + baseToPower + ", modVal: " + modVal);
            if(Integer.toString(modVal).equals(Integer.toString((int) ans))) {
                return (int) i;
            }
            else if (i >= end) {
                break;
            }
            i += 1;
        }
        return -1;
    }
	
	public static int attack(int exp, double n, double ans) {
		int m = -1;
		for (double i = 0; i < Double.MAX_VALUE; i += 10000) {
		    double spaceToEnd = 0;
		    // System.out.println("i: " + i);
		    double j;
		    for (j = 0; j < 10000; j++) {
		        spaceToEnd = Double.MAX_VALUE - (j + i);
		        if (spaceToEnd < 10000) {
		            break;
		        }
		        m = calcM((i + j), (i + 1), exp, n, ans);
		        if (m > 0) {
		            break;
		        }
		    }
		    if (spaceToEnd < 10000) {
		        break;
		    }
		    if (m > 0) {
		        break;
		    }
		    i += j;
		}
		return m;
	}
    
	public static void main (String[] args) {
		double n = 35263;
		double ans1 = 28657;
		double ans2 = 22520;
		int exp1 = 17;
		int exp2 = 23;
		int m = -1;
		ArrayList<Double> ms_to_crack = new ArrayList<>();
		ms_to_crack.add(ans1);
		ms_to_crack.add(ans2);
		ArrayList<Integer> exponents = new ArrayList<>();
		exponents.add(exp1);
		exponents.add(exp2);
		for (int i = 0; i < ms_to_crack.size(); i++) {
			double remainder = ms_to_crack.get(i);
			int exp = exponents.get(i);
			System.out.println("m: " + attack(exp, n, remainder));
		}
	}
}