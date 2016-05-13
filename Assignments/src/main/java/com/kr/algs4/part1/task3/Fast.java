package com.kr.algs4.part1.task3;

import com.kr.algs4.part1.task3.Point;
import seidgewick.*;

import java.io.File;
import java.util.Arrays;

public class Fast {
    public static void main(String[] args) {
        if (args.length != 1) {
            StdOut.println("Input filename should be passed to application");
            return;
        }
        In input = new In(new File(args[0]));
        int pointsCount = input.readInt();
        Point[] points = new Point[pointsCount];
        for (int i = 0; i < pointsCount; i++) {
            int x = input.readInt();
            int y = input.readInt();
            points[i] = new Point(x, y);
        }

        Quick.sort(points);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        SET<Double> slopes = new SET<Double>();

        for (int i = 0; i < pointsCount; i++) {
            Point[] slopeOrder = points.clone();
            Arrays.sort(slopeOrder, points[i].SLOPE_ORDER);
            boolean[] isCollinear = new boolean[pointsCount];
            isCollinear[0] = true;
            int lineSize = 3;
            double slope = .0;
            for (int j = 1; j < pointsCount - 2; j += 1) {
                double s0 = points[i].slopeTo(slopeOrder[j]);
                double s1 = points[i].slopeTo(slopeOrder[j + 1]);
                double s2 = points[i].slopeTo(slopeOrder[j + 2]);
                if (s0 == s1 && s1 == s2) {
                    lineSize++;
                    isCollinear[j] = true;
                    isCollinear[j + 1] = true;
                    isCollinear[j + 2] = true;
                    slope = slopeOrder[j].slopeTo(slopeOrder[j+1]);
                }
            }

            if (lineSize > 3) {
                if(!slopes.contains(slope)){
                    slopes.add(slope);
                    StdOut.print(String.format("%s", points[i]));
                    points[i].draw();
                    Point last = slopeOrder[0];
                    for (int j = 1; j < pointsCount; j++) {
                        if (isCollinear[j]) {
                            last = slopeOrder[j];
                            StdOut.print(String.format(" -> %s", last));
                            last.draw();
                        }
                    }
                    last.drawTo(points[i]);
                    StdOut.println();
                }
            }
        }
    }
}
