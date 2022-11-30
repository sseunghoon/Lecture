package org.gui;

import javax.swing.*;
import java.awt.*;

class MyFlickerlingLabel extends JLabel {
    int interval = 0;
    boolean boolBackGroundColor = true;
    MyFlickerlingLabel (String str, int interval) {
        super(str);
        this.interval = interval;

        Thread t = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(interval);
                    if (boolBackGroundColor) {
                        this.setForeground(Color.ORANGE);
                    } else {
                        this.setForeground(Color.blue);
                    }
                    boolBackGroundColor = !boolBackGroundColor;
                }
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        });
        t.start();
    }

}

class MoreThreadPanel extends JPanel implements Runnable{
    JLabel jLabel;
    float time = 0;
    Thread t;
    boolean runFlag = false;

    public MoreThreadPanel() throws Exception{
        jLabel = new JLabel("Time : " + time);
        add(jLabel);

        JButton but = new JButton("start");
        but.addActionListener((e) -> {
            runFlag = !runFlag;
            if (runFlag == true) {
                t = new Thread(this);
                t.start();
                but.setText("stop");
            } else {
                t.interrupt();
                but.setText("start");
            }
        });
        add(but);

        MyFlickerlingLabel sejongLabel = new MyFlickerlingLabel("Sejong", 500);
        add(sejongLabel);
        MyFlickerlingLabel universityLabel = new MyFlickerlingLabel("University", 300);
        add(universityLabel);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(100);
                time += 0.1f;
                jLabel.setText("Time:" + Math.round(time * 100) / 100.0);
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}

class MyCalcThread extends Thread {
    int st;
    int ed;
    int sum = 0;

    public MyCalcThread(String n, int st, int ed) {
        super(n);
        this.st = st;
        this.ed = ed;
    }

    @Override
    public void run() {
        for (int i = st; i < ed; i++) {
            sum += i;
        }
        System.out.println("T: " + getName() + " sum=" + sum);
    }
}

public class MoreThread extends JFrame {
    public MoreThread() throws Exception {
        setTitle("More Thread");
        setSize(500, 500);

        add(new MoreThreadPanel());

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) throws Exception {
        new MoreThread();

        MyCalcThread t1 = new MyCalcThread("t1", 0, 2500);
        MyCalcThread t2 = new MyCalcThread("t2", 2500, 5000);
        MyCalcThread t3 = new MyCalcThread("t3", 5000, 7500);
        MyCalcThread t4 = new MyCalcThread("t4", 7500, 10000);
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        long sum = t1.sum + t2.sum + t3.sum + t4.sum;

        System.out.println("total sum=" + sum);

    }
}
