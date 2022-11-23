package org.gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

class MouseListener implements java.awt.event.MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse Click!");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse Press!");
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
}

class MyButton extends JButton implements ActionListener{
    MyButton(String s){
        super(s);
        addActionListener(this);
    }
    boolean toggle = false;
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click2!!!");
        if(toggle != true)
            setText("haha2!");
        else
            setText("hoho2!");
        toggle = !toggle;

    }
}

class MyButtonListener implements ActionListener {
    boolean toggle = true;
    int r = 0;
    int g = 0;
    int b = 0;
    JPanel panel = null;

    public MyButtonListener(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("getActionCommand: " + e.getActionCommand());
        System.out.println("getSource: " + e.getSource());
        System.out.println("paramString: " + e.paramString());
        System.out.println("getID: " + e.getID());
        System.out.println("getWhen: " + e.getWhen());

        JButton but = (JButton) e.getSource();
        if (toggle != true) {
            but.setText("haha!");
        } else {
            but.setText("hoho!");
        }
        toggle = !toggle;

        Color c = new Color(r % 256, g % 256, b %256);
        r += 100;
        g += 100;
        b += 100;

        Color c2 = new Color((int) (Math.random()*255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        panel.setBackground(c2);
    }
}

class EventHandlingPracticePanel extends JPanel {
    public EventHandlingPracticePanel() {
        this.setBackground(Color.BLUE);
        JButton but = new JButton("click");
        MyButtonListener myButtonListener = new MyButtonListener(this);
        but.addActionListener(myButtonListener);
        add(but);
        setVisible(true);

        JButton but1 = new JButton("click");
        MyButtonListener myButtonListener1 = new MyButtonListener(this);
        but1.addActionListener(myButtonListener1);
        add(but1);
        setVisible(true);

        JButton but2 = new JButton("click");
        MyButtonListener myButtonListener2 = new MyButtonListener(this);
        but2.addActionListener(myButtonListener2);
        add(but2);
        setVisible(true);





        JButton but5 = new JButton("but5");
        but5.addActionListener(e -> {
            System.out.println("Lambda Expression");
        });
        add(but5);

        JTextField text = new JTextField("..............");
        text.addActionListener(e -> {
            but.setText(text.getText() + "!");
            but1.setText(text.getText()+ "!!");
            but2.setText(text.getText()+ "!!!");
        });

        addMouseListener(new MouseListener());
    }
}

public class EventHandlingPractice extends JFrame {
    EventHandlingPractice() {
        this.setTitle("Evenvt Handling");
        this.setSize(500, 500);

        var p = new EventHandlingPracticePanel();
        this.add(p);

        // Visible 하고 난 이후의 변경사항(add)은 다시 띄우기 전까지 반영 X
        // setBackground() 같은 것은 반영됨
        this.setVisible(true);

        p.setBackground(Color.RED);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new EventHandlingPractice();
    }
}
