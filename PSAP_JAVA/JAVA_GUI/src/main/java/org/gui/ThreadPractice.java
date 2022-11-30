package org.gui;

class MyThread extends Thread {
    int start;
    int end;
    int sum;

    public MyThread(String str) {
        super(str);
    }
    public MyThread(String str, int s, int e) {
        super(str);
        start = s;
        end = e;
    }

    @Override
    public void run() {
        super.run();
        sum = 0;
        for (int i = 1; i <= 1000; i++) {
            sum += i;
        }
        System.out.println("End: " + getName() + "sum=" + sum);
    }
}

class MyRunnable implements Runnable {
    String name;
    int sum;

    public MyRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        sum = 0;
        for (int i = 1; i <= 1000; i++) {
            sum += i;
        }
        System.out.println("End: " + name + "sum=" + sum);
    }
}

public class ThreadPractice implements Runnable {
    @Override
    public void run() {
        int sum = 0;
        for (int i = 1; i <= 1000; i++) {
            sum += i;
        }
        System.out.println("End: t5 sum=" + sum);
    }

    public static void main(String[] args) {
        // 아래 코드는 멀티스레딩이 아님. main이 run()을 직접 했음
        // run() 을 start()로 바꾸어줘야함
        System.out.println("Start:" + Thread.activeCount());
        MyThread t1 = new MyThread("t1");

        MyThread t2 = new MyThread("t1");

        Thread t3 = new Thread(new Runnable() {
            int sum;
            @Override
            public void run() {
                sum = 0;
                for (int i = 1; i <= 1000; i++) {
                    sum += i;
                }
                System.out.println("End: t3 sum=" + sum);

            }
        });

        Thread t4 = new Thread(() -> {
            int sum = 0;
            for (int i = 0; i <= 1000; i++) {
                sum += i;
            }
            System.out.println("End:t4 sum=" + sum);
        });

//        Thread t5 = new Thread(this);
        // -> static 이라서 this 불가능
        Thread t5 = new Thread(new ThreadPractice());

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        System.out.println("Finish:" + Thread.activeCount());

        int sum = t1.sum + t2.sum;
    }
}
