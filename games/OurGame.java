import java.util.*;
import java.awt.*;
import javax.swing.*;

public class OurGame extends JFrame {
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

	public OurGame() {
		initPanels();
	}

	private void initPanels() {
		Container contentPane = getContentPane();
		theBoard = new BoggleBoard(DICEROWS, DICECOLS, computerPLayer);
	}

	class BoggleBoard extends JPanel {
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
	}
}
