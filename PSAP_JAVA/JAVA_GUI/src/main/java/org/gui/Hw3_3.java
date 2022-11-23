package org.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Hw3_3JPanel extends JPanel implements KeyListener{
    String str = "Love Java";
    int idx = 0;

    JLabel jLabel = new JLabel(str);
    public Hw3_3JPanel() {
        add(jLabel);
        setFocusable(true);
        requestFocus();
        addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 37) {
            idx = (idx + 1) % 9;
            jLabel.setText(str.substring(idx, 9) + str.substring(0, idx));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

public class Hw3_3 extends JFrame {
    public Hw3_3() throws HeadlessException {
        add(new Hw3_3JPanel());

        setTitle("Left 키로 문자열 교체");
        setSize(500, 200);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Hw3_3();
    }
}
