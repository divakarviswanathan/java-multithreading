package edu.diva.java.core.threads;

import java.util.concurrent.CountDownLatch;

/*
 * 
 * A java.util.concurrent.CountDownLatch is a concurrency construct that allows one or more threads to wait for a given set of operations to complete
 * 
 * http://tutorials.jenkov.com/java-util-concurrent/countdownlatch.html
 * 
 * Countdownlatch is used when one or more threads need to wait until some operation happens.
 * The latch is initialized with a count and decremented by countDown method.
 * The threads that are waiting on this latch to become zero will be waiting in by calling await method.
 * When the count reaches zero, the thread comes into life.
 */
public class CountDownLatchExample {

	public static void main(String[] args) {
		CountDownLatch cdl = new CountDownLatch(10);
		Waiter w = new Waiter(cdl);
		Decrementer d = new Decrementer(cdl);
		new Thread(w).start();
		new Thread(d).start();
		
	}
}

class Waiter implements Runnable {

	private CountDownLatch latch;
	
	public Waiter(CountDownLatch latch) {
		this.latch = latch;
	}
	@Override
	public void run() {
		try {
			this.latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Waiter Released");
	}
	
}

class Decrementer implements Runnable {

	private CountDownLatch latch;
	
	public Decrementer(CountDownLatch latch) {
		this.latch = latch;
	}
	@Override
	public void run() {
		int i = 0;
		while(i<10) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
			this.latch.countDown();
			System.out.println("Decrementing");
		}
		System.out.println("Exiting Decrementer");
	}
	
}
