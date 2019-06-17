package com.xin.concurrent;

import java.util.Date;
import java.util.concurrent.CyclicBarrier;

/**
 * @author three
 * @since 2019/6/17 14:07
 * <p>
 *
 * </p>
 */
public class CycleBarrierTest {

	public static void main(String[] args) {
		int           totalThread = 5;
		CyclicBarrier barrier     = new CyclicBarrier(totalThread);

		for (int i = 0; i < totalThread; i++) {
			String threadName = "Thread " + i;
			new Thread(() -> {
				System.out.println(String.format("%s\t%s %s", new Date(), threadName, " is waiting"));
				try {
					barrier.await();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				System.out.println(String.format("%s\t%s %s", new Date(), threadName, "ended"));
			}).start();
		}
	}

}
