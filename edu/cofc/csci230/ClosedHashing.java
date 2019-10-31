package edu.cofc.csci230;

/**
 * 
 * Closed hashing data structure using linear probing.
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
public class ClosedHashing extends HashTable { 
    
    /* private instance variables */
    private String[] hash_table;
    private int[] collision_table;
    
    /**
     * Constructor
     */
    public ClosedHashing( int hash_function ) {
        
        this.hash_function = hash_function;
        
    } // end constructor
    
    /**
     * Initialize the hash table
     * 
     * The number of elements in the hash table is equal
     * to 2 x the number of words (may or may not be unique) 
     * in the word list.
     * 
     */
    public void initialize() {
        
        hash_table = new String[ 2*words.size() ];
        collision_table = new int[ 2*words.size() ];
        
        for ( int i=0; i<hash_table.length; i++ ) {
            
            hash_table[i] = null;
            
        }
        
    } // end initialize() method
    
    
    
    /**
     * Search for word in the hash table.
     * 
     * The collision_table is used to indicate a word/key collision key has 
     * occurred. In the collision_table the number at the computed key index 
     * position stores the total number of collisions. For example, 
     * collision_table[16] = 3 means three collisions have occurred at key 
     * index position 16 in the hash_table.
     * 
     * Exceptions: If the word does not exist in the hash table, then throw 
     *             a HashTableKeyException
     * 
     * return: The number of linear probes needed to find the word in the 
     *         hash table, e.g. Zero if no probing, n if probed n times to
     *         find the word location.
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
        
        // Provides an early loop exit and finds the word's location
        boolean found = false;
        int place = calcHash(word);
        
        // Determines how far over the search needs to go to find the true location of the word
        while (hash_table[place] != null && !(found)) {
            if (word.compareTo(hash_table[place]) == 0) {
                found = true;
            }
            else {
                place++;
                probes++;
            }
        }
        
        // Throws an exception if the word was never discovered
        if (!(found)) {
            throw (new HashTableKeyException(word));
        }
        
        // Returns how far over the word was from its alleged location
        return probes;
    } // end search() method
    
    /**
     * Insert word into hash table
     * 
     * Exceptions: Duplicate words are not allowed, e.g., if "dog" already exists
     *         in the hash table, then another "dog" word cannot be inserted.
     *         For a duplicate word insert operation throw a HashTableKeyException.
     * 
     * @param word
     */
    public void insert( String word ) throws HashTableKeyException {
        
        /* ----------------------------------
         * TODO: Put your solution here
         * ----------------------------------
         */
        
        // Original location of the word and an iteration starter
        int place = calcHash(word);
        int i = 0;
        
        // Loops until it finds the next empty slot
        while (hash_table[place] != null) {
            // Exception for duplicates
            if (hash_table[place].compareTo(word) == 0) {
                throw (new HashTableKeyException(word));
            }
            
            ++i;
            ++place;
        }
        // Enters the new word into the list
        hash_table[place] = word;
        // Increases the collision each time it needs to
        collision_table[place - i] = i;
        
    } // end insert() method
    
    /**
     * Delete a word from the hash table. 
     * 
     * The collision_table is used to indicate a word/key collision key has 
     * occurred. In the collision_table the number at the computed key index 
     * position stores the total number of collisions. For example, 
     * collision_table[16] = 3 means three collisions have occurred at key 
     * index position 16 in the hash_table. 
     * 
     * Every successful word deletion should decrement the corresponding 
     * collision_table value by one. For example, if the key index position 
     * is 3 and the word has been successfully deleted then 
     * collision_table[16] = collision_table[16] - 1; Note: the number of 
     * collisions will never be less than zero. If it is, you have a bug
     * in your code :)
     * 
     * Exceptions: if they word does not exist in the hash table then throw
     *             a HashTableKeyException
     * 
     * return: The number of linear probes needed to delete the word in the 
     *         hash table, e.g. Zero if no probing, n if probed n times to
     *         find the word location.
     * 
     * @param word
     * @return
     */
    public int delete ( String word ) throws HashTableKeyException {
        
        int probes = 0;
        
        /* ----------------------------------
         * TODO: Put your solution here
         * ----------------------------------
         */
        
        // Search handles exceptions. Finds alleged location and real location. Removes the word and decreases the collision table by one
        int place = calcHash(word);
        probes = search(word);
        hash_table[probes] = null;
        collision_table[place]--;
          
        return probes - 1;
        
    } // end delete() method
    
    /**
     * See page 271 in supplemental course textbook (chapter is provided as PDF 
     * on OAKS in content section).
     * 
     * Also, refer to your lecture notes. Note, for closed hashing, m is 
     * the number of locations in the hash table.
     * 
     * @return
     */
    public double loadFactor() {
        
        /* ----------------------------------
         * TODO: Put your solution here
         * ----------------------------------
         */
        
        // Calculates alpha
        int n = hash_table.length;
        double a = n / M;
        
        // Returns alpha
        return a;
                
    } // end loadFactor() method
    
    
    /**
     * See equation (7.5) on page 273 in supplemental course textbook (chapter 
     * is provided as PDF on OAKS in content section).
     * 
     * @return
     */
    public double successfulSearches() {
        
        /* ----------------------------------
         * TODO: Put your solution here
         * ----------------------------------
         */
        
        // Calculatees the successful search using alpha
        double a = loadFactor();
        double S = .5 * (1 + (1 / (1 - a)));
        
        // Returns it
        return S;
                
    } // end successfulSearches() method
    
    /**
     * See equation (7.5) on page 273 in supplemental course textbook (chapter 
     * is provided as PDF on OAKS in content section).
     * 
     * @return
     */
    public double unsuccessfulSearches() {
        
        /* ----------------------------------
         * TODO: Put your solution here
         * ----------------------------------
         */
        
        // Calculates and returns the unsuccessful search
        double a = loadFactor();
        double U = .5 * (1 + (1 / (Math.pow((1 - a), 2))));
        
        return U;
                
    } // end unsuccessfulSearches() method
    
} // end ClosedHashing class definition