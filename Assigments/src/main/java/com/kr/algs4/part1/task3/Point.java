package com.kr.algs4.part1.task3;

import seidgewick.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
        @Override
        public int compare(Point o1, Point o2) {
            double slope1 = slopeTo(o1);
            double slope2 = slopeTo(o2);

            if (slope1 == slope2) {
                return 0;
            }
            if (slope1 < slope2) {
                return -1;
            } else {
                return 1;
            }
        }
    };       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        if (that.y == this.y) {
            if (that.x == this.x) {
                return Double.NEGATIVE_INFINITY;
            }
            return 0;
        } else if (that.x == this.x) {
            return Double.POSITIVE_INFINITY;
        } else {
            return (double)(that.y - this.y) / (that.x - this.x);
        }
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        int result = 0;
        if (this.y == that.y) {
            result = this.x - that.x;
        } else {
            result = this.y - that.y;
        }
        if (result < 0) {
            return -1;
        } else if (result > 0) {
            return 1;
        } else return 0;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(2, 3);

        p1.draw();
        p2.drawTo(p1);
    }
}
