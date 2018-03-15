package edu.diva.java.core.threads.locklevels;

/*
 * This program is another variation of ObjectLevelLocking, which does object level locking using this keyword
  Run this program to check the output 1 and 2 being printed alternatively or randomly.
  Because 2 threads using two different objects of Sample4
 */
class Sample4 {
	String s;
	Sample4(String s) {
		this.s = s;
	}
	
	public void display() throws InterruptedException {
		synchronized(this) {
		for(int i = 0 ;i < 5;i++) {
			System.out.println(s);
			Thread.sleep(500);
		}
		}
	}
}

class OLL2 implements Runnable {

	Sample4 s;
	
	OLL2(Sample4 s) {
		this.s = s;
	}
	
	@Override
	public void run() {
		try {
			s.display();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
public class ObjectLevelLockExample {

	public static void main(String[] args) {
		Sample4 s1 = new Sample4("1");
		Sample4 s2 = new Sample4("2");
		OLL2 o1 = new OLL2(s1);
		OLL2 o2 = new OLL2(s2);
		new Thread(o1).start();
		new Thread(o2).start();
	}

}
