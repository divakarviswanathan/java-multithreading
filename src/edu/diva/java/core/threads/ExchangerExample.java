package edu.diva.java.core.threads;

import java.util.concurrent.Exchanger;

public class ExchangerExample {

	public static void main(String[] args) {
		Exchanger<String> e = new Exchanger<String>();
		ThreadClass t1 = new ThreadClass(e, "A");
		ThreadClass t2 = new ThreadClass(e, "B");
		ThreadClass t3 = new ThreadClass(e, "C");
		new Thread(t1).start();
		new Thread(t2).start();
		new Thread(t3).start();
	}

}

class ThreadClass implements Runnable {

	Exchanger<String> e;
	String s;
	
	ThreadClass(Exchanger<String> e, String s) {
		this.e = e;
		this.s = s;
	}
	
	@Override
	public void run() {
		String temp = this.s;
		try {
			this.s = this.e.exchange(s);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"\t Before:\t"+temp+"\t After:\t"+this.s);
	}
	
}