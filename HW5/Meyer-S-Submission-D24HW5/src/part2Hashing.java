import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class part2Hashing {

    static int C = 123;
    static int m = 997;

    static String[] hashTable = new String[m];

    public static void main(String[]args) throws FileNotFoundException {
        File file = new File("/Users/sarahmeyer/Documents/Java CS 2223/HW5/Meyer-S-Submission-D24HW5/src/data/TwelfthNightActOneScene1.txt");
        Scanner sc = new Scanner(file);
        while(sc.hasNext()){
            String word = clean(sc.next());
            addElem(word);
        }
        printHashTable();
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

    public static void addElem(String s){
        //check for duplicates
        if (Arrays.asList(hashTable).contains(s)){
            return;
        }

        int hashed = hash(s);
        int i = hashed;
        while (hashTable[i] != null && !hashTable[i].equals("-1")){
            i += 1;
            if (i == hashed){
                throw new RuntimeException("Error: Hashtable exceeded max capacity.");
            }
            if (i >= m){
                i = 0;
            }
        }
        hashTable[i] = s;
    }

    public static void printHashTable(){
        for (int i = 0; i < m; i++) {
            String s = hashTable[i];
            if (s != null && !s.equals("-1")){
                System.out.println(i + ", " + s + ", " + hash(s));
            } else{
                System.out.println(i + " -1");
            }
        }
    }

}