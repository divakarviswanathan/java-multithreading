package edu.diva.java.core.threads.locklevels;


/*
 Class Level Locking:

Before understanding class level locking, we should have an idea of the .class object (meta class). For any class, there will be .class object present in the heap by the JVM. 
When a method is locked at the class level, the Thread acquires lock on the .class object present in the heap. It does not need lock on any of the instances of the classes, 
all it needs is the lock on the .class object only.

when a static method is synchronized or a block is synchronized on the class object (like: Sample.class) then multiple threads are prevented to enter that method using any of 
the instances of the class. Irrespective of the number of objects available, only one thread will be able to access that method that is locked at the class level.

 */
class Sample {
	String s;
	Sample(String s) {
		this.s = s;
	}
	public void display() {
		synchronized(Sample.class) {
			for(int i = 0;i<5;i++) {
				System.out.println("hi"+s);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}

class CLLT implements Runnable {

	private Sample s;
	
	CLLT(Sample s) {
		this.s = s;
	}
	
	@Override
	public void run() {
		s.display();
	}
	
}

public class ClassLevelLockExample1 {

	public static void main(String[] args) {
		Sample s = new Sample("1");
		Sample s2 = new Sample("2");
		CLLT c = new CLLT(s);
		CLLT d = new CLLT(s2);
		new Thread(c).start();
		new Thread(d).start();
	}

}
