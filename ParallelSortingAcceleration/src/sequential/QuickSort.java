package sequential;

/**
 * Sequential QuickSort implementation
 * Average Time Complexity: O(n log n)
 * Worst Case: O(n²)
 * Space Complexity: O(log n)
 */
public class QuickSort {

    /**
     * Public method to sort array
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * Recursive quicksort implementation with tail recursion optimization
     */
    private static void quickSort(int[] arr, int low, int high) {
        while (low < high) {
            // Partition the array and get pivot index
            int pivotIndex = partition(arr, low, high);

            // Recursively sort the smaller partition first
            // This limits stack depth to O(log n)
            if (pivotIndex - low < high - pivotIndex) {
                quickSort(arr, low, pivotIndex - 1);
                low = pivotIndex + 1;
            } else {
                quickSort(arr, pivotIndex + 1, high);
                high = pivotIndex - 1;
            }
        }
    }

    /**
     * Partition method using median-of-three pivot selection
     */
    private static int partition(int[] arr, int low, int high) {
        // Use median-of-three pivot selection to avoid worst case
        int mid = low + (high - low) / 2;

        // Sort low, mid, high
        if (arr[mid] < arr[low]) swap(arr, low, mid);
        if (arr[high] < arr[low]) swap(arr, low, high);
        if (arr[mid] < arr[high]) swap(arr, mid, high);

        // Now arr[high] is the median - use it as pivot
        int pivot = arr[high];

        // Index of smaller element
        int i = low - 1;

        for (int j = low; j < high; j++) {
            // If current element is smaller than pivot
            if (arr[j] <= pivot) {
                i++;
                // Swap arr[i] and arr[j]
                swap(arr, i, j);
            }
        }

        // Swap arr[i+1] and arr[high] (pivot)
        swap(arr, i + 1, high);

        return i + 1;
    }

    /**
     * Swap two elements in array
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Get algorithm name
     */
    public static String getName() {
        return "Sequential QuickSort";
    }
}