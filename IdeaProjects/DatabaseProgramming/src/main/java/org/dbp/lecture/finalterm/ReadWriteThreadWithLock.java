package org.dbp.lecture.finalterm;

import java.util.concurrent.locks.ReentrantLock;

public class ReadWriteThreadWithLock extends Thread {

    private int indent;
    private ReentrantLock lock;
    private long startTime;

    public ReadWriteThreadWithLock(int indent, ReentrantLock lock, long startTime) {
        this.indent = indent;
        this.lock = lock;
        this.startTime = startTime;
    }

    @Override
    public void run() {
        String indentation = "";
        for (int i = 0; i < 10; i++) {
            lock.lock();
            // CRITICAL SECTION의 시작
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
            ExampleWithReentrantLock.count = tCount;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // CRITICAL SECTION의 종료
            lock.unlock();

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
