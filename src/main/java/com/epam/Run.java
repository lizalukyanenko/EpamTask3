package com.epam;

import java.util.Random;

public class Run {
    private static Run instance = new Run();

    public static Run getInstance() {
        return instance;
    }

    private Run() {
    }

    public void running() {
        CallCenter callCenter = new CallCenter(10,5);
        for (int i = 0; i < 20; i++) {
            Customer customer = new Customer(new Random().nextBoolean());
            callCenter.serveCustomer(customer);
            Main.LOG.info("Customer #" + customer.getNumber() + " call to CallCenter");
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        callCenter.getService().shutdown();
        callCenter.getTimeToLeave().shutdown();
    }
}
