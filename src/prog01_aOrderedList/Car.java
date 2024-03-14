package prog01_aOrderedList;

/**
 * Car object class with information about the car
 *
 * CSC 1351 Programming Project No 1
 * Section 2
 *
 * @author William Morales
 * @since March 17th, 2024
 */
public class Car implements Comparable<Car> {
    private String make;
    private int year;
    private int price;
    
    /**
     * Constructor for Car class.
     *
     * @param make  Sets the make of the car.
     * @param year  Sets the year of the car.
     * @param price Sets the price of the car.
     * 
     * CSC 1351 Programming Project No 1
	 * Section 2
	 * 
	 * @author William Morales
	 * @since March 17th, 2024
     */
    public Car(String make, int year, int price) {
        this.make = make;
        this.year = year;
        this.price = price;
    }

    /**
     * Gets the make of the car.
     * 
     * @return The make of the car.
     * 
     * CSC 1351 Programming Project No 1
	 * Section 2
	 * 
	 * @author William Morales
	 * @since March 17th, 2024
     */
    public String getMake() {
        return make;
    }

    /**
     * Gets the year of the car.
     *
     * @return The year of the car.
     * 
     * CSC 1351 Programming Project No 1
	 * Section 2
	 * 
	 * @author William Morales
	 * @since March 17th, 2024
     */
    public int getYear() {
        return year;
    }

    /**
     * Gets the price of the car.
     *
     * @return The price of the car.
     * 
     * CSC 1351 Programming Project No 1
	 * Section 2
	 * 
	 * @author William Morales
	 * @since March 17th, 2024
     */
    public int getPrice() {
        return price;
    }

    /**
     * Compares this car to another car based on make and year.
     *
     * @param other The other car being compared.
     * @return 1 if the make is greater alphabetically, and if makes are the same, compares years.
     * 
     * CSC 1351 Programming Project No 1
	 * Section 2
	 * 
	 * @author William Morales
	 * @since March 17th, 2024
     */
    public int compareTo(Car other) {
        // Compare the makes alphabetically
        if (make.compareTo(other.make) != 0) {
            return make.compareTo(other.make);
        } else {
        	// if makes are the same, compare years
        	return Integer.compare(year, other.year);
        }
    }

    /**
     * Returns a formatted string representation of the car.
     *
     * @return The string “Make: “ + make + “, Year: “ + year + “, Price: “ + price + “;”.
     * 
     * CSC 1351 Programming Project No 1
	 * Section 2
	 * 
	 * @author William Morales
	 * @since March 17th, 2024
     */
    public String toString() {
        return "Make: " + make + ", Year: " + year + ", Price: " + price + ";";
    }
}
