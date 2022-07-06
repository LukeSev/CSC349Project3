import java.io.*;
import java.util.*;

public class GameProblem {

    public static void main(String[] args) throws Exception
    {
        // Get file name from user
        System.out.println("Enter the name of the file with Game Data:");
        Scanner in = new Scanner(System.in);
        String filename = in.nextLine();
        File infile = new File(filename);

        // Generate matrices from info in file
        Scanner filescan = new Scanner(infile);
        int rows, columns;
        rows = filescan.nextInt();
        columns = filescan.nextInt();

        // create array to hold matrix
        int[][] A = new int[rows][columns];

        // loop for rows
        for(int i = 0; i < rows; i++)
        {
            // loop for columns
            for(int j = 0; j < columns; j++)
            {
                A[i][j] = filescan.nextInt();
            }
        }
    }

    public static void game(int n, int m, int[][] A)
    {
        int[][] S = new int[n][m];
        char[][] R = new char[n][m];

        S[n][m] = A[n][m];

        // Right column
        for(int i = n-1; i > 0; i--)
        {
            if(A[i][m] < A[i+1][m])
            {
                S[i][m] = A[i][m] + A[i+1][m];
                R[i][m] = 'd';
            }
        }

        // Bottom row
        for(int j = m-1; m > 0; m--)
        {
            if(A[n][j] < A[n][j+1])
            {
                S[n][j] = A[n][j] + A[n][j+1];
                R[n][j] = 'r';
            }
        }

        // Everything else
        for(int i = n - 1; i > 0; i--)
        {
            for(int j = m-1; j > 0; j--)
            {
                if(S[i+1][j] > S[i][j+1])
                {
                    S[i][j] = A[i][j] + S[i+1][j];
                    R[i][j] = 'd';
                }
                else
                {
                    S[i][j] = A[i][j] + S[i][j+1];
                    R[i][j] = 'r';
                }
            }
        }

    }

}
