import java.io.File;
import java.util.Scanner;
import java.util.Stack;

public class GroupingSymbolChecker {

    public static void main(String[] args) {

        // Check if the user provided a file name as a command-line argument
        if (args.length != 1) {
            System.out.println("Usage: java GroupingSymbolChecker <source-file>");
            return;
        }

        Stack<Character> stack = new Stack<>(); // Stack to store opening symbols

        try (Scanner sc = new Scanner(new File(args[0]))) {

            // Read the file line by line
            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                // Check each character in the line
                for (char ch : line.toCharArray()) {

                    // If it’s an opening symbol, push it onto the stack
                    if (ch == '(' || ch == '{' || ch == '[') {
                        stack.push(ch);
                    } 
                    // If it’s a closing symbol, check for a match
                    else if (ch == ')' || ch == '}' || ch == ']') {

                        // If no opening symbol to match, it’s incorrect
                        if (stack.isEmpty()) {
                            System.out.println("Incorrect grouping symbols.");
                            return;
                        }

                        char open = stack.pop(); // Get the last opening symbol

                        // Check if the opening and closing symbols match
                        if (!isMatchingPair(open, ch)) {
                            System.out.println("Incorrect grouping symbols.");
                            return;
                        }
                    }
                }
            }

            // If stack is empty, all symbols matched correctly
            if (stack.isEmpty()) {
                System.out.println("Correct grouping symbols.");
            } else {
                System.out.println("Incorrect grouping symbols.");
            }

        } catch (Exception e) {
            System.out.println("Error reading file."); // Handle file errors
        }
    }

    // Helper method to check if the opening and closing symbols match
    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '{' && close == '}') ||
               (open == '[' && close == ']');
    }
}
