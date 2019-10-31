package edu.cofc.csci230;

/**
 * Utilities class that will sort in ascending and descending order
 * the elements of a list.
 *
 * The sorting algorithms supported in this class are:
 *  1. selection sort
 *  2. bubble sort
 *  3. insertion sort
 *  
 *  Thomas Marshall
 *  
 *  I certify that this is my own work
 * 
 * @author CSCI 230: Data Structures and Algorithms Fall 2018
 *
 */
public class Utils {
    
    
    public static boolean ascending = true; 
    
    /**
     * 
     */
    private Utils() {
        
    } // end private constructor
    
    /**
     * 
     * @param list
     */
    public static <AnyType extends Comparable> void selectionSort( List<AnyType> list ) throws IndexOutOfBoundsException {
        
        /*
         * TODO:
         * 
         * Implement selection sort algorithm as
         * described in class. The pseudo-code for 
         * this algorithm can also be found in the
         * content section on OAKS and at the end
         * of the homework assignment.
         * 
         * Must sort in:
         * -----------------------------------------
         * 1. ascending order (first element 
         * in list is smallest value, last element in 
         * list is largest value).
         * 
         * 2. descending order (first element 
         * in list is largest value, last element in 
         * list is smallest value).
         * 
         */
        
        System.out.printf( "Ascending order [%b]\n", ascending );
        
        // Variable for to represent n.
        int len = list.size();
        
        // For loop length to loop through the necessary amount of times.
        for (int i = 0; i < len - 1; ++i) {
            // Largest or smallest variable
            int edge = i;
            // Nested for loop
            for (int j = i + 1; j < len; ++j) {
                // Checks if it is ascending or descending
                if (ascending) {
                    // If the current item is less than the assumed minimum it sets the item as the new minimum
                    if (list.get(j).compareTo(list.get(edge)) < 0) {
                        edge = j;
                    }
                }
                else {
                    // If the current item is more than the assumed maximum it sets the item as the new maximum
                    if (list.get(j).compareTo(list.get(edge)) > 0) {
                        edge = j;
                    }
                }
            }
            // Sets the found minimum or maximum to the current left most position
            list.swap(i, edge);
        }
    } // end selectionSort() method
    
    /**
     * 
     * @param list
     */
    public static <AnyType extends Comparable> void bubbleSort( List<AnyType> list ) throws IndexOutOfBoundsException {
        
        /*
         * TODO:
         * 
         * Implement bubble sort algorithm as
         * described in class. The pseudo-code for 
         * this algorithm can also be found in the
         * content section on OAKS and at the end
         * of the homework assignment.
         * 
         * 
         * Must sort in:
         * -----------------------------------------
         * 1. ascending order (first element 
         * in list is smallest value, last element in 
         * list is largest value).
         * 
         * 2. descending order (first element 
         * in list is largest value, last element in 
         * list is smallest value).
         * 
         */
        
        System.out.printf( "Ascending order [%b]\n", ascending );
        
        int len = list.size();
        
        // Loops the necessary amount of times
        for (int i = 0; i < len - 1; ++i) {
            // Nested for loop
            for (int j = 0; j < len - 1 - i; ++j) {
                // Checks for ascending or descending order
                if (ascending) {
                    // Checks to see which item is smaller and switches them if it is the one to the right
                    if (list.get(j + 1).compareTo(list.get(j)) < 0) {
                        list.swap(j + 1, j);
                    }
                }
                else {
                    // Checks to see which item is larger and switches them if it is the one to the right
                    if (list.get(j + 1).compareTo(list.get(j)) > 0) {
                        list.swap(j + 1, j);
                    }
                }
            }
        }
    } // end bubbleSort() method
    
    /**
     * 
     * @param list
     */
    public static <AnyType extends Comparable> void insertionSort( List<AnyType> list ) throws IndexOutOfBoundsException {
        
        /*
         * TODO:
         * 
         * Implement insertion sort algorithm as
         * described in class. The pseudo-code for 
         * this algorithm can also be found in the
         * content section on OAKS and at the end
         * of the homework assignment.
         * 
         * 
         * Must sort in:
         * -----------------------------------------
         * 1. ascending order (first element 
         * in list is smallest value, last element in 
         * list is largest value).
         * 
         * 2. descending order (first element 
         * in list is largest value, last element in 
         * list is smallest value).
         * 
         */
        
        System.out.printf( "Ascending order [%b]\n", ascending );
        
        int len = list.size();
        
        // Checks for ascending or descending order
        if (ascending) {
            // Loops the necessary amount of times
            for (int i = 1; i < len; ++i) {
                // Sets up the current leftmost item as a variable
                AnyType v = list.get(i);
                // Defines each loop's j
                int j = i - 1;
                // While loop for flexibility
                while (j >= 0 && list.get(j).compareTo(v) > 0) {
                    // Sets the item one step further in the list until it finds its spot in the list
                    list.set(j + 1, list.get(j));
                    // Subtract one from j for the next loop
                    --j;
                }
                // Puts the item in the appropriate place
                list.set(j + 1, v);
            }
        }
        else {
            // Loops the necessary amount of times
            for (int i = 1; i < len; ++i) {
                // Sets up the current leftmost item as a variable
                AnyType v = list.get(i);
                // Defines each loop's j
                int j = i - 1;
                // While loop for flexibility
                while (j >= 0 && list.get(j).compareTo(v) < 0) {
                    // Sets the item one step further in the list until it finds its spot in the list
                    list.set(j + 1, list.get(j));
                    // Subtract one from j for the next loop
                    --j;
                }
                // Puts the item in the appropriate place
                list.set(j + 1, v);
            }
        }
    } // end insertionSort() method
} // end Utils class definition
