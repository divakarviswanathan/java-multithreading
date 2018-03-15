package edu.diva.java.core.threads.locklevels;

/*
 Run this program to check the output 1 and 2 being printed alternatively or randomly.
  Because 2 threads using two different objects of Sample3
 */
class Sample3 {
	String s;
	Sample3(String s) {
		this.s = s;
	}
	
	public synchronized void display() throws InterruptedException {
		for(int i = 0 ;i < 5;i++) {
			System.out.println(s);
			Thread.sleep(500);
		}
	}
}

class OLL implements Runnable {

	Sample3 s;
	
	OLL(Sample3 s) {
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
public class ObjectLevelLockExample2 {

	public static void main(String[] args) {
		Sample3 s1 = new Sample3("1");
		Sample3 s2 = new Sample3("2");
		OLL o1 = new OLL(s1);
		OLL o2 = new OLL(s2);
		new Thread(o1).start();
		new Thread(o2).start();
	}

}
