package Sorting.Algorithms;

import javax.swing.*;
import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

import static Sorting.Algorithms.Helper.isSorted;

public class ParallelMergesort extends RecursiveAction {

    private JPanel panel;
    private long delay;

    public static int[] sort(int[] arr, JPanel panel, long delay) {

        ParallelMergesort sorter = new ParallelMergesort(arr, panel, delay);
        sorter.sort();
        assert isSorted(arr);
        return arr;
    }

    public static int[] sort(int[] arr) {
        return sort(arr, null, 0);
    }

    private static final int SORT_THRESHOLD = 128;

    private final int[] values;
    private final int from;
    private final int to;

    private ParallelMergesort(int[] values, JPanel panel, long delay) {
        this(values, 0, values.length - 1, panel, delay);
    }

    private ParallelMergesort(int[] values, int from, int to, JPanel panel, long delay) {
        this.values = values;
        this.from = from;
        this.to = to;
        this.panel = panel;
        this.delay = delay;
    }

    public void sort() {
        compute();
    }

    @Override
    protected void compute() {
        if (from < to) {
            // System.out.printf("Computing [%d,%d] in thread %s%n",´from, to, Thread.currentThread().getName());
            int size = to - from;
            if (size < SORT_THRESHOLD) {
                insertionSort();
            } else {
                int mid = from + Math.floorDiv(size, 2);
                invokeAll(
                        new ParallelMergesort(values, from, mid, panel, delay),
                        new ParallelMergesort(values, mid + 1, to, panel, delay));
                merge(mid);
            }
        }
    }

    private void insertionSort() {
        for (int i = from + 1; i <= to; ++i) {
            int current = values[i];
            int j = i - 1;
            while (from <= j && current < values[j]) {
                values[j + 1] = values[j--];
            }
            values[j + 1] = current;
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

    private void merge(int mid) {
        int[] left = Arrays.copyOfRange(values, from, mid + 1);
        int[] right = Arrays.copyOfRange(values, mid + 1, to + 1);
        int f = from;

        int li = 0, ri = 0;
        while (li < left.length && ri < right.length) {
            if (left[li] <= right[ri]) {
                values[f++] = left[li++];
            } else {
                values[f++] = right[ri++];
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

        while (li < left.length) {
            values[f++] = left[li++];
        }
        if (panel != null) {
            panel.repaint();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (ri < right.length) {
            values[f++] = right[ri++];
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
