package sample;

import java.math.BigInteger;

public class Model {

    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger e;
    private BigInteger d;
    private BigInteger m;
    private BigInteger c;
    private BigInteger phiN;
    private String workingE;

    private RSAEncryption rsa;

    Model() {
        this.p = new BigInteger("0");
        this.q = new BigInteger("0");
        this.n = new BigInteger("0");
        this.e = new BigInteger("0");
        this.d = new BigInteger("0");
        this.m = new BigInteger("0");
        this.c = new BigInteger("0");
        this.phiN = new BigInteger("0");
        this.rsa = new RSAEncryption();
    }

    int generatePrimeNumber() {
        return rsa.generatePrimeNumber(1000, 5000);
    }

    BigInteger getP() {
        return p;
    }

    void setP(BigInteger p) {
        this.p = p;
    }

    BigInteger getQ() {
        return q;
    }

    void setQ(BigInteger q) {
        this.q = q;
    }

    BigInteger getN() {
        return n;
    }

    void setN(BigInteger n) {
        this.n = n;
    }

    BigInteger getE() {
        return e;
    }

    void setE(BigInteger e) {
//        System.out.println("updating e: " + e);
        this.e = e;
    }

    public BigInteger getD() {
        return d;
    }

    void setD(BigInteger d) {
        this.d = d;
    }

    public BigInteger getM() {
        return m;
    }

    public void setM(BigInteger m) {
        this.m = m;
    }

    public BigInteger getC() {
        return c;
    }

    void setC(BigInteger c) {
        this.c = c;
    }

    BigInteger computeN() {
        return p.multiply(q);
    }

    public String getWorkingE() {
        return workingE;
    }

    void setWorkingE(String workingE) {
        this.workingE = workingE;
    }

    BigInteger getPhiN() {
        return phiN;
    }

    void setPhiN() {
        this.phiN = p.subtract(new BigInteger("1")).multiply(q.subtract(new BigInteger("1")));
    }

    BigInteger calculateD() {
        BigInteger x = phiN;
        try {
            x = modInverse(e, phiN);
            Main.controller.eIsNormal();
            return x;
        }
        catch (Exception e) {
            System.out.println("E is not set yet.");
            Controller.eNeedsToBeSelected();
            x = new BigInteger("0");
            return x;
        }
    }

    boolean checkPrime(BigInteger x) {
        return rsa.checkPrime(x) && x.compareTo(new BigInteger("0")) > 0;
    }

    private BigInteger modInverse(BigInteger n, BigInteger m) {
        return rsa.modInverse(n, m);
    }

    BigInteger gcd(BigInteger a, BigInteger b) {
        return rsa.gcd(a, b);
    }

    BigInteger encrypt(BigInteger m1) {
        return rsa.encrypt(m1, e, n);
    }

    BigInteger decrypt() {
        return rsa.decrypt(c, d, n);
    }
}
