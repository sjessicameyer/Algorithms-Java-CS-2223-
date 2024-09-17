package Subirach;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Subirach {
    private static final List<Integer> SUBIRACH_SQUARE = new ArrayList<>(List.of(1, 14, 14, 4, 11, 7, 6, 9, 8, 10, 10, 5, 13, 2, 3, 15));
    public static void main(String[] args) {
        System.out.println("HOW MANY 4 ELEMENT COMBINATIONS ADD UP TO THE SAME AS ROWS/COLS?");
        int sum = getSum(SUBIRACH_SQUARE.subList(0, 4));
        List<List<Integer>> combos = getCombos(SUBIRACH_SQUARE, 4);
        System.out.println(countWithSum(sum, combos));

        System.out.println("HOW MANY ANY SIZE ELEMENT COMBINATIONS ADD UP TO THE SAME AS ROWS/COLS?");
        combos = getCombos(SUBIRACH_SQUARE);
        System.out.println(countWithSum(sum, combos));

        System.out.println("HOW MANY WAYS EACH SUM CAN BE FORMED");
        int mostEqualSum = 0; // the sum with the most possible combos
        int mostEqualSumCount = 0; // the number of combos
        int highestsum = getSum(SUBIRACH_SQUARE);
        for (int i = 0; i <= highestsum ; i++) {
            int currentSumCount = countWithSum(i, combos);
            System.out.println(i + " can be formed in " + currentSumCount + " ways.");
            if (currentSumCount > mostEqualSumCount){
                mostEqualSumCount = currentSumCount;
                mostEqualSum = i;
            }
        }

        System.out.println("The sum " + mostEqualSum + " can be created in the most ways. This is " + mostEqualSumCount + " ways.");
    }

    /**
     * Get all possible combinations of elements with length r from arr
     * @param arr an array
     * @param r the length of combinations to create
     * @return all combinations
     */
    public static List<List<Integer>> getCombos(List<Integer> arr, int r){
        List<List<Integer>> combos = new ArrayList<List<Integer>>();
        getCombos(arr, r, 0, new ArrayList<Integer>(), combos);
        return combos;
    }

    /**
     * Private helper to get all possible combinations of elements with length r from arr
     * Adds the output to the results array
     * @param arr an array of elements
     * @param r the length of combinations to create
     * @param start an accumulator for where to start adding elements
     * @param soFar an accumulator for the combination so far
     * @param results a reference to the results
     */
    private static void getCombos(List<Integer> arr, int r, int start, List<Integer> soFar, List<List<Integer>> results){
        if (soFar.size() == r){
            results.add(soFar);
            return;
        }
        for (int i = start; i < arr.size() ; i++) {
            List<Integer> soFarCopy = new ArrayList<>(soFar);
            soFarCopy.add(arr.get(i));
            getCombos(arr, r, i + 1, soFarCopy, results);
        }
    }

    /**
     * Get all possible combinations of elements from arr
     * @param arr an array
     * @return all combinations
     */
    public static List<List<Integer>> getCombos(List<Integer> arr){
        List<List<Integer>> combos = new ArrayList<List<Integer>>();
        getCombos(arr, 0, new ArrayList<Integer>(), combos);
        return combos;
    }

    /**
     * Private helper to get all possible combinations of elements from arr
     * Adds the output to the results array
     * @param arr an array of elements
     * @param start an accumulator for where to start adding elements
     * @param soFar an accumulator for the combination so far
     * @param results a reference to the results
     */
    private static void getCombos(List<Integer> arr, int start, List<Integer> soFar, List<List<Integer>> results){
        results.add(soFar);
        for (int i = start; i < arr.size() ; i++) {
            List<Integer> soFarCopy = new ArrayList<>(soFar);
            soFarCopy.add(arr.get(i));
            getCombos(arr, i + 1, soFarCopy, results);
        }
    }

    /**
     * Counts how many combinations have a certain sum
     * @param s the sum
     * @param combos an array combinations
     * @return the number of combos with that sum
     */
    public static int countWithSum(int s, List<List<Integer>> combos){
        int count = 0;
        for (List<Integer> combo : combos) {
            if (getSum(combo) == s){
                count ++;
            }
        }
        return count;
    }

    /**
     * Private helper to get the sum of a list
     * @param list a list of integers
     * @return the sim of its elements
     */
    private static int getSum(List<Integer> list) {
        int sum = 0;
        for (int i : list)
            sum = sum + i;
        return sum;
    }

}
