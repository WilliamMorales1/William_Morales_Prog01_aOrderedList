package prog01_aOrderedList;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Main class for the program which reads and writes onto files
 *
 * CSC 1351 Programming Project No 1
 * Section 2
 *
 * @author William Morales
 * @since March 17th, 2024
 */
public class Prog01_aOrderedList {
	
    /**
     * Main method for the program, reads and writes onto files.
     *
     * @param args Arguments.
     * 
     * CSC 1351 Programming Project No 1
	 * Section 2
	 * 
	 * @author William Morales
	 * @since March 17th, 2024
     */
    public static void main(String[] args) {
        boolean continuing; // Used for whether continuing or not in the do while loop
        do {
            continuing = false;
            try {
                // Get input file
                Scanner fileScanner = getInputFile("Enter the input filename: ");

                // Read the CSV file and get the list
                aOrderedList list = readCSV(fileScanner);

                // Get output file and write list to it
                // Will create a new file if it does not already exist
                PrintWriter fileWriter = getOutputFile("Enter the output filename: ");

                // Format the string before printing to the output file
                String formattedList = format(list);
                fileWriter.println(formattedList);

                // Close resources
                fileScanner.close();
                fileWriter.close();
            } catch (FileNotFoundException e) {
                System.out.println("Error with entered filename: " + e.getMessage());
                continuing = askIfContinue("Would you like to continue? (Y/N)");
            }
        // Terminate if continuing is false
        } while (continuing);
    }

    /**
     * Gets the name and returns a scanner for the input file.
     *
     * @param userPrompt The prompt to display to the user.
     * @return Scanner for the input file.
     * @throws FileNotFoundException If the file is not found.
     * 
     * CSC 1351 Programming Project No 1
	 * Section 2
	 * 
	 * @author William Morales
	 * @since March 17th, 2024
     */
    public static Scanner getInputFile(String userPrompt) throws FileNotFoundException {
        // Ask and get filename
        System.out.print(userPrompt);
        String filename = new Scanner(System.in).nextLine(); // Scanner to get the filename

        // Check if the filename contains a path
        if (filename.contains(File.separator)) {
            // File is in another folder, use the provided path
            return new Scanner(new File(filename));
        } else {
            // File is in the workspace folder, prepend the current directory
            return new Scanner(new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + filename));
        }
    }

    /**
     * Asks the user if they want to continue and returns a boolean.
     *
     * @param userPrompt The prompt to display to the user.
     * @return True if user wants to continue, false otherwise.
     * 
     * CSC 1351 Programming Project No 1
	 * Section 2
	 * 
	 * @author William Morales
	 * @since March 17th, 2024
     */
    public static boolean askIfContinue(String userPrompt) {
        boolean continuing = false; // Boolean that'll be returned in the end

        // Ask if continue
        System.out.println(userPrompt);
        Scanner s = new Scanner(System.in); // Scanner to get the Y or N
        String response = s.nextLine(); // The response to be checked if =Y or =N
        
        if (response.equals("Y")) {
            continuing = true;
        } else if (response.equals("N")) {
            continuing = false;
            s.close();
            System.exit(0);
        }
        return continuing;
    }

    /**
     * Reads and processes the contents of the CSV file into an ordered list.
     *
     * @param scanner The scanner for reading from the CSV file.
     * @return aOrderedList containing the processed data.
     * 
     * CSC 1351 Programming Project No 1
	 * Section 2
	 * 
	 * @author William Morales
	 * @since March 17th, 2024
     */
    public static aOrderedList readCSV(Scanner scanner) {
        aOrderedList list = new aOrderedList(); // Ordered list for sorting/storing

        // Go line by line
        while (scanner.hasNextLine()) {
            String l = scanner.nextLine(); 
            Scanner lscanner = new Scanner(l); // Reads the line
            lscanner.useDelimiter(",\\s*"); // This allows it to ignore any whitespaces

            // Go word by word (separated by commas)
            while (lscanner.hasNext()) {
                String value = lscanner.next(); // The A or D at the start
                
                // Initialize variables with null values
                String make = "";
                int year = 0;
                int price = 0;
                
                // Try to get the values, but if there is an exception, just continue to use the null:
                // If any of the make, year or price is not there at all, then it will use the null values above
                // If the year or price is not an int, then it will use 0 instead
                try {
            		make = lscanner.next();
                    year = Integer.valueOf(lscanner.next());
                    price = Integer.valueOf(lscanner.next());
                } catch (NoSuchElementException e) {
                } catch (NumberFormatException e) {}

                // If adding
                if (value.equals("A")) {
                    // Then add the Car to the list
                    Car car = new Car(make, year, price);
                    list.add(car);

                // Else if deleting
                } else if (value.equals("D")) {
                    list.reset();
                    // Iterate through the list
                    for (int i = 0; i < list.size(); i++) {
                        if (list.hasNext()) {
                            Car currentCar = (Car) list.next();
                            // If the make and the year are the same
                            if (currentCar.getMake().equals(make) && currentCar.getYear() == year) {
                            	// If the price is either the same, or null
                            	if (currentCar.getPrice() == price || price == 0) {
                            		list.remove();
                            	}
                            }
                        }
                    }
                }
            }
            lscanner.close();
        }
        return list;
    }

    /**
     * Gets the name and returns a PrintWriter for the output file.
     *
     * @param userPrompt The prompt to display to the user.
     * @return PrintWriter for the output file.
     * @throws FileNotFoundException If the file is not found.
     * 
     * CSC 1351 Programming Project No 1
	 * Section 2
	 * 
	 * @author William Morales
	 * @since March 17th, 2024
     */
    public static PrintWriter getOutputFile(String userPrompt) throws FileNotFoundException {
        // Ask and get filename
        System.out.print(userPrompt);
        String filename = new Scanner(System.in).nextLine(); // Scanner to get the filename

        // Check if the filename contains a path
        if (filename.contains(File.separator)) {
            // File is in another folder, use the provided path
            return new PrintWriter(new File(filename));
        } else {
            // File is in the workspace folder, prepend the current directory
            return new PrintWriter(new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + filename));
        }
    }

    /**
     * Formats the ordered list into a string for output.
     *
     * @param list The ordered list of Car objects.
     * @return Formatted string for output.
     * 
     * CSC 1351 Programming Project No 1
	 * Section 2
	 * 
	 * @author William Morales
	 * @since March 17th, 2024
     */
    public static String format(aOrderedList list) {
        String output = ""; // Initial String

        // Append the number of cars at the beginning
        output += "Number of cars\t" + list.size();
        // For each Car, add the make, year, and price
        for (int i = 0; i < list.size(); i++) {
            Car car = (Car) list.get(i); 
            
            // If any of the values are null, write "null"
            if (car.getMake().equals("")) {
            	output += "\n\nMake:\tnull";
            } else {
                output += "\n\nMake:\t" + car.getMake();
            }
            
            if (car.getYear() == 0) {
                output += "\nYear:\tnull";
            } else {
                output += "\nYear:\t" + car.getYear();
            }
            
            if (car.getPrice() == 0) {
            	output += "\nPrice:\tnull";
            } else {
            	// Format it so it is like $#,###
	            String formattedPrice = String.format("$%,d", car.getPrice());
	            output += "\nPrice:\t" + formattedPrice;
            }
        }
        return output;
    }
}
