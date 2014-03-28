package com.kr.algs4.part1.task4;

import seidgewick.In;
import seidgewick.LinkedQueue;
import seidgewick.MinPQ;
import seidgewick.StdOut;

import java.util.Comparator;

public class Solver {
    private final Board board;
    private final LinkedQueue<Board> solution = new LinkedQueue<Board>();
    private boolean isSolvable = false;

    /**
     * find a solution to the initial board (using the A* algorithm)
     */
    public Solver(Board initial) {
        this.board = initial;

        final Comparator<Board> solverComparator = new Comparator<Board>() {
            @Override
            public int compare(Board o1, Board o2) {
                return o1.manhattan() - o2.manhattan();
            }
        };

        MinPQ<Board> pq = new MinPQ<Board>(solverComparator);
        MinPQ<Board> twinPq = new MinPQ<Board>(solverComparator);
        Board previous = board;
        Board twin = board.twin();
        Board previousTwin = twin;
        pq.insert(previous);
        twinPq.insert(twin);
        do {
            Board current = pq.delMin();
            twin = twinPq.delMin();

            int cValue = current.hamming();
            int twinCValue = twin.hamming();

            solution.enqueue(current);
            if (!current.isGoal()) {
                for (Board neighbour : current.neighbors()) {
                    if (!neighbour.equals(previous) /*&& !(neighbour.hamming() > cValue)*/) {
                        pq.insert(neighbour);
                    }
                }
            } else {
                isSolvable = true;
                break;
            }

            if (!twin.isGoal()) {
                for (Board neighbour : twin.neighbors()) {
                    if (!neighbour.equals(previousTwin) /*&& !(neighbour.hamming() > twinCValue)*/) {
                        twinPq.insert(neighbour);
                    }
                }
            } else {
                isSolvable = false;
                break;
            }

            previous = current;
            previousTwin = twin;
        } while (!pq.isEmpty() || !twinPq.isEmpty());
    }

    /**
     * is the initial board solvable?
     */
    public boolean isSolvable() {
        return isSolvable;
    }

    /**
     * min number of moves to solve initial board; -1 if no solution
     */
    public int moves() {
        return solution.size() - 1;
    }

    /**
     * sequence of boards in a shortest solution; null if no solution
     */
    public Iterable<Board> solution() {
        return this.solution;
    }

    /**
     * solve a slider puzzle
     */
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}