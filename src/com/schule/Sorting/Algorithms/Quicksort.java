package com.schule.Sorting.Algorithms;

import javax.swing.*;

import static com.schule.Sorting.Algorithms.Helper.swap;

public class Quicksort {
    public static int[] sort(int[] a) {
        return sort(a, 0, a.length - 1);
    }

    private static int[] sort(int[] a, int lo, int hi) {
        return sort(a, lo, hi, null, 0);
    }

    public static int[] sort(int[] a, JPanel panel, long delay) {
        return sort(a, 0, a.length - 1, panel, delay);
    }

    private static int[] sort(int[] a, int lo, int hi, JPanel panel, long delay) {
        if (lo < hi) {

            int p = lomuto_partition(a, lo, hi, panel, delay);
            sort(a, lo, p - 1, panel, delay);
            sort(a, p + 1, hi, panel, delay);
        }
        return a;
    }

    private static int lomuto_partition(int[] a, int lo, int hi, JPanel panel, long delay) {
        int pivot = a[hi];
        int i = lo;
        for (int j = lo; j < hi; ++j) {
            if (a[j]  < pivot) {
                swap(a, i, j);
                ++i;
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
        swap(a, i, hi);
        if (panel != null) {
            panel.repaint();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return i;
    }
}
