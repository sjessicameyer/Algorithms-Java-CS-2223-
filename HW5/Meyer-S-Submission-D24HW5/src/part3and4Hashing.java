import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class part3and4Hashing {

    static int C = 123;
    static int m = 997;

    static String[] hashTable = new String[m];

    public static void main(String[]args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the file path for what you would like to open:");
        File file = new File(input.nextLine());
        Scanner sc = new Scanner(file);
        while(sc.hasNext()){
            String word = clean(sc.next());
            addElem(word);
        }

        System.out.println("What table size would you like to use?");
        m = input.nextInt();
        printHashTable();
        System.out.println("There are " + countNonEmpty() + " non empty addresses.");
        System.out.println("The load factor is " + getLoadFactor());
        System.out.println("The longest empty area is " + getLongestEmptyArea());
        System.out.println("The longest cluster is " + getLongestCluster());
        System.out.println("The hash value from the greatest number of words is " + getMostHashValue());
        System.out.println("The word farthest from its actual hash value is " + getFarthestWord());

    }

    private static String getFarthestWord() {
        String farthestWord = "";
        int farthestDistance = 0;
        for (int i = 0; i < m; i++) {
            if (hashTable[i] == null){
                continue;
            }
            int hashVal = hash(hashTable[i]);
            int currentDistance;
            if (i < hashVal){
                currentDistance = m - hashVal + i;
            } else  {
                currentDistance = i - hashVal;
            }
            if (currentDistance > farthestDistance){
                farthestWord = hashTable[i];
                farthestDistance = currentDistance;
            }
        }
        return farthestWord + " which traveled a distance of " + farthestDistance;
    }

    private static String getMostHashValue() {
        int mostCommon = 0;
        int mostCommonFreq = 0;
        for (int i = 0; i < m; i++) {
            if (hashTable[i] == null){
                continue;
            }
            int current = hash(hashTable[i]);
            int currentFreq = 1;
            for (int j = i + 1; j < m; j++) {
                if (hashTable[j] != null && hash(hashTable[j]) == current){
                    currentFreq += 1;
                }
            }
            if (currentFreq > mostCommonFreq){
                mostCommon = current;
                mostCommonFreq = currentFreq;
            }
        }
        return mostCommon + ", which occurs " + mostCommonFreq + " times";
    }

    private static String getLongestCluster() {
        int longestLength = 0;
        int longestLoc = 0;
        int currentLength = 0;

        boolean notWrapped = true;
        int i = 0;
        while (notWrapped && i < m){
            //count number of not null in a row
            if (hashTable[i] != null){
                currentLength ++;
                i++;

                //wrap if end of list reached
                if (i == m){
                    i = 0;
                    notWrapped = false;
                }

                //prevent infinite loop if table completely full
                if (currentLength == m){
                    break;
                }
            } else {
                if (longestLength < currentLength){
                    longestLoc = i - currentLength;
                    longestLength = currentLength;
                }
                currentLength = 0;
                i++;
            }
        }

        return "length " + longestLength + " at location " + longestLoc;
    }

    private static String getLongestEmptyArea() {
        int longestLength = 0;
        int longestLoc = 0;
        int currentLength = 0;

        boolean notWrapped = true;
        int i = 0;
        while (notWrapped && i < m){

            //count number of null in a row
            if (hashTable[i] == null){
                currentLength ++;
                i++;

                //wrap if end of list reached
                if (i == m){
                    i = 0;
                    notWrapped = false;
                }

                //prevent infinite loop if table completely full
                if (currentLength == m){
                    break;
                }
            } else {
                if (longestLength < currentLength){
                    longestLoc = i - currentLength;
                    longestLength = currentLength;
                }
                currentLength = 0;
                i++;
            }
        }

        return "length " + longestLength + " at location " + longestLoc;
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

    public static int countNonEmpty(){
       int count = 0;
        for (String s: hashTable) {
            if (s != null){
                count += 1;
            }
        }
        return count;
    }

    public static double getLoadFactor(){
        return (double)countNonEmpty() / hashTable.length;
    }


}