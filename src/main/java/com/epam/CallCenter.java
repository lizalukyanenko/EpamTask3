package com.epam;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CallCenter {
    private Lock lock = new ReentrantLock();
    private Queue<Customer> call;
    private List<Line> lines;
    private int lineQuantity;
    private ExecutorService service;
    private AtomicInteger count;
    ScheduledExecutorService timeToLeave = Executors.newScheduledThreadPool(10);

    public CallCenter(int callQuantity, int lineQuantity) {
        call = new ArrayBlockingQueue(callQuantity);
        lines = new ArrayList();
        this.lineQuantity = lineQuantity;
        service = Executors.newFixedThreadPool(lineQuantity + 1);
        this.count = new AtomicInteger(0);
        for (int i = 0; i < lineQuantity; i++) {
            Line line = initializeLine();
            this.lines.add(line);
        }

    }

    public void serveCustomer(Customer customer) {
        customer.setNumber(count.incrementAndGet());
        service.submit(() -> process(customer));
    }

    public ExecutorService getService() {
        return service;
    }

    public ScheduledExecutorService getTimeToLeave() {
        return timeToLeave;
    }

    public Queue<Customer> getCall() {
        return call;
    }

    private Line initializeLine() {
        Operator operator = new Operator(10 + new Random().nextInt(5));
        Line line = new Line(operator);
        operator.setCustomerQueue(this.call);
        return line;
    }

    private void process(Customer customer) {
        if (this.call.isEmpty()) {
            boolean isOn = false;
            for (Line line : lines) {
                if (line.takeACall(customer)) {
                    isOn = true;
                    break;
                }
            }
            if (!isOn) {
                queueCustomer(customer);
            }
        } else {
            queueCustomer(customer);
        }
    }

    private void queueCustomer(Customer customer) {
        boolean isAdded = false;
        try {
            isAdded = this.call.add(customer);
        } catch (Exception ex) {
            Main.LOG.info("Customer #" + customer.getNumber() + " go away NOT serve - queue fulled!");
        }
        if (isAdded) {
            timeToLeave.schedule(() -> leaveQueue(customer), 2 + new Random().nextInt(1), TimeUnit.SECONDS);
            Main.LOG.info("Customer #" + customer.getNumber() + " add to queue");
        }
    }

    private void leaveQueue(Customer customer){
        lock.lock();
        if(call.contains(customer)){
            call.remove(customer);
            Main.LOG.info("Customer #" + customer.getNumber() + " go away NOT serve - time over!");
            recall(customer);
        }
        lock.unlock();
    }

    private void recall(Customer customer){
        boolean isWant2Recall = new Random().nextBoolean();
        if(isWant2Recall){
            if(call.add(customer)){
                Main.LOG.info("Customer #" + customer.getNumber() + " want to call again");
            }
        }
    }
}
