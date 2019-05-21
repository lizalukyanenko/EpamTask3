package com.epam;

import org.junit.Before;
import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static org.junit.Assert.*;

public class CallCenterTest {
    CallCenter callCenter;
    ExecutorService executorService;
    ScheduledExecutorService scheduledExecutorService;
    Queue<Customer> call;

    @Before
    public void setUp() throws Exception {
        callCenter = new CallCenter(1,1);
        executorService = callCenter.getService();
        scheduledExecutorService = callCenter.getTimeToLeave();
        call = callCenter.getCall();
    }

    @Test
    public void serveCustomer() {
        callCenter.serveCustomer(new Customer(true));
        assertEquals(call, callCenter.getCall());
    }

    @Test
    public void getService() {
        assertEquals(executorService, callCenter.getService());
    }

    @Test
    public void getTimeToLeave() {
        assertEquals(scheduledExecutorService, callCenter.getTimeToLeave());
    }

    @Test
    public void getCall() {
        assertEquals(call, callCenter.getCall());
    }
}