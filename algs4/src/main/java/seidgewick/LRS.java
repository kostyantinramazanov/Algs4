package seidgewick;

/*************************************************************************
 *  Compilation:  javac seidgewick.LRS.java
 *  Execution:    java seidgewick.LRS < file.txt
 *  Dependencies: StdIn.java seidgewick.SuffixArray.java
 *  Data files:   http://algs4.cs.princeton.edu/63suffix/tinyTale.txt
 *                http://algs4.cs.princeton.edu/63suffix/mobydick.txt
 *  
 *  Reads a text string from stdin, replaces all consecutive blocks of
 *  whitespace with a single space, and then computes the longest
 *  repeated substring in that text using a suffix array.
 * 
 *  % java seidgewick.LRS < tinyTale.txt
 *  'st of times it was the '
 *
 *  % java seidgewick.LRS < mobydick.txt
 *  ',- Such a funny, sporty, gamy, jesty, joky, hoky-poky lad, is the Ocean, oh! Th'
 * 
 *  % java seidgewick.LRS
 *  aaaaaaaaa
 *  'aaaaaaaa'
 *
 *  % java seidgewick.LRS
 *  abcdefg
 *  ''
 *
 *************************************************************************/


public class LRS {

    public static void main(String[] args) {
        String text = StdIn.readAll().replaceAll("\\s+", " ");
        SuffixArray sa = new SuffixArray(text);

        int N = sa.length();

        String lrs = "";
        for (int i = 1; i < N; i++) {
            int length = sa.lcp(i);
            if (length > lrs.length()) {
                // lrs = sa.select(i).substring(0, length);
                lrs = text.substring(sa.index(i), sa.index(i) + length);
            }
        }
        
        StdOut.println("'" + lrs + "'");
    }
}
