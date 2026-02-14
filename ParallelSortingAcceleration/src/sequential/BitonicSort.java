package sequential;

/**
 * Sequential Bitonic Sort implementation
 * Time Complexity: O(n log² n)
 * Space Complexity: O(log n)
 * Note: Works best when array size is a power of 2
 * This algorithm is naturally parallel and will show great speedup with GPU
 */
public class BitonicSort {

    /**
     * Public method to sort array
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        // Pad array to nearest power of 2 if necessary
        int[] paddedArr = padToPowerOfTwo(arr);

        // Perform bitonic sort
        bitonicSort(paddedArr, 0, paddedArr.length, true);

        // Copy back to original array
        System.arraycopy(paddedArr, 0, arr, 0, arr.length);
    }

    /**
     * Pad array to nearest power of 2
     */
    private static int[] padToPowerOfTwo(int[] arr) {
        int n = arr.length;
        int powerOfTwo = 1;

        // Find next power of 2
        while (powerOfTwo < n) {
            powerOfTwo *= 2;
        }

        if (powerOfTwo == n) {
            return arr; // Already power of 2
        }

        // Create padded array filled with max value
        int[] padded = new int[powerOfTwo];
        System.arraycopy(arr, 0, padded, 0, n);

        // Fill remaining with Integer.MAX_VALUE (will be sorted to end)
        for (int i = n; i < powerOfTwo; i++) {
            padded[i] = Integer.MAX_VALUE;
        }

        return padded;
    }

    /**
     * Recursive bitonic sort
     * @param arr Array to sort
     * @param low Starting index
     * @param cnt Number of elements
     * @param dir Direction (true = ascending, false = descending)
     */
    private static void bitonicSort(int[] arr, int low, int cnt, boolean dir) {
        if (cnt > 1) {
            int k = cnt / 2;

            // Sort in ascending order
            bitonicSort(arr, low, k, true);

            // Sort in descending order
            bitonicSort(arr, low + k, k, false);

            // Merge whole sequence in ascending order
            bitonicMerge(arr, low, cnt, dir);
        }
    }

    /**
     * Bitonic merge
     */
    private static void bitonicMerge(int[] arr, int low, int cnt, boolean dir) {
        if (cnt > 1) {
            int k = cnt / 2;

            for (int i = low; i < low + k; i++) {
                compareAndSwap(arr, i, i + k, dir);
            }

            bitonicMerge(arr, low, k, dir);
            bitonicMerge(arr, low + k, k, dir);
        }
    }

    /**
     * Compare and swap elements based on direction
     */
    private static void compareAndSwap(int[] arr, int i, int j, boolean dir) {
        if (dir == (arr[i] > arr[j])) {
            // Swap
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    /**
     * Get algorithm name
     */
    public static String getName() {
        return "Sequential BitonicSort";
    }
}