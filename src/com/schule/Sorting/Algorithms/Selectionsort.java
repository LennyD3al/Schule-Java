package com.schule.Sorting.Algorithms;

import javax.swing.*;

import static com.schule.Sorting.Algorithms.Helper.swap;

public class Selectionsort {
    public static int[] sort(int[] arr, JPanel panel, long delay) {
        int n = arr.length;

        for (int i = 0; i < n -1; ++i) {
            int min_idx = i;
            for (int j = i + 1; j < n; ++j) {
                if (arr[j] < arr[min_idx])
                    min_idx = j;
            }
            swap(arr, i, min_idx);
            if (panel != null) {
                panel.repaint();
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        return arr;
    }

    public static int[] sort(int[] arr) {
        return sort(arr, null, 0);
    }
}
