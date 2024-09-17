package Lucas;

import java.util.function.IntFunction;

public class LucasNumsPart2 {

    public static void main(String[] args) {
        System.out.println("JUST PRINTING NUMBERS");
        printLucasNumbers(40);
        System.out.println("SUCCESSIVE CALCULATION TIMES FOR LUCAS NUMS");
        printNumberComparisons(40, LucasNumsPart2::computeLucasNumber);
        System.out.println("SUCCESSIVE CALCULATION TIMES FOR SARAH NUMS");
        printNumberComparisons(40, LucasNumsPart2::computeSarahNumber);
    }

    /**
     * Print lucas numbers from 0 to n with the corresponding time required
     * @param n the highest input value to print the lucas number for
     */
    static private void printLucasNumbers(int n){
        for (int i = 0; i <= n ; i++) {
            long initialTime = System.nanoTime();
            int lucasNum = computeLucasNumber(i);
            long duration = System.nanoTime() - initialTime;
            System.out.println("L(" + i + ") = " + lucasNum + "\t time (nanosec) : " + duration);
        }
    }

    /**
     * Print numbers from 0 to n for a given function with the corresponding time required
     * @param n the highest input value to print the number for
     * @param f the function to apply for each value up to n
     */
    static private void printNumberComparisons(int n, IntFunction<Integer> f){

        //initialize 0th time
        long initialTime = System.nanoTime();
        int lastNum = f.apply(0);
        long lastDuration = System.nanoTime() - initialTime;

        for (int i = 1; i <= n ; i++) {
            //calculations for current num
            initialTime = System.nanoTime();
            int num = f.apply(i);
            long duration = System.nanoTime() - initialTime;
            System.out.println("L(" + i + ")/L(" + (i - 1) + ") = " + (double)num/lastNum +
                    "\t Time(L(" + i + "))/Time(L(" + (i - 1) + ")) = " + (double)duration/lastDuration );

            //update last number
            lastNum = num;
            lastDuration = duration;
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

    /**
     * Compute the sarah number for the input x recursively
     * @param x the input
     * @return the corresponding lucas number
     */
    static private int computeSarahNumber(int x){
        switch (x){
            case 0:
                return 3;
            case 1:
                return 18;
            default:
                return computeSarahNumber(x-1) + computeSarahNumber(x-2);
        }
    }

}