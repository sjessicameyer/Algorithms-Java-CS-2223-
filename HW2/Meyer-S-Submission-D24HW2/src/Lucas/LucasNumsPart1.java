package Lucas;
import java.util.Scanner;
public class LucasNumsPart1 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("What value of n?");
        int input = s.nextInt();
        printLucasNumbers(input);
    }

    /**
     * Print lucas numbers from 0 to n
     * @param n the highest input value to print the lucas number for
     */
    static private void printLucasNumbers(int n){
        for (int i = 0; i <= n ; i++) {
            System.out.println("L(" + i + ") = " + computeLucasNumber(i));
        }
    }

    /**
     * Compute the lucas number for the input x recursively
     * @param x the input
     * @return the corresponding lucas number
     */
    static private int computeLucasNumber(int x){
        switch (x){
            case 0:
                return 2;
            case 1:
                return 1;
            default:
                return computeLucasNumber(x-1) + computeLucasNumber(x-2);
        }
    }

}
