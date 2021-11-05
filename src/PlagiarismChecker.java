import java.util.Scanner;
import java.io.File;
import java.io.RandomAccessFile;

/**
 * Project Description:
 * This program checks for the similarity between files. In other words it checks for plagiarism. Works by utilizing
 * a double int array to find the largest common subsequence between files which is then used to help calculate
 * how similar the files are. The content of the files are read and compared via bytes as opposed to just sending the
 * file directly in as well.
 *
 *@Author Ivan Zarate
 *CSCI 340
 *Section 001*
 *Assignment 5 Plagiarism Checker
 *Known Bugs: None
 */

public class PlagiarismChecker {

    /**
     * Main method that starts off the program. Initiates the program to check for every files plagiarism score
     * while asking the user for what the threshold for plagiarism should be.
     * alongside
     * @param args
     */
    public static void main(String[] args) {

        // String variable to hold the name of the first file to compare for the standard output
        String testFile1 = "Player1.java";

        // String variable to hold the name of the second file to compare for the standard output
        String testFile2 = "Player2.java";

        // Double variable called "firstScore" that holds whatever is returned by the plagiarismScore method
        double firstScore = plagiarismScore(testFile1,testFile2);

        // Formatted print statement for output
        System.out.println("File1           " + "File2           " + "Score");

        // Print statements to print out the name of the files used for comparison
        System.out.print(testFile1 + "    " + testFile2 + "    ");

        // Formatted print statement for the score. Prints out variable "firstScore" up until two decimal places
        System.out.printf("%.2f\n", firstScore);

        // String array to hold the names for all the files we're gonna compare later
        String[] otherFiles = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9",
                "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
                "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",};

        // Scanner to be used for user input
        Scanner user = new Scanner(System.in);

        // Line to ask user for how much the files that are printed out should match by
        System.out.println("\nEnter a threshold for how much the files should match");

        // Double variable called "thresh" to hold whatever the user typed in for the threshold
        double thresh = user.nextDouble();

        // Sends the collection of the program files and variable "thresh" to the plagiarismChecker method
        plagiarismChecker(otherFiles,thresh);
    }

    /**
     * Method that calculates the length of the largest common subsequence between two files. Doesn't take files
     * as parameters but instead the content of the files. But still goes through both files completely and
     * compares the entire contents of one to the other and vice versa in order to do so.
     * @param prog1Contents is the name given to the first file's contents that are sent in
     * @param prog2Contents is the name given to the second file's contents that are sent in
     * @return is what gives back the longest common subsequence
     */

    private static int lcsLength(String prog1Contents, String prog2Contents) {

        // Double int array called "doubA" that's the length of the two parameters sent in + 1
        int[][] doubA = new int[prog1Contents.length() + 1][prog2Contents.length() + 1];

        // Nested for loop, first one is to go through the rows of the double int array, which is the length of the first parameter
        for (int row = 0; row <= prog1Contents.length(); row++) {

            // Second for loop is to go through the columns of the double int array, which is the length of the second parameter
            for( int column = 0; column <= prog2Contents.length(); column++) {

                // Checks to see if we're in the first row or column
                if (row == 0 || column == 0)

                    // If so fills the entry with a 0
                    doubA[row][column] = 0;

                // Else checks to see if the characters are the same in each file
                else if (prog1Contents.charAt(row-1) == prog2Contents.charAt(column - 1))

                    // If so sets it as the one in the double int array
                    doubA[row][column] = doubA[row - 1][column -1] + 1;

                else

                    // Else it checks to see that the largest common subsequence is filled into the double array
                    doubA[row][column] = Math.max(doubA[row - 1][column], doubA[row][column - 1]);
            }
        }

        // Returns the longest common subsequence which should be in the bottom corner of the double array
        return doubA[prog1Contents.length()][prog2Contents.length()];
    }

    /**
     * Method that calculates the score/similarity between two files.
     * @param filename1 is the name given to the first file sent in as the first parameter
     * @param filename2 is the name given to the second file sent in as the second parameter
     * @return is what gives back the calculated score
     */

    private static double plagiarismScore(String filename1, String filename2) {

        // Sends the first sent in file to the the "byteReader" method while setting it equal to the string "firstFile"
        String firstFile = byteReader(filename1);

        // Sends the second sent in file to the the "byteReader" method while setting it equal to the string "secondFile"
        String secondFile = byteReader(filename2);

        // Double variable to hold the length of the first file sent in
        double firstLength = firstFile.length();

        // Double variable to hold the length of the second file sent in
        double secondLength = secondFile.length();

        // Returns the calculated score
        return (200 *lcsLength(firstFile,secondFile)/ (firstLength +secondLength));
    }

    /**
     * Method to check how many files reach the plagiarism threshold set by the user in the main. Takes two parameters
     * first being the array of files created in main and second being the decided threshold by the user
     * @param filenames is the name given to the array of files passed in
     * @param threshold is the name given to the decided user threshold sent in
     */
    private static void plagiarismChecker (String [] filenames, double threshold) {

        // Print statement, with a new line for formatting reasons, to start off method
        System.out.println("\nSudoku Assignment with threshold set at " + threshold);

        // Spaced out print statement for formatting reasons
        System.out.println("File1           " + "File2           " + "Score");

        // Nested for loop to go through every file
        for (int outer = 0; outer < filenames.length; outer++) {

            for (int inner = outer + 1; inner < filenames.length; inner++) {

                // If statement to check that the files aren't the same
                if (!filenames[outer].equals(filenames[inner])) {

                    // Calculates the plagiarism score of the two files sent in
                    double score = plagiarismScore(filenames[outer], filenames[inner]);

                    // Checks to see if the score is greater or equal to the user threshold
                    if (score != 0 && score >= threshold) {

                        // If so prints out the files alongside their score
                        System.out.printf("%-16s%-16s%.2f\n", filenames[outer], filenames[inner], score);
                    }
                }
            }
        }
    }

    /**
     * Method to read the content of the files via a RandomAccessFile. As this will be used on every file
     * it was given it's own method for simplicity sake.
     * @param fileName is the file that's passed in to be read
     * @return is what gives back the contents of the file
     */

    private static String byteReader(String fileName) {

        // StringBuilder to hold the contents of the file
        StringBuilder holder = new StringBuilder();

        // Try catch to go through the file
        try {

            // RandomAccessFile used to read the file sent in
            RandomAccessFile reader = new RandomAccessFile(new File(fileName), "r");

            int i = reader.read();

            // While loop to go through till end of file (-1) is reached
            while (i != -1) {

                // Adds whatever is found to the builder "holder"
                holder.append((char) i);

                // Keeps going through file
                i = reader.read();
            }

        }
        // If an exception occurs the below print statement is printed
        catch (Exception e) {

            System.err.println("An exception occurred in " + fileName);
        }

        // Returns the string version of the file's contents
        return holder.toString();
    }
}
