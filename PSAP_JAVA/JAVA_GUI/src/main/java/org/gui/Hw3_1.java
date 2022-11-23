package org.gui;

import javax.swing.*;
import java.awt.*;

class Hw3_1Panel extends JPanel {
    public Hw3_1Panel() {
        setLayout(new GridLayout(1,10, 1, 1));
        JButton but0 = new JButton("0");
        JButton but1 = new JButton("1");
        JButton but2 = new JButton("2");
        JButton but3 = new JButton("3");
        JButton but4 = new JButton("4");
        JButton but5 = new JButton("5");
        JButton but6 = new JButton("6");
        JButton but7 = new JButton("7");
        JButton but8 = new JButton("8");
        JButton but9 = new JButton("9");
        but0.setOpaque(true);
        but0.setBorderPainted(false);
        but0.setBackground(Color.RED);

        but1.setOpaque(true);
        but1.setBorderPainted(false);
        but1.setBackground(Color.ORANGE);

        but2.setOpaque(true);
        but2.setBorderPainted(false);
        but2.setBackground(Color.YELLOW);

        but3.setOpaque(true);
        but3.setBorderPainted(false);
        but3.setBackground(Color.GREEN);

        but4.setOpaque(true);
        but4.setBorderPainted(false);
        but4.setBackground(Color.BLUE);

        but5.setOpaque(true);
        but5.setBorderPainted(false);
        but5.setBackground(new Color(0,0,80));

        but6.setOpaque(true);
        but6.setBorderPainted(false);
        but6.setBackground(Color.MAGENTA);

        but7.setOpaque(true);
        but7.setBorderPainted(false);
        but7.setBackground(Color.GRAY);

        but8.setOpaque(true);
        but8.setBorderPainted(false);
        but8.setBackground(Color.PINK);

        but9.setOpaque(true);
        but9.setBorderPainted(false);
        but9.setBackground(Color.LIGHT_GRAY);

        add(but0);
        add(but1);
        add(but2);
        add(but3);
        add(but4);
        add(but5);
        add(but6);
        add(but7);
        add(but8);
        add(but9);

    }


}
public class Hw3_1 extends JFrame {
    public Hw3_1() {
        this.setTitle("Ten Color Buttons Frame");
        this.setSize(800, 400);

        add(new Hw3_1Panel());

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Hw3_1();
    }
}
