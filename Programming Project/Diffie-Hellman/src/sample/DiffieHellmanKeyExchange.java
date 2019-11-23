package sample;

import java.math.BigInteger;

public class DiffieHellmanKeyExchange {

    private BigInteger p;
    private BigInteger g;
    private BigInteger x;
    private BigInteger y;
    private BigInteger bigX;
    private BigInteger bigY;
    private BigInteger k1;
    private BigInteger k2;

    DiffieHellmanKeyExchange(BigInteger p, BigInteger g) {
        this.p = p;
        this.g = g;
    }

    public BigInteger computeBigX() {
        this.bigX = g.pow(x.intValue()).mod(p);
        return this.bigX;
    }

    public BigInteger computeBigY() {
        this.bigY = g.pow(y.intValue()).mod(p);
        return this.bigY;
    }

    public BigInteger computeSessionKey1() {
        System.out.println("x: " + x);
        System.out.println("bigY: " + bigY);
        System.out.println("p: " + p);
        this.k1 = bigY.pow(x.intValue()).mod(p);
        return k1;
    }

    public BigInteger computeSessionKey2() {
        System.out.println("y: " + y);
        System.out.println("bigX: " + bigX);
        System.out.println("p: " + p);
        this.k2 = bigX.pow(y.intValue()).mod(p);
        return k2;
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

    public BigInteger getBigX() {
        return this.bigX;
    }

    public BigInteger getBigY() {
        return bigY;
    }

    public BigInteger getK1() {
        return k1;
    }

    public BigInteger getK2() {
        return k2;
    }
}
