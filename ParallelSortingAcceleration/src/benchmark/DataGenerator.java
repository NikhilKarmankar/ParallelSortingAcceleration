package benchmark;

import java.util.Random;
import java.util.Arrays;

/**
 * Generates various types of test data for sorting algorithms
 */
public class DataGenerator {
    private static final Random random = new Random();

    /**
     * Generate random array
     */
    public static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(size * 10);
        }
        return arr;
    }

    /**
     * Generate sorted array (best case for some algorithms)
     */
    public static int[] generateSortedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
        return arr;
    }

    /**
     * Generate reverse sorted array (worst case for some algorithms)
     */
    public static int[] generateReverseSortedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = size - i;
        }
        return arr;
    }

    /**
     * Generate nearly sorted array (90% sorted)
     */
    public static int[] generateNearlySortedArray(int size) {
        int[] arr = generateSortedArray(size);
        int swaps = size / 10; // 10% unsorted

        for (int i = 0; i < swaps; i++) {
            int idx1 = random.nextInt(size);
            int idx2 = random.nextInt(size);
            // Swap
            int temp = arr[idx1];
            arr[idx1] = arr[idx2];
            arr[idx2] = temp;
        }
        return arr;
    }

    /**
     * Generate array with duplicates
     */
    public static int[] generateArrayWithDuplicates(int size) {
        int[] arr = new int[size];
        int uniqueValues = size / 10; // Only 10% unique values

        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(uniqueValues);
        }
        return arr;
    }

    /**
     * Create a copy of array
     */
    public static int[] copyArray(int[] original) {
        return Arrays.copyOf(original, original.length);
    }

    /**
     * Verify if array is sorted
     */
    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Print array (for small arrays only)
     */
    public static void printArray(int[] arr, int maxElements) {
        int limit = Math.min(arr.length, maxElements);
        System.out.print("[");
        for (int i = 0; i < limit; i++) {
            System.out.print(arr[i]);
            if (i < limit - 1) System.out.print(", ");
        }
        if (arr.length > maxElements) {
            System.out.print("...");
        }
        System.out.println("]");
    }
}