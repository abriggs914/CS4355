package sample;

import java.math.BigInteger;

public class ElGamalEncryption {

    private BigInteger p;
    private BigInteger g;
    private BigInteger x;
    private BigInteger y;
    private BigInteger m1;
    private BigInteger r;
    private BigInteger c1;
    private BigInteger c2;
    private BigInteger m2;

    ElGamalEncryption(BigInteger p, BigInteger g) {
        this.p = p;
        this.g = g;
    }

    public BigInteger computeY() {
        this.y = g.pow(x.intValue()).mod(p);
        return y;
    }

    public BigInteger computeC1() {
        this.c1 = g.pow(r.intValue()).mod(p);
        return c1;
    }

    public BigInteger computeC2() {
        this.c2 = m1.multiply(y.pow(r.intValue())).mod(p);
        return c2;
    }

    public BigInteger decrypt() {
        BigInteger d = p.subtract(x).subtract(BigInteger.ONE);
        this.m2 = c1.pow(d.intValue()).multiply(c2).mod(p);
        return m2;
    }

    public BigInteger getP() {
        return p;
    }

    public void setP(BigInteger p) {
        this.p = p;
    }

    public BigInteger getG() {
        return g;
    }

    public void setG(BigInteger g) {
        this.g = g;
    }

    public BigInteger getX() {
        return x;
    }

    public void setX(BigInteger x) {
        this.x = x;
    }

    public BigInteger getY() {
        return y;
    }

    public void setY(BigInteger y) {
        this.y = y;
    }

    public BigInteger getM1() {
        return m1;
    }

    public void setM1(BigInteger m1) {
        this.m1 = m1;
    }

    public BigInteger getR() {
        return r;
    }

    public void setR(BigInteger r) {
        this.r = r;
    }

    public BigInteger getC1() {
        return c1;
    }

    public void setC1(BigInteger c1) {
        this.c1 = c1;
    }

    public BigInteger getC2() {
        return c2;
    }

    public void setC2(BigInteger c2) {
        this.c2 = c2;
    }

    public BigInteger getM2() {
        return m2;
    }

    public void setM2(BigInteger m2) {
        this.m2 = m2;
    }
}
