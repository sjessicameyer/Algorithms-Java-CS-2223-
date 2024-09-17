import java.util.ArrayList;
import java.util.Arrays;

public class MostPreciousPath {

    /**
     * The layout of the heart of the mountain
     */
    private static final int[][] layout =
            {{35, 89, 52, 66, 82, 20, 95, 21},
            {79, 5, 14, 23, 78, 37, 40, 74},
            {32, 59, 17, 25, 31, 4, 16, 63},
            {91, 11, 77, 48, 13, 71, 92, 15},
            {56, 70, 47, 64, 22, 88, 67, 12},
            {83, 97, 94, 27, 65, 51, 30, 7},
            {10, 41, 1, 86, 46, 24, 53, 93},
            {96, 33, 44, 98, 75, 68, 99, 84}};

    /**
     * The values for the value of the most valuable path to each tile
     */
    private static int [][] values = new int[8][8];

    /**
     * The arraylists for the most valuable path to each tile
     */
    private static ArrayList<Integer>[][] paths = new ArrayList[8][8];

    public static void main(String[] args) {

        // Initialize each element of the array
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                paths[i][j] = new ArrayList<Integer>();
            }
        }

        findMostPreciousPath();

    }

    public static void findMostPreciousPath(){

        //initialize first row
        for (int vault = 0; vault < layout[0].length; vault++) {
            values[0][vault] = layout[0][vault]; //values should be the same
            paths[0][vault].add(vault); //path should just be themselves
        }

        //fill the rest of the rows
        for (int row = 1; row < layout.length; row++) {
            for (int col = 0; col < layout[0].length; col++) {
                // find max path to tile
                int max_val = values[row-1][col];
                int max_vault = col;
                if (col != 0 && values[row-1][col-1] > max_val){
                    max_val = values[row-1][col - 1];
                    max_vault = col - 1;
                }
                if (col != layout[0].length - 1 && values[row-1][col+1] > max_val){
                    max_val = values[row - 1][col + 1];
                    max_vault = col + 1;
                }

                //store max path + gem
                values[row][col] = max_val + layout[row][col];
                paths[row][col] = new ArrayList<>(paths[row-1][max_vault]);
                paths[row][col].add(col);
            }
        }

        //pick best result from top row
        int most_gems = values[7][0];
        ArrayList<Integer> most_path = paths[7][0];
        for (int col = 1; col < layout[0].length; col++) {
            if (values[7][col] > most_gems){
                most_path = paths[7][col];
                most_gems = values[7][col];
            }
        }

        //output
        System.out.println(most_gems + " gems collected");
        System.out.println("Bilbo starts at square " + (most_path.get(0)+1));
        System.out.println("The Arkenstone is at vault " + (most_path.get(7)+1));
        System.out.println("The most precious path: ");

        //pretty path
        for (int row = layout.length-1; row >= 0; row--) {
            for (int col = 0; col < layout[0].length; col++) {
                if (most_path.get(row) != col){
                    System.out.printf("%-4s", layout[row][col]);
                }else {
                    System.out.printf("%-4s", "X");
                }
            }
            System.out.println();
        }

//        //text path
//        for (int i = 0; i < 8; i++) {
//            System.out.println("Row " + (i+1) + " Vault " + (most_path.get(i) + 1));
//        }


    }

}
