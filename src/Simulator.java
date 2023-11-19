import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Simulator {

    /**
     * Simulates the operation of a cache using data from a file.
     * 
     * @param ac The ArrayCache instance to use for the simulation.
     * @param fileName The name of the file containing the cache data.
     * @return The updated ArrayCache instance after processing the file.
     */
    public static ArrayCache simulate(ArrayCache ac, String fileName) {
        try (Scanner in = new Scanner(new File(fileName))) {
            while (in.hasNextLine()) {
                String query = in.nextLine();
                String[] rec = query.split(":");
                
                // Retrieve the cache entry, if it exists
                CacheEntry entry = ac.get(rec[0]);

                // If the entry is not in the cache, add it
                if (entry == null) {
                    ac.put(rec[0], rec[1]);
                }
            }
            return ac;
        } catch (FileNotFoundException ex) {
            // Print an error message if the file is not found
            System.out.println("File not found: " + ex.getMessage());
            return null;
        }
    }

    /**
     * Prints the hit and miss statistics of the cache.
     * 
     * @param ac The ArrayCache instance from which to gather statistics.
     */
    public static void printStats(ArrayCache ac) {
        int totalAccesses = ac.getHits() + ac.getMisses();
        double hitRate = totalAccesses > 0 ? ((double) ac.getHits() / totalAccesses) * 100.0 : 0.0;
        double missRate = totalAccesses > 0 ? ((double) ac.getMisses() / totalAccesses) * 100.0 : 0.0;

        // Print the calculated hit and miss rates
        System.out.printf("Hit Rate: %.2f%%\n", hitRate);
        System.out.printf("Miss Rate: %.2f%%\n", missRate);
    }

    /**
     * Main method to run the simulation.
     * 
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Prompt the user to enter the file name
        System.out.print("Enter File Name: ");
        String fileName = in.next();

        int size;

        // Continuously ask for a valid cache size until provided
        do {
            System.out.print("Enter Size: ");
            size = in.nextInt();
            if (size <= 0) {
                System.out.println("Error: Size must be a positive integer!");
            }
        } while (size <= 0);

        // Create a new ArrayCache instance and run the simulation
        ArrayCache ac = new ArrayCache(size);
        ac = simulate(ac, fileName);

        // If the simulation is successful, print the stats and cache contents
        if (ac != null) {
            printStats(ac);
            System.out.println("\n" + ac.toString());
        } else {
            System.out.println("Simulation was not completed due to errors.");
        }
    }
}
