package edu.cofc.csci230;

/**
 * 
 * Binary search that does not allow two (or more) binary nodes 
 * in the search tree to have the same element value.
 * 
 * @author CSCI 230: Data Structures and Algorithms Fall 2018
 * 
 * Thomas Marshall
 * 
 * I certify this is my work
 *
 * @param <AnyType>
 */
public class BinarySearchTree <AnyType extends Comparable<AnyType>> {
    
    // --------------------------------------
    // instance variable
    private BinaryNode<AnyType> root;
    
    /**
     *
     * Constructor with one parameter that
     * sets the root node of the BST.
     * 
     * @param rootElement
     * @throws NullBinaryNodeException
     */
    public BinarySearchTree( AnyType rootElement ) throws NullBinaryNodeException {
        
        if ( rootElement == null ) {
            throw new NullBinaryNodeException();
        }
        
        this.root = new BinaryNode<AnyType>( rootElement ) ;
        
    } // end constructor
    
    /**
     * If the BST does not have a root node, then it is empty. 
     * 
     * @return
     */
    public boolean isEmpty() {
        
        return ( root == null );
        
    } // end isEmpty() method
    
    /**
     * Make the BST empty, i.e. a BST that has 
     * no nodes (including a root node).
     * 
     */
    public void clear() {
        
        root = null;
        
    } // end clear() method
    
    /**
     * Method that inserts a new node with the specified element 
     * value in the BST.
     * 
     * This method has one parameter:
     *  1) The element value to be added
     * 
     * If the BST has an existing node with the same element 
     * value, throw an duplicate element exception.
     * 
     * @param element
     * @throws DuplicateElementException
     * @throws NullBinaryNodeException
     */
    public void insert( AnyType element ) throws NullBinaryNodeException, DuplicateElementException, EmptyBSTException {
        
        if ( element == null )
            throw new NullBinaryNodeException();
        
        if ( root == null )
            this.root = new BinaryNode<AnyType>( element );
        else
            insert( root, element );

    } // end insert() method
    
    /**
     * Private recursive method that inserts a new node (with the 
     * specified element value) in the BST.
     * 
     * This method has two parameters:
     *  1) The node visited while recursively walking the BST
     *  2) The element value to be added
     * 
     * If the BST has an existing node with the same element 
     * value, throw an duplicate element exception.
     * 
     * @param node
     * @param element
     * @throws DuplicateElementException
     */
    private void insert( BinaryNode<AnyType> node, AnyType element )  throws DuplicateElementException, NullBinaryNodeException, EmptyBSTException {
        
        /**
         * ------------------------------------
         * TODO: You complete the code.
         * 
         * Note: Your solution MUST USE recursion
         * 
         */
        
        // Checks to see if the item is already in the list
        if (search(element)) {
            throw new DuplicateElementException();
        }
        
        // Set up an anytype for the node
        AnyType current = node.getElement();
        
        // Checks if the item should move left
        if (element.compareTo(current) < 0) {
            // Gets left child
            BinaryNode<AnyType> next = node.getLeft();
            // Sets the current element as the left child
            if (next == null) {
                // Inserts the item to the left
                BinaryNode<AnyType> input = new BinaryNode(element);
                node.setLeft(input);
                node.getLeft().setParent(node);
            }
            else {
                // Moves the node left
                insert(node.getLeft(), element);
            }
        }
        else {
            // Gets right child
            BinaryNode<AnyType> next = node.getRight();
            // Sets the current element as the right child
            if (next == null) {
                BinaryNode<AnyType> input = new BinaryNode(element);
                node.setRight(input);
                node.getRight().setParent(node);
            }
            // Goes to the right node
            else {
                insert(node.getRight(), element);
            }
        }
    } // end insert() overloaded method
    
    /**
     * Method that determines if a node with the specified element value 
     * exists in the BST. 
     * 
     * This method has one parameter:
     *  1) The element value that is being searched.
     * 
     * If the BST is empty, throw an empty binary search tree
     * exception.
     * 
     * @param element
     * @throws EmptyBSTException
     * @throws NullBinaryNodeException
     * @return
     */
    public boolean search( AnyType element ) throws NullBinaryNodeException, EmptyBSTException, DuplicateElementException{
        
        if ( element == null )
            throw new NullBinaryNodeException();
        if ( isEmpty() )
            throw new EmptyBSTException();
        
        return search( root, element );
        
    } // end search() method
    
