package org.example;

public class Point {
    private final double x;
    private final int y;
    private final int r;
    private final boolean isShoot;

    public Point(double x, int y, int r, boolean result) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.isShoot = result;
    }

    public double getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public boolean isShoot() {
        return isShoot;
    }
}
