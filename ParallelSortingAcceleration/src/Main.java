import benchmark.*;
import sequential.*;

/**
 * Main application entry point
 * Phase 1: Sequential Algorithms Foundation
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("\n");
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║         PARALLEL ACCELERATION OF SORTING ALGORITHMS - PHASE 1               ║");
        System.out.println("║                     Sequential Baseline Implementation                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        if (args.length > 0 && args[0].equals("--quick")) {
            // Quick test mode
            PerformanceTester.quickTest();
        } else if (args.length > 0 && args[0].equals("--full")) {
            // Full benchmark mode
            PerformanceTester.runBenchmark();
        } else {
            // Interactive menu
            showMenu();
        }
    }

    /**
     * Interactive menu
     */
    private static void showMenu() {
        System.out.println("Select an option:");
        System.out.println();
        System.out.println("  1. Quick Test (Verify algorithms work correctly)");
        System.out.println("  2. Full Benchmark (Test all sizes and data types)");
        System.out.println("  3. Custom Test (Choose size and algorithm)");
        System.out.println("  4. Data Generator Demo");
        System.out.println();

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Enter choice (1-4): ");

        try {
            int choice = scanner.nextInt();
            System.out.println();

            switch (choice) {
                case 1:
                    PerformanceTester.quickTest();
                    break;
                case 2:
                    PerformanceTester.runBenchmark();
                    break;
                case 3:
                    customTest(scanner);
                    break;
                case 4:
                    dataGeneratorDemo();
                    break;
                default:
                    System.out.println("Invalid choice. Running quick test...");
                    PerformanceTester.quickTest();
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Running quick test...");
            PerformanceTester.quickTest();
        } finally {
            scanner.close();
        }
    }

    /**
     * Custom test with user input
     */
    private static void customTest(java.util.Scanner scanner) {
        System.out.println("CUSTOM TEST");
        System.out.println("─".repeat(80));

        System.out.print("Enter array size (e.g., 10000): ");
        int size = scanner.nextInt();

        System.out.println("\nSelect algorithm:");
        System.out.println("  1. QuickSort");
        System.out.println("  2. MergeSort");
        System.out.println("  3. BitonicSort");
        System.out.print("Choice: ");
        int algoChoice = scanner.nextInt();

        String algorithm = switch (algoChoice) {
            case 1 -> "QuickSort";
            case 2 -> "MergeSort";
            case 3 -> "BitonicSort";
            default -> "QuickSort";
        };

        System.out.println("\nSelect data type:");
        System.out.println("  1. Random");
        System.out.println("  2. Sorted");
        System.out.println("  3. Reverse Sorted");
        System.out.println("  4. Nearly Sorted");
        System.out.print("Choice: ");
        int dataChoice = scanner.nextInt();

        String dataType = switch (dataChoice) {
            case 1 -> "Random";
            case 2 -> "Sorted";
            case 3 -> "Reverse";
            case 4 -> "Nearly Sorted";
            default -> "Random";
        };

        System.out.println("\n" + "─".repeat(80));
        System.out.println("Running test...\n");

        int[] data = switch (dataType) {
            case "Random" -> DataGenerator.generateRandomArray(size);
            case "Sorted" -> DataGenerator.generateSortedArray(size);
            case "Reverse" -> DataGenerator.generateReverseSortedArray(size);
            case "Nearly Sorted" -> DataGenerator.generateNearlySortedArray(size);
            default -> DataGenerator.generateRandomArray(size);
        };

        PerformanceTester.TestResult result = PerformanceTester.testAlgorithm(algorithm, data, dataType);
        System.out.println(result);
        System.out.println("─".repeat(80));
    }

    /**
     * Demo of data generator
     */
    private static void dataGeneratorDemo() {
        System.out.println("DATA GENERATOR DEMO");
        System.out.println("─".repeat(80));

        int size = 20;

        System.out.println("\n1. Random Array:");
        int[] random = DataGenerator.generateRandomArray(size);
        DataGenerator.printArray(random, size);

        System.out.println("\n2. Sorted Array:");
        int[] sorted = DataGenerator.generateSortedArray(size);
        DataGenerator.printArray(sorted, size);

        System.out.println("\n3. Reverse Sorted Array:");
        int[] reverse = DataGenerator.generateReverseSortedArray(size);
        DataGenerator.printArray(reverse, size);

        System.out.println("\n4. Nearly Sorted Array:");
        int[] nearlySorted = DataGenerator.generateNearlySortedArray(size);
        DataGenerator.printArray(nearlySorted, size);

        System.out.println("\n5. Array with Duplicates:");
        int[] duplicates = DataGenerator.generateArrayWithDuplicates(size);
        DataGenerator.printArray(duplicates, size);

        System.out.println("\n" + "─".repeat(80));
        System.out.println("Data generator working correctly! ✓");
        System.out.println("─".repeat(80));
    }
}