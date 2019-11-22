package com.schule.Sorting.Algorithms;

import javax.swing.*;
import java.util.Arrays;

import static com.schule.Sorting.Algorithms.Helper.swap;

public class Heapsort {

    public static int[] sort(int[] arr, int start, int end) {
        int[] beginning = Arrays.copyOfRange(arr, 0, start);
        int[] middle = Arrays.copyOfRange(arr, start, end);
        int[] ending = Arrays.copyOfRange(arr, end, arr.length);

        sort(middle);

        arr = Helper.concatenate(Helper.concatenate(beginning, middle), ending);
        return arr;
    }

    public static int[] sort(int[] a, JPanel panel, long delay) {
        int count = a.length;
        heapify(a);

        int end = count - 1;
        while (end > 0) {
            if (panel != null) {
                panel.repaint();
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            swap(a, end, 0);
            end--;
            siftDown(a, 0, end);

        }
        return a;
    }

    public static int[] sort(int[] a) {
        return sort(a, null, 0);
    }

    private static void heapify(int[] a) {
        int count = a.length;

        int start = iParent(count - 1);

        while (start >= 0) {
            siftDown(a, start, count - 1);
            start--;
        }

    }

    private static void siftDown(int[] a, int start, int end) {
        int root = start;

        while (iLeftChild(root) <= end) {
            int child = iLeftChild(root);
            int swap = root;

            if (a[swap] < (a[child])) {
                swap = child;
            }

            if (child + 1 <= end && a[swap] < (a[child + 1])) {
                swap = child + 1;
            }

            if (swap == root) {
                return;
            } else {
                swap(a, root, swap);
                root = swap;
            }
        }
    }

    private static int iParent(int i) {
        return Math.floorDiv(i - 1, 2);
    }

    private static int iLeftChild(int i) {
        return 2 * i + 1;
    }

    private static int iRigthChild(int i) {
        return 2 * i + 2;
    }

}
