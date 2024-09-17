
public class BetterForwardElimination {

    public static void main(String[] args) {
        //double[][] data = {{1, 1, 1, 6}, {1, 1, 2, 9}, {1, 2, 3, 14}}; // easy matrix
        //double[][] data = {{1, 1, 1, 6}, {1, 1, 2, 9}, {2, 2, 3, 15}}; // unsolvable matrix
        double[][] data = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 364},
                {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                {0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 16},
                {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 36},
                {0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 64},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 100},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 79},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 61},
                {0, 0, 0, 0, 0, 4, -3, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 3, -2, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 1, -1, 0, 0, 0},
                {1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, -42}
        };
        try {
            printArray(doBetterForwardElimination(data));
        } catch (Exception e){
            System.out.println("This array is unsolvable!");
        }
    }
    public static double[][] doBetterForwardElimination(double[][] arr) throws Exception {
        //zeros below main diagonal
        for (int i = 0; i < arr.length - 1; i++) {
            int pivotrow = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (Math.abs(arr[j][i]) > Math.abs(arr[pivotrow][i])){
                    pivotrow = j;
                }
            }
            for (int k = i; k < arr.length + 1; k++) {
                double temp = arr[i][k];
                arr[i][k] = arr[pivotrow][k];
                arr[pivotrow][k] = temp;
            }

            if (arr[i][i] == 0){
                throw new Exception("Unsolvable Matrix");
            }

            for (int j = i + 1; j < arr.length ; j++) {
                double temp = arr[j][i] / arr[i][i];
                for (int k = i; k < arr.length + 1; k++) {
                    arr[j][k] = arr[j][k] - arr[i][k] * temp;
                }
            }
        }

        //turn main diagonal into 1s
        for (int i = 0; i < arr.length; i++) {
            double temp = 1/arr[i][i];
            for (int k = i; k < arr.length + 1; k++) {
                arr[i][k] = arr[i][k] * temp;
            }
        }

        //zeros above main diagonal
        for (int i = arr.length - 1; i >= 0; i--) { //i represents each diagonal
            for (int j = 0; j < i; j++) { // j represents each row above i
                double temp = arr[j][i]; //factor to multiply by
                for (int k = 0; k < arr.length + 1; k++) { // k represents each column in row j
                    arr[j][k] = arr[j][k] - arr[i][k] * temp;
                }
            }
        }

        return arr;
    }

    private static void printArray(double[][] arr){
        for (double[] row: arr) {
            for (double item: row) {
                System.out.printf("%-4s", Math.round(item));
            }
            System.out.println();
        }
    }
}
