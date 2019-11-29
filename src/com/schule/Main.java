package com.schule;


import com.schule.Sorting.Algorithms.*;

import java.util.Arrays;

import static com.schule.Sorting.Algorithms.Helper.scale;
import static com.schule.Sorting.Algorithms.Helper.testSorting;

public class Main {

    public static void main(String[] args) {

        /*


        testSorting(Mergesort::sort, "Mergesort", 100000, 10);
        testSorting(Heapsort::sort, "Heapsort", 1000000, 10);
        testSorting(Main::sort, "Standard sort", 1000000, 10);
        testSorting(Quicksort::sort, "Quicksort", 1000000, 10);


        */
        testSorting(Bubblesort::sort, "Bubblesort", 100000, 1);
        testSorting(Insertionsort::sort, "Insertionsort", 100000, 1);
        testSorting(Selectionsort::sort, "Selection", 100000, 1);
        testSorting(ParallelMergesort::sort, "Parallel Mergesort", 1000000, 10);
        testSorting(Introsort::sort, "Introsort", 1000000, 10);
        testSorting(LSD_Radixsort::sort, "LSD", 1000000, 10);

    }

    private static int[] sort(int[] arr) {
        Arrays.sort(arr);
        return arr;
    }
}



