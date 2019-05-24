package com.xin.concurrent;

import com.google.common.util.concurrent.*;
import com.xin.CallableBean;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import static com.xin.Const.printLineSeparator;
import static com.xin.Const.printSeparator;

/**
 * @author Three
 * @since 18/6/9上午8:06
 */
public class FutureTest {
    public static void main(String[] args) throws Exception {
        printSeparator("jdkFuture");
        jdkFutureTest();
        printLineSeparator();

        printSeparator("listenableFuture");
        listenableFuture();

    }

    private static void jdkFutureTest() throws Exception {
        Callable   acct   = new CallableBean(2l, 3l, 34l, 53l, 243l);
        FutureTask task   = new FutureTask(acct);
        Thread     thread = new Thread(task);
        System.out.println("futureTask线程现在开始启动，启动时间为：" + System.nanoTime());
        thread.start();
        System.out.println("主线程开始执行其他任务");

        // 从其他账户获取总金额
        int totalMoney = new Random().nextInt(100000);
        System.out.println("现在你在其他账户中的总金额为" + totalMoney);
        System.out.println("等待私有账户总金额统计完毕...");
        while (!task.isDone()) {
            try {
                Thread.sleep(500);
                System.out.println("私有账户计算未完成继续等待...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("futureTask线程计算完毕，此时时间为" + System.nanoTime());
        System.out.println("您现在的总金额为：" + totalMoney + task.get());
    }

    private static void listenableFuture() throws Exception {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture explosion = service.submit(
                new CallableBean(1l, 32l, 42l)
        );
        Futures.addCallback(explosion, new FutureCallback<Object>() {
            @Override
            public void onSuccess(Object o) {
                System.out.println("总金额为：" + o);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }
}
