package com.kr.algs4.part1.task2;

import seidgewick.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Deque<Item> deque;

    /**
     * construct an empty randomized queue
     */
    public RandomizedQueue() {
        deque = new Deque<Item>();
    }

    /**
     * is the queue empty?
     */
    public boolean isEmpty() {
        return deque.isEmpty();
    }

    /**
     * return the number of items on the queue
     */
    public int size() {
        return deque.size();
    }

    /**
     * add the item
     */
    public void enqueue(Item item) {
        int factor = StdRandom.uniform(deque.size() + 1);
        if (factor % 2 == 0) {
            deque.addLast(item);
        } else {
            deque.addFirst(item);
        }
    }

    /**
     * delete and return a random item
     */
    public Item dequeue() {
        int factor = StdRandom.uniform(deque.size() + 1);
        if (factor % 2 == 0) {
            return deque.removeLast();
        } else {
            return deque.removeLast();
        }
    }

    /**
     * return (but do not delete) a random item
     */
    public Item sample() {
        Item result = dequeue();
        enqueue(result);
        return result;
    }

    @Override
    /**return an independent iterator over items in random order*/
    public Iterator<Item> iterator() {
        return deque.iterator();
    }

    /**
     * unit testing
     */
    public static void main(String[] args) {
    }
}
