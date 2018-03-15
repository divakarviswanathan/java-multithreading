package edu.diva.java.core.threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Locks in Java offer much more sophistication than the synchronized block
 * 
 * The following are some of the differences between Locks and Synchronized Blocks
 * 
 * 1) A lock and unlock sequence can span over more than one methods. Whereas synchronized block has to be at one method level
 * 2) A thread waiting to enter a criticial section at the syncrhonized block can become a victim of starvation. While locks offer much better mechanism for threads to wait for a specified amount of time.
 * 
 * Lock is an interface there are several implementations of it
 * 
 * 1) ReentrantLock
 * 2) ReadWriteLock
 * 
 * ReentrantLock: Suppose if a thread has acquired a lock on an object, that thread can use the same lock to enter any of that locks critical section
 * 
 * ReadWriteLock: Multiple threads are allowed to read while only one thread will be allowed to write. 
 * 
 * ReadLock: Can be acquired when no thread has got the write the lock
 * WriteLock: Can be acquired when no thread has the read lock
 */

class LockedResource {
	
	private Lock l = new ReentrantLock();
	
	public void display() {
		l.lock();
		for(int i=0;i<5;i++) {
			System.out.println(Thread.currentThread().getName() + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		l.unlock();
	}
}

class LET implements Runnable {
	LockedResource lr;
	
	LET(LockedResource lr) {
		this.lr = lr;
	}

	@Override
	public void run() {
		lr.display();
	}
	
}
public class LockExample {

	public static void main(String[] args) {
		LockedResource lr = new LockedResource();
		LockedResource lr2 = new LockedResource();
		LET l1 = new LET(lr);
		LET l2 = new LET(lr2);
		
		new Thread(l1, "T1").start();
		new Thread(l2, "T2").start();
	}

}
