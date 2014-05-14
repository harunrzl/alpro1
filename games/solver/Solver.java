import gineer.bogglesolver.trie.Trie;
import gineer.bogglesolver.util.Constants;
import gineer.bogglesolver.util.Util;
import org.apache.log4j.Logger;

public class Solver
{
    private char[] puzzle;
    private int maxSize;

    private boolean[] used;
    private StringBuilder stringSoFar;

    private boolean[][] matrix;
    private Trie trie;

    private final static Logger logger = Logger.getLogger(Solver.class);

    public Solver(int size, String puzzle)
    {
        trie = Util.getTrie(size);
        matrix = Util.connectivityMatrix(size);

        maxSize = size * size;
        stringSoFar = new StringBuilder(maxSize);
        used = new boolean[maxSize];

        if (puzzle.length() == maxSize)
        {
            this.puzzle = puzzle.toCharArray();
        }
        else
        {
            logger.error("The puzzle size does not match the size specified: " + puzzle.length());
            this.puzzle = puzzle.substring(0, maxSize).toCharArray();
        }
    }

    public void solve()
    {
        for (int i = 0; i < maxSize; i++)
        {
            traverseAt(i);
        }
    }

    private void traverseAt(int origin)
    {
        stringSoFar.append(puzzle[origin]);
        used[origin] = true;

        //Check if we have a valid word
        if ((stringSoFar.length() >= Constants.MINIMUM_WORD_LENGTH) && (trie.containKey(stringSoFar.toString())))
        {
            logger.info("Found: " + stringSoFar.toString());
        }

        //Find where to go next
        for (int destination = 0; destination < maxSize; destination++)
        {
            if (matrix[origin][destination] && !used[destination] && trie.containPrefix(stringSoFar.toString() + puzzle[destination]))
            {
                traverseAt(destination);
            }
        }

        used[origin] = false;
        stringSoFar.deleteCharAt(stringSoFar.length() - 1);
    }

}

