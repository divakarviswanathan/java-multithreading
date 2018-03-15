package edu.diva.java.core.threads;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/*
 * Cyclic barrier acts as a barrier at which point all the threads have to wait at until they meet there.
 * 
 * Additionally, CyclicBarrier can execute an action when all threads meet at the barrier point.
 * 
 * The threads waiting at  CyclicBarrier wait until
 * 
 * 1) The last thread arrives (by calling the await method)
 * 2) When one of the thread is interrupted
 * 3) The cyclicbarrier is reset by some other external thread
 * 4) When one of the thread times out waiting
 */
public class CyclicBarrierExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Runnable cyclicBarrierAction = () -> System.out.println("Barrier Completed");
		CyclicBarrier cb = new CyclicBarrier(2, cyclicBarrierAction);
		Runnable cyclicBarrierAction2 = () -> System.out.println("Barrier2 Completed");
		CyclicBarrier cb2 = new CyclicBarrier(2, cyclicBarrierAction2);
		CyclicBarrierThread cbt1 = new CyclicBarrierThread(cb, cb2);
		CyclicBarrierThread cbt2 = new CyclicBarrierThread(cb, cb2);
		new Thread(cbt1).start();
		new Thread(cbt2).start();
		
	}

}

class CyclicBarrierThread implements Runnable {
	private CyclicBarrier cb;
	private CyclicBarrier cb2;
	
	CyclicBarrierThread(CyclicBarrier cb,CyclicBarrier cb2) {
		this.cb = cb;
		this.cb2=cb2;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
			this.cb.await();
			System.out.println("called cb wait");
			Thread.sleep(1000);
			this.cb2.await();
			System.out.println("called cb2 wait");
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}