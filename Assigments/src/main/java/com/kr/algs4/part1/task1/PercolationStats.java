package com.kr.algs4.part1.task1;

/**
 * Created with IntelliJ IDEA.
 * User: cONST
 * Date: 18.02.14
 * Time: 1:05
 * To change this template use File | Settings | File Templates.
 */
public class PercolationStats {

    private final int size;
    private final int count;

    /**
     * perform T independent computational experiments on an N-by-N grid
     */
    public PercolationStats(int N, int T) {
        this.size = N;
        this.count = T;

        for(int i=0; i<count; i++){
            Percolation percolation = new Percolation(size);

        }
    }

    /**
     * sample mean of percolation threshold
     */
    public double mean() {
        return 0;
    }

    /**
     * sample standard deviation of percolation threshold
     */

    public double stddev() {
        return 0;
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
    }
}
