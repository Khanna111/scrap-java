package com.khanna111.javascrap;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.checkerframework.common.reflection.qual.Invoke;

import lombok.extern.java.Log;
import lombok.extern.slf4j.XSlf4j;


@XSlf4j
public class CompletableFutureReactor1 {
	
	
	public static void main(String[] args) {
		ExecutorService e = Executors.newFixedThreadPool(2);
		CompletableFuture<String> c1 = CompletableFuture.supplyAsync(() ->  invoker("c1", 7000), e);
		CompletableFuture<String> c2 = CompletableFuture.supplyAsync(() ->  invoker("c2", 1000), e);
		CompletableFuture<String> c3 = CompletableFuture.supplyAsync(() ->  invoker("c3", 1000), e);
		
		CompletableFuture<String> c4 = c3.thenCombine(c2, (a,b) -> invoker(a+b,  2000));
		
		try {
			log.info(c4.get());
		} catch (InterruptedException | ExecutionException e1) {
			log.warn("Error", e1);
		}
		
		
		
		
	}

	
	static String invoker(String name, long time) {
		log.info("ENTER: {}", name);
		try {
			Thread.sleep(time);		}
		catch (Exception e) {
			log.warn("Exception", e);
		}
		log.info("EXIT: {}", name);
		return "\n" + name;
	}
	
	
	
}


