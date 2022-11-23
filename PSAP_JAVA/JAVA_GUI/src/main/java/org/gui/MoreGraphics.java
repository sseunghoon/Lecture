package org.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.awt.Point;
import java.util.LinkedList;

class MyStroke {
    ArrayList<Point> pts = new ArrayList<>();
    Color color;

    public MyStroke(int x, int y, Color c) {
        pts.add(new Point(x, y));
        color = c;
    }

    void addPoint(int x, int y) {
        pts.add(new Point(x, y));
    }

    void draw(Graphics g) {
        g.setColor(color);
        for (int i = 0; i < pts.size() - 1; i++) {
            Point p1 = pts.get(i);
            Point p2 = pts.get(i + 1);
            g.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }
}

class MyColorButton extends JButton{
    String rgb;
    Color color;

    public MyColorButton(String str, Color color) {
        super(str);
        this.color = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g.setColor(color);

        g.fillRect(2, 2, getWidth()-6, getHeight()-6);

    }
}

class MoreGraphicsPanel extends JPanel {
    LinkedList<MyStroke> strokes = new LinkedList<>();
    ArrayList<Point> pts = new ArrayList<>();
    Color color  = Color.black;

    public MoreGraphicsPanel() {
        MyColorButton but1 = new MyColorButton("R", Color.red);
        but1.addActionListener((e) -> color = but1.color);

        MyColorButton but2 = new MyColorButton("G", Color.green);
        but2.addActionListener((e) -> color = but2.color);

        MyColorButton but3 = new MyColorButton("B", Color.blue);
        but3.addActionListener((e) -> color = but3.color);

        add(but1);
        add(but2);
        add(but3);


        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                strokes.add(new MyStroke(e.getX(), e.getY(), color));
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                strokes.get(strokes.size() - 1).addPoint(e.getX(), e.getY());


                pts.add(new Point(e.getX(), e.getY()));
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        strokes.get(strokes.size() - 1).draw(g);

        for (MyStroke s : strokes) {
            s.draw(g);
        }
//        g.drawString("Num of Points:" + pts.size(), 0, 30);
//        for (int i = 0; i < pts.size() - 1; i++) {
//            Point p1= pts.get(i);
//            Point p2= pts.get(i + 1);
//            g.drawLine(p1.x, p1.y, p2.x, p2.y);
//        }

//        for (Point p: pts) {
//            g.fillOval(p.x, p.y, 3, 3);
//        }


        g.drawString("Num of Strokes: " + strokes.size(), 100, 100);
    }
}

public class MoreGraphics extends JFrame {
    public MoreGraphics() throws HeadlessException {
        setTitle("More Graphics");
        setSize(500, 500);

        add(new MoreGraphicsPanel());

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new MoreGraphics();
    }
}
