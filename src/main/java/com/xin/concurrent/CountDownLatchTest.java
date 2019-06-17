package com.xin.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author three
 * @since 2019/6/14 17:40
 * <p>
 *
 * </p>
 */
public class CountDownLatchTest {

	public static void main(String[] args) throws Exception {
		int            totalThread = 3;
		long           start       = System.currentTimeMillis();
		CountDownLatch countDown   = new CountDownLatch(totalThread);

		for (int i = 0; i < totalThread; i++) {
			final String threadName = "Thread " + i;
			new Thread(() -> {
				System.out.println(String.format("%s %s", threadName, "started"));
				try {
					Thread.sleep(1000);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				System.out.println(String.format("%s %s", threadName, "ended"));
				countDown.countDown();
			}).start();
		}
		System.out.println("main thread waiting");
		countDown.await();
		long stop = System.currentTimeMillis();
		System.out.println(String.format("Total time : %sms", (stop - start)));
	}
}


