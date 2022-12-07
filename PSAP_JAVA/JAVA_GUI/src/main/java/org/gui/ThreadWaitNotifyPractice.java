package org.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ProgressButton extends JButton implements Runnable{
    int value = 0; // 0 ~ 100

    Thread t = null;

    ThreadWaitNotifyPanel panel = null;

    ProgressButton(String str) {
        super(str);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.red);
        int w = (int) (getWidth() / 100.0f * value);
        g.fillRect(0, 0, w, getHeight());
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            panel.addValue(this);
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        }


    }
}

class ThreadWaitNotifyPanel extends JPanel implements ActionListener {
    ProgressButton[] bts = new ProgressButton[4];
    JButton wake;
    ThreadWaitNotifyPanel() {
        this.setLayout(null);

        for (int i = 0; i <bts.length ; i++) {
            bts[i] = new ProgressButton("push");
            bts[i].setBounds(50, 50+50*i, 400, 30);
            bts[i].addActionListener(this);
            add(bts[i]);
        }

        wake = new JButton("wake");
        wake.setBounds(50, 300, 400, 30);
        add(wake);
    }

    @Override
    synchronized public void actionPerformed(ActionEvent e) {
        if ((JButton)(e.getSource())== wake) {
            notify();
            return;
        }

        ProgressButton b = (ProgressButton) e.getSource();
        if (b.t == null) {
            b.t = new Thread(b);
            b.t.start();
        }
    }

    synchronized public void addValue(ProgressButton b) {
        try {
            if (b.value == 0)
                b.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        b.value++;
    }
}

public class ThreadWaitNotifyPractice extends JFrame {
    public ThreadWaitNotifyPractice() throws HeadlessException {
        setTitle("Wait / Notify Practice");
        setSize(500, 500);

        add(new ThreadWaitNotifyPanel());

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new ThreadWaitNotifyPractice();
    }
}

/*
import java.awt.Color;
        import java.awt.Graphics;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

        import javax.swing.JButton;
        import javax.swing.JFrame;
        import javax.swing.JPanel;

class ProgressButton extends JButton{
    int value = 0;					// 0~100
    Thread t = null;
    ThreadWaitNotifyPanel p = null;
    ProgressButton(String str, ThreadWaitNotifyPanel in){
        super(str);
        p = in;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.red);
        int w = (int)(getWidth() / 100.0f * value);
        g.fillRect(0, 0, w, getHeight());
    }
}

class ThreadWaitNotifyPanel extends JPanel implements ActionListener{
    ProgressButton [] bts = new ProgressButton[4];
    JButton wake = null;
    ThreadWaitNotifyPanel(){

        this.setLayout(null);

        for(int i = 0; i<bts.length; i++)
        {
            bts[i] = new ProgressButton("push", this);
            bts[i].setBounds(50,50+50*i, 400, 30);
            bts[i].addActionListener(this);
            add(bts[i]);
        }

        wake = new JButton("wake");
        wake.setBounds(50,300,400,30);
        wake.addActionListener(this);
        add(wake);


    }
    private class MyThread extends Thread{
        ProgressButton but = null;
        MyThread(ProgressButton b){
            but = b;
        }
        public void run() {
            for(int i = 0; i<100; i++) {
                addValue(but);
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
            }
        }
    }
    synchronized public void addValue(ProgressButton b) {

        try {
            if(b.value == 0)
                wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        b.value ++;
    }

    @Override
    synchronized public void actionPerformed(ActionEvent e) {
        if((JButton)(e.getSource())== wake) {
            notifyAll();
            return;
        }
        ProgressButton b = (ProgressButton) e.getSource();
        if(b.t == null) {
            b.t = new MyThread(b);
            b.t.start();
        }
    }

}

public class ThreadWaitNotifyPractice extends JFrame {

    ThreadWaitNotifyPractice(){
        setTitle("Wait / Notify Practice");
        setSize(500,500);

        add(new ThreadWaitNotifyPanel());

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        new ThreadWaitNotifyPractice();
    }

}

 */