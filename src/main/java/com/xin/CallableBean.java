package com.xin;

import java.util.concurrent.Callable;

/**
 * @author Three
 * @since 18/6/9上午7:51
 */

public class CallableBean implements Callable {
    private long[] money;

    public CallableBean(long... money) {
        this.money = money;
    }

    public Object call() throws Exception {
        Thread.sleep(5000);
        long totalMoney = 0L;
        for (long temp : money) {
            totalMoney += temp;
        }

        return totalMoney;
    }
}
