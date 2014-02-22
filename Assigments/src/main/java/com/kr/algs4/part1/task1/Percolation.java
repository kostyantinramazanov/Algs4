package com.kr.algs4.part1.task1;

import seidgewick.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF sitesSet;
    private final boolean[][] sites;
    private final int size;

    /**
     * create N-by-N grid, with all sites blocked
     */
    public Percolation(int n) {
        this.size = n;
        sitesSet = new WeightedQuickUnionUF(size + size * size);
        for (int col = 1; col < size; col++) {
            sitesSet.union(0, col);
        }
        sites = new boolean[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                sites[row][col] = false;
            }
        }
    }

    /**
     * open site (row i, column j) if it is not already
     */
    public void open(int i, int j) {
        if (i < 1 || i > size || j < 1 || j > size)
            throw new IndexOutOfBoundsException("row or column values are out of range");

        sites[i - 1][j - 1] = true;
        if (i == 1 || i > 1 && isOpen(i - 1, j)) sitesSet.union(indexOf(i, j), indexOf(i - 1, j));
        if (j > 1 && isOpen(i, j - 1)) sitesSet.union(indexOf(i, j), indexOf(i, j - 1));
        if (j < size && isOpen(i, j + 1)) sitesSet.union(indexOf(i, j), indexOf(i, j + 1));
        if (i < size && isOpen(i + 1, j)) sitesSet.union(indexOf(i, j), indexOf(i + 1, j));
    }

    /**
     * is site (row i, column j) open?
     */
    public boolean isOpen(int i, int j) {
        if (i < 1 || i > size || j < 1 || j > size)
            throw new IndexOutOfBoundsException("row or column values are out of range");

        return sites[i - 1][j - 1];
    }

    /**
     * is site (row i, column j) full?
     */
    public boolean isFull(int i, int j) {
        if (i < 1 || i > size || j < 1 || j > size)
            throw new IndexOutOfBoundsException("row or column values are out of range");

        if (isOpen(i, j) && sitesSet.connected(indexOf(i, j), 0)) return true;

        return false;
    }

    /**
     * does the system percolate?
     */
    public boolean percolates() {
        for (int col = 1; col <= size; col++) {
            if (isFull(size, col)) return true;
        }
        return false;
    }

    /**
     * Evaluates UF index of element with row <code>i</code> and column <code>j</code>
     */
    private int indexOf(int i, int j) {
        return i * size + j - 1;
    }
}