    /**
     * Private recursive method that determines if the element is 
     * currently stored in the BST.
     * 
     * This method has two parameters:
     *  1) The node visited while recursively walking the BST
     *  2) The element value that is being searched.
     *  
     *  Hint: use the compareTo() method
     * 
     * @param element
     * @param node
     * @return
     */
    private boolean search( BinaryNode<AnyType> node, AnyType element ) throws DuplicateElementException, NullBinaryNodeException, EmptyBSTException {
        
        /**
         * ------------------------------------
         * 
         * Note: Your solution MUST USE recursion
         * 
         */
        
        // Defaults to false
        boolean found = false;
        
        // Sets up anytype variable
        AnyType current = node.getElement();
        
        // If the current node matches it returns true
        if (element.compareTo(current) == 0) {
            found = true;
        }
        // If the node in question is less then the method moves left
        else if (element.compareTo(current) < 0) {
            BinaryNode<AnyType> next = node.getLeft();
            // Checks if it hit the end of the BST
            if (next != null) {
                found = search(node.getLeft(), element);
            }
        }
        // If the node is more then the method moves right
        else if (element.compareTo(current) > 0) {
            BinaryNode<AnyType> next = node.getRight();
            // Checks if it hit the end of the BST
            if (next != null) {
                found = search(node.getRight(), element);
            }
        }

        return found;
    } // end search() overloaded method
    
    /**
     * Find the node with the minimum element value in the BST.
     * 
     * This method has no parameters.
     * 
     * If the BST is empty, throw an empty binary search tree
     * exception.
     * 
     * @return
     * @throws EmptyBSTException
     */
    public AnyType min() throws EmptyBSTException {
        
        if ( isEmpty() )
            throw new EmptyBSTException();
        
        return min( root ).getElement();
        
    } // end findMin() method
    
    /**
     * Private recursive method that walks the BST searching
     * for the node with the minimum element value.
     * 
     * This method has one parameter:
     *  1) The node visited while recursively walking the BST
     * 
     * @param node
     * @return
     */
    private BinaryNode<AnyType> min( BinaryNode<AnyType> node ) {
        
        /**
         * ------------------------------------
         * TODO: You complete the code.
         * 
           * Note: Your solution MUST USE recursion
         * 
         */

        // Defines the potential future return value least
        BinaryNode<AnyType> least = node;
        
        // Moves left to the next lesser node until it reaches the end
        if (node.getLeft() != null) {
            least = min(node.getLeft());
        }
        
        // Returns the least value
        return least;
    } // end min() method
    
    /**
     * Find the node with the maximum element value in the BST.
     * 
     * This method has no parameters.
     * 
     * If the BST is empty, throw an empty binary search tree
     * exception.
     * 
     * @return
     * @throws EmptyBSTException
     */
    public AnyType max() throws EmptyBSTException {
        
        if ( isEmpty() )
            throw new EmptyBSTException();
        
        return max( root ).getElement();
        
    } // end max() method
    
    /**
     * Private recursive method that walks the BST searching
     * for the node with the maximum element value.
     * 
     * This method has one parameter:
     *  1) The node visited while recursively walking the BST
     * 
     * @param node
     * @return
     */
    private BinaryNode<AnyType> max( BinaryNode<AnyType> node ) {
        
        /**
         * ------------------------------------
         * TODO: You complete the code.
         * 
         * Note: Your solution must use recursion
         * 
         */
        
        // Defines the potential max value
        BinaryNode<AnyType> most = node;
        
        // Move right until it reaches the max value
        if (node.getRight() != null) {
            most = max(node.getRight());
        }
        
        // Returns the max value
        return most;
    } // end max() method

