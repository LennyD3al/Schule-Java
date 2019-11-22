package com.schule.Sorting.Algorithms;

import java.lang.reflect.Array;
import java.util.Random;
import java.util.function.Function;

public class Helper {
     static <T> void swap(T[] arr, int i, int j) {
        T t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void printArr(int[] arr) {
        for (int value : arr) {
            System.out.printf("%s, ", value);
        }
        System.out.print("\n");
    }

    public static long testSorting(Function<int[], int[]> func, String name, int testSize, boolean out) {
        int[] arr = new int[testSize];
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = Math.toIntExact(Math.round(Math.random() * 1000000));
        }

        long startTime = System.nanoTime();
        func.apply(arr);
        // System.out.println(isSorted(arr));
        long endTime = System.nanoTime();

        long time = endTime - startTime;
        if (out) System.out.printf("%s: %dms\n", name, time / 1000000);

        return time;
    }

    public static long testSorting(Function<int[], int[]> func, String name, int testSize, int repetitions) {
        long time = 0;
        int i;
        for (i = 0; i < repetitions; i++) {
            time += testSorting(func, name, testSize);
        }
        time = time / i;
        System.out.printf("Average Time (%s): %dms\n", name, time / 1000000);

        return time;
    }

    public static long testSorting(Function<int[], int[]> func, String name, int testSize) {
        return testSorting(func, name, testSize, false);
    }

    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false; // It is proven that the array is not sorted.
            }
        }

        return true;
    }

    static int[] concatenate(int[] a, int[] b) {
        int aLen = a.length;
        int bLen = b.length;

        int[] c = (int[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);

        return c;
    }

    public static void randomizeArray(int[] arr) {
        Random rand = new Random();

        for (int i = 0; i < arr.length; i++) {
            int randomIndexToSwap = rand.nextInt(arr.length);
            int temp = arr[randomIndexToSwap];
            arr[randomIndexToSwap] = arr[i];
            arr[i] = temp;
        }
    }

    public static double scale(double num, double in_min, double in_max, double out_min, double out_max) {
         return (num - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }

    public static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static int getMin(int[] arr) {
        int min = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    public static void scaleArr(int[] arr, int in_min, int in_max, int out_min, int out_max) {
         for (int i = 0; i < arr.length; i++) {
             arr[i] = (int) scale(arr[i], in_min, in_max, out_min, out_max);
         }
    }
}
