
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

	/**
	 * Implementation of various sorting algorithms 
	 * that includes a framework for testing with various input array sizes and over
	 * multiple runs
	 * 
	 */

public class Sorts {

private static Random r = new Random(System.currentTimeMillis());
public static void main(String args[]) { 

System.out.println("running main");
//use an integer variable to decide which sorting algorithm to use below
int type = 0; 

			
///adjust input size to vary size of arrays
for (int inputSize = 1; inputSize < 10; inputSize++) {
//vary total Runs to give you many empirical tests
	System.out.print("inputSize " + inputSize);
	
	int totalRuns = 1000;
	System.out.println(" total runs " + totalRuns); 
    

	long totalruntime = 0;

	for (int run = 0; run < totalRuns; run++) {

	int[] nums = new int[inputSize];

    for (int i = 0; i < nums.length; i++) {
	 nums[i] = r.nextInt(5 * inputSize);
	  }
    
  
    long time = System.nanoTime();

					switch (type) {
						case 0:
						//selectionsort algorithm
					     selectionSort(nums);
						break;
						
						case 1:
						//insertsort algorithm
						insertionSort(nums);
						break;
						
						case 2:
						bogoSort(nums);
						break;

						case 3:
						enhancedMergeSort(nums)
						break;

						case 4:
						quickSort(nums, 0, nums.length-1);
						break;

						case 5:
						enhancedQuickSor(nums, 0, nums.length-1);
						break;
						
						default:
							System.err.printf("\nBad sort ID '%d'", type);
							System.exit(-2);
					}


					totalruntime += System.nanoTime() - time;

				}
				//printout runtime.
				System.out.println("total run time " + totalruntime);
			}

		}
		
		//********print helper class*****
		// Prints the input array 
	    private static void printArray(int arr[]) 
	    { 
	        int n = arr.length; 
	        for (int i=0; i<n; ++i) 
	            System.out.print(arr[i]+" "); 
	        	System.out.println(); 
	    } 
	 



		// ***************************** Insertion Sorts *****************************


		//insert your MergeSort implementation here
		public static int[] mergeSort(int[] a) {

			int n = a.length;
	
			// base case
			if (n == 1) {
				return a;
			}
	
			// create left and right sub-arrays
			int mid = (a.length-1/2);
			int[] left = new int[mid];
			int[] right = new int[a.length - mid];
			for(int i = 0; i < mid; i++){
				left[i] = a[i];
			}
			for(int i = 1; i <= mid; i++){
				left[i] = a[mid+i+1];
			}
			left = mergeSort(left);
			right = mergeSort(right);
	
			int[] mergedArray = merge(left, right);
	
			return mergedArray;
		}
	
		public static int[] enhancedMergeSort(int[] a) {
	
			int n = a.length;
	
			if(n <= 10){
				insertionSort(a);
				return a;
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

		//insert your QuickSort implementation here
		public static void quickSort(int[] arr, int low, int high){
			if(low < high){
				int pi = partition(arr, low, high);
				quickSort(arr, low, pi-1);
				quickSort(arr, pi+1, high);
			}
		}

		private static int partition(int[] arr, int low, int high) {
			int middle = ( low + high ) / 2;
            if( arr[middle] < arr[low] ){
				int swap = arr[low];
				arr[low] = arr[middle];
				arr[middle] = swap;
			}
            if(arr[high] < arr[low]){
				int swap = arr[low];
				arr[low] = arr[high];
				arr[high] = swap;
			}
            if(arr[high] < arr[middle]){
				int swap = arr[high];
				arr[high] = arr[middle];
				arr[middle] = swap;
			}
			int swap = arr[high-1];
			arr[high-1] = arr[middle];
			arr[middle] = swap;
			int pivot = arr[high-1];

			int i = low - 1;
			for(int j = low; j <=high-1; j++){
				if(arr[j] < pivot){
					i++;
					int swap2 = arr[i];
            		arr[i] = arr[j];
            		arr[j] = swap2;
				}
			}
			int swap2 = arr[i+1];
            arr[i+1] = arr[high];
			arr[high] = swap2;
			return i+1;
		}
		public static void enhancedQuickSor(int[] arr, int low, int high) {
			if(high <= low + 10){
				insertionSort(arr);
				return;
			}
			
                
			shuffle(arr);;
			if(low < high){
				int pi = partition(arr, low, high);
				quickSort(arr, low, pi-1);
				quickSort(arr, pi+1, high);
			}
		}

		
				public static void insertionSort(int[] arr) {
					System.out.println("Insertion Sort"); 
				  for (int i = 1; i < arr.length; i++) {
					  int valueToSort = arr[i]; 
					  int j = i;
					  
					  while (j > 0 && arr[j-1] > valueToSort) {
						  arr[j] = arr[j-1]; 
						  j--; 
					  }
					  arr[j] = valueToSort; 
				  }
            
			    printArray(arr);
			    
				}

		

		// ***************************** Selection Sort ****************************

		public static void selectionSort(int[] nums) {
			int minindex;
			for (int i = 0; i < nums.length - 1; i++) {
				minindex = i;
				for (int j = i; j < nums.length; j++) {
					if (nums[j] < nums[minindex]) {
						minindex = j;
					}
				}
				if (minindex != i) {
					int tmp = nums[i];
					nums[i] = nums[minindex];
					nums[minindex] = tmp;
				}
			}
		}

		// ***************************** Silly Sorts *****************************
		//*** the silliest sorts of them all
		public static void bogoSort(int[] nums) {
			while (!isSorted(nums)) {
				shuffle(nums);
			}
		}
		
       //******shuffle helpfer for bogoSort
		// Knuth Shuffle
		private static void shuffle(int[] nums) { 
			int n, tmp;
			for (int i = nums.length - 1; i > 0; i--) {
				n = r.nextInt(i + 1);
				tmp = nums[i];
				nums[i] = nums[n];
				nums[n] = tmp;
			}
		}
		
		//**helper function to check if your array is sorted or not
		public static boolean isSorted(int[] nums) {
			for (int i = 0; i < nums.length - 1; i++) {
				if (nums[i] > nums[i + 1]) {
					return false;
				}
			}
			return true;
		}
	

	}
