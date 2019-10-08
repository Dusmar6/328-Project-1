import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        int[] inputArr = new int[10];
        String input = "";

        try (Scanner in = new Scanner(System.in)) {

            while (!input.equals("exit")) {

                System.out.println("\nEnter a valid option\n1: User generated array\n2: Randomly generated array\nexit: end the program\n");
                input = in.nextLine();

                int algor1Sum = 0;

                switch (input) {

                    case "1":
                        inputArr = userEnteredArray(in);
                        testAlgorithms(inputArr);
                        break;
                    case "2":
                        inputArr = randomGenArray(in);
                        testAlgorithms(inputArr);
                        break;
                    case "exit":
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Not a valid input.");
                        break;

                }



            }

        } catch (Exception e) {
            System.out.println("Improper Input.");
        }

    }

    public static int solutionOne(int[] array) {

        int maxSum = array[0];

        for (int i = 0; i < array.length; i++) {

            for (int j = i; j < array.length; j++) {

                int tempSum = 0;

                for (int k = i; k < j; k++) {

                    tempSum += array[k];

                }

                if (tempSum > maxSum) {
                    maxSum = tempSum;
                }

            }

        }

        return maxSum;

    }
    
    //SOLUTION 3

    public static int mssJunior(int[] a, int l, int r) {

        //if there is one value in the array
        if (l == r) {
            return a[l];
        }

        //if there are two elements in the array
        if (r == l+1) {
            return Math.max(a[l], Math.max(a[r], (a[r]+a[l])));
        }

        int m = (l+r) / 2;

        int mssL = mssJunior(a, l, m);

        int mssR = mssJunior(a, m+1, r);

        int mssM = mssJuniorMiddle(a, l, m, r);

        return Math.max(mssL, Math.max(mssR, mssM));

    }

    public static int mssJuniorMiddle(int[] a, int l, int m, int r) {

        int maxLeftSum = Integer.MIN_VALUE;
        int sum = 0;

        for (int i = m; i >= l; i--) {

            sum += a[i];

            if (maxLeftSum < sum) {
                maxLeftSum = sum;
            }

        }

        int maxRightSum = Integer.MIN_VALUE;
        sum = 0;

        for (int i = m+1; i <= r; i++) {

            sum += a[i];

            if (maxRightSum < sum) {
                maxRightSum = sum;
            }

        }

        return maxLeftSum + maxRightSum;

    }

    public static void testAlgorithms(int[] inputArr) {

        long startTime = System.nanoTime();
        int algor1Sum = solutionOne(inputArr);
        long endTime = System.nanoTime();
        System.out.println("System run time, " + (endTime - startTime) + " nano seconds.");
        System.out.println("First algorithms max sum: " + algor1Sum);

        startTime = System.nanoTime();
        int algor3Sum = mssJunior(inputArr, 0, inputArr.length-1);
        endTime = System.nanoTime();
        System.out.println("System run time, " + (endTime - startTime) + " nano seconds.");
        System.out.println("Third algorithms max sum: " + algor3Sum);

    }

    /**
     * process user input to generate an integer array of n-size
     * @param in scanner to take the user input
     * @return a list of integers
     */
    public static int[] userEnteredArray(Scanner in) {

        //get user input
        System.out.print("Enter an array of integers (1, 2, 3, 4, 5): ");
        String userArray = in.nextLine();

        //adjust user input to be a list of only commas and ints
        Pattern cleanArray = Pattern.compile("[^-0-9,]");
        Matcher arrayMatcher = cleanArray.matcher(userArray);

        userArray = arrayMatcher.replaceAll("");

        //convert string array to int array
        String[] strArray = userArray.split(",");
        int[] intArray = new int[strArray.length];

        for (int i = 0; i < intArray.length; i++) {

            intArray[i] = Integer.parseInt(strArray[i]);

        }

        return intArray;

    }

    /**
     * generates a list of user defined length filled with random integers in the range of [-50 - 50]
     * @param in scanner to take the user input
     * @return a list of integers
     */
    public static int[] randomGenArray(Scanner in) {

        Random rand = new Random();
        System.out.print("Enter the length of an array to generate: ");
        int[] intArray = new int[in.nextInt()];
        in.nextLine();

        //generate random values for the array
        for (int i = 0; i < intArray.length; i++) {

            intArray[i] = rand.nextInt(101) - 50;

        }

        return intArray;

    }
}
