package com.epam;

import java.util.Queue;
import java.util.concurrent.locks.Lock;

public class Operator {
    private Lock lock;
    private Queue<Customer> customerQueue;
    private int quantityCustomer;

    public Operator(int quantityCustomer) {
        this.quantityCustomer = quantityCustomer;
    }

    public void serveCustomer(Customer customer) {
        while ((customer != null) && (this.quantityCustomer > 0)) {
            try {
                Thread.sleep(customer.getTalkTime());
                Main.LOG.info("Customer #" + customer.getNumber() + " served");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            customer = null;
            this.quantityCustomer--;
            if (this.quantityCustomer > 0) {
                customer = this.customerQueue.poll();
                if (customer != null) {
                    Main.LOG.info("Operator pulled customer #" + customer.getNumber());
                }
            }
        }
        if ((customer == null) && (this.quantityCustomer > 0)) {
            lock.unlock();
        }
    }

    public int getQuantityCustomer() {
        return quantityCustomer;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }

    public Lock getLock () {
        return lock;
    }

    public void setCustomerQueue(Queue<Customer> queue) {
        this.customerQueue = queue;
    }

    public Queue<Customer> getCustomerQueue() {
        return customerQueue;
    }
}
