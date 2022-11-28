package org.dbp.lecture.finalterm;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ExampleWithReentrantReadWriteLock {

    public static int count = 0;

    public static void main(String[] args) throws InterruptedException {

        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        long pre = System.currentTimeMillis();

        new ReadWriteWithReadWriteLock(0, lock, pre).start();
        new ReadWriteWithReadWriteLock(1, lock, pre).start();
        new ReadWriteWithReadWriteLock(2, lock, pre).start();

        System.out.println("MAIN TERMINATED");

//    MAIN TERMINATED
//    7824
//    24193
//    25969
//    30981

    }
}


