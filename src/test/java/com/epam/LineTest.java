package com.epam;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.Assert.*;

public class LineTest {
    Line line;
    Operator operator;
    Lock lock;

    @Before
    public void setUp() throws Exception {
        lock = new ReentrantLock();
        operator = new Operator(1);
        line = new Line(operator);
    }

    @Test
    public void dontTakeACall() {
        assertFalse(line.takeACall(new Customer(false)));
    }

    @Test
    public void takeACall() {
        operator.setLock(lock);
        assertTrue(line.takeACall(new Customer(true)));
    }
}