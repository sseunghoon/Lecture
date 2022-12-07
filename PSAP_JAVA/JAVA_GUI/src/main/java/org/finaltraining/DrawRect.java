package org.finaltraining;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

class Rect {
    int x1;
    int y1;
    int x2;
    int y2;

    Color color;

    public Rect(int x1, int y1, int x2, int y2, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }
}
class DrawRectPanel extends JPanel implements ActionListener{
    ArrayList<Rect> rectArr = new ArrayList<>();
    Color color = Color.black;
    JRadioButtonMenuItem redRadio;
    JRadioButtonMenuItem greenRadio;
    JRadioButtonMenuItem blueRadio;

    public DrawRectPanel() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu colorMenu = new JMenu("Color");
        redRadio = new JRadioButtonMenuItem("Red");
        redRadio.addActionListener(this);
        redRadio.setActionCommand("setRed");

        greenRadio = new JRadioButtonMenuItem("Green");
        greenRadio.addActionListener(this);
        greenRadio.setActionCommand("setGreen");

        blueRadio = new JRadioButtonMenuItem("Blue");
        blueRadio.addActionListener(this);
        blueRadio.setActionCommand("setBlue");

        colorMenu.add(redRadio);
        colorMenu.add(greenRadio);
        colorMenu.add(blueRadio);
        jMenuBar.add(colorMenu);
        add(jMenuBar);


        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                rectArr.add(new Rect(e.getX(), e.getY(), e.getX() + 1, e.getY() + 1, color));
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                Rect rect = rectArr.get(rectArr.size() - 1);
                rect.x2 = e.getX();
                rect.y2 = e.getY();

                repaint();
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println("test");
        System.out.println(command);
        if (command.equals("setRed")) {
            color = Color.RED;
            blueRadio.setSelected(false);
            greenRadio.setSelected(false);
        }
        if (command.equals("setGreen")) {
            color = Color.GREEN;
            redRadio.setSelected(false);
            blueRadio.setSelected(false);
        }
        if (command.equals("setBlue")) {
            color = Color.BLUE;
            redRadio.setSelected(false);
            greenRadio.setSelected(false);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Rect rect: rectArr) {
            int startX = Math.min(rect.x1, rect.x2);
            int startY = Math.min(rect.y1, rect.y2);
            int width = Math.abs(rect.x1 - rect.x2);
            int height = Math.abs(rect.y1 - rect.y2);
            g.setColor(rect.color);
            g.fillRect(startX, startY, width, height);
        }
    }
}

public class DrawRect extends JFrame {
    public DrawRect() throws HeadlessException {
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(new DrawRectPanel());



        setTitle("DrawRect");
        setVisible(true);
    }

    public static void main(String[] args) {
        new DrawRect();
    }
}
