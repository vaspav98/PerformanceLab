package org.example;

public class Point {
    private int x;
    private int y;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Point toPoint(String str) {
        Point point = new Point();
        point.setX(str.charAt(0) - '0');
        point.setY(str.charAt(2) - '0');
        return point;
    }




}
