package com.kr.algs4.part1.task3;

import seidgewick.In;
import seidgewick.Quick;
import seidgewick.StdDraw;
import seidgewick.StdOut;

import java.io.File;

public class Brute {
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

        for (int i = 0; i < pointsCount - 3; i++) {
            for (int j = i+1; j < pointsCount - 2; j++) {
                for (int k = j+1; k < pointsCount - 1; k++) {
                    for (int l = k+1; l < pointsCount; l++) {
                        Point p = points[i];
                        Point q = points[j];
                        Point r = points[k];
                        Point s = points[l];
                        double pq = p.slopeTo(q);
                        double rs = r.slopeTo(s);
                        double ps = p.slopeTo(s);
                        if (pq == rs && rs == ps){
                            StdOut.println(String.format("%s -> %s -> %s -> %s", p, q, r, s));
                            p.draw();
                            q.draw();
                            r.draw();
                            s.draw();
                            p.drawTo(q);
                            q.drawTo(r);
                            r.drawTo(s);
                        }
                    }
                }
            }
        }
    }
}
