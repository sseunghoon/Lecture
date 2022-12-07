package org.gui;

import javax.swing.plaf.TableHeaderUI;
import java.awt.*;

class BankClient extends Thread {
    BankAccount account;

    BankClient(String name, BankAccount bankAccount) {
        super(name);
        account = bankAccount;
    }

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 100; i++) {
            account.deposit(10, this);
        }
    }
}

class BankAccount {
    long money = 0;

//    synchronized void deposit(int inc, BankClient client) {
    void deposit(int inc, BankClient client) {
        synchronized (this) {
            long cur = money;
            cur = cur + inc;

//            멀티스레딩에서 우선순위를 낮춰달라
//            Thread.yield();
            try {
                Thread.sleep((int)(Math.random())*10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            money = cur;
        }


        System.out.println("Current Money: " + money + "by " + client.getName());
    }
}

public class ThreadSynchronizedPractice {

    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        BankClient client1 = new BankClient("c1", bankAccount);
        BankClient client2 = new BankClient("c1", bankAccount);
        BankClient client3 = new BankClient("c1", bankAccount);

        client1.start();
        client2.start();
        client3.start();

    }
}
