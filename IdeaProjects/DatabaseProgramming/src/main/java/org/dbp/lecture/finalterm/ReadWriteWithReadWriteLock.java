package org.dbp.lecture.finalterm;


import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteWithReadWriteLock extends Thread {

    private int indent;
    private ReentrantReadWriteLock lock;
    private long startTime;

    public ReadWriteWithReadWriteLock(int indent, ReentrantReadWriteLock lock, long startTime) {
        this.indent = indent;
        this.lock = lock;
        this.startTime = startTime;
    }

    @Override
    public void run() {
        String indentation = "";
        for (int i = 0; i < 10; i++) {
            lock.readLock().lock();
            int tCount = ExampleWithReentrantLock.count;
            tCount++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            indentation = "";
            for (int j = 0; j < indent; j++) {
                indentation += "\t";
            }
            System.out.println(indentation + " " + (System.currentTimeMillis() - startTime) + "R");
            lock.readLock().unlock();
            lock.writeLock().lock();
            ExampleWithReentrantLock.count = tCount;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock.writeLock().unlock();

            indentation = "";
            for (int j = 0; j < indent; j++) {
                indentation += "\t";
            }
            System.out.println(indentation + " " + (System.currentTimeMillis() - startTime) + "W");

        }
        indentation = "";
        for (int j = 0; j < indent; j++) {
            indentation += "\t";
        }
        System.out.println(indentation + (System.currentTimeMillis() - startTime));
    }

}

