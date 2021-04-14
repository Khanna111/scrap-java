package com.khanna111.javascrap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

public class PrintStringInReverse {

	public static void main(String[] args) {
		
		String r = "This is a String";
		
		PrintStringInReverse p = new PrintStringInReverse();
//		p.reverse(r);
		
		p.defineArrayAndHashMap();
		
	}
	
	public void reverse(String s) {
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = s.length() -1 ; i >= 0; i--) {
			// odd is even 
			// and even is odd
			// 0 => odd, 1 => even and so on
			
			if (i%2 == 1) {
				// even
				sb.append(Character.toLowerCase(s.charAt(i)));
			}
			else {
				sb.append(Character.toUpperCase(s.charAt(i)));
			}
		}
		
		System.out.println("Reverse: " + sb);
	}
	
	public void defineArrayAndHashMap() {
		int[] arr = { 1, 2, 3, 4 };
		// Another way to generate
		// IntStream.generate(( ) -> { Random r =  new Random(); return r.nextInt();});
		
		for (int i : arr) {
			System.out.println("Looping Array: " + i);
		}
		
		
		Map<Integer, String> map = new HashMap<>();
		
		
		map.put(1, "One");
		map.put(2, "Two");
		
		Set<Entry<Integer, String>> entrySet = map.entrySet();
		
		map.forEach((x,y) -> System.out.println(String.format("Looping map: %s,\t%s ", x, y))) ;
		
//		for (Map.Entry<Integer, String> e : entrySet) {
//			System.out.println(String.format("Looping map: %s,\t%s ", e.getKey(), e.getValue()));
//		}
		
	}
}
