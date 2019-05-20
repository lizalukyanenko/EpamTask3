package com.epam;

import java.util.Queue;
import java.util.concurrent.locks.Lock;

public class Operator{
    private Lock lock;
    private Queue<Customer> customerQueue;
    private int quantityCustomer;

    public void serveCustomer (Customer customer) {
        while ( (customer != null) && (this.quantityCustomer > 0) ) {
            try {
                Thread.sleep(customer.getTalkTime());
                System.out.println("Customer " + customer.getNumber() + " served");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            customer = null;
            this.quantityCustomer--;
            if (this.quantityCustomer > 0) {
                customer = this.customerQueue.poll();
                if (customer != null) {
                    System.out.println("Operator pulled customer " + customer.getNumber());
                }
            }
        }
        if ( (customer == null) && (this.quantityCustomer > 0) ) {
            lock.unlock();
        }
    }

    public Operator (int quantityCustomer) {
        this.quantityCustomer = quantityCustomer;
    }

    public void setLock (Lock lock) {
        this.lock = lock;
    }

    public void setCustomerQueue (Queue<Customer> queue) {
        this.customerQueue = queue;
    }
}
