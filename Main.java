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
                        algor1Sum = solutionOne(inputArr);
                        System.out.println("First algorithms max sum: " + algor1Sum);
                        break;
                    case "2":
                        inputArr = randomGenArray(in);
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
