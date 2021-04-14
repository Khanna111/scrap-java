package com.khanna111.javascrap;
import java.util.Arrays;
import java.util.List;

public class TwoDArrayToIntStream {

	
	public static void main(String[] args) {
		
		
		
		
	    int[] student1 = {3, 2, 6, 4, 3, 6, 6, 7, 3, 2};
	    int[] student2 = {8, 7, 8, 9, 10, 7, 6, 8, 9, 6};
	    int[] student3 = {2, 5, 3, 1, 4, 3, 3, 2, 5, 6};
	    int[] student4 = {2, 5, 50, 1, 4, 3, 3, 2, 5, 6};
	    int[][] allStudents = {student1, student2, student3, student4};
	    int numberFails = 0;
	    int passMark = 50;

	    // YOUR CODE GOES HERE
	    
	    int sum = 
		   	Arrays.stream(allStudents)
		   		.peek(a -> System.out.println(Arrays.toString(a)))
		   		.flatMapToInt(a -> Arrays.stream(a)) // returns an IntStream
		   		.reduce(0, (i, j) -> i +j) 		;
		
	    // will return the sum of all the elements across the arrays.
	    System.out.print(sum);
	   


	    // will return the count of the arrays - the sum of the elements
	    // of which are equal to or cross the threshold.
	    long count = 
    	   	Arrays.stream(allStudents)
    	   		.peek(a -> System.out.println(Arrays.toString(a)))
    	   		.filter(a -> thresholdSum(a) )
    	   		.count();
    	   	
    	System.out.print(count);
    	   


	    

	}
	
	public static boolean thresholdSum(int[] a) {
		
		int threshold = 50;
		int sum =0;
		for (int i : a) {
			sum += i;
			if (sum >= threshold) {
				return true;
			}
			
		}
		return false;
		
		
	}
	
	public void findingSumUsingReduce() {
		List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
		Integer sum = integers.stream()
		  .reduce(0, (a, b) -> a + b);
	}
}