    /**
     * Delete the node with the specified element value in the BST.
     * 
     * This method has one parameter:
     *  1) the element value to be deleted
     * 
     * This method performs the following delete operations
     *  1) delete node with no children (case 1)
     *  2) delete node with one child (case 2)
     *  3) delete node with two children (case 3)
     * 
     * If the BST is empty, throw an empty binary search tree
     * exception.
     * 
     * If the element is null, throw a null binary node 
     * exception
     *  
     * @param element
     * @throws EmptyBSTException
     * @throws NullBinaryNodeException
     */
    public AnyType delete( AnyType element ) throws EmptyBSTException, NullBinaryNodeException {
        
        if ( element == null )
            throw new NullBinaryNodeException();
        if ( isEmpty() )
            throw new EmptyBSTException();
        
        return delete( root, element ).getElement();
        
    } // end delete() method


    /**
     * Private recursive method that walk the BST looking for 
     * the specified element value to be removed.
     * 
     * This method has two parameters:
     *  1) The node visited while recursively walking the BST
     *  2) The element value that is being removed.
     *  
     * @param node
     * @param element
     */
    private BinaryNode<AnyType> delete( BinaryNode<AnyType> node, AnyType element ) {
        
        /**
         * ------------------------------------
         * TODO: You complete the code.
         * 
         * Note: Your solution MUST USE recursion
         * 
         */
        
        // Values to make the later code more readable
        AnyType current = node.getElement();
        BinaryNode<AnyType> left = node.getLeft();
        BinaryNode<AnyType> right = node.getRight();
        BinaryNode<AnyType> gone = null;
        BinaryNode<AnyType> parent = node.getParent();
        
        // Checks if the current node should be deleted
        if (element.compareTo(current) == 0) {
            // Checks if the node to be deleted is a terminal node
            if (left == null && right == null) {
                // Removes the selected node if it's on the left of its parent
                if (element.compareTo(parent.getElement()) < 0) {
                    parent.setLeft(null);
                }
                // Removes the selected node if it's on the right of its parent
                else {
                    parent.setRight(null);
                }
            }
            // Checks if it has one child on the left
            else if (right == null && left != null) {
                // Removes the selected node if it's on the left of its parent and sets the grandchild of the removed node as the new left node
                if (parent.getElement().compareTo(element) > 0) {
                    parent.setLeft(node.getLeft());
                    node.getLeft().setParent(parent);
                }
                // Removes the selected node if it's on the right of its parent and sets the grandchild of the removed node as the new left node
                else {
                    parent.setRight(node.getLeft());
                    node.getLeft().setParent(parent);
                }
            }
            // Checks if it has one child on the right
            else if (right != null && left == null){
                // Removes the selected node if it's on the left of its parent and sets the grandchild of the removed node as the new right node
                if (parent.getElement().compareTo(element) > 0) {
                    parent.setLeft(node.getRight());
                    node.getRight().setParent(parent);
                }
                // Removes the selected node if it's on the right of its parent and sets the grandchild of the removed node as the new right node
                else {
                    parent.setRight(node.getRight());
                    node.getRight().setParent(parent);
                }
            }
            // The node to be removed can only have two children
            else {
                // The two child code records the least value to the right of the removed node and uses the delete method to remove the node that was moved up
                AnyType least = min(node.getRight()).getElement();
                delete(node.getRight(), least);
                node.setElement(least);
            }
            // Gives a return value to show which node was deleted
            gone = node;
        }
        // Moves to the left node if the item to be deleted is less than the current node
        else if (element.compareTo(current) < 0){
            gone = delete(node.getLeft(), element);
        }
        // Moves to the right node since the item to be deleted can only be more than the current node at this point
        else {
            gone = delete(node.getRight(), element);
        }
        
        // Returns the deleted node
        return gone;
    } // end delete() method


    /**
     * Recursive method that traverses the BST 
     * dynamically creating a string with the
     * element values stored in an pre-order 
     * tree traversal format.
     * 
     * The return string MUST be formated as follows:
     * <element>,<element>,<element>,<element>, .... <element>
     * where <element> is the element value
     * For example,
     *  2,1,3
     * Hint: you may want to use regular expressions
     * 
     * Discussed in class, please review 
     * your notes
     * 
     * If the BST is empty, throw an empty binary search 
     * tree exception
     * 
     * @throws EmptyBSTException 
     * @return 
     * 
     */
    public String preOrder() throws EmptyBSTException {
        
        if ( isEmpty() )
            throw new EmptyBSTException();
        
        return preOrder( root );
        
    } // end preOrder() method
    
