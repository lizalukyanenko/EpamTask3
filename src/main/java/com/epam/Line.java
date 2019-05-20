package com.epam;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Line {
    private Lock lock = new ReentrantLock();
    private Operator operator;

    public Line (Operator operator) {
        this.operator = operator;
        operator.setLock(lock);
    }

    public boolean takeACall(Customer customer) {
        if (lock.tryLock()) {
            operator.serveCustomer(customer);
            return true;
        } else {
            return false;
        }
    }
}
