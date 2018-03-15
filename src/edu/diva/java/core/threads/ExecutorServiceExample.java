package edu.diva.java.core.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 * ExecutorService is a java concurrent implementation of ThreadPool mechanism
 * 
 * There are several methods inside the executor framework
 * 
 * 1. execute() takes a runnable object returns nothing
 * 2. submit() takes a runnable object returns a Future object. If the future.get() is null then the task is executed properly
 * 3. submit() takes a callable object returns a Future object. future.get() returns the actual object returned by the callable
 * 4. invokeAny() takes a collection of callables. Executes one of the callable, when it executes properly, it returns its results and cancels other callables from executed. Does not run any particular callable, it is randomly chosen.
 * 5. invokeAll() takes a collection of callables. Executes all callables. returns a list of future objects
 * 
 * ExecutorService is an example of Factory pattern
 * 
 * 
 * ThreadPoolExecutor:
 * 
 * In a ThreadPoolExecutor, the number of threads created is based on the parameters such as corePoolSize and maxPoolSize
 * 
 * ScheduledThreadPoolExcutor:
 * 
 * Another version of Executor, where the threads are scheduled to execute after a specified amount of time or at particular interval of time. Basically it is scheduling the threads
 */

public class ExecutorServiceExample {
	private static int i = 0;
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		Callable c = () -> {i++; return "hello"+i;};
		List<Future> futures = new ArrayList<>();
		for(int j = 0 ;i < 5;j++) {
			futures.add(executorService.submit(c));
		}
		for(Future future : futures) {
		try {
				System.out.println(future.get());
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		executorService.shutdown();
	}

}
