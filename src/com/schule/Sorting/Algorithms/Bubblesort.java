package com.schule.Sorting.Algorithms;

import javax.swing.*;

import static com.schule.Sorting.Algorithms.Helper.isSorted;
import static com.schule.Sorting.Algorithms.Helper.swap;

public class Bubblesort {
    public static int[] sort(int[] arr, JPanel panel, long delay) {
        boolean swapped;
        int n = arr.length;
        do {
            swapped = false;
            for (int i = 1; i < n; ++i) {
                if (arr[i - 1] > (arr[i])) {
                    swap(arr, i - 1, i);
                    swapped = true;
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
            --n;
        } while (swapped);
        assert isSorted(arr);
        return arr;
    }

    public static int[] sort(int[] arr) {
        return sort(arr, null, 0);
    }
}
