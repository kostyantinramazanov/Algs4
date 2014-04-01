package com.kr.algs4.part1.task4;

import seidgewick.*;

import java.util.Comparator;

public class Solver {
    private Board board;
    private LinkedStack<Board> solution;

    /**
     * find a solution to the initial board (using the A* algorithm)
     */
    public Solver(Board initial) {
        this.board = initial;

        final Comparator<SearchNode> solverComparator = new Comparator<SearchNode>() {
            @Override
            public int compare(SearchNode o1, SearchNode o2) {
                return o1.weight() - o2.weight();
            }
        };
        MinPQ<SearchNode> pq = new MinPQ<SearchNode>(solverComparator);
        MinPQ<SearchNode> twinPq = new MinPQ<SearchNode>(solverComparator);

        pq.insert(new SearchNode(board, null, 0));
        twinPq.insert(new SearchNode(board.twin(), null, 0));
        do {
            SearchNode current = pq.delMin();
            SearchNode twin = twinPq.delMin();

            //solution.enqueue(current.getBoard());
            if (!current.getBoard().isGoal()) {
                for (Board neighbour : current.getBoard().neighbors()) {
                    if (current.getParent() == null || current.getParent() != null && !neighbour.equals(current.getParent().getBoard())) {
                        pq.insert(new SearchNode(neighbour, current, current.moves() + 1));
                    }
                }
            } else {
                solution = new LinkedStack<Board>();
                SearchNode node = current;
                while (node != null) {
                    solution.push(node.getBoard());
                    node = node.getParent();
                }
                break;
            }

            if (!twin.getBoard().isGoal()) {
                for (Board neighbour : twin.getBoard().neighbors()) {
                    if (twin.getParent() == null || twin.getParent() != null && !neighbour.equals(twin.getParent().getBoard())) {
                        twinPq.insert(new SearchNode(neighbour, twin, twin.moves() + 1));
                    }
                }
            } else {
                break;
            }
        } while (!pq.isEmpty() || !twinPq.isEmpty());
    }

    /**
     * is the initial board solvable?
     */
    public boolean isSolvable() {
        return solution() != null;
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

    private class SearchNode {
        private final int moves;
        private final Board board;
        private final SearchNode parent;

        private SearchNode(Board board, SearchNode parent, int moves) {
            this.board = board;
            this.parent = parent;
            this.moves = moves;
        }

        public int weight() {
            return board.manhattan() + moves;
        }

        public Board getBoard() {
            return board;
        }

        private SearchNode getParent() {
            return parent;
        }

        public int moves() {
            return moves;
        }
    }
}