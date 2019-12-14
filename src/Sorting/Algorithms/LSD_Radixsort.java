package Sorting.Algorithms;

import javax.swing.*;

import static Sorting.Algorithms.Helper.isSorted;

public class LSD_Radixsort {

    private static final int BITS_PER_BYTE = 8;

    /**
     * Rearranges the array of 32-bit integers in ascending order.
     * This is about 2-3x faster than Arrays.sort().
     *
     * @param a the array to be sorted
     */
    public static int[] sort(int[] a, JPanel panel, long delay) {
        final int BITS = 32;                 // each int is 32 bits
        final int R = 1 << BITS_PER_BYTE;    // each bytes is between 0 and 255
        final int MASK = R - 1;              // 0xFF
        final int w = BITS / BITS_PER_BYTE;  // each int is 4 bytes

        int n = a.length;
        int[] aux = new int[n];

        for (int d = 0; d < w; d++) {

            // compute frequency counts
            int[] count = new int[R + 1];
            for (int value : a) {
                int c = (value >> BITS_PER_BYTE * d) & MASK;
                count[c + 1]++;
            }

            // compute cumulates
            for (int r = 0; r < R; r++)
                count[r + 1] += count[r];

            // for most significant byte, 0x80-0xFF comes before 0x00-0x7F
            if (d == w - 1) {
                int shift1 = count[R] - count[R / 2];
                int shift2 = count[R / 2];
                for (int r = 0; r < R / 2; r++)
                    count[r] += shift1;
                for (int r = R / 2; r < R; r++)
                    count[r] -= shift2;
            }

            // move data
            for (int value : a) {
                int c = (value >> BITS_PER_BYTE * d) & MASK;
                aux[count[c]++] = value;
            }

            // copy back
            for (int i = 0; i < n; i++) {
                a[i] = aux[i];
                if (panel != null) {
                    panel.repaint();
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        assert isSorted(a);
        return a;
    }

    public static int[] sort(int[] a) {
        return sort(a, null, 0);
    }
}
