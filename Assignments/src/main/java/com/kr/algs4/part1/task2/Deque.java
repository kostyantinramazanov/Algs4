package com.kr.algs4.part1.task2;

import seidgewick.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int size;

    private class Node<Item> {
        private Item value;
        private Node<Item> prev;
        private Node<Item> next;

        public Node(Item value, Node<Item> prev, Node<Item> next) {
            if (value == null) {
                throw new NullPointerException("Unable to add Node with empty value");
            }
            this.value = value;
            this.prev = prev;
            this.next = next;
            if (this.prev != null) {
                this.prev.next = this;
            }
            if (this.next != null) {
                this.next.prev = this;
            }
        }
    }

    private class DequeIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public DequeIterator(Deque deque) {
            current = deque.first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Called next() on the empty Deque.");
            }

            Node<Item> result = current;
            current = result.next;
            return result.value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Iterator doesn't support removal.");
        }
    }

    /**
     * construct an empty deque
     */
    public Deque() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    /**
     * is the deque empty?
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * return the number of items on the deque
     */
    public int size() {
        return size;
    }

    /**
     * insert the item at the front
     */
    public void addFirst(Item item) {
        Node<Item> temp = this.first;
        this.first = new Node<Item>(item, null, temp);
        this.size++;
        if (temp == null) {
            this.last = this.first;
        }
    }

    /**
     * insert the item at the end
     */
    public void addLast(Item item) {
        if (this.last == null) {
            addFirst(item);
        } else {
            Node<Item> temp = this.last;
            this.last = new Node<Item>(item, temp, null);
            this.size++;
        }
    }

    /**
     * delete and return the item at the front
     */
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        Node<Item> result = this.first;
        this.size--;

        if (this.first == this.last) {
            this.last = null;
            this.first = null;
        } else {
            this.first = result.next;
            this.first.prev = null;
        }
        return result.value;
    }

    /**
     * delete and return the item at the end
     */
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        Node<Item> result = this.last;
        this.size--;

        if (this.first == this.last) {
            this.last = null;
            this.first = null;
        } else {
            this.last = result.prev;
            this.last.next = null;
        }
        return result.value;
    }

    /**
     * return an iterator over items in order from front to end
     */
    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator<Item>(this);
    }

    /**
     * unit testing
     */
    public static void main(String[] args) {
        Deque<String> deq1 = new Deque<String>();

        deq1.addLast("so");
        deq1.addFirst("hate");
        deq1.addLast("simple");
        deq1.addFirst("I");
        deq1.addLast("tasks");

        while (!deq1.isEmpty()) {
            StdOut.println(deq1.removeFirst());
        }

        Deque<String> deq2 = new Deque<String>();
        deq2.addFirst("Yesterday");
        deq2.addFirst("all my");
        deq2.addFirst("troubles");
        deq2.addFirst("seemed so far");
        deq2.addFirst("away");

        Iterator<String> iterator = deq2.iterator();
        while (iterator.hasNext()) {
            StdOut.println(iterator.next());
        }
        ;

        while (!deq2.isEmpty()) {
            StdOut.println(deq2.removeLast());
        }
    }
}
