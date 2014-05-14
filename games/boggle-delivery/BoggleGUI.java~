

package cs235.boggle;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.Set;
import java.util.HashSet;
import java.util.Random;
import java.util.Collections;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**  A GUI for the game of Boggle.
 * 
 * BoggleGUI defines and implements methods to do these things:
 * 
 * 1.  Draw the boggle board
 * 2.  Manage the computer and human players' word lists
 * 3.  Update the scoreboard
 *
 * BoggleGUI requires class BogglePlayer, to define these instance methods:
 * <PRE>
 * public void loadDictionary(String fileName)
 * public void setBoard(String[] letterArray)
 * public SortedSet getAllValidWords(int minimumWordLength)
 * public boolean isInDictionary(String wordToCheck)
 * public java.util.List isOnBoard(String wordToCheck)
 * public String[] getCustomBoard()
 * </PRE>
 *
 * @author Michael Green
 * @author Paul Kube
 *
 * Copyright (C) 2002 Michael Green <mtgreen@cs.ucsd.edu>
 * Copyright (C) 2002 Paul Kube <kube@cs.ucsd.edu>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.  See
 * http://www.gnu.org/copyleft/gpl.html
 */
public class BoggleGUI extends JFrame 
{

    public static final String DEFAULTWORDLISTFILENAME = "dictionary.txt";  // default
    public static final int DEFAULTDICEROWS = 4;  // default
    public static final int DEFAULTDICECOLS = 4;  // default
    public static final int DEFAULTMINIMUMWORDLENGTH = 4; // default

    private static String WORDLISTFILENAME = DEFAULTWORDLISTFILENAME;
    private static int DICEROWS = DEFAULTDICEROWS;
    private static int DICECOLS = DEFAULTDICECOLS;
    private static int MINIMUMWORDLENGTH = DEFAULTMINIMUMWORDLENGTH;

	private BogglePlayer computerPlayer;
	private BoggleBoard theBoard;
	private ScoreArea humanArea;
	private ScoreArea computerArea;
	private WordEntryField wordEntryField;
        
