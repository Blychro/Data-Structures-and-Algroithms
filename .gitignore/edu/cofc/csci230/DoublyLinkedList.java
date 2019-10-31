package edu.cofc.csci230;


/**
 * Doubly Linked List Data Structure
 * 
 * Thomas Marshall
 * 
 * I certify this is my work
 * 
 * @author CSCI 230: Data Structures and Algorithms Fall 2018
 *
 * @param <AnyType>
 */
public class DoublyLinkedList<AnyType extends Comparable<AnyType>> implements List<AnyType> {
    
    // instance variables
    private Node<AnyType> headNode = null;
    private Node<AnyType> tailNode = null;
    
    private int size = 0;
    
    /**
     * Appends the specified element to the end of this list.
     * 
     * @param t
     */
    public void add( AnyType t) throws NullPointerException {
        
        if ( t == null ) throw new NullPointerException();
        
        addNode( new Node<AnyType>(t) );
        
    } // end add() method
    
    /**
     * 
     * implementation for public add(AnyType t) method
     * 
     * @param t
     * 
     */
    private void addNode(Node<AnyType> t) {
        
        if ( isEmpty() ) {
            
            headNode = tailNode = t;
            
        } else { 
            
            Node<AnyType> node = getNode( size-1 );
            node.setNextNode( t );
            t.setPreviousNode( node );
            
            tailNode = t;
            
        }
        
        size++;
        
    } // end addNode() method
    
    
    /**
     * Inserts the specified element at the specified position in this list.
     * 
     * @param index
     * @param t
     * @throws IndexOutOfBoundsException, NullPointerException
     */
    public void add(int index, AnyType t) throws IndexOutOfBoundsException, NullPointerException {
        
        if ( t == null ) throw new NullPointerException();
        
        addNode( index, new Node<AnyType>(t) );
        
    } // end add() method
    
    /*
     * 
     * Implementation for public add(int index, AnyType t) method
     * 
     */
    private void addNode(int index, Node<AnyType> t) throws IndexOutOfBoundsException {
        
        if ( index == 0 && !isEmpty() ) {
            
            t.setNextNode( headNode );
            headNode.setPreviousNode( t );
            headNode = t;
            size++;
            
        } else if ( index == 0 && isEmpty() ) { 
            
            t.setNextNode( headNode );
            headNode = tailNode = t;
            size++;
            
        } else if ( index == size ) {
            
            addNode( t );
            
        } else {
            
            Node<AnyType> node = getNode( index );
            
            node.getPreviousNode().setNextNode( t );
            t.setPreviousNode( node.getPreviousNode() );
            node.setPreviousNode( t );
            t.setNextNode( node );
        
            size++;
            
        }
        
    } // end addNode() method
    
    /**
     * Replaces the element at the specified position in this list with the specified element.
     * 
     * @param index
     * @param t
     * @throws IndexOutOfBoundsException, NullPointerException
     */
    public void set(int index, AnyType t) throws IndexOutOfBoundsException, NullPointerException {

        if ( t == null ) throw new NullPointerException();
        
        setNode( index, new Node<AnyType>(t) );
        
    } // end set() method
    
    /**
     * 
     * @param index
     * @param t
     * @throws IndexOutOfBoundsException
     */
    private void setNode( int index, Node<AnyType> t )  throws IndexOutOfBoundsException {
        
        getNode( index ).setData( t.getData() );
        
    } // end setNode() method
    
    
    /**
     * Removes the element at the specified position in this list.
     * 
     * @param index
     * @return
     * @throws IndexOutOfBoundsException
     */
    public AnyType remove( int index ) throws IndexOutOfBoundsException {
        
        return removeNode( index ).getData();
        
    } // end remove() method
    
    /**
     * 
     * @param index
     * @return
     * @throws IndexOutOfBoundsException
     */
    private Node<AnyType> removeNode( int index ) throws IndexOutOfBoundsException {
        
        
        Node<AnyType> node = getNode( index );
        
        if ( index == 0 ) {
            
            headNode = node.getNextNode();
            if ( headNode != null ) headNode.setPreviousNode( null );
            
        } else if ( index == ( size -1 ) ) {
            
            Node<AnyType> prev_node = node.getPreviousNode();
            prev_node.setNextNode( null );
            tailNode = prev_node;
            
        } else {
            
            Node<AnyType> prev_node = node.getPreviousNode();
            Node<AnyType> next_node = node.getNextNode();
            
            prev_node.setNextNode( next_node );
            next_node.setPreviousNode( prev_node );
            
        }
        
        node.setNextNode( null );
        node.setPreviousNode( null );
        
        size--;
        
        if ( isEmpty() ) clear();
        
        return node;
        
    } // end removeNode() method
    
    /**
     * Returns the element at the specified position in this list.
     * 
     * @param index
     * @return
     * @throws IndexOutOfBoundsException
     */
    public AnyType get( int index ) throws IndexOutOfBoundsException {
        
        return getNode( index ).getData();
        
    } // end get() method
    
