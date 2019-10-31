package edu.cofc.csci230;

/**
 * 
 * Open hashing data structure
 * 
 * @author CSCI 230: Data Structures and Algorithms 
 * 
 * Thomas Marshall
 * 
 * I certify this is my work
 * 
 * Fall 2018 - Homework 7
 *
 */
public class OpenHashing extends HashTable { 
    
    /* private instance variables */
    private ArrayList<String>[] hash_table;
    
    /**
     * Constructor
     */
    public OpenHashing( int hash_function ) {
        
        this.hash_function = hash_function;
        
    } // end constructor
    
    /**
     * Initialize the hash table
     * 
     * The number of elements in the hash table (m) is equal M.
     * 
     */
    @SuppressWarnings("unchecked")
    public void initialize() {
        
        hash_table = (ArrayList<String>[]) new ArrayList[ HashTable.M ];
        
        for ( int i=0; i<hash_table.length; i++ ) {
            
            hash_table[i] = new ArrayList<String>();
            
        }
        
    } // end initialize() method
    
    
    /**
     * Search for word in the hash table
     * 
     * Exceptions: If the key does not exist in the hash table, the throw
     *             a HashTableKeyException
     * 
     * return: The number of list probes needed to find the word in a list at 
     *         at the computed key location in the hash table, e.g. One if the 
     *         word is the first element in the list, n if the word is the very 
     *         last element in the list (where n is the number of elements in the 
     *         list at a the computed key location in the hash_table).
     * 
     * @param word
     * @return
     */
    public int search( String word ) throws HashTableKeyException {
        
        int probes = 0;
        
        /* ----------------------------------
         * TODO: Put your solution here
         * ----------------------------------
         */
        
        // Boolean to exit early and defining the location of the item in the hash
        boolean found = false;
        int place = calcHash(word);
        
        // Check for its location
        while (probes < hash_table[place].size() && !(found)) {
            if (word.compareTo(hash_table[place].get(probes++)) == 0) {
                found = true;
            }
        }
        
        // Throw item not found exception if necessary
        if (!(found)) {
            throw (new HashTableKeyException(word));
        }
        
        // Returns how many steps down it was
        return probes;
    } // end search() method
    
    /**
     * Insert word into hash table
     * 
     * Exceptions: Duplicate words are not allowed, e.g., if "dog" already exists
     *             in the hash table, then another "dog" word cannot be inserted.
     *             For a duplicate word insert operation throw a HashTableKeyException.
     * 
     * @param word
     */
    public void insert( String word ) throws HashTableKeyException {
        
        /* ----------------------------------
         * TODO: Put your solution here
         * ----------------------------------
         */
        
        // Determines the items location
        int place = calcHash(word);
        
        // Short cut and index exception prevention for the start
        if (hash_table[place].size() == 0) {
            hash_table[place].add(word);
        }
        // Finds how deep the item belongs
        else {
            int i = 0;
            while (i < hash_table[place].size()) {
                // Prevents duplicates
                if (hash_table[place].get(i).compareTo(word) == 0) {
                    throw (new HashTableKeyException(word));
                }
                ++i;
            }
            // Adds the new word
            hash_table[place].add(word);
        }
    } // end insert() method
    
    /**
     * Delete a word from the hash table
     * 
     * Exceptions: if they word does not exist in the hash table, then throw
     *             a HashTableKeyException
     * 
     * return: The number of list probes needed to delete the word in the list 
     *         located at the key location in the hash table, e.g. One if the 
     *         word is the first element in the list, n if the word is the very 
     *         last element in the list (where n is the number of elements in the 
     *         list at a the computed key location in the hash_table).
     * 
     * @param key
     * @return
     */
    public int delete ( String word ) throws HashTableKeyException {
        
        int probes = 0;
        
        /* ----------------------------------
         * TODO: Put your solution here
         * ----------------------------------
         */
        
        // Search takes care of the exceptions
        probes = search(word);
        // Finds its location and removes it
        int place = calcHash(word);
        hash_table[place].remove(probes - 1);
        // Returns how deep the word was
        return probes;
    } // end delete() method
    
    /**
     * See page 271 in supplemental course textbook (chapter is provided as PDF 
     * on OAKS in content section).
     * 
     * Also, refer to your lecture notes.
     * 
     * @return
     */
    public double loadFactor() {
        
        /* ----------------------------------
         * TODO: Put your solution here
         * ----------------------------------
         */
        
        // Calculates and returns alpha equls n / m
        int n = hash_table.length;
        double a = n / M;
        
        return a;     
    } // end loadFactor() method
    
    
    /**
     * See equation (7.4) on page 271 in supplemental course textbook (chapter 
     * is provided as PDF on OAKS in content section).
     * 
     * @return
     */
    public double successfulSearches() {
        
        /* ----------------------------------
         * TODO: Put your solution here
         * ----------------------------------
         */
        
        // Calculates the successful search formula and returns it
        double a = loadFactor();
        double S = 1.0 + (a / 2);
        
        return S;
                
    } // end successfulSearches() method
    
    /**
     * See equation (7.4) on page 271 in supplemental course textbook (chapter 
     * is provided as PDF on OAKS in content section).
     * 
     * @return
     */
    public double unsuccessfulSearches() {
        
        /* ----------------------------------
         * TODO: Put your solution here
         * ----------------------------------
         */
        
        // An open hash unsuccessful search is simply alpha. Returns aloha
        return loadFactor();
                
    } // end unsuccessfulSearches() method
        
} // end OpenHashing class definition
