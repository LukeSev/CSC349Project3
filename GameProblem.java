import java.io.*;
import java.util.*;

public class GameProblem {

    public static void main(String[] args) throws Exception
    {
        // Get file name from user
        System.out.println("Enter the name of the file with Game Data:");
        Scanner in = new Scanner(System.in);
        String filename = System.getProperty("user.dir") + "/" + in.nextLine();
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

        game(rows, columns, A);
    }

    public static void game(int n, int m, int[][] A)
    {
        int[][] S = new int[n][m];
        char[][] R = new char[n][m];

        S[n-1][m-1] = A[n-1][m-1];
        int[] maxloc = new int[2];
        maxloc[0] = n-1;
        maxloc[1] = m-1;
        int max = A[n-1][m-1];

        // Right column
        for(int i = n-2; i >= 0; i--)
        {
            if(S[i+1][m-1] > 0)
            {
                S[i][m-1] = A[i][m-1] + S[i+1][m-1];
                R[i][m-1] = 'd';
            }
            else
            {
                S[i][m-1] = A[i][m-1];
                R[i][m-1] = 'r';
            }
            if(S[i][m-1] >= max)
            {
                max = S[i][m-1];
                maxloc[0] = i;
                maxloc[1] = m-1;
            }
        }

        // Bottom row
        for(int j = m-2; j >= 0; j--)
        {
            if(S[n-1][j+1] > 0)
            {
                S[n-1][j] = A[n-1][j] + S[n-1][j+1];
                R[n-1][j] = 'r';

            }
            else
            {
                S[n-1][j] = A[n-1][j];
                R[n-1][j] = 'd';
            }
            if(S[n-1][j] >= max)
            {
                max = S[n-1][j];
                maxloc[0] = n-1;
                maxloc[1] = j;
            }
        }

        // Everything else
        for(int i = n-2; i >= 0; i--)
        {
            for(int j = m-2; j >= 0; j--)
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
                if(S[i][j] >= max)
                {
                    max = S[i][j];
                    maxloc[0] = i;
                    maxloc[1] = j;
                }
            }
        }
        printResult(R, max, maxloc, n, m);
    }

    private static void printResult(char[][] R, int maxScore, int[] location, int n, int m)
    {
        System.out.printf("Best score: %d%n", maxScore);
        System.out.printf("Best route: ");

        int flag = 0;
        while(flag == 0)
        {
            System.out.printf("[%d,%d] to ", location[0]+1, location[1]+1);
            if(R[location[0]][location[1]] == 'r')
                if(location[1] == m-1)
                    flag = 1;
                else
                    location[1]++;
            else
                if(location[0] == n-1)
                    flag = 1;
                else
                    location[0]++;
        }
        System.out.printf("exit%n");
    }
}
