package edu.diva.java.core.threads;

/*
 * Thread interruption is a mechanism to interrupt a thread.
 * 
 * There are cases in which we would want to interrupt a long running thread.
 * 1) When servicing a web request, we may distribute the process across several threads, when some or all of the threads fail, we will have to interrupt the remaining threads
 * 2) While shutting down an application, which might use threads. During application shutdown, we may have to quickly stop the threads from running.
 * 
 *   When a Thread instance interrupt() method is called, the interrupt flag is set to true. 
 *   
 *   If a blocked thread (thread that is sleeping or waiting) calls the interrupt method, it throws a ThreadInterrupted Exception
 *   
 *   If an active thread calls, only the threads interrupt flas is set to true.
 *   
 *   If the threads interrupt flag is true and if the interrupted() method is called, then 
 */
public class ThreadInterruptionExample {

	public static void main(String[] args) throws InterruptedException {
		Runnable r = () -> {
			for(int i = 0;i<5;i++) {
				System.out.println(i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Thread t = new Thread(r);
		t.start();
		Thread.sleep(3000);
		t.interrupt();
		//System.out.println(t.isInterrupted());
		
		/*
		 * 0
1
2
3
false
true
java.lang.InterruptedException: sleep interrupted
4
	at java.lang.Thread.sleep(Native Method)
	at edu.diva.java.core.threads.ThreadInterruptionExample.lambda$0(ThreadInterruptionExample.java:25)
	at java.lang.Thread.run(Thread.java:748)
		 */
	}

}
