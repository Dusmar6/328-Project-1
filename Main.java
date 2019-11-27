// Greyson Cabrera 014121118
// Dustin Martin 015180085


import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        int[] inputArr = new int[10];
        String input = "";

        String algos = "";

        try (Scanner in = new Scanner(System.in)) {

            while (!input.equals("exit")) {

                System.out.println("\nEnter a valid option\n1: User generated array\n2: Randomly generated array\n3: Estimate runtime \n4: Exit\n");
                input = in.nextLine();

                

                switch (input) {

                    case "1":
                        inputArr = userEnteredArray(in);
                        algos = "1234";
                        testAlgorithms(inputArr, algos);
                        break;
                    case "2":
                        inputArr = randomGenArray(in);
                        System.out.println("\nEnter a 4-character string denoting which algorithms you'd like to run.");
                        algos = in.nextLine();
                        testAlgorithms(inputArr, algos);
                        break;
                    case "3":
                        System.out.println("\nPick an Algorithm\n1: Solution 1\n2: Solution 2\n3: Solution 3 \n4: Solution 4\n");
                        String algo_input = in.nextLine();

                        System.out.println("\nEnter array size to get runtime (m):\n");
                        int m = Integer.parseInt(in.nextLine());

                        System.out.println("\nEnter array size to predict runtime (n):\n");
                        int n = Integer.parseInt(in.nextLine());

                        inputArr = random_gen_array(m);
                        int[] new_arr = random_gen_array(n);

                        double milli_time_m =-1;
                        double milli_time_n =-1;
                        double predicted_time_n =-1;

                        System.out.println("\n-----------------------------");
                        System.out.println("Runtime for M: ");
                        switch(algo_input){

                          case "1":
                            algos = "1";
                            milli_time_m = option_3(inputArr, algos);
                            break;
                          case "2":
                            algos = "2";
                            milli_time_m = option_3(inputArr, algos);
                            break;

                          case "3":
                            algos = "3";
                            milli_time_m = option_3(inputArr, algos);
                            break;

                          case "4":
                            algos = "4";
                            milli_time_m = option_3(inputArr, algos);
                            break;

                        }
                        System.out.println("-----------------------------");

                        System.out.println("\n-----------------------------");
                        predicted_time_n = (milli_time_m * n)/m;
                        System.out.println("Predicted time for size n: "+ predicted_time_n);
                        System.out.println("\nActual runtime for size n: ");
                        milli_time_n = option_3(new_arr, algos);
                        System.out.println("-----------------------------");

                        break;
                    case "4":
                        System.out.println("Goodbye!");
                        System.exit(0);
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


    public static int solutionTwo( int[] a){
      int n = a.length;
      int max_sum = 0;
      for (int i=0; i<n; i++){
        int this_sum = 0;
        for (int j=i; j<n; j++){
          this_sum += a[j];
          if (this_sum>max_sum){
            max_sum = this_sum;
          }
        }
      }
      return max_sum;
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
    
    public static int solutionFour(int[] a){
      
      int max_sum = 0;
      int this_sum = 0;

      for (int i =0; i<a.length; i++){
        this_sum = this_sum + a[i];
        if(this_sum > max_sum){
          max_sum = this_sum;
        }
        else if(this_sum<0){
          this_sum =0;
        }
      }
      return max_sum;
    }
    public static double print_runtime(long nano){
      double d_nano = nano;
      double milli = d_nano/1000000;
      double seconds = milli/1000;

      if(milli >1000){
        System.out.println("Runtime: "+ seconds + " seconds");
      }
      else{
        System.out.println("Runtime: "+ milli +" milliseconds");
      }
      return milli;
    }

    public static void testAlgorithms(int[] inputArr, String algos) {
        long startTime;
        long endTime;
        long nano_runtime;
        System.out.println("\n-----------------------------");
        if (algos.contains("1")){

          startTime = System.nanoTime();
          int algor1Sum = solutionOne(inputArr);
          endTime = System.nanoTime();
          nano_runtime = (endTime - startTime);
          System.out.println("First algorithms max sum: " + algor1Sum);
          print_runtime(nano_runtime);
        }
        
        if (algos.contains("2")){
          startTime = System.nanoTime();
          int algor2Sum = solutionTwo(inputArr);
          endTime = System.nanoTime();

          nano_runtime = (endTime - startTime);   
          System.out.println("Second algorithms max sum: " + algor2Sum);
          print_runtime(nano_runtime);
        }

        if (algos.contains("3")){
          startTime = System.nanoTime();
          int algor3Sum = mssJunior(inputArr, 0, inputArr.length-1);
          endTime = System.nanoTime();
          nano_runtime = (endTime - startTime);
          System.out.println("Third algorithms max sum: " + algor3Sum);
          print_runtime(nano_runtime);
        }

        if (algos.contains("4")){
          startTime = System.nanoTime();
          int algor4Sum = solutionFour(inputArr);
          endTime = System.nanoTime();
          nano_runtime = (endTime - startTime);
          System.out.println("Fourth algorithms max sum: " + algor4Sum);
          print_runtime(nano_runtime);
          
        }
        System.out.println("\n-----------------------------");
    }

    public static double option_3(int[] inputArr, String algos) {
        long startTime;
        long endTime;
        long nano_runtime;
        
        
        if (algos.contains("1")){

          startTime = System.nanoTime();
          int algor1Sum = solutionOne(inputArr);
          endTime = System.nanoTime();
          nano_runtime = (endTime - startTime);
          System.out.println("First algorithms max sum: " + algor1Sum);
          return print_runtime(nano_runtime);
        }
        
        else if (algos.contains("2")){
          startTime = System.nanoTime();
          int algor2Sum = solutionTwo(inputArr);
          endTime = System.nanoTime();

          nano_runtime = (endTime - startTime);   
          System.out.println("Second algorithms max sum: " + algor2Sum);
          return print_runtime(nano_runtime);
        }

        else if (algos.contains("3")){
          startTime = System.nanoTime();
          int algor3Sum = mssJunior(inputArr, 0, inputArr.length-1);
          endTime = System.nanoTime();
          nano_runtime = (endTime - startTime);
          System.out.println("Third algorithms max sum: " + algor3Sum);
          return print_runtime(nano_runtime);
        }

        else if (algos.contains("4")){
          startTime = System.nanoTime();
          int algor4Sum = solutionFour(inputArr);
          endTime = System.nanoTime();
          nano_runtime = (endTime - startTime);
          System.out.println("Fourth algorithms max sum: " + algor4Sum);
          return print_runtime(nano_runtime);
          
        }
        return 0.0;
        
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
    public static int[] random_gen_array(int n) {
        Random rand = new Random();
        
        int[] intArray = new int[n];

        //generate random values for the array
        for (int i = 0; i < intArray.length; i++) {

            intArray[i] = rand.nextInt(101) - 50;

        }

        return intArray;

    }
}