    /**
     * Private recursive method that traverses the BST 
     * dynamically creating a string with the
     * element values stored in an pre-order 
     * tree traversal format. 
     * 
     * This method has one parameter:
     *  1) The node visited while recursively walking the BST
     *  
     * @param node
     * @return
     * 
     */
    private String preOrder( BinaryNode<AnyType> node ) {
        
        /**
         * ------------------------------------
         * TODO: You complete the code.
         * 
         * Note: Your solution MUST USE recursion
         * 
         */
        
        // Sets up a string to return
        String rtnstr = "";
        
        // Sets up node to use
        BinaryNode<AnyType> current = node;
        
        // Adds the current parent node to the return value
        rtnstr += current;
        
        // Goes left first as far as possible and returns the items it finds through recursion
        if (node.getLeft() != null) {
            rtnstr += " " + preOrder(node.getLeft());
        }
        // Then goes right as far as possible and adds them last to the pre order
        if (node.getRight() != null) {
            rtnstr += " " + preOrder(node.getRight());
        }
        
        // Returns the currently built string up to the next level to be added to until completion
        return rtnstr;
    } // end preOrder() method

    
    /**
     * 
     * You may modify this (no restrictions) to help debug your code.
     * 
     */
    public String toString() {
        
        
        
        
        // Preorder was used to test the code
        return preOrder(root);


    } // end toString() method
    
    
    /**
     * 
     * You may modify this (no restrictions) to help debug your code.
     * 
     */
    public void printBST() {
        // I only used toString() and did not need this method
        
    } // end printBST() method

    
    /**
     * 
     * @param args
     */
    public static void main( String[] args ) throws EmptyBSTException, DuplicateElementException, NullBinaryNodeException {
        
        /**
         * ------------------------------------
         * TODO: You put your test cases here
         * 
         * 
         */
        
        // this will get you started :)
        
        // Bug Test code
        BinarySearchTree<Integer> bst;
        
        try {
            
            bst = new BinarySearchTree<Integer>( 10 );
            bst.insert(2);
            bst.insert(3);
            bst.insert(1);
            bst.insert(11);
            bst.insert(15);
            bst.insert(13);
            bst.insert(20);
            bst.insert(19);
            bst.insert(8);
            bst.insert(30);
            bst.insert(17);
            bst.insert(23);
            bst.insert(27);
            bst.insert(9);
            bst.insert(24);
            
            System.out.println("\nSearch Tests:");
            System.out.println(bst.search(2));
            System.out.println(bst.search(5));
            System.out.println(bst.search(13));
            System.out.println(bst.search(19));
            System.out.println(bst.search(50));
            System.out.println(bst.search(30));
            System.out.println(bst.search(1));
            
            System.out.println("\nMin-Max Test:");
            System.out.println(bst.min());
            System.out.println(bst.max());
            
            System.out.println("\nPre order test:");
            System.out.println(bst.preOrder());
            
            System.out.println("\nDelete 1:");
            bst.delete(1);
            System.out.println(bst.preOrder());
            
            System.out.println("Delete 15:");
            bst.delete(15);
            System.out.println(bst.preOrder());
            
            System.out.println("Delete 8:");
            bst.delete(8);
            System.out.println(bst.preOrder());
            
            System.out.println("Delete 23:");
            bst.delete(23);
            System.out.println(bst.preOrder());
        } catch( NullBinaryNodeException error ) {
            
            System.out.println( error );
            
        } catch(NullPointerException e) {
            System.out.print("Item to delete does not exist within the BST.");
        } catch(EmptyBSTException e) {
            System.out.print("The Binary Search Tree is empty.");
        } catch(DuplicateElementException e) {
            System.out.print("Item already exists in the BST.");
        }
        
        
        
    } // end main method
    

} // end BinarySearchTree class
