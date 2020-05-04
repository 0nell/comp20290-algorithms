import java.util.ArrayList;

import java.util.List;
import java.util.Random;

public class Sorting {
    public static int[] selectionSort(int arr[]) {
        int temp;
        int min_index;

        for (int i = 0; i < arr.length - 1; i++) {
            min_index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min_index] > arr[j]) {
                    min_index = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[min_index];
            arr[min_index] = temp;
        }
        return arr;
    }

    public static int[] insertSort(int arr[]) {
        int key;
        int j;
        for (int i = 1; i < arr.length; ++i) {
            key = arr[i];
            j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
                arr[j + 1] = key;
            }
        }
        return arr;
    }

    public static boolean is_sorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static int[] bogoSort(int[] nums) {
        while (!is_sorted(nums)) {
            shuffle(nums);
        }
        return nums;
    }
    
    public static void shuffle(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            
            int r = (int) (Math.random() * (i + 1));
            int swap = a[r];
            a[r] = a[i];
            a[i] = swap;
        }
    }

    public static void printArray(int[] arr) {
        System.out.println("Array ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
            if (i + 1 % 10 == 0) {
                System.out.println("");
            }
        }
    }

    public static int[] setTest(int size) {
        Random rand = new Random();
        int[] testArray = new int[size];
        for (int i = 0; i < size; i++) {
            testArray[i] = rand.nextInt(size * 10);
        }
        return testArray;
    }

    public static int[] mergeSort(int[] a) {

        int n = a.length;

        
        // base case
        if (n == 1) {
            return a;
        }

        // create left and right sub-arrays
        int mid = ((n) /2);
        int[] left = new int[mid];
        int[] right = new int[n - mid];
        for(int i = 0; i < mid; i++){
            left[i] = a[i];
        }
        for(int i = mid; i < n; i++){
            right[i-mid] = a[i];
        }

        left = mergeSort(left);
        right = mergeSort(right);

        int[] mergedArray = merge(left, right);

        return mergedArray;
    }

    public static int[] enhancedMergeSort(int[] a) {

        int n = a.length;

        if(n <= 10){
            return insertSort(a);
        }

        // base case
        if (n == 1) {
            return a;
        }

        // create left and right sub-arrays
        int mid = ((a.length-1) /2);
        int[] left = new int[mid];
        int[] right = new int[a.length - mid];
        for(int i = 0; i < mid; i++){
            left[i] = a[i];
        }
        for(int i = mid; i < a.length-1; i++){
            right[i-mid] = a[i];
        }

        left = enhancedMergeSort(left);
        right = enhancedMergeSort(right);

        if(left[mid-1] <= right[0]){
            int[] mergedArray = left;
            for(int i = 0; i < a.length-mid -1; i++){
                mergedArray[i] = right[i];
            }
            return mergedArray;
        }
        int[] mergedArray = merge(left, right);

        return mergedArray;
    }

    static int[] merge(int[] a, int[] b) {
        int size = a.length+b.length;
        ArrayList<Integer> s = new ArrayList<Integer>();

        List<Integer> aList = new ArrayList<>();
        for(int num:a){
            aList.add(num);
        }
        
        List<Integer> bList = new ArrayList<>();
        for(int num:b){
            bList.add(num);
        }
        
        //repeat while both arrays have elements in them
        while (!aList.isEmpty() && !bList.isEmpty()){
            
            //if element in 1st array is <= 1st element in 2nd array
            if (aList.get(0) <= bList.get(0)){
                s.add(aList.remove(0));
            } 
            else if (bList.get(0) <= aList.get(0)){
                s.add(bList.remove(0));
            }
            
        }
        
        //when while loop ends
        if (!aList.isEmpty()){
            for(int num: aList){
                s.add(num);
            }
        } else if (!bList.isEmpty()){
            for(int num: bList){
                s.add(num);
            }
        } 
        int[] newArr = new int[size];
        for(int i = 0; i < size; i++){
            newArr[i] = s.get(i);
        }
        return newArr;
    }

    
    public static void main(String[] args) {
        int[][] testArray = new int[3][];
        long startTime;
        long elapsedTime;

        for(int i = 0; i < 10; i ++){
            
            

            testArray[i%3] = setTest(i*10);
            startTime = System.nanoTime();
            testArray[i%3] = mergeSort(testArray[i%3]);
            elapsedTime = System.nanoTime() - startTime; 
            System.out.println(elapsedTime);

        
            

            // Commented out because it takes forever
            /*
            testArray[i%3] = setTest(i*10);
            startTime = System.nanoTime();
            testArray[i%3] = bogoSort(testArray[i%3]);
            elapsedTime = System.nanoTime() - startTime; 
            System.out.println(elapsedTime);
            */
             
            
        
            

        }
            

        
    }
}