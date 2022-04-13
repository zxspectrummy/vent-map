package com.demo.advanced;

import com.demo.base.Point;

public class Region {
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public Region(int x1, int y1, int x2, int y2) {
        if (x1 >= x2 || y1 >= y2)
            throw new IllegalArgumentException("(x1,y1) should be lesser than (x2,y2)");
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public Region getQuadrant(int quadrantIndex) {
        int quadrantWidth = (this.x2 - this.x1) / 2;
        int quadrantHeight = (this.y2 - this.y1) / 2;

        // 0=SW, 1=NW, 2=NE, 3=SE
        return switch (quadrantIndex) {
            case 0 -> new Region(x1, y1, x1 + quadrantWidth, y1 + quadrantHeight);
            case 1 -> new Region(x1, y1 + quadrantHeight, x1 + quadrantWidth, y2);
            case 2 -> new Region(x1 + quadrantWidth, y1 + quadrantHeight, x2, y2);
            case 3 -> new Region(x1 + quadrantWidth, y1, x2, y1 + quadrantHeight);
            default -> null;
        };
    }

    public boolean containsPoint(Point point) {
        // Consider left and top side to be inclusive for points on border
        return point.getX() >= this.x1
            && point.getX() < this.x2
            && point.getY() >= this.y1
            && point.getY() < this.y2;
    }

    public boolean doesOverlap(Region testRegion) {
        // Is test region completely to left of my region?
        if (testRegion.getX2() <= this.getX1()) {
            return false;
        }
        // Is test region completely to right of my region?
        if (testRegion.getX1() >= this.getX2()) {
            return false;
        }
        // Is test region completely above my region?
        if (testRegion.getY1() >= this.getY2()) {
            return false;
        }
        // Is test region completely below my region?
        if (testRegion.getY2() <= this.getY1()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[Region (" + x1 + ", " + y1 + "), (" + x2 + ", " + y2 + ")]";
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public int getWidth() {
        return x2 - x1;
    }

    public int getHeight() {
        return y2 - y1;
    }
}