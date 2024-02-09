package prog01_aOrderedList;
import java.util.Arrays;

/**
 * An ordered list object class for storing objects into a sorted array
 *
 * CSC 1351 Programming Project No 1
 * Section 2
 * 
 * @author William Morales
 * @since <insert due date here>
 */
public class aOrderedList {
    final int SIZEINCREMENTS = 20; // Size of increments for increasing the ordered list
    private Comparable<Car>[] oList; // The ordered list
    private int listSize; // The size of the ordered list
    private int numObjects; // The number of objects in the ordered list
    private int curr; // Index of the current element accessed via iterator methods

    /**
     * Constructor to initialize the ordered list.
     * 
     * CSC 1351 Programming Project No 1
	 * Section 2
	 * 
	 * @author William Morales
	 * @since <insert due date here>
     */
    public aOrderedList() {
        numObjects = 0;
        listSize = SIZEINCREMENTS;
        oList = new Car[SIZEINCREMENTS]; // Use Comparable as the type of the array
    }

    /**
     * Adds a new object to the ordered list.
     * 
     * @param newObject The object to be added to the ordered list.
     * 
     * CSC 1351 Programming Project No 1
	 * Section 2
	 * 
	 * @author William Morales
	 * @since <insert due date here>
     */
    public void add(Comparable<Car> newObject) {
        // Ensure capacity
        if (numObjects == listSize) {
            listSize += SIZEINCREMENTS;
            oList = Arrays.copyOf(oList, listSize);
        }

        // Find the correct position to insert
        int index = 0;
        while (index < numObjects && newObject.compareTo((Car) oList[index]) > 0) {
            index++;
        }
        
        // Shift elements to make space
        for (int i = numObjects - 1; i >= index; i--) {
            oList[i + 1] = oList[i];
        }

        // Insert the newObject
        oList[index] = newObject;

        // Update the counter
        numObjects++;
    }
    
    /**
     * Returns a string representation of the ordered list.
     *
     * @return The string representation of the ordered list.
     * 
     * CSC 1351 Programming Project No 1
	 * Section 2
	 * 
	 * @author William Morales
	 * @since <insert due date here>
     */
    public String toString() {
        String output = ""; // Initial String
        
        // loop for numObjects
        for (int i = 0; i < numObjects; i++) {
            if (oList[i] != null) {
            	// add [Car.toString] every time
                output += "[" + oList[i].toString() + "]";

                // Add a comma if it's not the last element
                if (i < numObjects - 1) {
                    output += ", ";
                }
            }
        }
        return output;
    }

    /**
     * Returns the size of the ordered list.
     *
     * @return The size of the ordered list.
     * 
     * CSC 1351 Programming Project No 1
	 * Section 2
	 * 
	 * @author William Morales
	 * @since <insert due date here>
     */
    public int size() {
        return numObjects;
    }

    /**
     * Gets the object at the specified index in the ordered list.
     *
     * @param index The index of the object to retrieve.
     * @return The object at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     * 
     * CSC 1351 Programming Project No 1
	 * Section 2
	 * 
	 * @author William Morales
	 * @since <insert due date here>
     */
    public Comparable<Car> get(int index) {
        if (index < 0 || index >= numObjects) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return oList[index];
    }

    /**
     * Checks if the ordered list is empty.
     *
     * @return True if the ordered list is empty, false otherwise.
     * 
     * CSC 1351 Programming Project No 1
	 * Section 2
	 * 
	 * @author William Morales
	 * @since <insert due date here>
     */
    public boolean isEmpty() {
        return numObjects == 0;
    }

    /**
     * Removes the object at the specified index from the ordered list.
     *
     * @param index The index of the object to be removed.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     * 
     * CSC 1351 Programming Project No 1
	 * Section 2
	 * 
	 * @author William Morales
	 * @since <insert due date here>
     */
    public void remove(int index) {
        if (index < 0 || index >= numObjects) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        // Shift elements down to fill the hole
        for (int i = index; i < numObjects - 1; i++) {
            oList[i] = oList[i + 1];
        }

        // Update the counter
        numObjects--;
    }

    /**
     * Resets the iterator to the beginning of the ordered list.
     * 
     * CSC 1351 Programming Project No 1
	 * Section 2
	 * 
	 * @author William Morales
	 * @since <insert due date here>
     */
    public void reset() {
        curr = -1;
    }

    /**
     * Returns the next object in the ordered list and advances the iterator.
     *
     * @return The next object in the ordered list.
     * 
     * CSC 1351 Programming Project No 1
	 * Section 2
	 * 
	 * @author William Morales
	 * @since <insert due date here>
     */
    public Comparable<Car> next() {
        curr += 1;
        return oList[curr];
    }

    /**
     * Checks if there is a next object in the ordered list.
     *
     * @return True if there is a next object, false otherwise.
     * 
     * CSC 1351 Programming Project No 1
	 * Section 2
	 * 
	 * @author William Morales
	 * @since <insert due date here>
     */
    public boolean hasNext() {
        return curr + 1 < numObjects;
    }

    /**
     * Removes the current object from the ordered list.
     * 
     * CSC 1351 Programming Project No 1
	 * Section 2
	 * 
	 * @author William Morales
	 * @since <insert due date here>
     */
    public void remove() {
        remove(curr);
    }
}