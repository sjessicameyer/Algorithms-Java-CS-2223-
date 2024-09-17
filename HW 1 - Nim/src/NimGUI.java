import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A console UI for playing Nim
 */
public class NimGUI implements INimUI{

    private Scanner input = new Scanner(System.in);

    private JFrame frame  = new JFrame();
    private JPanel panel = new JPanel();

    public NimGUI(){
        //set up frame
        frame.add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Sarah's Nim Game");
        frame.setSize(800,800);
        frame.setPreferredSize(new Dimension(800, 800));
        panel.setLayout(new GridBagLayout());
        frame.setVisible(true);
    }

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
        panel.removeAll();

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(20, 0, 20, 0);
        c.gridheight = 1;
        c.gridwidth = 1;

        if (board.containsKey('G')){
            for (int i = 0; i < board.get('G'); i++) {
                NimGUI.circleComponent circleComponent = new NimGUI.circleComponent(Color.GREEN, 70, 70);
                if (i > 0){
                    c.gridy = 1;
                    c.gridx = (i - 1) * 2 + 3;
                } else{
                    c.gridy = 0;
                    c.gridx = i * 2 + 4;
                }
                panel.add(circleComponent, c);
            }
        }
        if (board.containsKey('Y')){
            for (int i = 0; i < board.get('Y'); i++) {
                NimGUI.circleComponent circleComponent = new NimGUI.circleComponent(Color.YELLOW, 70, 70);
                if (i > 2){
                    c.gridy = 4;
                    c.gridx = (i - 3) * 2 + 1;
                } else {
                    c.gridy = 3;
                    c.gridx = i * 2 + 2;
                }
                panel.add(circleComponent, c);
            }
        }

        if (board.containsKey('R')){
            for (int i = 0; i < board.get('R'); i++) {
                NimGUI.circleComponent circleComponent = new NimGUI.circleComponent(Color.RED, 70, 70);
                c.gridx = i * 2;
                c.gridy = 5;
                panel.add(circleComponent, c);
            }
        }

        //make visible
        frame.pack();

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

    private static class circleComponent extends JComponent {
        private Color color;
        private int width;
        private int height;

        public circleComponent(Color color, int width, int height) {
            super();
            this.color = color;
            this.width = width;
            this.height = height;
            this.setMinimumSize(new Dimension(width, height));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(color);
            g.fillOval(0, 0, width, height);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(width, height); // Set default size for the component
        }
    }
}
