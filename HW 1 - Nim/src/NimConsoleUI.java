import java.sql.SQLOutput;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A console UI for playing Nim
 */
public class NimConsoleUI implements INimUI{

    private Scanner input = new Scanner(System.in);

    /**
     * Prompts for starting player
     *
     * @return 0 if player starts, 1 if computer starts
     */
    @Override
    public int start() {
        System.out.println("""
                
                -X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-
                Welcome to Sarah's Nim Game! Nim is a combinatorial game first solved by
                mathematician C. L. Bouton in 1901. Players alternate taking one or more
                from one color grouping of their choice. The player to take the last one wins.
                -X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-
                
                """);
        System.out.println("Who moves first?");
        System.out.println("(0) Player\n(1) Computer");
        return input.nextInt();
    }

    /**
     * Update the board
     *
     * @param board a HashMap with each color as a key and the number as a value
     */
    @Override
    public void updateBoard(HashMap<Character, Integer> board) {
        System.out.println("---------------");
        for (Map.Entry<Character, Integer> e: board.entrySet()) {
            System.out.print(" ".repeat((16 - e.getValue()) / 2));
            System.out.print(String.valueOf(e.getKey()).repeat(e.getValue()));
            System.out.println();
        }
        System.out.println("---------------");
    }





    /**
     * Get user input for each move
     *
     * @return an Entry with the color to remove as the key and number as a value
     */
    @Override
    public Map.Entry<Character, Integer> userTurn() {
        System.out.println("User turn:");
        System.out.println("What color would you like to remove? (G) Green (Y) Yellow (R) Red");
        Character color = input.next().charAt(0);
        System.out.println("How many would you like to remove?");
        int num = input.nextInt();
        return new AbstractMap.SimpleEntry<Character, Integer>(color, num);
    }

    /**
     * Print out what the computer turn was
     *
     * @param move
     */
    @Override
    public void computerTurn(Map.Entry<Character, Integer> move) {
        System.out.println("Computer turn: ");
        System.out.println("The computer removes " + move.getValue() + " of color " + move.getKey());
    }

    /**
     * Output for when the game is over
     *
     * @param turn
     */
    @Override
    public void gameOver(int turn) {
        if (turn == 0){
            System.out.println("The computer won!");
        } else {
            System.out.println("The user won!");
        }
    }

    /**
     * Output for when you make an invalid move
     */
    @Override
    public void invalidMove() {
        System.out.println("Invalid move. Please try making another move.");
    }
}
