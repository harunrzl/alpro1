import java.util.Random;
import java.io.*;

public class myGrep {

public static void main(String[] args){
char t40[][] = new char[40][10];
char table[][] = new char[10][10]; //Creates the array for the 10x10 grid
int ii=0, jj=0;
char c;

int ctr = 0;
char m;

Random r = new Random();//for the random characters

// First, I create a random table of 10x10 characters to work with
// To make it more interesting, I use a string with approximate frequencies
// for characters in the English language - this should yield more, and longer
// "findable" words in the grid
// source: http://www.math.cornell.edu/~mec/2003-2004/cryptography/subs/frequencies.html
String cfreq = "eeeeeeeeeeeetttttttttaaaaaaaaoooooooo" +
               "iiiiiiinnnnnnnssssssrrrrrrhhhhhhddddl" +
               "llluuucccmmmffyywwggppbbvkxqjz";

for (int x = 0; x < 10; x++)
{
  for (int y = 0; y < 10; y++)
  {
    table[x][y] = cfreq.charAt(r.nextInt(cfreq.length()));
   }
}   

// Now, make a second table of 40 lines; this speeds up the search
// since I won't have to think about "direction" any more ... just search L to R
for (int x = 0; x < 10; x++)
{
   for (int y = 0; y < 10; y++)
   {
      m = table[x][y];
      t40[x][y]=m;       // L to R
      t40[x+10][9-y]=m;  // R to L
      t40[y+20][x]=m;    // T to B
      t40[y+30][9-x]=m;  // B to T
   }
}

// confirm that we now have 40 rows of strings:
System.out.println("The following 40 strings represent searching the 10x10 grid in all directions:");
for( ii = 0; ii<40; ii++) {
  System.out.println(t40[ii]);
}

// Now starts the fun... for each word in the input file, look for a match
  try {
    FileInputStream fstream = new FileInputStream("textfile.txt");
    DataInputStream in = new DataInputStream(fstream);
    BufferedReader br = new BufferedReader(new InputStreamReader(in));
    String strLine;

    //Read File Line By Line
    while ((strLine = br.readLine()) != null)   {
      if(canFind(strLine, t40)) 
      {
        System.out.println (strLine);
      }
    }

    //Close the input stream
    in.close();
  } catch (Exception e) { //Catch exception if any
    System.err.println("Error: " + e.getMessage());
  }

} // end of main()

private static boolean canFind(String findme, char[][] searchme) {
  boolean isfound = false;
  int li = 0;

  // loop over all lines in the searchme table: we know there are 40
  while (li < 40 && !isfound) {
    // look for a match of the first character:
    for(int ii = 0; ii<11-findme.length(); ii++) {
      if(searchme[li][ii] == findme.charAt(0)) {
        isfound = true;
        for(int jj = 1; (jj < findme.length()) && isfound; jj++) {
          // **** this line could probably be optimized more: ****
          isfound = (isfound && (findme.charAt(jj) == searchme[li][ii+jj]));
        }
      ii = 10; // exit for(int ii = 0;..) loop...
      }
    }
    li++;
  }
  return isfound;

}
}
