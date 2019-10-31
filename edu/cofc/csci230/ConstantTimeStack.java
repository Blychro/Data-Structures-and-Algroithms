package edu.cofc.csci230;

import java.util.EmptyStackException;

/**
 * A LIFO stack that has constant time complexity O(1) for
 * all three stack interface methods (i.e., push, pop, and 
 * peek).
 * 
 * 
 * @author CSCI 230: Data Structures and Algorithms Fall 2018
 *
 * Thomas Marshall
 * 
 * I certify that this is my work
 *
 * @param <AnyType>
 */
public class ConstantTimeStack<AnyType extends Comparable<AnyType>> implements Stack<AnyType> {
    
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
     * Pushes an item onto the top of this stack in constant 
     * time O(1)
     * 
     * @param t the item to be pushed onto this stack.
     */
    public void push(AnyType t) {
        
        /**
         * -------------------------------------------
         * TODO: You fully implement this method
         * 
         * Note: Your push solution must be a constant 
         * time O(1) operation
         * 
         */
        
        // Adds the element to the top of the stack
        list.add(0, t);
        
    } // end push() method

    /**
     * Removes the object at the top of this stack and return the 
     * item in constant time O(1)
     * .
     * @return The item at the top of this stack
     * @throws EmptyStackException - if this stack is empty.
     */
    public AnyType pop() throws EmptyStackException {
        
        /**
         * -------------------------------------------
         * TODO: You fully implement this method
         * 
         * Note: Your pop solution must be a constant 
         * time O(1) operation
         * 
         */
        
        // Checks to see if there are any elements to remove
        if (list.isEmpty()) {
            throw new EmptyStackException();
        }
        
        // Sets up the element to be returned
        AnyType temp = list.get(0);
        
        // Removes the element on the top of the stack
        list.remove(0);
        
        // Returns the removed element
        return temp;   
    } // end pop() method

    /**
     * Looks at the item at the top of this stack without removing it 
     * from the stack in constant time O(1)
     * 
     * @return the item at the top of this stack
     * @throws EmptyStackException  - if this stack is empty.
     */
    public AnyType peek() throws EmptyStackException {
        
        /**
         * -------------------------------------------
         * TODO: You fully implement this method
         * 
         * Note: Your peek solution must be a constant 
         * time O(1) operation
         * 
         */
        
        // Checks to see if the stack is empty
        if (list.size() == 0) {
            throw new EmptyStackException();
        }
        
        // Returns the element on the top of the stack
        return list.get(0);
    } // end peek() method
    
    /**
     * 
     * @param args
     */
    public static void main( String[] args ) throws EmptyStackException {
        
        /**
         * -------------------------------------------
         * TODO: You put your test cases here
         * 
         */
        
        // Test code
        ConstantTimeStack<Integer> test = new ConstantTimeStack<Integer>();
        
        System.out.println("Tester start:\n");
        
        System.out.println("Exception tests:");
        
        try {
            int top = test.pop();
            System.out.println(top + " removed from the stack.");
        } catch (EmptyStackException ime) {
            System.out.println("Empty stack found.");
        }
        
        try {
            int current = test.peek();
            System.out.println(current + " is currently on the top of the stack.");
        } catch (EmptyStackException ime) {
            System.out.println("Empty stack found.");
        }
        
        test.push(0);
        test.push(1);
        
        System.out.println("\nPop test:");
        try {
            int top = test.pop();
            System.out.println(top + " removed from the stack.");
        } catch (EmptyStackException ime) {
            System.out.println("Empty stack found.");
        }
        
        try {
            int current = test.peek();
            System.out.println(current + " is currently on the top of the stack.");
        } catch (EmptyStackException ime) {
            System.out.println("Empty stack found.");
        }
        
        test.push(5);
        test.push(10);
        
        try {
            int current = test.peek();
            System.out.println(current + " is now on the top of the stack.");
        } catch (EmptyStackException ime) {
            System.out.println("Empty stack found.");
        }
        
        try {
            int top = test.pop();
            System.out.println(top + " has been removed from the stack.");
        } catch (EmptyStackException ime) {
            System.out.println("Empty stack found.");
        }
        
        try {
            int current = test.peek();
            System.out.println(current + " is the new top of the stack.");
        } catch (EmptyStackException ime) {
            System.out.println("Empty stack found.");
        }
    } // end main method

} // end ConstantTimeStack class definition
