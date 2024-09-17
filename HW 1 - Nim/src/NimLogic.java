import java.util.*;

/**
 * Class for all nimLogic
 */
public class NimLogic {
    /**
     * A hashmap representing each color in the board state ('r', 'y', 'g') and
     * the corresponding number of that color
     */
    private HashMap<Character, Integer> board;

    /**
     * The UI to use
     */
    private INimUI UI;

    /**
     * Whose turn (0 or 1)
     */
    private int turn;

    /**
     * 1 represents the computer turn
     */
    private static final int COMP_TURN = 1;

    /**
     * 0 represents the user turn
     */
    private static final int USER_TURN = 0;

    /**
     * Constructs a NimLogic class according to typical game rules
     * @param UI The specified UI
     */
    public NimLogic(INimUI UI){
        this.board = new HashMap<>(
                Map.of('G', 3, 'Y', 7, 'R', 5)
        );
        this.UI = UI;
        this.turn = UI.start();
        while (!board.isEmpty()){
            tick();
        }
        UI.gameOver(turn);
    }

    /**
     * Performs one turn cycle
     */
    private void tick(){
        UI.updateBoard(board);
        Map.Entry<Character, Integer> move;
        if (turn == COMP_TURN){
            move = getComputerTurn();
            UI.computerTurn(move);
            turn = USER_TURN;
        } else {
            move = UI.userTurn();
            while (!isValidMove(move)){
                UI.invalidMove();
                move = UI.userTurn();
            }
            turn = COMP_TURN;
        }
        applyMove(move, board);
    }

    /**
     * Gets the computer turn
     * @return The color and number of that color to remove
     */
    private Map.Entry<Character, Integer>  getComputerTurn(){
        if (checkIfWinning(board)){
            return getRandMove();
        }
        return getWinningMove();
    }

    /**
     * Checks if the given board state is a winning board state
     * @param board
     */
    private boolean checkIfWinning(HashMap<Character, Integer> board){
        ArrayList<Character> colors = new ArrayList<>(board.keySet());
        int result = 0;
        for (Character c : colors) {
            result = result ^ board.get(c);
        }
        return result == 0;
    }

    /**
     * Chooses a random move with equal probability for each move
     * @return the corresponding color and number for that move
     */
    private Map.Entry<Character, Integer> getRandMove(){
        ArrayList<Map.Entry<Character, Integer>> possible_moves = new ArrayList<>();
        for (Character color: board.keySet()) {
            for (int i = 1; i < board.get(color) + 1; i++) {
                possible_moves.add(new AbstractMap.SimpleEntry<>(color, i));
            }
        }
        int rand = (int)(Math.random()*possible_moves.size());
        return possible_moves.get(rand);
    }

    /**
     * Finds the first winning move
     * @return the corresponding color and number for that move
     */
    private Map.Entry<Character, Integer> getWinningMove(){
        for (Character color: board.keySet()) {
            for (int i = 1; i < board.get(color) + 1; i++) {
                HashMap<Character, Integer> theoretical_board = new HashMap<>(board);
                AbstractMap.SimpleEntry<Character, Integer> move = new AbstractMap.SimpleEntry<>(color, i);
                applyMove(new AbstractMap.SimpleEntry<>(color, i), theoretical_board);

                if (checkIfWinning(theoretical_board)){
                    return move;
                }
            }
        }
        return null;
    }

    /**
     * Applies the given move to a given board as a side effect
     * @param move the corresponding color and number for the move to make
     * @param board the corresponding color and number for the given board
     */
    private void applyMove(Map.Entry<Character, Integer> move, HashMap<Character, Integer> board){

        board.put(move.getKey(), board.get(move.getKey()) - move.getValue());
        if (board.get(move.getKey()) == 0){
            board.remove(move.getKey());
        }
    }

    /**
     * Checks if a move can be applied to the current board
     *
     * @param move the corresponding color and number for the move to make
     * @return
     */
    private boolean isValidMove(Map.Entry<Character, Integer> move){
        return board.containsKey(move.getKey()) && board.get(move.getKey()) >= move.getValue() && move.getValue() > 0;
    }
}
