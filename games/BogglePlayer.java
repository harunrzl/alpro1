import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import java.io.*;

/**
   This program implements code that allows the user to play a game of
    boggle, the famous fun fabulous board game.  However to display
    the results of the BogglePlayer class and game, Boggle GUI must be
    implemented as a graphical interface and to interpret the results
    of BoglePlayer.  The results are stored in useful data structures,
    such to make for easy retrieval and implementation.  The game
    works for any sized board and for any minimum word length.  The
    computer then gets all valid words from the board and displays
    them, thus thoroughly trouncing the human opponent.  The program
    checks the input the user puts in, and checks the board and all
    the words in enable1.txt (the dictionary) and gives the user
    points for that word.  In this fashion a Boggle Game is played.

 *
 * BogglePlayer implements methods that will be used by {@link
 * BoggleGUI} to play an interactive game of Boggle.  The code will
 * work for boards of any size, and for any minimum sized word length.
 * @author Michael Green
 * @author Paul Kube
 * @author Eric Chown
 * @author Laura Toma
 *
 * Copyright (C) 2002 Michael Green <mtgreen@cs.ucsd.edu>
 * Copyright (C) 2002 Paul Kube <kube@cs.ucsd.edu>
 * Copyright (C) 2003/5 Eric Chown <echown@bowdoin.edu>
 * Copyright (C) 2004 Pat Costello
 */



public class BogglePlayer {
    
    
    /**
     * Builds a data structure for use as a dictionary.  Given a Set of
     * words to use in a lexicon for the game, this method will
     * extract the words, sort them, and put them into a suitable
     * data structure such that they can be quickly searched.
     * @param wordlist the collection of words to use in the lexicon
     * (potentially unordered)
     * @return void
     * @see    BoggleGUI
     */
    
    //Constants - only used if call doesn't give values for these things
    private static final int MIN_WORD_DEFAULT = 4;
    private static final int ROWS_DEFAULT = 4;
    private static final int COLS_DEFAULT = 4;
    private static final int TILES_DEFAULT = (ROWS_DEFAULT*COLS_DEFAULT);
    
  
    //vars
    private Vector<String> lexicon; //Stores lexicon
    private String board[][];  //Stores board
    
