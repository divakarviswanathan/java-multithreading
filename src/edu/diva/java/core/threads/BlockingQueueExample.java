package edu.diva.java.core.threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*
 * Blocking Queue implementation is generally used when there are producer and consumer kind of set up.
 * ArrayBlockingQueue comes with a limit, hence it is called a bounded queue 
 */
public class BlockingQueueExample {

	public static void main(String[] args) {
		BlockingQueue<String> queue = new ArrayBlockingQueue<>(1024);
		Producer p = new Producer(queue);
		Consumer c = new Consumer(queue);
		new Thread(p).start();
		new Thread(c).start();
	}

}

class Producer implements Runnable {

	private BlockingQueue<String> queue;
	
	public Producer(BlockingQueue<String> queue) {
		this.queue = queue;
	}
	@Override
	public void run() {
		int i =0;
		while(true) {
			try {
				this.queue.put(i+"");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
	}
	
}

class Consumer implements Runnable {

	private BlockingQueue<String> queue;
	
	public Consumer(BlockingQueue<String> queue) {
		this.queue = queue;
	}
	@Override
	public void run() {
		while(true) {
			try {
				System.out.println(this.queue.take());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
