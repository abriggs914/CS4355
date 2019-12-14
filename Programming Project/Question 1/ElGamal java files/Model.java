package sample;

import java.math.BigInteger;

public class Model {

    private BigInteger p;
    private BigInteger g;
    private BigInteger x;
    private BigInteger y;
    private BigInteger m1;
    private BigInteger r;
    private BigInteger c1;
    private BigInteger c2;
    private BigInteger m2;

    private ElGamalEncryption elgamal;

    Model() {
        this.p = toBigInteger(65537);
        this.g = toBigInteger(3);

//        this.p = toBigInteger(17);
//        this.g = toBigInteger(6);
        this.elgamal = new ElGamalEncryption(p, g);
    }

    public String computePublicKeyY() {
        return this.elgamal.computeY().toString();
    }

    public String computeC1() {
        return this.elgamal.computeC1().toString();
    }

    public String computeC2() {
        return this.elgamal.computeC2().toString();
    }

    public String decrypt() {
        return this.elgamal.decrypt().toString();
    }

    BigInteger toBigInteger(int x) {
        return new BigInteger(Integer.toString(x));
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
        this.elgamal.setX(this.x);
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
        this.elgamal.setM1(this.m1);
    }

    public BigInteger getR() {
        return r;
    }

    public void setR(BigInteger r) {
        this.r = r;
        this.elgamal.setR(this.r);
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
