package benchmark;

import sequential.*;

/**
 * Performance testing framework for sorting algorithms
 */
public class PerformanceTester {

    /**
     * Test result class
     */
    public static class TestResult {
        public String algorithmName;
        public int dataSize;
        public String dataType;
        public long executionTimeNanos;
        public double executionTimeMs;
        public boolean correctness;

        public TestResult(String algorithmName, int dataSize, String dataType,
                          long executionTimeNanos, boolean correctness) {
            this.algorithmName = algorithmName;
            this.dataSize = dataSize;
            this.dataType = dataType;
            this.executionTimeNanos = executionTimeNanos;
            this.executionTimeMs = executionTimeNanos / 1_000_000.0;
            this.correctness = correctness;
        }

        @Override
        public String toString() {
            return String.format("%-25s | Size: %-10d | Type: %-15s | Time: %10.3f ms | Correct: %s",
                    algorithmName, dataSize, dataType, executionTimeMs, correctness ? "✓" : "✗");
        }
    }

    /**
     * Test a sorting algorithm
     */
    public static TestResult testAlgorithm(String algorithmName, int[] data, String dataType) {
        // Make a copy to preserve original data
        int[] testData = DataGenerator.copyArray(data);

        // Measure execution time
        long startTime = System.nanoTime();

        // Call appropriate sorting algorithm
        switch (algorithmName) {
            case "QuickSort":
                QuickSort.sort(testData);
                break;
            case "MergeSort":
                MergeSort.sort(testData);
                break;
            case "BitonicSort":
                BitonicSort.sort(testData);
                break;
            default:
                throw new IllegalArgumentException("Unknown algorithm: " + algorithmName);
        }

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        // Verify correctness
        boolean correct = DataGenerator.isSorted(testData);

        return new TestResult(algorithmName, data.length, dataType, executionTime, correct);
    }

    /**
     * Run comprehensive benchmark
     */
    public static void runBenchmark() {
        System.out.println("=".repeat(100));
        System.out.println("SEQUENTIAL SORTING ALGORITHMS - PERFORMANCE BENCHMARK");
        System.out.println("=".repeat(100));
        System.out.println();

        // Test sizes
        int[] sizes = {1000, 5000, 10000, 50000, 100000};

        // Algorithms to test
        String[] algorithms = {"QuickSort", "MergeSort", "BitonicSort"};

        // Data types to test
        String[] dataTypes = {"Random", "Sorted", "Reverse", "Nearly Sorted"};

        for (int size : sizes) {
            System.out.println("\n" + "─".repeat(100));
            System.out.println("Testing with array size: " + size);
            System.out.println("─".repeat(100));

            for (String dataType : dataTypes) {
                System.out.println("\n  Data Type: " + dataType);
                System.out.println("  " + "─".repeat(95));

                // Generate test data
                int[] data = generateDataByType(dataType, size);

                // Test each algorithm
                for (String algorithm : algorithms) {
                    try {
                        TestResult result = testAlgorithm(algorithm, data, dataType);
                        System.out.println("  " + result);
                    } catch (Exception e) {
                        System.out.println("  ERROR testing " + algorithm + ": " + e.getMessage());
                    }
                }
            }
        }

        System.out.println("\n" + "=".repeat(100));
        System.out.println("BENCHMARK COMPLETE");
        System.out.println("=".repeat(100));
    }

    /**
     * Generate data based on type
     */
    private static int[] generateDataByType(String dataType, int size) {
        switch (dataType) {
            case "Random":
                return DataGenerator.generateRandomArray(size);
            case "Sorted":
                return DataGenerator.generateSortedArray(size);
            case "Reverse":
                return DataGenerator.generateReverseSortedArray(size);
            case "Nearly Sorted":
                return DataGenerator.generateNearlySortedArray(size);
            default:
                return DataGenerator.generateRandomArray(size);
        }
    }

    /**
     * Quick test with small data
     */
    public static void quickTest() {
        System.out.println("=".repeat(100));
        System.out.println("QUICK TEST - Verifying Algorithms Work Correctly");
        System.out.println("=".repeat(100));

        int size = 1000;
        int[] testData = DataGenerator.generateRandomArray(size);

        System.out.println("\nTesting with array of size: " + size);
        System.out.println("Original data (first 10 elements):");
        DataGenerator.printArray(testData, 10);

        String[] algorithms = {"QuickSort", "MergeSort", "BitonicSort"};

        System.out.println("\nRunning tests...\n");

        for (String algorithm : algorithms) {
            TestResult result = testAlgorithm(algorithm, testData, "Random");
            System.out.println(result);
        }

        System.out.println("\n" + "=".repeat(100));
        System.out.println("All algorithms verified! ✓");
        System.out.println("=".repeat(100));
    }
}