package edu.cofc.csci230;

import java.util.NoSuchElementException;

/**
 * A FIFO stack that has constant time complexity O(1) for
 * all three queue interface methods (i.e., add, remove, and 
 * peek).
 * 
 * @author CSCI 230: Data Structures and Algorithms Fall 2018
 * 
 * Thomas Marshall
 * 
 * I certify this is my work
 *
 * @param <AnyType>
 */
public class ConstantTimeQueue<AnyType extends Comparable<AnyType>> implements Queue<AnyType> {
    
    /**
     * ------------------------------
     * TODO:
     * ------------------------------
     * 
     * Select and instantiate a new ArrayList or DoublyLinkedList
     * that will achieve constant time performance
     */
    private DoublyLinkedList<AnyType> list = new DoublyLinkedList<AnyType>(); // modify this line of code


    /**
     * Inserts the specified element at the end of the queue in 
     * constant time O(1)
     * 
     * @param t element to add
     * @throws NullPointerException- if the specified element is null 
     *                               (queue does not permit null elements)
     */
    public void add(AnyType t) throws NullPointerException {
        
        /**
         * -------------------------------------------
         * TODO: You fully implement this method
         * 
         * Note: Your add solution must be a constant 
         * time O(1) operation.
         * 
         */
        
        // Checks to see if the newly added item is null
        if (t == null) {
            throw new NullPointerException();
        }
        
        // Adds the item to the back of the queue
        list.add(t);
    } // end add() method

    /**
     * Retrieves and removes the head of the queue in
     * constant time O(1).
     * 
     * @return the head of the queue
     * @throws NoSuchElementException - if this queue is empty
     */
    public AnyType remove() throws NoSuchElementException {
        
        /**
         * -------------------------------------------
         * TODO: You fully implement this method
         * 
         * Note: Your push solution must be a constant 
         * time O(1) operation. 
         * 
         *
         */
        
        // Checks to see if the queue is empty
        if (list.isEmpty()) {
            throw new NoSuchElementException();
        }
        
        // Retrieves the item at the front of the queue
        AnyType temp = list.get(0);
        
        // Removes it from the list
        list.remove(0);
        
        // Returns the item removed
        return temp;
    } // end remove() method

    /**
     * Retrieves, but does not remove, the head of the queue, 
     * or returns null if this queue is empty.
     * 
     * @return the head of this queue, or null if this queue is empty
     */
    public AnyType peek() {
        
        /**
         * -------------------------------------------
         * TODO: You fully implement this method
         * 
         * Note: Your add solution must be a constant 
         * time O(1) operation 
         * 
         */
        
        // Returns the item at the front of the queue list
        return list.get(0);
    } // end peek() method
    
    /**
     * 
     * @param args
     */
    public static void main(String[] args) throws NullPointerException, NoSuchElementException{
        
        /**
         * -------------------------------------------
         * TODO: You put your test cases here
         * 
         */
        
        ConstantTimeQueue<Integer> test = new ConstantTimeQueue<Integer>();
        
        // Test code
        System.out.println("Test start:\n");
        
        try {
            test.add(0);
        } catch (NullPointerException ime) {
            System.out.println("Null entry.");
        }
        try {
            test.add(1);
        } catch (NullPointerException ime) {
            System.out.println("Null entry.");
        }
        try {
            test.add(2);
        } catch (NullPointerException ime) {
            System.out.println("Null entry.");
        }

        try {
            System.out.println(test.remove() + " has been removed from the queue.");
        } catch (NoSuchElementException ime) {
            System.out.println("The queue is empty.");
        }
        
        System.out.println(test.peek() + " is the next item in the queue.");
        
        ConstantTimeQueue<Integer> test2 = new ConstantTimeQueue<Integer>();
        
        System.out.println("\nException tests:");
        
        try {
            test2.add(null);
        } catch (NullPointerException ime) {
            System.out.println("Null entry.");
        }
        
        try {
            System.out.println(test2.remove() + " has been removed from the queue.");
        } catch (NoSuchElementException ime) {
            System.out.println("The queue is empty.");
        }
    } // end main() method

} // end ConstantTimeQueue class definition

