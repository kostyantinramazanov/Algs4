package com.kr.algs4.part1.task1;

import seidgewick.StdOut;
import seidgewick.StdRandom;
import seidgewick.Stopwatch;

/**
 * Created with IntelliJ IDEA.
 * User: cONST
 * Date: 18.02.14
 * Time: 1:05
 * To change this template use File | Settings | File Templates.
 */
public class PercolationStats {
    private double mean = 0.0;
    private double stddev = 0.0;

    /**
     * perform T independent computational experiments on an N-by-N grid
     */
    public PercolationStats(int N, int T) {
        double x[] = new double[T];

        for (int i = 0; i < T; i++) {
            Percolation percolation = new Percolation(N);
            long openedCells = 0;
            while (!percolation.percolates()) {
                boolean opened;
                do {
                    opened = false;
                    int cellIndex = StdRandom.uniform(N * N);
                    int row = cellIndex / N + 1;
                    int col = cellIndex % N + 1;
                    if (!percolation.isOpen(row, col)) {
                        percolation.open(row, col);
                        opened = true;
                        openedCells++;
                    }
                } while (!opened);
            }
            x[i] = (double) openedCells / (N * N);
            mean += x[i];
        }

        mean = mean / T;

        for (int i = 0; i < T; i++) {
            stddev += Math.pow(x[i] - mean, 2);
        }
        stddev = Math.sqrt(stddev / (double) (T - 1));
    }

    /**
     * sample mean of percolation threshold
     */
    public double mean() {
        return mean;
    }

    /**
     * sample standard deviation of percolation threshold
     */

    public double stddev() {
        return stddev;
    }

    /**
     * returns lower bound of the 95% confidence interval
     */
    public double confidenceLo() {
        return 0;
    }

    /**
     * returns upper bound of the 95% confidence interval
     */
    public double confidenceHi() {
        return 0;
    }

    public static void main(String[] args) {
        if (args.length > 1) {
            final int N = Integer.parseInt(args[0]);
            final int T = Integer.parseInt(args[1]);

            Stopwatch timer = new Stopwatch();
            PercolationStats percolationStats = new PercolationStats(N, T);
            StdOut.printf("Seconds elapsed = %f\n", timer.elapsedTime());
            StdOut.printf("mean = %f\n", percolationStats.mean());
            StdOut.printf("stddev = %f\n", percolationStats.stddev());

            double v = 1.96 * percolationStats.stddev() / Math.sqrt(T);
            StdOut.printf("95%% confidence interval = %f, %f\n", percolationStats.mean() - v, percolationStats.mean() + v);
        } else StdOut.println("Missing required parameters N and T");
    }
}
