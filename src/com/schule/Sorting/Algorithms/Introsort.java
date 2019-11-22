package com.schule.Sorting.Algorithms;

import javax.swing.*;

public class Introsort {

    /*
     * Class Variables
     */
    private static int size_threshold = 16;

    /*
     * Public interface
     */
    public static int[] sort(int[] a, JPanel panel, long delay) {
        introsort_loop(a, 0, a.length, 2 * floor_lg(a.length), panel, delay);
        return a;
    }

    public static int[] sort(int[] a) {
        introsort_loop(a, 0, a.length, 2 * floor_lg(a.length), null, 0);
        return a;
    }

    public static void sort(int[] a, int begin, int end, JPanel panel, long delay) {
        if (begin < end) {
            introsort_loop(a, begin, end, 2 * floor_lg(end - begin), panel, delay);
        }
    }
    public static void sort(int[] a, int begin, int end) {
        if (begin < end) {
            introsort_loop(a, begin, end, 2 * floor_lg(end - begin), null, 0);
        }
    }

    /*
     * Quicksort algorithm modified for Introsort
     */
    private static void introsort_loop(int[] a, int lo, int hi, int depth_limit, JPanel panel, long delay) {
        while (hi - lo > size_threshold) {
            if (depth_limit == 0) {
                heapsort(a, lo, hi, panel, delay);
                return;
            }
            depth_limit = depth_limit - 1;
            int p = partition(a, lo, hi, medianof3(a, lo, lo + ((hi - lo) / 2) + 1, hi - 1), panel, delay);
            introsort_loop(a, p, hi, depth_limit, panel, delay);
            hi = p;
        }
        insertionsort(a, lo, hi, panel, delay);
    }

    private static int partition(int[] a, int lo, int hi, int x, JPanel panel, long delay) {
        int i = lo, j = hi;
        while (true) {
            while (a[i] < x) i++;
            j = j - 1;
            while (x < a[j]) j = j - 1;
            if (!(i < j))
                return i;
            exchange(a, i, j);
            if (panel != null) {
                panel.repaint();
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i++;
        }
    }

    private static int medianof3(int[] a, int lo, int mid, int hi) {
        if (a[mid] < a[lo]) {
            if (a[hi] < a[mid])
                return a[mid];
            else {
                if (a[hi] < a[lo])
                    return a[hi];
                else
                    return a[lo];
            }
        } else {
            if (a[hi] < a[mid]) {
                if (a[hi] < a[lo])
                    return a[lo];
                else
                    return a[hi];
            } else
                return a[mid];
        }
    }

    /*
     * Heapsort algorithm
     */
    private static void heapsort(int[] a, int lo, int hi, JPanel panel, long delay) {
        int n = hi - lo;
        for (int i = n / 2; i >= 1; i = i - 1) {
            downheap(a, i, n, lo);
        }
        for (int i = n; i > 1; i = i - 1) {
            if (panel != null) {
                panel.repaint();
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            exchange(a, lo, lo + i - 1);
            downheap(a, 1, i - 1, lo);
        }
    }

    private static void downheap(int[] a, int i, int n, int lo) {
        int d = a[lo + i - 1];
        int child;
        while (i <= n / 2) {
            child = 2 * i;
            if (child < n && a[lo + child - 1] < a[lo + child]) {
                child++;
            }
            if (d >= a[lo + child - 1]) break;
            a[lo + i - 1] = a[lo + child - 1];
            i = child;
        }
        a[lo + i - 1] = d;
    }

    /*
     * Insertion sort algorithm
     */
    private static void insertionsort(int[] a, int lo, int hi, JPanel panel, long delay) {
        int i, j;
        int t;
        for (i = lo; i < hi; i++) {
            j = i;
            t = a[i];
            while (j != lo && t < a[j - 1]) {
                a[j] = a[j - 1];
                j--;
                if (panel != null) {
                    panel.repaint();
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            a[j] = t;
        }
        if (panel != null) {
            panel.repaint();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * Common methods for all algorithms
     */
    private static void exchange(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static int floor_lg(int a) {
        return (int) (Math.floor(Math.log(a) / Math.log(2)));
    }

}
