import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class fastinversioncount {

    static int inversionCount = 0;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // 5 6 1 3 7 4
        System.out.println("How many numbers would you like to enter?");
        int n = input.nextInt();

        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.println("Please enter number " + i + ":");
            arr.add(input.nextInt());
        }
        System.out.println(countFastInversions(arr));
        System.out.println("There are " + inversionCount + " inversions in this list.");
    }

   public static List<Integer> countFastInversions(List<Integer> arr){
        if (arr.size() <= 1){
            return arr;
        }
        int len = arr.size();
        int mid = len / 2;

        List<Integer> left = countFastInversions(new ArrayList<>(arr.subList(0, mid)));
        List<Integer> right  = countFastInversions(new ArrayList<>(arr.subList(mid, len)));
        arr.clear();

        int lIndex = 0;
        int rIndex = 0;
        int lLength = mid;
        int rLength = len - mid;
        int index = 0;
        while ((lIndex < lLength) && (rIndex < rLength)){
            int lVal = left.get(lIndex);
            int rVal = right.get(rIndex);
            if (lVal <= rVal){
                arr.add(index, lVal);
                lIndex ++;
            } else {
                arr.add(index, rVal);
                inversionCount += lLength - lIndex;
                rIndex ++;
            }
            index ++;

            if (lLength == lIndex){
                arr.addAll(right.subList(rIndex, rLength));
            }

            if (rLength == rIndex){
                arr.addAll(left.subList(lIndex, lLength));
            }
        }

        return arr;
   }
}
