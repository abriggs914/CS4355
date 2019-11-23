package sample;

import java.math.BigInteger;

public class Model {

    private BigInteger p;
    private BigInteger g;
    private BigInteger x;
    private BigInteger y;
    private BigInteger bigX;
    private BigInteger bigY;
    private BigInteger k1;
    private BigInteger k2;

    private DiffieHellmanKeyExchange dh;

    public final BigInteger ZERO = new BigInteger("0");
    public final BigInteger ONE = new BigInteger("1");

    Model() {
        this.p = toBigInteger(65537);
        this.g = toBigInteger(3);
        this.dh = new DiffieHellmanKeyExchange(p, g);
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

    public void setX(int x) {
        this.x = toBigInteger(x);
        this.dh.setX(this.x);
    }

    public BigInteger getY() {
        return y;
    }

    public void setY(int y) {
        this.y = toBigInteger(y);
        this.dh.setY(this.y);
    }

    public BigInteger getBigX() {
        return bigX;
    }

    public void setBigX(BigInteger bigX) {
        this.bigX = bigX;
    }

    public BigInteger getBigY() {
        return bigY;
    }

    public void setBigY(BigInteger bigY) {
        this.bigY = bigY;
    }

    public BigInteger getK1() {
        return k1;
    }

    public void setK1(BigInteger k1) {
        this.k1 = k1;
    }

    public BigInteger getK2() {
        return k2;
    }

    public void setK2(BigInteger k2) {
        this.k2 = k2;
    }

    public String computeBigX() {
        dh.setX(x);
        this.bigX = dh.computeBigX();
        return bigX.toString();
    }

    public String computeBigY() {
        dh.setY(y);
        this.bigY = dh.computeBigY();
        return bigY.toString();
    }

    public String computeSessionKey1() {
        System.out.println("BigY: " + this.dh.getBigY());
        this.k1 = dh.computeSessionKey1();
        return k1.toString();
    }

    public String computeSessionKey2() {
        System.out.println("BigX: " + this.dh.getBigX());
        this.k2 = dh.computeSessionKey2();
        return k2.toString();
    }
}
