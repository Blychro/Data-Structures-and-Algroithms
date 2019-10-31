package edu.cofc.csci230;

/**
 * 
 * Hash Simulator (main method)
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
public class HashSimulator { 
    
    /**
     * 
     * @param args
     */
    public static void main( String[] args ) {
        
        HashTable hashDS = new ClosedHashing( HashTable.HASH_FUNC1 );
        System.out.printf("\n%s ----------------------------------\n", "Closed Hashing: FUNC1" );
        
        if ( hashDS.loadWords() ) {
            
            ((ClosedHashing)hashDS).initialize();
            
            System.out.printf( "Number of non-unique words in file = %d\n", hashDS.getWords().size() );
            
            /* ------------------------------------------------
             * TODO:
             * ------------------------------------------------
             * 1) Insert each word into hash data structure
             * 2) Calculate load factor value and print to 
             *    console (using System.printf or System.println)
             * 3) Calculate successful searches value and print to 
             *    console (using System.printf or System.println)
             * 4) Calculate unsuccessful searches value and print to 
             *    console (using System.printf or System.println)
             * 5) For each word in words list, search in the hashDS, 
             *    and print mean probe value to console (using 
             *    System.printf or System.println)
             * 6) For a word (you choose) that does not exist in hashDS, 
             *    try to search for it in the hashDS and then print the probe 
             *    value to console (using System.printf or System.println)
             * 7) For each word in words list, delete word in the hashDS, 
             *    and print mean probe value to console (using 
             *    System.printf or System.println)
             * 8) For a word (you choose) that does not exist in hashDS, 
             *    try to delete in the hashDS and then print the probe value to 
             *    console (using System.printf or System.println)
             * 
             */
            
            List<String> words = hashDS.getWords();
            
            for (int i = 0; i < words.size(); ++i) {
                try {
                    ((ClosedHashing)hashDS).insert(words.get(i));
                } catch (HashTableKeyException e) {
                    System.out.println("The word " + words.get(i) + " already exists within the hash");
                }
            }
            
            System.out.println("The load factor is " + ((ClosedHashing)hashDS).loadFactor());
            System.out.println("The successful search value is " + ((ClosedHashing)hashDS).successfulSearches());
            System.out.println("The unsuccessful search value is " + ((ClosedHashing)hashDS).unsuccessfulSearches());
            
            for (int i = 0; i < words.size(); ++i) {
                try {
                    int found = ((ClosedHashing)hashDS).search(words.get(i));
                    System.out.println("The probe value of " + words.get(i) + " is " + found);
                } catch (HashTableKeyException e) {
                    System.out.println("The word " + words.get(i) + " was not found in the hash");
                }
            }
            try {
                int missing = ((ClosedHashing)hashDS).search("narwhal");
                System.out.println("The word narwhal was missing. Its probe is " + missing);
            } catch (HashTableKeyException e) {
                System.out.println("The word narwhal did not exist.");
            }
            
            for (int i = 0; i < words.size(); ++i) {
                try {
                    int found = ((ClosedHashing)hashDS).delete(words.get(i));
                    System.out.println(words.get(i) + " has been deleted. Its probe was " + found);
                } catch (HashTableKeyException e) {
                    System.out.println("The word " + words.get(i) + " was not found within the hash");
                }
            }
            try {
                int missing = ((ClosedHashing)hashDS).delete("aqwsedrfgyhu");
                System.out.println("The word aqwsedrfgyhu did not exist. Its probe is " + missing);
            } catch (HashTableKeyException e) {
                System.out.println("The word aqwsedrfgyhu did not exist.");
            }
        } else {
            
            System.out.println("Failed to load words from text file" );
        }
        
        // ------------------------------------------------
        // Repeat for second hash function
        
        System.out.printf("\n%s ----------------------------------\n", "Closed Hashing: FUNC2" );
        hashDS = new ClosedHashing( HashTable.HASH_FUNC2 );
        
        if ( hashDS.loadWords() ) {
            
            ((ClosedHashing)hashDS).initialize();
            
            System.out.printf( "Number of non-unique words in file = %d\n", hashDS.getWords().size() );
            
            /* ------------------------------------------------
             * TODO:
             * ------------------------------------------------
             * 1) Insert each word into hash data structure
             * 2) Calculate load factor value and print to 
             *    console (using System.printf or System.println)
             * 3) Calculate successful searches value and print to 
             *    console (using System.printf or System.println)
             * 4) Calculate unsuccessful searches value and print to 
             *    console (using System.printf or System.println)
             * 5) For each word in words list, search in the hashDS, 
             *    and print mean probe value to console (using 
             *    System.printf or System.println)
             * 6) For a word (you choose) that does not exist in hashDS, 
             *    try to search for it in the hashDS and then print the probe 
             *    value to console (using System.printf or System.println)
             * 7) For each word in words list, delete word in the hashDS, 
             *    and print mean probe value to console (using 
             *    System.printf or System.println)
             * 8) For a word (you choose) that does not exist in hashDS, 
             *    try to delete in the hashDS and then print the probe value to 
             *    console (using System.printf or System.println)
             * 
             */
            
            List<String> words = hashDS.getWords();
            
            for (int i = 0; i < words.size(); ++i) {
                try {
                    ((ClosedHashing)hashDS).insert(words.get(i));
                } catch (HashTableKeyException e) {
                    System.out.println("The word " + words.get(i) + " already exists within the hash");
                }
            }
            
            System.out.println("The load factor is " + ((ClosedHashing)hashDS).loadFactor());
            System.out.println("The successful search value is " + ((ClosedHashing)hashDS).successfulSearches());
            System.out.println("The unsuccessful search value is " + ((ClosedHashing)hashDS).unsuccessfulSearches());
            
            for (int i = 0; i < words.size(); ++i) {
                try {
                    int found = ((ClosedHashing)hashDS).search(words.get(i));
                    System.out.println("The probe value of " + words.get(i) + " is " + found);
                } catch (HashTableKeyException e) {
                    System.out.println("The word " + words.get(i) + " was not found in the hash");
                }
            }
            try {
                int missing = ((ClosedHashing)hashDS).search("narwhal");
                System.out.println("The word narwhal was missing. Its probe is " + missing);
            } catch (HashTableKeyException e) {
                System.out.println("The word narwhal did not exist.");
            }
            
            for (int i = 0; i < words.size(); ++i) {
                try {
                    int found = ((ClosedHashing)hashDS).delete(words.get(i));
                    System.out.println(words.get(i) + " has been deleted. Its probe was " + found);
                } catch (HashTableKeyException e) {
                    System.out.println("The word " + words.get(i) + " was not found within the hash");
                }
            }
            try {
                int missing = ((ClosedHashing)hashDS).delete("aqwsedrfgyhu");
                System.out.println("The word aqwsedrfgyhu did not exist. Its probe is " + missing);
            } catch (HashTableKeyException e) {
                System.out.println("The word aqwsedrfgyhu did not exist.");
            }
        } else {
            
            System.out.println("Failed to load words from text file" );
        }

        hashDS = new OpenHashing( HashTable.HASH_FUNC1 );
        System.out.printf("\n%s ----------------------------------\n", "Open Hashing: FUNC1" );
        
        if ( hashDS.loadWords() ) {
            
            ((OpenHashing)hashDS).initialize();
            
            System.out.printf( "Number of non-unique words in file = %d\n", hashDS.getWords().size() );
            
            /* ------------------------------------------------
             * TODO:
             * ------------------------------------------------
             * 1) Insert each word into hash data structure
             * 2) Calculate load factor value and print to 
             *    console (using System.printf or System.println)
             * 3) Calculate successful searches value and print to 
             *    console (using System.printf or System.println)
             * 4) Calculate unsuccessful searches value and print to 
             *    console (using System.printf or System.println)
             * 5) For each word in words list, search in the hashDS, 
             *    and print mean probe value to console (using 
             *    System.printf or System.println)
             * 6) For a word (you choose) that does not exist in hashDS, 
             *    try to search for it in the hashDS and then print the probe 
             *    value to console (using System.printf or System.println)
             * 7) For each word in words list, delete word in the hashDS, 
             *    and print mean probe value to console (using 
             *    System.printf or System.println)
             * 8) For a word (you choose) that does not exist in hashDS, 
             *    try to delete in the hashDS and then print the probe value to 
             *    console (using System.printf or System.println)
             * 
             */
            
            List<String> words = hashDS.getWords();
            
            for (int i = 0; i < words.size(); ++i) {
                try {
                    ((OpenHashing)hashDS).insert(words.get(i));
                } catch (HashTableKeyException e) {
                    System.out.println("The word " + words.get(i) + " already exists within the hash");
                }
            }
            
            System.out.println("The load factor is " + ((OpenHashing)hashDS).loadFactor());
            System.out.println("The successful search value is " + ((OpenHashing)hashDS).successfulSearches());
            System.out.println("The unsuccessful search value is " + ((OpenHashing)hashDS).unsuccessfulSearches());
            
            for (int i = 0; i < words.size(); ++i) {
                try {
                    int found = ((OpenHashing)hashDS).search(words.get(i));
                    System.out.println("The probe value of " + words.get(i) + " is " + found);
                } catch (HashTableKeyException e) {
                    System.out.println("The word " + words.get(i) + " was not found in the hash");
                }
            }
            try {
                int missing = ((OpenHashing)hashDS).search("narwhal");
                System.out.println("The word narwhal was missing. Its probe is " + missing);
            } catch (HashTableKeyException e) {
                System.out.println("The word narwhal did not exist.");
            }
            
            for (int i = 0; i < words.size(); ++i) {
                try {
                    int found = ((OpenHashing)hashDS).delete(words.get(i));
                    System.out.println(words.get(i) + " has been deleted. Its probe was " + found);
                } catch (HashTableKeyException e) {
                    System.out.println("The word " + words.get(i) + " was not found within the hash");
                }
            }
            try {
                int missing = ((OpenHashing)hashDS).delete("aqwsedrfgyhu");
                System.out.println("The word aqwsedrfgyhu did not exist. Its probe is " + missing);
            } catch (HashTableKeyException e) {
                System.out.println("The word aqwsedrfgyhu did not exist.");
            }
        } else {
            
            System.out.println("Failed to load words from text file" );
            
        }
        
        // ------------------------------------------------
        // Repeat for second hash function
        
        hashDS = new OpenHashing( HashTable.HASH_FUNC2 );
        System.out.printf("\n%s ----------------------------------\n", "Open Hashing: FUNC2" );
        
        if ( hashDS.loadWords() ) {
            
            ((OpenHashing)hashDS).initialize();
            
            System.out.printf( "Number of non-unique words in file = %d\n", hashDS.getWords().size() );
            
            /* ------------------------------------------------
             * TODO:
             * ------------------------------------------------
             * 1) Insert each word into hash data structure
             * 2) Calculate load factor value and print to 
             *    console (using System.printf or System.println)
             * 3) Calculate successful searches value and print to 
             *    console (using System.printf or System.println)
             * 4) Calculate unsuccessful searches value and print to 
             *    console (using System.printf or System.println)
             * 5) For each word in words list, search in the hashDS, 
             *    and print mean probe value to console (using 
             *    System.printf or System.println)
             * 6) For a word (you choose) that does not exist in hashDS, 
             *    try to search for it in the hashDS and then print the probe 
             *    value to console (using System.printf or System.println)
             * 7) For each word in words list, delete word in the hashDS, 
             *    and print mean probe value to console (using 
             *    System.printf or System.println)
             * 8) For a word (you choose) that does not exist in hashDS, 
             *    try to delete in the hashDS and then print the probe value to 
             *    console (using System.printf or System.println)
             * 
             */
            
            List<String> words = hashDS.getWords();
            
            for (int i = 0; i < words.size(); ++i) {
                try {
                    ((OpenHashing)hashDS).insert(words.get(i));
                } catch (HashTableKeyException e) {
                    System.out.println("The word " + words.get(i) + " already exists within the hash");
                }
            }
            
            System.out.println("The load factor is " + ((OpenHashing)hashDS).loadFactor());
            System.out.println("The successful search value is " + ((OpenHashing)hashDS).successfulSearches());
            System.out.println("The unsuccessful search value is " + ((OpenHashing)hashDS).unsuccessfulSearches());
            
            for (int i = 0; i < words.size(); ++i) {
                try {
                    int found = ((OpenHashing)hashDS).search(words.get(i));
                    System.out.println("The probe value of " + words.get(i) + " is " + found);
                } catch (HashTableKeyException e) {
                    System.out.println("The word " + words.get(i) + " was not found in the hash");
                }
            }
            try {
                int missing = ((OpenHashing)hashDS).search("narwhal");
                System.out.println("The word narwhal was missing. Its probe is " + missing);
            } catch (HashTableKeyException e) {
                System.out.println("The word narwhal did not exist.");
            }
            
            for (int i = 0; i < words.size(); ++i) {
                try {
                    int found = ((OpenHashing)hashDS).delete(words.get(i));
                    System.out.println(words.get(i) + " has been deleted. Its probe was " + found);
                } catch (HashTableKeyException e) {
                    System.out.println("The word " + words.get(i) + " was not found within the hash");
                }
            }
            try {
                int missing = ((OpenHashing)hashDS).delete("aqwsedrfgyhu");
                System.out.println("The word aqwsedrfgyhu did not exist. Its probe is " + missing);
            } catch (HashTableKeyException e) {
                System.out.println("The word aqwsedrfgyhu did not exist.");
            }
        } else {
            
            System.out.println("Failed to load words from text file" );
            
        }
    } // end main() method
    
} // end Hash Simulator class definition