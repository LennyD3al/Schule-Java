package com.schule.Sorting.Algorithms;

import javax.swing.*;

import static com.schule.Sorting.Algorithms.Helper.randomizeArray;

public class Bogosort {
    public static int[] sort(int[] arr, JPanel panel, long delay) {
        long j = 0;
        while (!Helper.isSorted(arr))
        {
            randomizeArray(arr);
            // Helper.printArr(arr);
            if (panel != null) {
                panel.repaint();
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            j++;
        }
        System.out.print("Run: " + j + "\n");
        return arr;
    }
    public static int[] sort(int[] arr) {
        return sort(arr, null, 0);
    }


}
