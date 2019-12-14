package Sorting.Algorithms;

import javax.swing.*;

import static Sorting.Algorithms.Helper.isSorted;
import static Sorting.Algorithms.Helper.swap;

public class Insertionsort {
    public static int[] sort(int[] arr, JPanel panel, long delay) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j - 1] > (arr[j]); j--) {
                swap(arr, j, j - 1);
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
        assert isSorted(arr);
        return arr;
    }

    public static int[] sort(int[] arr) {
        return sort(arr, null, 0);
    }
}
