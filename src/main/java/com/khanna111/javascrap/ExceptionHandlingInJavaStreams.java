package com.khanna111.javascrap;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class ExceptionHandlingInJavaStreams {

	static Consumer<String> exceptionHandledConsumer(Consumer<String> unhandledConsumer) {
	    Consumer<String> c =  obj -> {
	        try {
	            unhandledConsumer.accept(obj);
	            System.out.println();
	        } catch (NumberFormatException e) {
	            System.err.println(
	                    "Can't format this string");
	        }
	        System.out.println();
	    };
	    return c;
	}

	
	static <T, R> Function<T, R> exceptionHandlingFunction(Function<T, R> f) {
		Function<T, R> o = x -> { 
			try {
				R r = f.apply(x); 
				return r;
			}
			catch (NumberFormatException n) {
				System.err.println(
	                    "Can't format this string");
				return null;
			}
		};
		
		return o;

	}

	public static void main(String[] args) {
	    List<String> integers = Arrays.asList("44", "xyz", "145");
	    // ForEach requires a Consumer as arg. 
	    // exceptionHandledConsumer also takes in a Consumer (Consumer A) as arg and returns 
	    // a Consumer (Consumer B).
	    // Consumer B is coded to envelop Consumer A. 
//	    integers.forEach(exceptionHandledConsumer(str -> System.out.println(Integer.parseInt(str))));
	    
	    
	    // This one take a Function as an argument, envelops that and returns the enveloping
	    // Function. Same as the above Consumer example but with a function.
	    // This is useful for cases when an output is required.
	    checkedExceptions();
	    
	    
	}



	static void checkedExceptions() {

		List<String> integers = Arrays.asList("44", "373", "qw", "145");

		integers.stream().map(exceptionHandlingFunction(x -> Integer.parseInt(x))).filter(x -> x!=null ).forEach(x -> System.out.println(x));

	}

	

}
