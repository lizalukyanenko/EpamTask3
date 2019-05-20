package com.epam;

import java.util.Random;

public class Run {
    private static Run ourInstance = new Run();

    public static Run getInstance() {
        return ourInstance;
    }

    private Run() {
    }

    public void running() {
        CallCenter callCenter = new CallCenter(10,5);
        for (int i = 0; i < 20; i++) {
            Customer customer = new Customer(new Random().nextBoolean());
            callCenter.serveCustomer(customer);
            System.out.println("Customer " + customer.getNumber() + " enter to CallCenter");
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
