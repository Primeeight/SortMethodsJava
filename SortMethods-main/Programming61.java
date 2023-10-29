import java.util.Random;
import java.util.Scanner;
import java.util.*;
import java.io.*;

/**
 * Program 6
 * 7/6/22
 * Zachary Nelson
 */

public class Programming61 {

    //See Listing 23.6 in Page 887
    // Should not need to be modified from book implementation
    public static void mergeSort(int[] list) {
        if (list.length > 1) {
            //Merge sort the first half
            int[] firstHalf = new int[list.length / 2];
            System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
            mergeSort(firstHalf);

            //Merge sort the second half
            int secondHalfLength = list.length - list.length / 2;
            int[] secondHalf = new int[secondHalfLength];
            System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);
            mergeSort(secondHalf);
            /**
             * double check this method.
             **/
            //Merge firstHalf with secondHalf into list
            merge(firstHalf, secondHalf, list);

        }
    }

    /**
     * merge list1 and list2 into temp
     */
    // Should not need to be modified from book implementation
    public static void merge(int[] list1, int[] list2, int[] temp) {
        int current1 = 0; //Index in list1
        int current2 = 0; //Index in list2
        int current3 = 0; //Index in temp

        while (current1 < list1.length && current2 < list2.length) {
            if (list1[current1] < list2[current2])
                temp[current3++] = list1[current1++];
            else
                temp[current3++] = list2[current2++];
        }
        while (current1 < list1.length)
            temp[current3++] = list1[current1++];
        while (current2 < list2.length)
            temp[current3++] = list2[current2++];

    }

    //See Listing 23.1 in Page 883
    // Should not need to be modified from book implementation

    public static void insertionSort(int[] list) {
        //implement selection sort here.
        for (int i = 1; i < list.length; i++) {
            int currentElement = list[i];
            int k = 8;
            for (k = i - 1; k >= 0 && list[k] > currentElement;
                 k--) {
                list[k + 1] = list[k];
            }
            list[k + 1] = currentElement;
        }
    }

    public static void main(String[] args) {

    /*
     What are we trying to accomplish here?
     To do empirical data analysis, you have to have a sound experimental setup.
     Here we are trying to collect data for a comparative analysis of three different sort algorithms.
     The three algorithms are mergeSort, insertionSort, and Java's native sort Arrays.sort()
     which is dual-pivot Quicksort. I will explain how to set up your experiment to collect data
     for one of the sorting algorithms.

     - Create two integer arrays, list1, and list2 of input size N (see Excel sheet).
     - Populate both arrays with the same data.
     - create two variables, start and stop, of type long for the timers
     - Inside a for loop that iterates 10 times, do the following
          - set the start timer with current time in nanoseconds(see codehelp.txt)
          - call the sort algorithm with list1
          - set the stop timer with current time in nanoseconds(see codehelp.txt)
          - calculate the time elapsed (elapsed = stop-start)
          - output the elapsed time

          - copy list2 back to list 1 (this avoids sorting an already sorted list),
            you can use System.arraycopy() method to do this.
     - You should have 10 output values for the specific N, record these in the relevant row in the Excel sheet.
     - Then record the average of those 10 values. You may compute the average using another variable sum in your code,
       or easily compute it in the Excel sheet.

     You have to perform the above algorithm multiple times to record data for all three algorithms
     and all different inputs of N.

     */
        Scanner in = new Scanner(System.in);
        int N;
        System.out.print("Enter the size of the data set N = ");
        N = in.nextInt();
        int[] list1 = new int[N];
        int[] list2 = new int[N];

        Random rnd = new Random(System.currentTimeMillis());
        for (int i = 0; i < list1.length; i++) {
            if (rnd.nextDouble() < .5)
                list1[i] = (1 + rnd.nextInt(100));
            else
                list1[i] = ((1 + rnd.nextInt(100)) * -1);

            //list2[i] = list1[i];



        } System.arraycopy(list1, 0, list2, 0, list1.length);

        long total = 0;
        long elapsedTime = 0;  // time taken for a single run
        long startT = System.nanoTime();  //start clock
        long endT = System.nanoTime(); //stop clock
        for (int i =0; i < 10; i++) {
            startT = System.nanoTime();
            mergeSort(list1);
            endT = System.nanoTime(); //stop the clock.
            elapsedTime = (endT - startT);
            total = total + elapsedTime;
            System.out.println(elapsedTime + " ns");
            System.arraycopy(list2, 0, list1, 0, list1.length);

        } System.out.println("total Average: " + total/10 + " ns");
        //System.arraycopy(list2, 0, list1, 0, list1.length);

        //call to method to be measured


    }
}
