package com.schule;


import com.schule.Sorting.Algorithms.*;

import java.util.Arrays;

import static com.schule.Sorting.Algorithms.Helper.scale;
import static com.schule.Sorting.Algorithms.Helper.testSorting;

public class Main {

    public static void main(String[] args) {

        /*
        testSorting(Bubblesort::sort, "Bubblesort", 10000, 10);
        testSorting(Insertionsort::sort, "Insertionsort", 10000, 10);
        testSorting(Mergesort::sort, "Mergesort", 100000, 10);
        testSorting(Heapsort::sort, "Heapsort", 1000000, 10);
        testSorting(Main::sort, "Standard sort", 1000000, 10);
        testSorting(Quicksort::sort, "Quicksort", 1000000, 10);
        testSorting(ParallelMergesort::sort, "Parallel Mergesort", 1000000, 10);
        testSorting(Introsort::sort, "Introsort", 1000000, 10);
        */
        testSorting(LSD_Radixsort::sort, "LSD", 1000000, 10);

        System.out.println(scale(5, 0, 10, -50, 50));


    }

    private static int[] sort(int[] arr) {
        Arrays.sort(arr);
        return arr;
    }
}



