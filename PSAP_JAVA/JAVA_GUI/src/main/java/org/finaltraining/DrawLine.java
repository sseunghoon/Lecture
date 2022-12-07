package org.finaltraining;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

class Line {
    ArrayList<relativePoint> pointArr = new ArrayList<>();
}

class relativePoint {
    double x;
    double y;

    public relativePoint(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

class DrawLinePanel extends JPanel{
    ArrayList<Line> lineArr = new ArrayList<>();
    public DrawLinePanel() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                lineArr.add(new Line());
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                Line line =  lineArr.get(lineArr.size() - 1);
                line.pointArr.add(new relativePoint((double) (e.getX() * 100  / getWidth()), (double) (e.getY() * 100 / getHeight())));
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Line line: lineArr) {
            ArrayList<relativePoint> pointArr = line.pointArr;
            for (int i = 0; i < pointArr.size() - 1; i++) {
                g.drawLine((int) (pointArr.get(i).x * getWidth() / 100),
                        (int) (pointArr.get(i).y * getHeight() / 100),
                        (int) (pointArr.get(i+1).x * getWidth() / 100),
                        (int)(pointArr.get(i+1).y * getHeight()) / 100);
            }
        }

        g.drawString("Line Count: " + lineArr.size(), 100, 100);
    }
}
public class DrawLine extends JFrame {
    public DrawLine() throws HeadlessException {
        setTitle("DrawLine");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(new DrawLinePanel());

        setSize(500, 500);
        setVisible(true);
    }

    public static void main(String[] args) {
        new DrawLine();
    }
}
