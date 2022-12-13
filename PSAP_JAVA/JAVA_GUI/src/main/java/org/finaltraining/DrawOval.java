package org.finaltraining;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

class Oval {
    int x;
    int y;
    int radius;
    Color color;

    public Oval(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = Color.red;
    }

    @Override
    public String toString() {
        return "x: " + x + " y: " + y + " r: " + radius;
    }
}

class DrawOvalPanel extends JPanel {
    ArrayList<Oval> ovalArr = new ArrayList<>();
    ArrayList<Oval> selectedOvals = new ArrayList<>();
    Point clickPoint = new Point(0, 0);

    public DrawOvalPanel() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                clickPoint.x = e.getX();
                clickPoint.y = e.getY();
                selectedOvals = selectOval(e.getX(), e.getY());
                if (selectedOvals.size() == 0) {
                    ovalArr.add(new Oval(e.getX(), e.getY(), 0));
                }
                for (var oval: selectedOvals)
                    oval.color = Color.YELLOW;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                for (var oval: selectedOvals)
                    oval.color = Color.RED;
                selectedOvals.clear();
                repaint();
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                if (selectedOvals.size() == 0) {
                    Oval oval = ovalArr.get(ovalArr.size() - 1);
                    oval.radius = (int) (Math.sqrt(Math.pow(oval.x - e.getX(), 2) + Math.pow(oval.y - e.getY(), 2)));
                    System.out.println("r: " + oval.radius);
                    repaint();
                }
                else {
                    int moveX = e.getX() - clickPoint.x;
                    int moveY = e.getY() - clickPoint.y;
                    clickPoint.x = e.getX();
                    clickPoint.y = e.getY();
                    for (var oval : selectedOvals) {
                        oval.x += moveX;
                        oval.y += moveY;
                    }
                    repaint();
                }
            }
        });
    }
    ArrayList<Oval> selectOval(int x, int y) {

        Iterator<Oval> iterator = ovalArr.iterator();
        while (iterator.hasNext()) {
            Oval oval = iterator.next();
            if (isInOval(x, y, oval))
                selectedOvals.add(oval);
        }
        return selectedOvals;
    }

    boolean isInOval(int x, int y, Oval oval) {
        if (Math.pow(oval.x - x, 2) + Math.pow(oval.y - y, 2) < Math.pow(oval.radius, 2))
            return true;
        else
            return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (var oval: ovalArr) {
            g.setColor(Color.BLACK);
            g.drawOval(oval.x - oval.radius, oval.y - oval.radius, oval.radius * 2, oval.radius * 2);
            g.setColor(oval.color);
            g.fillOval(oval.x - oval.radius, oval.y - oval.radius, oval.radius * 2, oval.radius * 2);
            System.out.println(oval);
        }
        g.setColor(Color.BLACK);
        g.drawString("Oval Count: " + ovalArr.size(), 100, 100);
        g.drawString("Selected Oval Count: " + selectedOvals.size(), 100, 50);
    }
}
public class DrawOval extends JFrame {
    public DrawOval() throws HeadlessException {
        setTitle("DrawOval");
        setSize(500, 500);

        add(new DrawOvalPanel());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    public static void main(String[] args) {
        new DrawOval();
    }
}
