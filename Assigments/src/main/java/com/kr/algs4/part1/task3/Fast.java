package com.kr.algs4.part1.task3;

import seidgewick.In;
import seidgewick.Quick;
import seidgewick.StdDraw;
import seidgewick.StdOut;

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

        for (int i = 0; i < pointsCount; i++) {
            Point[] slopeOrder = points.clone();
            Arrays.sort(slopeOrder, points[i].SLOPE_ORDER);
            boolean[] isCollinear = new boolean[pointsCount];
            for (int j = 0; j < pointsCount - 2; j++) {
                double s0 = points[i].slopeTo(slopeOrder[j]);
                double s1 = points[i].slopeTo(slopeOrder[j + 1]);
                double s2 = points[i].slopeTo(slopeOrder[j + 2]);
                if (s0 == s1 && s1 == s2) {
                    isCollinear[j] = true;
                    isCollinear[j + 1] = true;
                    isCollinear[j + 2] = true;
                }
            }
            for (int j = 0; j < pointsCount; j++) {
//                if (isCollinear[j] && isCollinear[j + 1] && isCollinear[j + 2]) {
//
//                }
                if(isCollinear[j]){
                    StdOut.print(slopeOrder[j]);
                }
            }
            StdOut.println();
        }
    }
}
