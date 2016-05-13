package com.kr.algs4.part1.task4;

import seidgewick.LinkedBag;

public class Board {
    private final int size;
    private final int[][] blocks;
    private int manhattan;
    private int hamming;

    public Board(int[][] blocks) {
        this.size = blocks.length;
        this.blocks = cloneArray(blocks, size + 1, size + 1);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.blocks[i][j] = blocks[i][j];
                if (blocks[i][j] != i * size + j + 1 && blocks[i][j] != 0) {
                    this.hamming++;
                    int x = (blocks[i][j] - 1) / size;
                    int y = (blocks[i][j] - 1) % size;

                    this.manhattan += Math.abs(x - i) + Math.abs(y - j);
                }
            }
        }
    }

    private int[][] cloneArray(int[][] array, int row1, int row2) {
        int[][] result = new int[size][size];
        for (int i = 0; i < size; i++) {
            if (i == row1 || i == row2) {
                result[i] = array[i].clone();
            } else {
                result[i] = array[i];
            }
        }
        return result;
    }

    /**
     * board dimension N
     */
    public int dimension() {
        return size;
    }

    /**
     * number of blocks out of place
     */
    public int hamming() {
        return this.hamming;
    }

    /**
     * sum of Manhattan distances between blocks and goal
     */
    public int manhattan() {
        return this.manhattan;
    }

    /**
     * is this board the goal board?
     */
    public boolean isGoal() {
        return hamming == 0;
    }

    /**
     * a board obtained by exchanging two adjacent blocks in the same row
     */
    public Board twin() {
        for (int i = 0; i < size; i++) {
            if (blocks[i][0] != 0 && blocks[i][1] != 0) {
                int[][] newBlocks = cloneArray(blocks, i, i);
                int temp = blocks[i][0];
                newBlocks[i][0] = blocks[i][1];
                newBlocks[i][1] = temp;

                return new Board(newBlocks);
            }
        }

        return null;
    }

    /**
     * does this board equal y?
     */
    public boolean equals(Object y) {
        if (y == null) return false;
        if (this == y) return true;
        if (y instanceof Board && this.dimension() == ((Board) y).dimension()) {
            Board that = (Board) y;

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (blocks[i][j] != that.blocks[i][j]) return false;
                }
            }
            return true;
        }
        return false;

    }

    /**
     * all neighboring boards
     */
    public Iterable<Board> neighbors() {
        LinkedBag<Board> result = new LinkedBag<Board>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (blocks[i][j] == 0) {
                    //watch up
                    if (i > 0) {
                        int[][] newBlocks = cloneArray(blocks, i, i - 1);
                        newBlocks[i][j] = blocks[i - 1][j];
                        newBlocks[i - 1][j] = 0;
                        result.add(new Board(newBlocks));
                    }
                    //watch down
                    if (i < size - 1) {
                        int[][] newBlocks = cloneArray(blocks, i, i + 1);
                        newBlocks[i][j] = blocks[i + 1][j];
                        newBlocks[i + 1][j] = 0;
                        result.add(new Board(newBlocks));
                    }
                    //watch left
                    if (j > 0) {
                        int[][] newBlocks = cloneArray(blocks, i, i);
                        newBlocks[i][j] = blocks[i][j - 1];
                        newBlocks[i][j - 1] = 0;
                        result.add(new Board(newBlocks));
                    }
                    //watch right
                    if (j < size - 1) {
                        int[][] newBlocks = cloneArray(blocks, i, i);
                        newBlocks[i][j] = blocks[i][j + 1];
                        newBlocks[i][j + 1] = 0;
                        result.add(new Board(newBlocks));
                    }
                    break;
                }
            }
        }
        return result;
    }

    /**
     * string representation of the board (in the output format specified below)
     */
    public String toString() {
        StringBuffer result = new StringBuffer(String.valueOf(size));
        result.append("\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result.append(" ");
                result.append(blocks[i][j]);
            }
            result.append("\n");
        }
        return result.toString();
    }
}