import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class part1Hashing {

    static int C = 123;
    static int m = 997;

    public static void main(String[]args) throws FileNotFoundException {
        File file = new File("/Users/sarahmeyer/Documents/Java CS 2223/HW5/Meyer-S-Submission-D24HW5/src/data/TwelfthNightActOne.txt");
        Scanner sc = new Scanner(file);
        while(sc.hasNext()){
            String word = clean(sc.next());
            System.out.println(word + " --> " + hash(word));
        }
    }

    public static String clean(String s){
        String cleaned = "";
        for (int i = 0; i < s.length(); i++) {
            char letter = s.charAt(i);
            if (Character.isLetter(letter) || letter == '\'' || letter == '-'){
                cleaned += letter;
            }
        }
        return cleaned;
    }

    public static int hash(String s){
        int h = 0;
        for (int i = 0; i < s.length(); i++) {
            h = (h * C + (int) s.charAt(i)) % m;
        }
        return h;
    }

}