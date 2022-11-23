package org.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;

abstract class Shape2D {

}

class Line {
    int startX ;
    int startY;
    int endX;
    int endY;
    static int count = 0;

    Color color;

    public Line(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
        color = new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));

    }

    public void setStart(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
    }

    public void setEnd(int endX, int endY) {
        this.endX = endX;
        this.endY = endY;
    }
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawLine(startX, startY, endX, endY);
    }
}

class DrawingPracticePanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener {
    int px = 0;
    int py = 0;

    LinkedList<Line> arrayList = new LinkedList<Line>();

    public DrawingPracticePanel() {
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
        requestFocus();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Line line = arrayList.getLast();
        line.setEnd(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        arrayList.add(new Line(e.getX(), e.getY()));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        Line line = arrayList.pop();
//        line.setEnd(e.getX(), e.getY());
//        arrayList.add(line);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> px -= 10;
            case KeyEvent.VK_RIGHT -> px += 10;
            case KeyEvent.VK_UP -> py -= 10;
            case KeyEvent.VK_DOWN -> py += 10;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.RED);
        for (int i = 0; i < 100; i += 5)
            g.drawRect(100 + i, 100 + i, 200, 100);

        g.setColor(Color.BLUE);
        g.fillOval(100, 100, 200, 200);
        g.setColor(Color.BLACK);
        g.drawLine(400, 400, 300, 300);

        int[] x = {150, 400, 400};
        int[] y = {150, 100, 400};
        g.drawPolygon(x, y, 3);

        g.setColor(Color.blue);
        Font font = new Font("Comic Sans MS", Font.ITALIC, 30);
        Font font2 = new Font("맑은 고딕", Font.ITALIC, 30);
        g.setFont(font);
        g.drawString("Sejong", px, py);

        for (Line line: arrayList) {
            line.draw(g);
        }
        repaint();
    }
}
public class DrawingPractice extends JFrame {
    public DrawingPractice() throws HeadlessException {
        setSize(500, 500);
        setTitle("Drawing Practice");

        add(new DrawingPracticePanel());

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new DrawingPractice();
    }
}
