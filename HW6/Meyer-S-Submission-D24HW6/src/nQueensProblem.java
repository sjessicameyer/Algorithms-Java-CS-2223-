import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class nQueensProblem {

    public static void main(String[] args) throws Exception {

        for (int n = 4; n < 20; n++) {
            printFirstSolution(n);
        }

        for (int n = 4; n < 15; n++) {
            printNumSolution(n);
        }

        printFirstSolution(32);
        printFirstSolution(37);
        printNumSolution(15);
        printNumSolution(17);
    }

    /**
     * Takes a (possibly partial) position and n and returns true
     * if and only if no queens attack each other
     * @param board the board state
     * @param n the dimensions of the board
     * @return
     */
    public static boolean isLegalPosition(int[] board, int n) {
        LegalBoard legalBoard = new LegalBoard(n);

        for (int row = 0; row < n; row++) {
            int col = board[row];
            if (col == 0){
                break;
            }
            if (!legalBoard.isLegalNextMove(col)){
                return false;
            }

            legalBoard.doNextMove(col);
        }

        return true;
    }

    /**
     * From any (possibly partial) position, find the next legal position.
     * Fills board from top down and left to right
     * @param board the board state
     * @param n the dimensions of the board
     * @return the next legal position
     */
    public static int[] nextLegalPosition(int[] board, int n) throws Exception {
        int[] nextboard = successor(board, n, isLegalPosition(board, n));
        while (!isLegalPosition(nextboard, n)){
            nextboard = successor(board, n, false);
        }
        return nextboard;
    }

    /**
     * From any (possibly partial) position, find the next position,mregardless if legal
     * Fills board from top down and left to right
     * @param board the board state
     * @param n the dimensions of the board
     * @param isLegal if the current position is legal
     * @return the next legal position
     */
    public static int[] successor(int[] board, int n, boolean isLegal) throws Exception {
        //System.out.println("before: " + Arrays.toString(board));
        // is partial and legal
        if (board[n - 1] == 0 & isLegal){
            //find first empty row
            int firstEmptyRow = 0;
            while (board[firstEmptyRow] != 0){
                firstEmptyRow += 1;
            }
            //add queen to that row at pos 1
            board[firstEmptyRow] = 1;
        } else {
            int currentRow = n - 1;
            while (board[currentRow] == 0){
                currentRow -= 1;
            }

            if (board[currentRow] < n){
                board[currentRow] += 1;
            } else if (currentRow > 0){
                while(board[currentRow] >= n){
                    board[currentRow] = 0;
                    currentRow --;
                    board[currentRow] += 1;
                }
            } else {
                throw new Exception("No successor exists");
            }
        }
        return board;
    }

    public static void printFirstSolution(int n) throws Exception {
        System.out.println("First solution to n = " + n + ": " + LegalBoard.getFirstSolution(n).boardToString());
    }

    public static void printNumSolution(int n){
        System.out.println("There are " + LegalBoard.getNumSolution(n) + " solutions to n = " + n);
    }


}
