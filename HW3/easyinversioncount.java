import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class easyinversioncount {
    static Scanner input;
    public static void main(String[] args) {
        input = new Scanner(System.in);

        System.out.println("How many numbers would you like to enter?");
        int n = input.nextInt();

        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.println("Please enter number " + i + ":");
            arr.add(input.nextInt());
        }
        System.out.println("There are " + countEasyInversions(arr) + " inversions in this list.");
    }

   public static int countEasyInversions(List<Integer> arr){
        int inversion_count = 0;
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                if (arr.get(j) < arr.get(i)){
                    inversion_count ++;
                }
            }
        }
        return inversion_count;
   }
}
