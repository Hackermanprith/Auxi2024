import java.util.*;

public class Bitwise {
    // Function to count the number of set bits in an integer
    public static int countSetBits(int x) {
        return Integer.bitCount(x);
    }
    static int takeinpT(String message,Scanner sc) {
        System.out.println(message);
        int x= 0;
        try {
            x = sc.nextInt();
            if (x < 0) {
                System.out.println("Please enter a number greater than 0");
                x = takeinpT(message,sc);
            }
        }
        catch (InputMismatchException e) {
            System.out.println("There was an error");
            x = takeinpT(message,sc);

        }
        return x;
    }
    static int takeinpA(String message,Scanner sc,int condition) {
        System.out.println(message);
        int x= 0;
        try {
            x = sc.nextInt();
            if (x < 1 || x > condition) {
                System.out.println("Please enter a number greater than 0");
                x = takeinpA(message,sc,condition);
            }
        }
        catch (InputMismatchException e) {
            System.out.println("There was an error");
            x = takeinpA(message,sc,condition);

        }
        return x;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = takeinpT("Please enter the no of test cases: ",sc) ;// Number of test cases
        int[] results = new int[T]; // To store the results of each test case

        for (int t = 0; t < T; t++) {
            int N = takeinpA("Please enter the no of inputs: ",sc,106); // Size of the array
            int[] A = new int[N];
            for (int i = 0; i < N; i++) {
                A[i] = takeinpA("Please enter the value:",sc,109);
            }

            int validSubarraysCount = 0;
            int[] prefixXor = new int[N + 1];
            int[] prefixAnd = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                prefixXor[i] = prefixXor[i - 1] ^ A[i - 1];
                prefixAnd[i] = i > 1 ? prefixAnd[i - 1] & A[i - 1] : A[i - 1];
            }
            for (int i = 1; i <= N; i++) {
                for (int j = i; j <= N; j++) {
                    int subarrayXor = prefixXor[j] ^ prefixXor[i - 1];
                    int subarrayAnd = i > 1 ? prefixAnd[j] & prefixAnd[i - 1] : prefixAnd[j];

                    if (countSetBits(subarrayAnd) % 2 == 1 && countSetBits(subarrayXor) % 2 == 0) {
                        validSubarraysCount++;
                    }
                }
            }

            results[t] = validSubarraysCount;
        }

        // Print the results for each test case
        for (int result : results) {
            System.out.println(result);
        }

        sc.close();
    }
}