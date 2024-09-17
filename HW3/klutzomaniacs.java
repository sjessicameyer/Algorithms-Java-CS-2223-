import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class klutzomaniacs {

    public static void main(String[] args) {
        List<String> greycode = (BRGC(5));
        List<String> names = new ArrayList<>(List.of("Axel" , "Boxo", "Crunchy", "Doofus", "Enzo")).reversed(); //Requires openJDK 21 or later

        System.out.printf("| %-5s | %-9s |  %-30s | %-15s |%n", "Index", "Gray Code", "Klutzomaniacs Riding", "Action");
        String lastGraycodeVal = "00000";
        ArrayList<String> riding = new ArrayList<>();
        for (int i = 0; i < greycode.size(); i++) {
            //get graycode
            String graycodeVal = greycode.get(i);

            //find difference and update riding
            String diff = "Spotlight";
            for (int j = 0; j < graycodeVal.length(); j++) {
                if (graycodeVal.charAt(j) == '0' && lastGraycodeVal.charAt(j) == '1'){
                    diff = names.get(j) + " Leaves";
                    riding.remove(names.get(j));
                }else if (graycodeVal.charAt(j) == '1' && lastGraycodeVal.charAt(j) == '0') {
                    diff = names.get(j) + " Joins";
                    riding.add(names.get(j));
                }
            }
            lastGraycodeVal = graycodeVal;

            System.out.printf("| %-5s | %-9s |  %-30s | %-15s |%n", i, graycodeVal, riding, diff);
        }
    }

    /**
     * Generates recursively the binary reflected Gray code of order n
     * @param n A positive integer n
     * @return A list of all bit strings of length n composing the Gray code
     */
    public static List<String> BRGC(int n){
        if (n == 1){
            return new ArrayList<>(List.of("0", "1"));
        }
        List<String> l1 = BRGC(n-1);
        List<String> l2 = new ArrayList<>(l1.reversed()); //Requires openJDK 21 or later
        for (int i = 0; i < l1.size() ; i++) {
            l1.set(i, 0 + l1.get(i));
            l2.set(i, 1 + l2.get(i));
        }
        l1.addAll(l2);
        return l1;
    }
}
