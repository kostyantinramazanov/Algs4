package seidgewick;

/***********************************************************************************
 *  Compilation: javac seidgewick.LSD.java
 *  Execution:   java seidgewick.LSD < input.txt
 *
 *  seidgewick.LSD radix sort an array of extended ASCII strings, each of length W.
 *
 *  % java seidgewick.LSD < words3.txt
 *  all
 *  bad
 *  bed
 *  bug
 *  dad
 *  ...
 *  yes
 *  yet
 *  zoo
 *
 ***********************************************************************************/

public class LSD {

    // seidgewick.LSD radix sort
    public static void sort(String[] a, int W) {
        int N = a.length;
        int R = 256;   // extend ASCII alphabet size
        String[] aux = new String[N];

        for (int d = W-1; d >= 0; d--) {
            // sort by key-indexed counting on dth character

            // compute frequency counts
            int[] count = new int[R+1];
            for (int i = 0; i < N; i++)
                count[a[i].charAt(d) + 1]++;

            // compute cumulates
            for (int r = 0; r < R; r++)
                count[r+1] += count[r];

            // move data
            for (int i = 0; i < N; i++)
                aux[count[a[i].charAt(d)]++] = a[i];

            // copy back
            for (int i = 0; i < N; i++)
                a[i] = aux[i];
        }
    }


    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        int N = a.length;

        // check that strings have fixed length
        int W = a[0].length();
        for (int i = 0; i < N; i++)
            assert a[i].length() == W : "Strings must have fixed length";

        // sort the strings
        sort(a, W);

        // print results
        for (int i = 0; i < N; i++)
            StdOut.println(a[i]);
    }
}
