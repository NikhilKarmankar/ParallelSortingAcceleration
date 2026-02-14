package sequential;

/**
 * Sequential MergeSort implementation
 * Time Complexity: O(n log n) - always
 * Space Complexity: O(n)
 */
public class MergeSort {

    /**
     * Public method to sort array
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int[] temp = new int[arr.length];
        mergeSort(arr, temp, 0, arr.length - 1);
    }

    /**
     * Recursive merge sort implementation
     */
    private static void mergeSort(int[] arr, int[] temp, int left, int right) {
        if (left < right) {
            // Find middle point
            int mid = left + (right - left) / 2;

            // Sort first and second halves
            mergeSort(arr, temp, left, mid);
            mergeSort(arr, temp, mid + 1, right);

            // Merge the sorted halves
            merge(arr, temp, left, mid, right);
        }
    }

    /**
     * Merge two sorted subarrays
     */
    private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        // Copy data to temp array
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }

        int i = left;      // Initial index of left subarray
        int j = mid + 1;   // Initial index of right subarray
        int k = left;      // Initial index of merged array

        // Merge temp arrays back into arr[left..right]
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of left subarray
        while (i <= mid) {
            arr[k] = temp[i];
            i++;
            k++;
        }

        // Copy remaining elements of right subarray
        while (j <= right) {
            arr[k] = temp[j];
            j++;
            k++;
        }
    }

    /**
     * Get algorithm name
     */
    public static String getName() {
        return "Sequential MergeSort";
    }
}