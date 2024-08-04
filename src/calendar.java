import java.util.InputMismatchException;
import java.util.Scanner;

public class calendar {
    static int takeinpA(String message, Scanner sc, int condition1, int condition2) {
        System.out.println(message);
        int x = 0;
        try {
            x = sc.nextInt();
            if (x < condition1 || x > condition2) {
                System.out.println("Please enter a number between " + condition1 + " and " + condition2);
                x = takeinpA(message, sc, condition1, condition2);
            }
        } catch (InputMismatchException e) {
            System.out.println("There was an error. Please enter a valid number.");
            sc.next(); // Clear the invalid input
            x = takeinpA(message, sc, condition1, condition2);
        }
        return x;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int days = takeinpA("Enter the number of days: ", sc, 1, 366);
        int year = takeinpA("Enter the year: ", sc, 1000, 69444);
        if (days == 366 && !isLeapYear(year)) {
            System.out.println("The year was not a leap year, please enter a number less than or equal to 365.");
            days = takeinpA("Enter the number of days: ", sc, 1, 365);
        }
        int n = takeinpA("Enter the value of n: ", sc, 1, 100);

        int initialMonth = 1;
        int initialDay = days;
        int[] daysInMonths = getDaysInMonths(year);
        while (initialDay > daysInMonths[initialMonth - 1]) {
            initialDay -= daysInMonths[initialMonth - 1];
            initialMonth++;
        }

        // Calculate final date after adding n days
        int finalDay = initialDay + n;
        int finalMonth = initialMonth;
        int finalYear = year;
        while (finalDay > daysInMonths[finalMonth - 1]) {
            finalDay -= daysInMonths[finalMonth - 1];
            finalMonth++;
            if (finalMonth > 12) {
                finalMonth = 1;
                finalYear++;
                daysInMonths = getDaysInMonths(finalYear);
            }
        }

        // Output results
        System.out.println("The date for the given days in the year:" + initialDay + " " + getMonthName(initialMonth) + " " + year);
        System.out.println("The date after the additional days: " + finalDay + " " + getMonthName(finalMonth) + " " + year);
    }

    // Method to get the number of days in each month
    private static int[] getDaysInMonths(int year) {
        return new int[]{
                31, // January
                isLeapYear(year) ? 29 : 28, // February
                31, // March
                30, // April
                31, // May
                30, // June
                31, // July
                31, // August
                30, // September
                31, // October
                30, // November
                31  // December
        };
    }

    // Method to check if a year is a leap year
    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // Method to get the name of the month
    private static String getMonthName(int month) {
        String[] months = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };
        return months[month - 1];
    }
}
