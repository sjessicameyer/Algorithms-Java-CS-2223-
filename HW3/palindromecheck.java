import java.util.Scanner;

public class palindromecheck {
    private static Scanner input;
    public static void main(String[] args) {
        input = new Scanner(System.in);
        System.out.println("Enter a string:");
        boolean answer = isPalindrome(input.nextLine());
        if (answer){
            System.out.println("That's a palindrome!");
        }else {
            System.out.println("That's not a palindrome.");
        }
    }
    
    public static boolean isPalindrome(String str){
        // base case: string is empty or length 1
       if (str.length() <= 1) {
           return true;
       }
       // check if first char is not a letter
       char first = str.charAt(0);
       if (!Character.isLetter(first)) {
           return isPalindrome(str.substring(1));
       }
        // check if last char is not a letter
       char last = str.charAt(str.length()-1);
       if (!Character.isLetter(last)) {
           return isPalindrome(str.substring(0, str.length() - 1));
       }
       //check if characters not equal
       if (Character.toLowerCase(first) != Character.toLowerCase(last)) {
           return false;
       }
       //recurse otherwise
       return isPalindrome(str.substring(1, str.length() - 1));
    }
}
