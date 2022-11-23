package org.gui;

import javax.swing.*;
import java.awt.*;

class HelloSwingPanel extends JPanel {
    HelloSwingPanel() {
        setLayout(new GridLayout());
        JButton but = new JButton("Click!");
        JButton but2 = new JButton("Click!");
        add(but);
        add(but2);



        setBackground(Color.BLUE);
    }
}

public class App extends JFrame{

    App() {
        setTitle("My First Swing App");
        setSize(1000, 600);

//        this.setLayout(new FlowLayout());

        HelloSwingPanel p = new HelloSwingPanel();
        add(p, BorderLayout.EAST);
        JButton but = new JButton("bottom");
        add(but, BorderLayout.SOUTH);
        JButton but2 = new JButton("bottom2");
        add(but2, BorderLayout.SOUTH);



        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        JFrame app = new App();
    }
}