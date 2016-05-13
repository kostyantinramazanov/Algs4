package com.kr.algs4.part1.task1;

import seidgewick.StdOut;
import seidgewick.StdRandom;
import seidgewick.Stopwatch;

public class PercolationStats {
    private double mean = 0.0;
    private double stddev = 0.0;
    private final double T;

    /**
     * perform T independent computational experiments on an N-by-N grid
     */
    public PercolationStats(int N, int T) {
        if (N < 1 || T < 1) {
            throw new IllegalArgumentException("N and T values should be more than 0");
        }

        this.T = T;
        long[] x = new long[T];
        long totalOpened = 0;

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
            x[i] = openedCells;
            totalOpened += x[i];
        }

        mean = (double) totalOpened / (N * N * T);

        for (int i = 0; i < T; i++) {
            stddev += (((double) x[i] / (N * N) - mean) * ((double) x[i] / (N * N) - mean));
        }
        stddev = Math.sqrt(stddev / (T - 1));
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
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    /**
     * returns upper bound of the 95% confidence interval
     */
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
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
            StdOut.printf("95%% confidence interval = %f, %f\n", percolationStats.confidenceLo(), percolationStats.confidenceHi());
        } else StdOut.println("Missing required parameters N and T");
    }
}
