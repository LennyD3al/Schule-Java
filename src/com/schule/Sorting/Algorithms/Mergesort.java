package com.schule.Sorting.Algorithms;

import javax.swing.*;
import java.util.ArrayList;

public class Mergesort {

    // Main function that sorts arr[l..r] using
    // merge()
    private static int[] sort(int arr[], int l, int r, JPanel panel, long delay)
    {
        if (l < r)
        {
            // Find the middle point
            int m = (l+r)/2;

            // Sort first and second halves
            sort(arr, l, m, panel, delay);
            sort(arr , m+1, r, panel, delay);

            // Merge the sorted halves
            merge(arr, l, m, r, panel, delay);
        }

        return arr;
    }

    public static int[] sort(int[] arr, JPanel panel, long delay) {
        return sort(arr, 0, arr.length - 1, panel, delay);
    }

    public static int[] sort(int[] arr) {
        return sort(arr, null, 0);
    }

    public static void merge(int arr[], int l, int m, int r, JPanel panel, long delay)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int [n1];
        int R[] = new int [n2];

        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = arr[m + 1+ j];


        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                arr[k] = L[i];
                i++;

            }
            else
            {
                arr[k] = R[j];
                j++;
            }
            k++;
            if (panel != null) {
                panel.repaint();
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1)
        {
            arr[k] = L[i];
            i++;
            k++;
        }
        if (panel != null) {
            panel.repaint();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2)
        {
            arr[k] = R[j];
            j++;
            k++;
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
}
