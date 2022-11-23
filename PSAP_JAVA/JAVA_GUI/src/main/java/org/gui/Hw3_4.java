package org.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class Hw3_4JPanel extends JPanel {
    JButton jButton = new JButton("C");

    public Hw3_4JPanel() {
        setFocusable(true);
        requestFocus(true);
        jButton.setLocation(100, 100);
        jButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                jButton.setLocation((int) (Math.random() * 500), (int) (Math.random() * 200));
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(jButton);
    }
}
public class Hw3_4 extends JFrame {

    public Hw3_4() throws HeadlessException {
        add(new Hw3_4JPanel());

        setSize(600, 300);

        setTitle("클릭 연습용 응용 프로그램");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new Hw3_4();
    }
}
