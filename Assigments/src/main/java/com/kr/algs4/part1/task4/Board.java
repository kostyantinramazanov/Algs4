package com.kr.algs4.part1.task4;

import seidgewick.LinkedBag;

public class Board {
    private final int size;
    private final int[][] blocks;

    public Board(int[][] blocks) {
        this.size = blocks.length;
        this.blocks = blocks;
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
        int result = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (blocks[i][j] != i * size + j + 1 && blocks[i][j] != 0) {
                    result++;
                }
            }
        }
        return result;
    }

    /**
     * sum of Manhattan distances between blocks and goal
     */
    public int manhattan() {
        int result = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (blocks[i][j] != i * size + j + 1 && blocks[i][j] != 0) {
                    int x = (blocks[i][j] - 1) / size;
                    int y = (blocks[i][j] - 1) % size;

                    result += Math.abs(x - i) + Math.abs(y - j);
                }
            }
        }
        return result;
    }

    /**
     * is this board the goal board?
     */
    public boolean isGoal() {
        return hamming() == 0;
    }

    /**
     * a board obtained by exchanging two adjacent blocks in the same row
     */
    public Board twin() {
        Board result;
        boolean swapNextRow = false;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (swapNextRow || blocks[i][j] != i * size + j + 1 && blocks[i][j] != 0) {
                    int other = j == 0 ? j + 1 : j - 1;
                    if(blocks[i][other] == 0){
                        swapNextRow = true;
                        break;
                    }
                    int[][] newBlocks = cloneArray(blocks);
                    int temp = newBlocks[i][j];
                    newBlocks[i][j] = newBlocks[i][other];
                    newBlocks[i][other] = temp;
                    return new Board(newBlocks);
                }
            }
        }
        return this;
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
                        int[][] newBlocks = cloneArray(blocks);
                        newBlocks[i][j] = newBlocks[i - 1][j];
                        newBlocks[i - 1][j] = 0;
                        result.add(new Board(newBlocks));
                    }
                    //watch down
                    if (i < size - 1) {
                        int[][] newBlocks = cloneArray(blocks);
                        newBlocks[i][j] = newBlocks[i + 1][j];
                        newBlocks[i + 1][j] = 0;
                        result.add(new Board(newBlocks));
                    }
                    //watch left
                    if (j > 0) {
                        int[][] newBlocks = cloneArray(blocks);
                        newBlocks[i][j] = newBlocks[i][j - 1];
                        newBlocks[i][j - 1] = 0;
                        result.add(new Board(newBlocks));
                    }
                    //watch right
                    if (j < size - 1) {
                        int[][] newBlocks = cloneArray(blocks);
                        newBlocks[i][j] = newBlocks[i][j + 1];
                        newBlocks[i][j + 1] = 0;
                        result.add(new Board(newBlocks));
                    }
                    break;
                }
            }
        }
        return result;
    }

    private int[][] cloneArray(int[][] blocks) {
        int[][] result = new int[size][size];
        for (int i = 0; i < size; i++) {
            result[i] = blocks[i].clone();
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