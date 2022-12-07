package org.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.LinkedList;

class MyMovingBall {
    LinkedList<MyMovingBall> balls;
    float px, py;
    float vx, vy;
    float ax, ay;
    float radius;

    float age;
    Color color;

    public MyMovingBall(LinkedList<MyMovingBall> balls) {
        this.balls = balls;


        int r = (int)(Math.random()*255);
        int g = (int)(Math.random()*255);
        int b = (int)(Math.random()*255);
        color = new Color(r, g, b);
        radius = 5 + (int)(Math.random()*25);

        ax = 0.0f;
        ay = 98.0f;

        vx = 100 + (float)(Math.random() * 100);
        vy = -50.0f - (float)(Math.random() * 100);
//        vy = 0.0f;

        px = 100;
        py = 100;
        age = 0.0f;
    }

    void draw(Graphics g) {
        g.setColor(color);
        g.fillOval((int)(px - radius), (int)py, (int)(radius * 2), (int)(radius * 2));
    }
    void update(float dt) {
        // dt 초 만큼 뒤
        vx += ax * dt;
        vy += ay * dt;

        px += vx * dt;
        py += vy * dt;
        age += dt;
        radius = 10 * (1 - age/5.0f);
    }

    MyMovingBall makeClone() {
        MyMovingBall clone = new MyMovingBall(balls);
        clone.px = px;
        clone.py = py;
        clone.vx = -vx;
        clone.vy = vy;
        clone.ax = ax;
        clone.ay = ay;
        clone.balls = balls;
        clone.color = color;
        clone.radius = radius;
        clone.age = age;
        return clone;
    }
    void collisionResolution (JPanel p) {
        if (px > p.getWidth() - radius) {
            px = p.getWidth() - radius;
            vx = -vx;
        }
        if (px < 0 + radius) {
            px = radius;
            vx = -vx;
        }

        if (py > p.getHeight() - radius) {
            py = p.getHeight() - radius;
            vy = -vy;
            balls.add(makeClone());
        }
        if (py < 0 + radius) {
            py = radius;
            vy = -vy;
        }
    }

    boolean isDead() {
        if (age > 5.0f) return true;
        else return false;
    }
}

class MovingBoxPanel extends JPanel implements KeyListener, Runnable{

    LinkedList<MyMovingBall> balls = new LinkedList<>();
    float x = 100;
    float y = 100;

    float vx = 0.0f;
    float vy = 0.0f;


    MovingBoxPanel() {
        setFocusable(true);
        requestFocus();
        addKeyListener(this);

        Thread t = new Thread(this);
        t.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

//        g.fillRect((int) x, (int) y, 50, 50);
//        for (var b: balls) {
//            b.draw(g);
//        }
        Iterator<MyMovingBall> iterator = balls.iterator();
        while (iterator.hasNext()) {
            MyMovingBall ball = iterator.next();
            ball.draw(g);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {    }
    @Override
    public void keyPressed(KeyEvent e) {  }
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
//            case KeyEvent.VK_UP: vy -= 100; break;
//            case KeyEvent.VK_DOWN: vy += 100; break;
//            case KeyEvent.VK_LEFT: vx -= 100; break;
//            case KeyEvent.VK_RIGHT: vx += 100; break;
            case KeyEvent.VK_SPACE: balls.add(new MyMovingBall(balls));
            break;
        }
        repaint();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(16);
                // 0. key input

                // 1. update
                Iterator<MyMovingBall> iterator0 = balls.iterator();
                while (iterator0.hasNext()) {
                    MyMovingBall b = iterator0.next();
                    b.update(0.016f);
                }
                for (var b : balls)
                    b.update(0.016f);
//                x += vx * 0.016f;
//                y += vy * 0.016f;


                // 2. collision resolution
                Iterator<MyMovingBall> iterator = balls.iterator();
                while (iterator.hasNext()) {
                    MyMovingBall b = iterator.next();
                    b.collisionResolution(this);
                }
//                for (var b : balls)
//                    b.collisionResolution(this);
//                if (x > getWidth() - 50) {
//                    x = getWidth();
//                    vx = -vx;
//                }
//                if (x < 0) {
//                    x = 0;
//                    vx = -vx;
//                }
//
//                if (y > getHeight() - 50) {
//                    y = getHeight() - 50;
//                    vy = -vy;
//                }
//                if (y < 0) {
//                    y = 0;
//                    vy = -vy;
//                }

                // 3. remove dead object
                Iterator<MyMovingBall> iterator2 = balls.iterator();
                while (iterator2.hasNext()) {
                    MyMovingBall b = iterator2.next();
                    if (b.isDead())
                        iterator2.remove();
                }
//                balls.removeIf(MyMovingBall::isDead);

                // 4. redraw
                repaint();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class MovingBox extends JFrame {
    public MovingBox() throws HeadlessException {
        setTitle("Moving Box");
        setSize(500, 500);

        add(new MovingBoxPanel());

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new MovingBox();

    }
}
