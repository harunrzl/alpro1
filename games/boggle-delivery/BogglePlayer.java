//package cs235.boggle;

import java.util.Set;
import java.util.SortedSet;
import java.util.List;

public interface BogglePlayer
{
	/**
	* Loads the dictionary into a data structure for later use. 
	* 
	* @param fileName A string containing the dictionary to be opened.
	* @throws IllegalArgumentException if fileName is null
	* @throws IllegalArgumentException if fileName cannot be opened.
	*/
	void loadDictionary(String fileName);
	
	/**
	* Stores the incoming array of Strings in a fashion that will
	*      make it convenient to find words.
	* 
	* @param letterArray Each string in this array corresponds to 
	*      a die on the Boggle board. The die are in order left to 
	*      right, top to bottom. The size of letterArray = Row X Col.
	*      Note that the Strings inside may be longer than one 
	*      character. Also note that the board might not be 4x4.
	* @throws IllegalArgumentException if letterArray is null, or is 
	*      not square (i.e. it's the square-root of the length is not 
	*      a whole number).
	*/
	void setBoard(String[] letterArray);
	
	/**
	* Retrieves all the words in the Boggle board that appear in the 
	*       dictionary.
	* 
	* @param minimumWordLength The minimum allowed length for 
	*	strings that will be stated as being on the board.
	* @return java.util.SortedSet which contains all the words found 
	*	from the boggle board that appear in the dictionary.
	* @throws IllegalArgumentException if minimumWordLength < 1
        * @throws IllegalStateException if loadDictionary has not been called.
	*/
	SortedSet getAllValidWords(int minimumWordLength);
	
	/**
	* Determines if the given word is in the dictionary.
	* 
	* @param wordToCheck The word to validate
	* @return true if wordToCheck appears in dictionary, false otherwise.
	* @throws IllegalArgumentException if wordToCheck is null.
        * @throws IllegalStateException if loadDictionary has not been called.
	*/
	boolean isValidWord(String wordToCheck);
	
	/**
	* Determines if there is at least one word in the dictionary with the 
	* given prefix.
	* 
	* @param prefixToCheck The prefix to validate
	* @return true if prefixToCheck appears in dictionary, false otherwise.
	* @throws IllegalArgumentException if prefixToCheck is null.
	* @throws IllegalStateException if loadDictionary has not been called.
        */
	boolean isValidPrefix(String prefixToCheck);
		
	/**
	* Determines if the given word is in on the Boggle board. If so, 
	*	it returns the path that makes up the word.
	* 
	* @param wordToCheck The word to validate
	* @return java.util.List containing java.lang.Integer objects with 
	*	the path that makes up the word on the Boggle board. If word
	*	is not on the boggle board, return null.
	* @throws IllegalArgumentException if wordToCheck is null.
        * @throws IllegalStateException if loadDictionary has not been called.
	*/
	List isOnBoard(String wordToCheck);
	
	/**
	* An optional method that gives a user-defined boggle board to the GUI.
	* 
	* @return a String array in the same form as the input to setBoard().
	*/
	String[] getCustomBoard();
				
}
	
