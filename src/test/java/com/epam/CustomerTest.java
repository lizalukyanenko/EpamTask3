package com.epam;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {
    boolean lowNeed;
    Customer customerLong;
    Customer customer;
    int number;

    @Before
    public void setUp() throws Exception {
        customer = new Customer(true);
        customerLong = new Customer(false);
    }

    @Test
    public void getTalkTimeLong() {
        assertEquals(10000, customerLong.getTalkTime());
    }

    @Test
    public void getTalkTime() {
        assertEquals(5000, customer.getTalkTime());
    }

    @Test
    public void setNumber() {
        customer.setNumber(1);
        assertEquals(1, customer.getNumber());
    }

    @Test
    public void getNumber() {
        assertEquals(0, customer.getNumber());
    }
}