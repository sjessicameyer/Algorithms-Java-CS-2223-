import java.util.Arrays;
import java.util.BitSet;

public class LegalBoard {

    /**
     * Each bit from 0 to n * 2 -1 represents whether a queen occupies
     * the corresponding positive diagonal
     */
    private BitSet positiveDiagonals;

    /**
     * Each bit from 0 to n * 2 - 1 represents whether a queen occupies
     * the corresponding negative diagonal
     */
    private BitSet negativeDiagonals;

    /**
     * Each bit from 0 to n - 1 represents whether a queen occupies
     * the corresponding negative diagonal
     */
    private BitSet columns;

    /**
     * Represents the board, where each index from 0 to n - 1 is a row
     * and each value from 1 to n - 1 represents a queen in that column of the row.
     * The value 0 represents no queen in that row
     */
    private int[] board;

    /**
     * Represents the first empty row on the board
     */
    private int firstEmptyRow;

    /**
     * Constructs an empty legal board
     * @param n length of the legal board
     */
    public LegalBoard(int n){
        positiveDiagonals = new BitSet(n * 2 -1);
        negativeDiagonals = new BitSet(n * 2 -1);
        columns = new BitSet(n);
        this.board = new int[n];
        this.firstEmptyRow = 0;
    }

    /**
     * Check if the next move for a given row or col is legal, ignoring existing piece in that row
     * @param col the col to place it in, 1 indexed
     * @return if that move is legal
     */
    public boolean isLegalNextMove(int col){

        int posDiagonal = getPosDiagonal(firstEmptyRow, col);
        int negDiagonal = getNegDiagonal(firstEmptyRow, col);

        return !(positiveDiagonals.get(posDiagonal) || negativeDiagonals.get(negDiagonal) || columns.get(col));
    }

    /**
     * Update the board with the next move, which must be legal
     * @param col the col to place it in, 1 indexed
     */
    public void doNextMove(int col){
        //update board
        board[firstEmptyRow] = col;

        //get diagonals
        int posDiagonal = getPosDiagonal(firstEmptyRow, col);
        int negDiagonal = getNegDiagonal(firstEmptyRow, col);

        //update the bit sets
        columns.set(col, true);
        positiveDiagonals.set(posDiagonal, true);
        negativeDiagonals.set(negDiagonal, true);

        firstEmptyRow += 1;
    }

    public void removeRow(int row){
        int col = board[row];

        //update diagonals
        int posDiagonal = getPosDiagonal(row, col);
        int negDiagonal = getNegDiagonal(row, col);

        //update bit sets
        columns.set(col, false);
        positiveDiagonals.set(posDiagonal, false);
        negativeDiagonals.set(negDiagonal, false);

        board[row] = 0;
        firstEmptyRow -= 1;
    }

    /**
     * Given a legal board, find the next board
     * @throws Exception
     */
    public void nextLegalBoard() throws Exception {
        int move = nextMove();
        while (!isLegalNextMove(move)){
            move = nextMove(move);
        }
        doNextMove(move);
    }

    /**
     * Given a legal board, find the next move
     * @throws Exception
     */
    public int nextMove() throws Exception {
        //handles legal boards

        //if board is full, logic is same as illegal board
        if (isSolution()){
            return nextMove(board[firstEmptyRow - 1]);
        }

        //if board is not full, try placing something in the row below
        return 1;
    }

    /**
     * Given an illegal board, find the next move
     * @param lastMove the last move made on the board
     * @throws Exception
     */
    public int nextMove(int lastMove) throws Exception {
        //handles illegal boards

        //if not on last col, you should increment
        if (lastMove < board.length){
            return lastMove + 1;
        }
        //otherwise (if on last col)
        else {
            // on first row you except
            if (firstEmptyRow == 0){
                throw new Exception("No next move exists");
            }
            // otherwise backtrack
            else {
                int currentRow = firstEmptyRow - 1;
                int next = board[currentRow];
                removeRow(currentRow);
                return nextMove(next);
            }
        }
    }


    public String boardToString(){
        return Arrays.toString(board);
    }

    public int[] getBoard(){
        return board.clone();
    }

    public boolean isSolution() {
        return board[board.length - 1] != 0;
    }

    public int getNegDiagonal(int row, int col){
        return row + board.length - col;
    }

    public int getPosDiagonal(int row, int col){
        return row + col - 1;
    }

    public static LegalBoard getFirstSolution(int n) throws Exception {
        LegalBoard legalBoard = new LegalBoard(n);
        legalBoard.nextLegalBoard();
        while (!legalBoard.isSolution()){
            legalBoard.nextLegalBoard();
        }
        return legalBoard;
    }

    public static int getNumSolution(int n){
        int symmetryn = n/2;
        boolean oddN = (n % 2) == 1;

        int doublecount = 0;
        int count = 0;
        LegalBoard legalBoard = new LegalBoard(n);
        while (true){
            try {
                legalBoard.nextLegalBoard();
                while (!legalBoard.isSolution()){
                    legalBoard.nextLegalBoard();
                }
            }
            catch (Exception e){
                break;
            }
            if (legalBoard.getBoard()[0] <= symmetryn){
                doublecount++;
            } else if (oddN && legalBoard.getBoard()[0] == symmetryn + 1){
                count ++;
            } else{
                break;
            }
        }
        return doublecount * 2 + count;
    }

}
