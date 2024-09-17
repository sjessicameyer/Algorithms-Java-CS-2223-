import java.util.HashMap;
import java.util.Map;

/**
 * An interface for any UI implementation of Nim
 */
public interface INimUI {
    /**
     * Prompts for starting player
     * @return 0 if player starts, 1 if computer starts
     */
    public int start();

    /**
     * Update the board
     * @param board a HashMap with each color as a key and the number as a value
     */
    public void updateBoard(HashMap<Character, Integer> board);

    /**
     * Get user input for each move
     * @return an Entry with the color to remove as the key and number as a value
     */
    public Map.Entry<Character, Integer> userTurn();

    /**
     * Output what the computer turn was
     */
    public void computerTurn(Map.Entry<Character, Integer> move);

    /**
     * Output for when the game is over
     * @param turn
     */
    public void gameOver(int turn);

    /**
     * Output for when you make an invalid move
     */
    public void invalidMove();

}
