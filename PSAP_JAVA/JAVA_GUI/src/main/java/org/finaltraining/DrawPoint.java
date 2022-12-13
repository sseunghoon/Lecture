package org.finaltraining;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;


class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

}

class DrawPaintPanel extends JPanel {
    ArrayList<Point> pointArr = new ArrayList<Point>();
    public DrawPaintPanel() {
        requestFocus();
        setFocusable(true);
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                if (e.getButton() == 1) {
                    pointArr.add(new Point(e.getX(), e.getY()));
                }
                if (e.getButton() == 3) {
                    Iterator<Point> iterator = pointArr.iterator();
                    while (iterator.hasNext()) {
                        if (isDelete(iterator.next(), e.getX(), e.getY())) {
                            iterator.remove();
                        }
                    }
                }
                repaint();
            }
        });

    }

    public boolean isDelete(Point point, int x, int y) {
        int xDiff = Math.abs(point.x - x);
        int yDiff = Math.abs(point.y - y);

        if (xDiff <= 10 && yDiff <= 10) {
            return true;
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        String size = Integer.toString(pointArr.size());
        for (Point p:pointArr) {
            g.drawOval(p.x, p.y, 3,3);
        }
        g.drawString(size,100,100);
        System.out.println(size);
    }
}
public class DrawPoint extends JFrame {
    public DrawPoint() throws HeadlessException {
        setTitle("DrawPoint");
        setSize(500, 500);

        add(new DrawPaintPanel());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new DrawPoint();
    }
}
