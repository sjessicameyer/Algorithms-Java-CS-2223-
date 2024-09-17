import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class nQueensProblemTests{

    @Test
    public void testIsLegalPositionTrueComplete(){
        int[] board = {1, 6, 8, 3, 7, 4, 2, 5};
        assertTrue(nQueensProblem.isLegalPosition(board, 8));
    }

    @Test
    public void testIsLegalPositionTrueIncomplete(){
        int[] board = {1, 6, 8, 3, 7, 0, 0, 0};
        assertTrue(nQueensProblem.isLegalPosition(board, 8));
    }

    @Test
    public void testIsLegalPositionFalseIncomplete(){
        int[] board = {1, 6, 8, 3, 5, 0, 0, 0};
        assertFalse(nQueensProblem.isLegalPosition(board, 8));
    }
    @Test
    public void testIsLegalPositionFalseIncomplete2(){
        int[] board = {1, 1, 0, 0, 0, 0, 0, 0};
        assertFalse(nQueensProblem.isLegalPosition(board, 8));
    }

    @Test
    public void testNextLegalPositionAfterIllegalPartial() throws Exception {
        int[] board = {1, 6, 8, 3, 5, 0, 0, 0};
        assertArrayEquals(new int[]{1, 6, 8, 3, 7, 0, 0, 0}, nQueensProblem.nextLegalPosition(board, 8));
    }

    @Test
    public void testNextLegalPositionAfterLegalPartial() throws Exception {
        int[] board = {1, 6, 8, 3, 7, 0, 0, 0};
        assertArrayEquals(new int[]{1, 6, 8, 3, 7, 4, 0, 0}, nQueensProblem.nextLegalPosition(board, 8));
    }
    @Test
    public void testNextLegalPositionAfterLegalFull() throws Exception {
        int[] board = {2, 4, 1, 3};
        board = nQueensProblem.nextLegalPosition(board, 4);
        while (board[3] == 0){
            nQueensProblem.nextLegalPosition(board, 4);
        }
        assertArrayEquals(new int[]{3, 1, 4, 2}, board);
    }

    @Test
    public void testNextLegalBoard() throws Exception {
        LegalBoard legalBoard = new LegalBoard(8);
        legalBoard.doNextMove(1);
        legalBoard.doNextMove(6);
        legalBoard.doNextMove(8);
        legalBoard.doNextMove(3);
        legalBoard.doNextMove(7);
        legalBoard.nextLegalBoard();
        assertArrayEquals(new int[]{1, 6, 8, 3, 7, 4, 0, 0}, legalBoard.getBoard());
    }

    @Test
    public void testNextLegalBoard2() throws Exception {
        LegalBoard legalBoard = new LegalBoard(4);
        legalBoard.doNextMove(2);
        legalBoard.doNextMove(4);
        legalBoard.doNextMove(1);
        legalBoard.doNextMove(3);
        legalBoard.nextLegalBoard();
        while (!legalBoard.isSolution()){
            legalBoard.nextLegalBoard();
        }
        assertArrayEquals(new int[]{3, 1, 4, 2}, legalBoard.getBoard());
    }

    @Test
    public void testDiagonals(){
        LegalBoard legalBoard = new LegalBoard(3);
        assertEquals(legalBoard.getNegDiagonal(1, 1), legalBoard.getNegDiagonal(2,2));
    }

    @Test
    public void testIsLegalNextMove(){
        LegalBoard legalBoard = new LegalBoard(3);
        assertTrue(legalBoard.isLegalNextMove(1));
    }


}
