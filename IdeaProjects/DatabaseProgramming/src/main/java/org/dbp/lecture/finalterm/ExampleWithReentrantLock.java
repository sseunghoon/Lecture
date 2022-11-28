package org.dbp.lecture.finalterm;

import java.util.concurrent.locks.ReentrantLock;

public class ExampleWithReentrantLock {

    public static int count = 0;

    public static void main(String[] args) throws InterruptedException {

        ReentrantLock lock = new ReentrantLock();

        long pre = System.currentTimeMillis();

        new ReadWriteThreadWithLock(0, lock, pre).start();
        new ReadWriteThreadWithLock(1, lock, pre).start();
        new ReadWriteThreadWithLock(2, lock, pre).start();
        new ReadWriteThreadWithLock(3, lock, pre).start();


        System.out.println("MAIN TERMINATED");

//    MAIN TERMINATED
//    7824
//    24193
//    25969
//    30981

    }
}