	public BoggleGUI()  
	{
		super( "Welcome to CS235 BOGGLE!");
    
		initialize();
		// Intialize graphics panels
		initPanels();
		// Establish menu bar options and listeners for them
		setUpMenuBar();
		// WindowClosing listener:  for JDK 1.2 compatibility
		addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e) 
			{
				System.exit(0);
			}});
		// Trust contained components to set their sizes; top level frame packs
		pack();
	}
	
	public void initialize()
	{
		// create a BogglePlayer computer player to use
		computerPlayer = BoggleFactory.createBogglePlayer();
		// Read word list from file and initialize computerPlayer's Dictionary
		initDictionary();
	}

	/**
	 * Get ready for a new game, given a letter list specification of
	 * the Boggle board.
	 */
	public void newGame(String[] letterList)  
	{
		initialize();
		// Tell theBoard about the board layout
		theBoard.setBoard(letterList);
		// Tell computerPlayer about the board layout
		computerPlayer.setBoard(letterList);
		// Prepare score areas
		humanArea.setReady();
		computerArea.setReady();
		// Clear board highlighting
		theBoard.unHighlightAllDice();
		// Allow edits to text area and set focus
		wordEntryField.setReady();
		// redraw the whole thing so it looks as nice as can be
		repaint();
	}
        
	/**
	 * Consider a word entered by the human player.
	 */
	public void checkAndAddWordHuman(String wordToCheck)   
	{

		// clear any board highlighting
		theBoard.unHighlightAllDice();

		// if empty word, the computer gets to take its turn
		if(wordToCheck.length() == 0)      
		{
			wordEntryField.setUnready();
			computerPlay();
			return;
		}

		// check that word is:
		// (1) at least minimum length, (2) on the board, (3) in the dictionary,
		// (4) not already entered by the player
		if(wordToCheck.length() <  MINIMUMWORDLENGTH)      
		{
			chideUser(wordToCheck, "Less Than " + MINIMUMWORDLENGTH + " Letters");
			return;
		}

		java.util.List letterLocations;
    
		// letterLocations = theBoard.getLocationsForWord(wordToCheck);
		// String wordOnBoard = theBoard.getWordFromLocations(letterLocations);
		//    if(!wordToCheck.equalsIgnoreCase(wordOnBoard)) { // double check

		// we just trust the computer player to check this
		letterLocations = computerPlayer.isOnBoard(wordToCheck);
		if(letterLocations == null) 
		{ 
			chideUser(wordToCheck, "Not On Board");
			return;
		}

		if(!computerPlayer.isValidWord(wordToCheck))          
		{
			chideUser(wordToCheck, "Not In Dictionary");
			return;
		}

		if(humanArea.containsWord(wordToCheck)) 
		{
			chideUser(wordToCheck, "Duplicate Word");
			return;
		}

		// OK, this word passed our rigorous suite of tests.  Add it
		humanArea.addWord(wordToCheck);
		// Highlight locations on board
		theBoard.highlightDice(letterLocations);
		wordEntryField.clear();      //clear the wordEntryField text
	}

	public void chideUser(String attemptedWord, String complaint) 
	{
		JOptionPane.showMessageDialog(this,
			attemptedWord + ": " + complaint + "!!!",
			complaint,
			JOptionPane.ERROR_MESSAGE);
		wordEntryField.clear();      //clear the wordEntryField text
	}

	/**
	 * Let the computer player take its turn.
	 */
	public void computerPlay() 
	{
		computerArea.setName("Thinking!");
		computerArea.paintImmediately(computerArea.getVisibleRect());
		SortedSet allWords = computerPlayer.getAllValidWords(MINIMUMWORDLENGTH);
		Iterator allWordsIterator = allWords.iterator();
		computerArea.setName("Computer");
		while(allWordsIterator.hasNext())     
		{
			String newWord = (String) allWordsIterator.next();
			java.util.List wordPath = computerPlayer.isOnBoard(newWord);
			// Add word to appropriate score area & highlight appropriate dice
			computerArea.addWord(newWord);
			theBoard.highlightDice(wordPath);
			// pause for a tenth of a second
			// try {Thread.sleep(100); }   catch (Exception e) {}
		}
		theBoard.unHighlightAllDice(); // leave board unhighlighted when done
	} 


	/**
	 * Read word list from file with name WORDLISTFILENAME, and pass a Set
	 * containing those words to the computer player to intialize its dictionary.
	 */
	private void initDictionary()  
	{ 
		computerPlayer.loadDictionary(WORDLISTFILENAME);
	}

	private void initPanels()  
	{
		Container contentPane = getContentPane();
		humanArea = new ScoreArea("Me");
		computerArea = new ScoreArea("Computer");
		theBoard = new BoggleBoard(DICEROWS,DICECOLS,computerPlayer);
		wordEntryField = new WordEntryField();
		contentPane.add(wordEntryField,BorderLayout.SOUTH);
		contentPane.add(humanArea, BorderLayout.WEST);
		contentPane.add(theBoard, BorderLayout.CENTER);
		contentPane.add(computerArea, BorderLayout.EAST);
	}
                

	private void setUpMenuBar()  
	{
		//Set Up Menu Bar
		JMenuBar menu = new JMenuBar();
                
		// Game Menu
		JMenu gameMenu = new JMenu("Game");
		gameMenu.setMnemonic('G');
		menu.add(gameMenu);

		JMenuItem newRandom = new JMenuItem("New Random");
		gameMenu.add(newRandom);
		newRandom.setMnemonic('N');
		newRandom.addActionListener(new ActionListener() 
		{
			public void actionPerformed( ActionEvent e) 
			{
				newGame(theBoard.getRandomBoard());
			}});

		JMenuItem customGame = new JMenuItem("New Custom");
		gameMenu.add(customGame);
		customGame.setMnemonic('C');
		customGame.addActionListener(new ActionListener() 
		{
			public void actionPerformed( ActionEvent e) 
			{
				newGame(computerPlayer.getCustomBoard());
			}});

		JMenuItem quitGame = new JMenuItem("Quit");
		gameMenu.add(quitGame);
		quitGame.setMnemonic('Q');
		quitGame.addActionListener( new ActionListener() 
		{
			public void actionPerformed( ActionEvent e) 
			{
				System.exit(0);
			}});

		// Help menu
		JMenu helpMenu = new JMenu("Help");
		menu.add(helpMenu);
		helpMenu.setMnemonic('H');
                
		JMenuItem aboutGame = new JMenuItem("About...");
		helpMenu.add(aboutGame);
		aboutGame.setMnemonic('A');
		aboutGame.addActionListener(new ActionListener() 
		{
			public void actionPerformed( ActionEvent e)  
			{
				JOptionPane.showMessageDialog(BoggleGUI.this,
					"It's ABOUT time you got to work", 
					"About Game",
					JOptionPane.PLAIN_MESSAGE);
			}});
		setJMenuBar(menu);
	}

	/**
	 * A class defining the visual appearance of a Boggle board, and
	 * defining some related methods.
	 */
	static class BoggleBoard extends JPanel 
	{
		public static final Color BACKGROUNDCOLOR = new Color(255, 219, 13);

	    private DiePanel[][] theDice;
		private List diceBag;  // used for drawing dice for a random game
		private int rows;
		private int cols;
		private BogglePlayer theComputer;

		BoggleBoard(int rows, int cols, BogglePlayer theComputer ) 
		{
			this.rows = rows; this.cols = cols;
			this.theComputer = theComputer;

			// create some dice to use when getting a random game
			initDiceBag();

			// create a JPanel with rowsXcols GridLayout to hold the DiePanels
			JPanel innerPanel = new JPanel();
			innerPanel.setLayout(new GridLayout(rows,cols,1,1));
			innerPanel.setBackground(BACKGROUNDCOLOR);
        
			// Create Individual DiePanels, and add them
			theDice = new DiePanel[rows][cols];
			for (int row = 0; row < rows; row++)      
			{
				for (int col = 0; col < cols; col++)      
				{
					theDice[row][col] = new DiePanel();

					innerPanel.add(theDice[row][col]);
				}
			}

			final int BorderSize = 10;
			innerPanel.setBorder(BorderFactory.createMatteBorder(
			     BorderSize, BorderSize, BorderSize, BorderSize, BACKGROUNDCOLOR));
			this.add(innerPanel);
		}

		// fill the diceBag with the 16 "official" Boggle dice for the 4X4 game
		private void initDiceBag() 
		{
		    diceBag = new ArrayList();
			diceBag.add(new Die("A", "O", "B", "B", "O", "J"));
			diceBag.add(new Die("W", "H", "G", "E", "E", "N"));
			diceBag.add(new Die("N", "R", "N", "Z", "H", "L"));
			diceBag.add(new Die("N", "A", "E", "A", "G", "E"));
			diceBag.add(new Die("D", "I", "Y", "S", "T", "T"));
			diceBag.add(new Die("I", "E", "S", "T", "S", "O"));
			diceBag.add(new Die("A", "O", "T", "T", "W", "O"));
			diceBag.add(new Die("H", "Qu", "U", "M", "N", "I"));
			diceBag.add(new Die("R", "Y", "T", "L", "T", "E"));
			diceBag.add(new Die("P", "O", "H", "C", "S", "A"));
			diceBag.add(new Die("L", "R", "E", "V", "Y", "D"));
			diceBag.add(new Die("E", "X", "L", "D", "I", "R"));
			diceBag.add(new Die("I", "E", "N", "S", "U", "E"));
			diceBag.add(new Die("S", "F", "F", "K", "A", "P"));
			diceBag.add(new Die("I", "O", "T", "M", "U", "C"));
			diceBag.add(new Die("E", "H", "W", "V", "T", "R"));
		}

		/**
		 * Return an array of Strings showing the sequence of faces on
		 * a randomly generated board.
		 */
		public String[] getRandomBoard()  
		{
			// Get random faces from randomy selected dice to fill the letterList
			int diceBagSize = diceBag.size();
			int boardSize = rows * cols;
			String[] letterList = new String[rows * cols];
			// we try to handle any size board with a fixed diceBag
			for (int i = 0; i<boardSize; i++) 
			{
				if(i % diceBagSize == 0 )  Collections.shuffle(diceBag);
				Die d = (Die) diceBag.get(i % diceBagSize);
				letterList[i] =   d.getRandomFace();
			}
			// set the corresponding DiePanels, and return the result
			return letterList;
		}

		/**
		 * Non-randomly reset the board according to 
		 * an array of Strings showing the sequence of faces on
		 * the new board.  Return the array.
		 */
		public void setBoard(String[] letterList)  
		{
			if(letterList == null ||
				letterList.length != rows*cols) 
			{
				throw new IllegalArgumentException(
				    "setBoard(): String array length is not " + rows*cols);
			}
                
			//Set the DiePanels with given letters
			for (int row = 0; row < rows; row++)  
			{
				for (int col = 0; col < cols; col++)  
				{
					theDice[row][col].setFace(letterList[(row*cols) + col]);
				}
			}
		}

		public java.util.List getLocationsForWord(String word) 
		{
			// we use the computer player for this
			return theComputer.isOnBoard(word);
		}
    
		public String getWordFromLocations(java.util.List locations) 
		{
			int loc;
			int row;
			int col;
			if (locations==null) return null;
			String result = "";
			for(int i = 0; i < locations.size(); i++)              
			{
				loc = ((Integer)locations.get(i)).intValue();
				row = loc / rows;
				col = loc % cols;
				result += theDice[row][col].getFace();
			}
			return result;
		}

		public void highlightDice(java.util.List locations) 
		{
			if(locations == null) return;

			Integer loc;
			int row;
			int col;

			unHighlightAllDice();
			for(int i = 0; i < locations.size(); i++) 
			{
				highlightDie((Integer) locations.get(i));
			}
			this.paintImmediately(this.getVisibleRect());
		}

		/**
		 * Highlight the specified die, given row and column.
		 */
		public void highlightDie(int row, int column) 
		{
			theDice[row][column].highlight();
		}

		/**
		 * Highlight the specified die, given Integer offset
		 * from upper-left-hand corner, using left-to-right,
		 * top-to-bottom ordering of dice.
		 */
		public void highlightDie(Integer indx) 
		{
			int i = indx.intValue();
			int row = i / theDice[0].length;
			int col = i % theDice[0].length;
			highlightDie(row, col);
		}

		/**
		 * Unhighlight all dice.
		 */
		public void unHighlightAllDice()  
		{
			for (int row = 0; row < theDice.length; row++)   
			{
				for (int col = 0; col < theDice[row].length; col++)  
				{
					theDice[row][col].unHighlight();
				}
			}
			this.paintImmediately(this.getVisibleRect());
		}


		static class Die 
		{
			private String[] sides;
			private int currentSideUp;
			private Random randomizer = new Random();

			public Die(String side1, String side2, String side3,
				String side4, String side5, String side6)    
			{
				sides = new String[] {side1,side2,side3,side4,side5,side6};
				randomize();
			}

			public String getLetter()    
			{
				return sides[currentSideUp];
			}

			public String getRandomFace()    
			{
				randomize();
				return sides[currentSideUp];
			}

			private void randomize()    
			{
			    final int Sides = 6;
				currentSideUp = randomizer.nextInt(Sides);
			}
		}
                


		// For displaying one Die on the board
		static class DiePanel extends JPanel 
		{
			private String face;
			private boolean isHighlighted;
			private JLabel faceLabel;

			private final Color DIECOLOR = new Color(230, 230, 230);
			private final Color FACECOLOR = new Color(3, 51, 217);
        
			private final Font FACEFONT = new Font("SansSerif", Font.PLAIN, 24);
			private final int DIESIZE = 50;

			public DiePanel()  
			{
				face = new String(""); 
				faceLabel = new JLabel("", SwingConstants.CENTER);
				setLayout(new BorderLayout());
				add(faceLabel, BorderLayout.CENTER);
				setBackground(BoggleBoard.BACKGROUNDCOLOR);
				setSize(DIESIZE, DIESIZE);
			}

			public Dimension getPreferredSize()  
			{
				return new Dimension (DIESIZE+1, DIESIZE+1);
			}

			public Dimension getMinimumSize()  
			{
				return getPreferredSize();
			}

			public void setFace( String chars )  
			{
				face = chars;
			}

			public String getFace() 
			{
				return face;
			}

			/**
			 * Draw one die including the letter centered in the middle of the die.
			 * If highlight is true, we 
			 * reverse the background and letter colors to highlight the die.
			 */
			public void paintComponent(Graphics g)  
			{
				super.paintComponent(g);
                
				int centeredXOffset;
				int centeredYOffset;
				// Draw the blank die
				g.setColor( isHighlighted ? FACECOLOR : DIECOLOR);
				g.fillRoundRect( 0, 0, DIESIZE, DIESIZE, DIESIZE/2, DIESIZE/2);
                
				// Outline the die with black
				g.setColor(Color.black);
				g.drawRoundRect( 0, 0, DIESIZE, DIESIZE, 
					DIESIZE/2, DIESIZE/2);
				Graphics faceGraphics = faceLabel.getGraphics();
				faceGraphics.setColor( isHighlighted ? DIECOLOR : FACECOLOR);   
				Color myColor =  isHighlighted ? DIECOLOR : FACECOLOR;  
				faceLabel.setForeground(myColor);
				faceLabel.setFont(FACEFONT);
				faceLabel.setText(face);
			}

			public void unHighlight()  
			{
				isHighlighted = false;
			}

			public void highlight()  
			{
				isHighlighted = true;
			}
		}

	} // class BoggeBoard

	// Maintains name, score, and word list information for one player
	class ScoreArea extends JPanel 
	{
	    private String playerName;
	    private int playerScore;
        
	    private JPanel namePanel;
	    private JLabel nameText;
	    private JPanel scorePanel;
	    private JLabel scoreText;
	    private JPanel topPanel;
        
	    private final Font ScoreFont = new Font("SansSerif", Font.PLAIN, 18);

		public ScoreArea(String player) 
		{
			playerName = new String(player);
			playerScore = 0;
                
			// Set-Up Top of Score Area
			namePanel = new JPanel();
			nameText = new JLabel(player);
			nameText.setFont(ScoreFont);
			namePanel.setLayout( new BorderLayout() );
			namePanel.add(nameText, BorderLayout.CENTER);

			scorePanel = new JPanel();
			scoreText = new JLabel("  0");
			scoreText.setFont(ScoreFont);
			scorePanel.setLayout( new BorderLayout() );
			scorePanel.add(scoreText, BorderLayout.CENTER);

			topPanel = new JPanel();
			topPanel.setLayout( new BorderLayout());
			topPanel.add(namePanel, BorderLayout.WEST);
			topPanel.add(scorePanel, BorderLayout.EAST);
                
			// Create bordering for top panel
			Border raisedBevel = BorderFactory.createRaisedBevelBorder();
			Border loweredBevel = BorderFactory.createLoweredBevelBorder(); 
			Border compound = BorderFactory.createCompoundBorder(raisedBevel,
				loweredBevel);
			topPanel.setBorder(compound);

			initWordArea();

			setLayout(new BorderLayout());
			add(topPanel, BorderLayout.NORTH);
			add(wordPanel, BorderLayout.CENTER);
		}

		public void setReady()  
		{
			resetScore();  // zero out score
			wordList.clear();  // remove words from HashSet
			// remove words from TextArea
			wordArea.setEditable(true);
			wordArea.selectAll();
			wordArea.cut();
			wordArea.setEditable(false);
			paintImmediately(getVisibleRect());
		}

		public void setName(String newName)  
		{
			playerName = newName;
			nameText.setText(playerName);
			repaint();
		}

		/**
		 * Define how many points a player gets for a given word.
		 */
		public int pointsForWord(String word) 
		{
			return word.length() - BoggleGUI.MINIMUMWORDLENGTH + 1;
		}

		public void addPoints(int points)  
		{
			playerScore += points;
			scoreText.setText(playerScore+"");
			scoreText.paintImmediately(scoreText.getVisibleRect());
		}

		public void resetScore() 
		{
			playerScore = 0;
			scoreText.setText(playerScore+"");
			scoreText.paintImmediately(scoreText.getVisibleRect());
		}

	    private final int WORDAREALINES = 16;
	    private Set wordList;
	    private JPanel wordPanel;
	    private JTextArea wordArea;

	    void initWordArea() {
		// Set-Up area to display word list
		wordList = new HashSet();
		wordPanel = new JPanel();
			Border etched = BorderFactory.createEtchedBorder();
			TitledBorder etchedTitle =
				BorderFactory.createTitledBorder(etched, "Word List");
			etchedTitle.setTitleJustification(TitledBorder.RIGHT);
			wordPanel.setBorder(etchedTitle);
			final int TextAreaFactor = 3;
			wordArea = new JTextArea(WORDAREALINES,
				// 2/3 of max word len is a good # of columns
				 BoggleGUI.DICEROWS*BoggleGUI.DICECOLS*2/TextAreaFactor);
			wordArea.addMouseListener(new MouseAdapter() 
			{
			    public void mouseClicked(MouseEvent e) 
			    {
				// if double-click, highlight the selection on the board
				if(e.getClickCount() == 2) 
				    {
					String word = wordArea.getSelectedText().trim();
					theBoard.highlightDice(theBoard.getLocationsForWord(word));
				    }
			    }});
			wordArea.setEditable(false); // for now
			wordPanel.add(new JScrollPane(wordArea,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		}

		public boolean containsWord(String word) 
		{
			return wordList.contains(word);
		}

		public void addWord(String word)  
		{
			if (containsWord(word)) return;
			wordList.add(word);
			wordArea.append(" " +word+"\n");
			addPoints(pointsForWord(word));
			wordArea.paintImmediately(wordArea.getVisibleRect());
		}

	} // class ScoreArea
                


	class WordEntryField extends JPanel 
	{
		private JTextField textField;
    
		public WordEntryField() 
		{
			//Set up for human player's Text Entry Field
		    final int TextFieldWidth = 30;
		    textField = new JTextField(TextFieldWidth);
			//Add listener to text entry field
			textField.addActionListener( new ActionListener() 
			{
				public void actionPerformed( ActionEvent e) 
				{
					checkAndAddWordHuman(textField.getText().toLowerCase());
				}});
			this.add(new JLabel("Enter word: "));
			this.add(textField);
			setUnready();
		}

		public void clear() 
		{
			textField.setText("");
		}

		public void setUnready() 
		{
			clear();
			textField.setEditable(false);
			paintImmediately(getVisibleRect());
			// attempt to give up focus to top-level frame
			BoggleGUI.this.requestFocus();
		}
		public void setReady() 
		{
			textField.setEditable(true);
			textField.requestFocus();
		}
	} // class WordEntryField

	/**
	 * The entry point for the BoggleGUI application.
	 * Usage:
	 * java BoggleGUI [ wordfile [ boardsize [ minwordlength ]]]
	 */
	public static void main(String[] args)  
	{
		if(args.length > 0) WORDLISTFILENAME = args[0];
		if(args.length > 1) 
		{
	// note that this assumes a square board. You may modify this to take 2 params.
			DICEROWS = Integer.parseInt(args[1]);
			DICECOLS = DICEROWS;
		}
		if(args.length > 2) MINIMUMWORDLENGTH = Integer.parseInt(args[2]);

		System.err.println("Starting " + DICEROWS + "x" + DICECOLS + " game," +
			" words from " + WORDLISTFILENAME +
			", min word length " + MINIMUMWORDLENGTH + ".");
		(new BoggleGUI()).setVisible(true);
	}
}


