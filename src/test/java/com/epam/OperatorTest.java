package com.epam;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OperatorTest {
    Operator operator;
    Customer customer;
    Queue<Customer> customerQueue;
    Lock lock;
    int quantityCustomer;

    @Before
    public void setUp() throws Exception {
        quantityCustomer = 1;
        operator = new Operator(quantityCustomer);
        customer = new Customer(false);
        lock = new ReentrantLock();
        customerQueue = new ArrayBlockingQueue(1);
    }

    @Test
    public void serveCustomer() {
        lock.lock();
        operator.setCustomerQueue(customerQueue);
        operator.setLock(lock);
        operator.serveCustomer(customer);
        assertEquals(0, operator.getQuantityCustomer());
    }

    @Test
    public void setLock() {
        operator.setLock(lock);
        assertEquals(lock, operator.getLock());
    }

    @Test
    public void getLock() {
        assertEquals(null, operator.getLock());
    }

    @Test
    public void setCustomerQueue() {
        operator.setCustomerQueue(customerQueue);
        assertEquals(customerQueue, operator.getCustomerQueue());
    }

    @Test
    public void getCustomerQueue() {
        assertEquals(null, operator.getCustomerQueue());
    }

    @Test
    public void getQuantityCustomer() {
        assertEquals(quantityCustomer, operator.getQuantityCustomer());
    }
}