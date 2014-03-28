package com.kr.algs4.part1.task2;

import seidgewick.StdIn;
import seidgewick.StdOut;

import java.util.NoSuchElementException;

public class Subset {
    public static void main(String[] args) {
        if (args.length != 1) {
            StdOut.println("Not enough arguments specified. Please set k");
            return;
        }
        int k = Integer.parseInt(args[0]);
        if (k < 0) {
            StdOut.println("k can not be negative");
            return;
        }
        RandomizedQueue<String> queue = new RandomizedQueue<String>();

        while (true) {
            try {
                String item = StdIn.readString();
                queue.enqueue(item);
            } catch (NoSuchElementException e) {
                break;
            }
        }
        for (int i = 0; i < k; i++) {
            StdOut.println(queue.dequeue());
        }
    }
}