    /**
     * 
     * Implementation of get(int index) method
     * 
     * @param index
     * @return
     * @throws IndexOutOfBoundsException
     * 
     */
    private Node<AnyType> getNode(int index) throws IndexOutOfBoundsException {
        
        /**
         * -------------------------------------------
         * 
         * Your implementation MUST do the following link traversals:
         * 
         * 1) If the index location is <= floor( size/2 ), start traversal from head node
         * 2) if the index location is > floor( size/2), start traversal from tail node
         * 
         * Your code will be reviewed by instructor to ensure the two conditions
         * are fully meet by your solution.
         * 
         */
        
        if ( index < 0 || index >= size ) {
            
            throw new IndexOutOfBoundsException();
            
        }
        
        Node<AnyType> node = null;
        
        if ( index <= Math.floor( ((double)size)/2.0 ) ) {
            
            node = headNode;
            
            for ( int i=1; i<=index; i++ ) {
                
                node = node.getNextNode();
                
            }
            
        } else {
            
            node = tailNode;
            
            for ( int i=(size-1); i>index; i-- ) {
                
                node = node.getPreviousNode();
                
            }
            
        }
        
        return node;
        
    } // end get() method
    
    /**
     * Returns the number of elements in this list. 
     * 
     * @return
     */
    public int size() {
        
        return size;
        
    } // end size() method
    
    /**
     * Returns true if this list contains no elements.
     * 
     * @return
     */
    public Boolean isEmpty() {
        
        return ( size == 0 ) ? true : false;
        
    } // end isEmpty() method
    
    /**
     * swaps to elements in a list at index 
     * position i and j.
     * 
     * For example, 
     *  if A = 1->2->3->4->5 and swap( 1, 3 ) is executed
     *  then A = 1->4->3->2->5
     *
     * @param i
     * @param j
     * @throws IndexOutOfBoundsException
     */
     public void swap( int i, int j )  throws IndexOutOfBoundsException {
        
         /**
          * -------------------------------------------
          * TODO: You fully implement this method
          * 
          */
         
         // Checks for Index errors and throws found ones
         if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IndexOutOfBoundsException();
         }
         
         // Sets variables that are getting switched
         AnyType pick1 = get(i);
         AnyType pick2 = get(j);
         
         // Uses set to switch the two items
         set(i, pick2);
         set(j, pick1);
    } // end swap() method
    
    
    /**
     * 
     * Do not modify
     * 
     * To help you debug your code
     * 
     */
    public void printList() {
        
        Node<AnyType> n = headNode;
        
        while ( n != null ) {
            
            System.out.println( n );
            
            n = n.getNextNode();
            
        }
        
    } // end printlist()
    
    /**
     * Removes all of the elements from this list.
     * 
     */
    public void clear() {
        
        headNode = null;
        tailNode = null;
        size=0;
        
    } // end clear method
    
    /**
     * 
     * For debugging and testing purpose
     * 
     * !!! Do not remove or modify !!!
     * 
     */
    public String toString() {
        
        StringBuffer buffer = new StringBuffer();
        
        buffer.append( String.format( "Size=%d, A = [ ", size ) );
        
        if ( !isEmpty() ) {
            
            for ( int i=0; i<size-1; i++ ) {
                buffer.append( String.format( "%d, ", get(i) ) );    
            }
            
            buffer.append( String.format( "%d ]", get(size-1 ) ) );
            
        } else {
            
            buffer.append( "] " );
        }
        
        return buffer.toString();
        
    } // end toString()
    
    
    /**
     * 
     * @param args
     */
    public static void main( String[] args ) {
                
        // -------------------------------------
        // Put your test cases here
        // -------------------------------------
        
        System.out.println("Swap Index Out of Bounds Exception Test:");
        
        DoublyLinkedList<Integer> test = new DoublyLinkedList<Integer>();
        
        for (int i = 0; i < 5; ++i) {
            test.add(i);
        }
        
        System.out.println(test);
        
        try {
            test.swap(-1, 3);
        } catch (IndexOutOfBoundsException ime) {
            System.out.println("Index out of bounds.");
        }
        try {
            test.swap(1, 10);
        } catch (IndexOutOfBoundsException ime) {
            System.out.println("Index out of bounds.");
        }
        try {
            test.swap(-7, 15);
        } catch (IndexOutOfBoundsException ime) {
            System.out.println("Index out of bounds.");
        }
        try {
            test.swap(1, 3);
        } catch (IndexOutOfBoundsException ime) {
            System.out.println("Index out of bounds.");
        }
        try {
            test.swap(0, 2);
        } catch (IndexOutOfBoundsException ime) {
            System.out.println("Index out of bounds.");
        }
        
        System.out.println(test + "\n");
        
        for (int i = 0; i < 3; ++i) {
            DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
            
            list.add(-1);
            list.add(100);
            list.add(20);
            list.add(13);
            
            System.out.println(list);
    
            Utils.ascending = true; // sort in ascending order
            
            if (i == 0) {
                System.out.println("\nBubble Sort:");
                Utils.bubbleSort(list);
            }
            else if (i == 1) {
                System.out.println("Selection Sort:");
                Utils.selectionSort(list);
            }
            else {
                System.out.println("Insertion Sort:");
                Utils.insertionSort(list);
            }
            
            System.out.println(list);
            
            Utils.ascending = false; // sort in descending order
            
            if (i == 0) 
                Utils.bubbleSort(list);
            else if (i == 1)
                Utils.selectionSort(list);
            else
                Utils.insertionSort(list);
    
            System.out.println(list + "\n\n");
        }
    } // end main() method
} // end DoublyLinkedList class definition