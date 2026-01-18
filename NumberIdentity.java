import java.util.Scanner;

public class NumberIdentity {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int[][] m1 = new int[3][3];
        int[][] m2 = new int[3][3];

        // Read the first array in one line
        System.out.println("Enter the first 3x3 array (9 numbers):");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                m1[i][j] = input.nextInt();
            }
        }

        // Read the second array in one line
        System.out.println("Enter the second 3x3 array:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                m2[i][j] = input.nextInt();
            }
        }

        // Check if the arrays are identical and print the result
        if (equals(m1, m2)) {
            System.out.println("\n*Hoorayy!The two arrays are identical.*");
        } else {
            System.out.println("\n*The two arrays are not identical.*");
        }

        input.close();
    }

    // Method to check if arrays are identical
    public static boolean equals(int[][] m1, int[][] m2) {
        if (m1.length != m2.length) return false;
        for (int i = 0; i < m1.length; i++) {
            if (m1[i].length != m2[i].length) return false;
            for (int j = 0; j < m1[i].length; j++) {
                if (m1[i][j] != m2[i][j]) return false;
            }
        }
        return true;
    }
}