    private int rows, cols;   //Size of board
    private int tiles; //Total number of tile on board
    private int minWordLength; //Minimum size for a valid word
    
    
    //Constructor for default BogglePlayer with default values
public static void main(String[] args) {
	BogglePlayer bp = new BogglePlayer(4, 4, 4);
}
    public BogglePlayer() {
	minWordLength = MIN_WORD_DEFAULT;
	rows = ROWS_DEFAULT;
	cols = COLS_DEFAULT;
	tiles = TILES_DEFAULT;
	
	lexicon = new Vector<String>();
	board = new String[rows][cols];
    }
  
  
    /*Constructor for when user or BoggleGUI wants to state minimum
     * word-size and board size.
     */
    public BogglePlayer(int minLength, int r, int c)  {
	minWordLength = minLength;
	rows = r;
	cols = c;
	tiles = (rows*cols);
	
	lexicon = new Vector<String>();
	board = new String[rows][cols];
    }
    
  
    /* Method to get lexicon from file; lexicon is sorted and only has
     * valid length words.
     * @param wordList   A list of legal words in Boggle
     * @see BoggleGUI
     */
  public void buildLexicon(Set wordList) {
      Iterator i;
      String newWord;
      i = wordList.iterator();
      
      //read in each word to newWord until there are no words left
      while(i.hasNext() == true) { 
	  newWord = (String)i.next();
	  
	  //only add word to lexicon if it is long enough
	  if (newWord.length() >= minWordLength)
	      lexicon.addElement(newWord);
      }   
      
      quickSort(lexicon, 0, lexicon.size() - 1);
  }
    
  
    /**
     * Method to create the board given an array of letters.  This
     * method is passed an array of strings.  Each element of the
     * array corresponds to one cube in the boggle board.  This method
     * converts the array into a suitable data structure to enable
     * efficient searching.
     * @param letterArray   the letters that make up the board
     * @return void
     * @see    BoggleGUI
     */
    public void setBoard(String[] letterArray) {
	for(int i = 0; i < rows; i++)
	    for(int j = 0; j < cols; j++)
		board[i][j] = letterArray[i*rows +j];
    }
    
  
    /**
     * Method to retrieve all legal words on the board.  This method
     * returns all of the words in the board that are in the lexicon
     * and are at least the minimum length size.
     * @param minimumWordLength  the minimum size of a legal word
     * @return a Vector of strings, each representing a word on the board
     * @see BoggleGUI
     */
    public Vector<String> getAllValidWords(int minimumWordLength) { 
	return null;
    }
    
    
    /**
     * This method checks if a word is in the lexicon specified by buildLexicon.
     * @param wordToCheck the word to be checked
     * @return true when the word is in the lexicon, false otherwise
     * @see BoggleGUI
     */
    public boolean isInLexicon(String wordToCheck) {
	boolean result;
	result = BinarySearch(lexicon, 0, lexicon.size() - 1, wordToCheck);  
	return result;
    }
  
  
    /**
     * Method to check whether or not a word in on the board.  This
     * method checks if the given word can be found on the board using
     * a legal connected path (i.e. consecutive letters are adjacent,
     * no cube on the board is used twice)
     * @param wordToCheck the word to be checked
     * @return a Vector of the locations of the letters.  If not found, returns null
     * @see BoggleGUI
    */
  public Vector isOnBoard(String wordToCheck) {
      return null;
  }
    
    
    /**
     * This method creates a fixed board.
     * It is mainly useful in debugging such
     * that specific problems can be addressed easily (e.g. "Qu" boards).
     * @param none
     * @return a string array representing the board
     * @see BoggleGUI
     */
    public String[] getCustomBoard(){
	String[] customboard={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P"};      
	return customboard;
    }
    
    
    /**
     * This method sorts a vector of strings using Quicksort.
     * @param data  the Vector to be sorted
     *        low   the leftmost boundary of the Vector to be sorted
     *        high  the rightmost boundary of the Vector to be sorted
     * @return void
     */
    public void quickSort(Vector<String> dat, int low, int high) {
        // Base case
        if (low >= high) return;
        // partition the Vector into two halves
        int pivot = partition(dat, low, high);
        // recursive calls to sort the two parts
        quickSort(dat, low, pivot - 1);
        quickSort(dat, pivot + 1, high);
    } 


    /**
     * Quicksort helper method that partitions a Vector into two
     *  halves based on a "pivot."  All the elements less than the
     *  pivot are placed in the left half, all the rest are in the
     *  right half.
     * @param data  The Vector being sorted
     *       left  The leftmost boundary
     *       right The rightmost boundary
     * @return the location of the pivot in the Vector
     */
    public int partition(Vector<String> data, int left, int right){
        // left and right represent the boundaries of elements we
        // haven't partitioned Our goal is to get to all of them
        // moving partitioned elements to either side as necessary.
        while (true) {
            // move right "pointer" toward left
            while (left < right && 
		   ((String)data.elementAt(left)).compareTo(((String)data.elementAt(right))) < 0) {
                right--;
            }
	    
            if (left < right) swap(data,left++,right);
            else return left;
            // move left pointer toward right
            while (left < right && ((String)data.elementAt(left)).compareTo(((String)data.elementAt(right))) < 0){
                left++;
            } 
            if (left < right) swap(data,left,right--);
            else return right;
        }
    }
    

    
    /**
     * This method swaps two elements in a Vector (regardless of their type).
     * @param data The vector
     *        i    The position of one element
     *        j    The position of the other element
     * @return void
     */
    public void swap(Vector<String> data, int i, int j) {
        String temp;
        temp = (String)data.elementAt(i);
        data.setElementAt(data.elementAt(j), i);
        data.setElementAt(temp, j);
    }
    

    
    /**
     * This method performs a recursive binary search on a Vector.  
     * It returns true if the search item is in the Vector and false otherwise.
     * @param s The Vector to be searched
     *        front The leftmost boundary of the Vector that we're still interested in
     *        back  The rightmost boundary
     *        item  The thing we're searching for
     * @return true when the item is in the vector, false otherwise
    */
    public boolean BinarySearch(Vector<String> s, int front, int back, String item){
        // Check the middle spot
        int middle = (front + back) / 2;
        // base cases
        if(((String)s.elementAt(middle)).compareTo(item)==0)
            return true;
        if(front >= back)
            return false;
        // More searching necessary
        if(((String)s.elementAt(middle)).compareTo(item) > 0)
            return BinarySearch(s, front, middle-1, item);
        return BinarySearch(s, middle + 1, back, item);
    }


}
