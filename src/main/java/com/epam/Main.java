package com.epam;

import java.util.Random;
import java.util.logging.Logger;

public class Main {
    public final static Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Run run = Run.getInstance();
        run.running();
    }
}
