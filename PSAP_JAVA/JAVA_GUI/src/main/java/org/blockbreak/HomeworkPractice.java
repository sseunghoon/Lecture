package org.blockbreak;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

abstract class MObject{
    float x;
    float y;
    Color color;
    MObject(float _x, float _y, Color c){
        x = _x;
        y = _y;
        color = c;
    }
    abstract void draw(Graphics g);
    void update(float dt) {};
    abstract void collisionResolution(MObject o);
}

class MWall extends MObject{
    float width;
    float height;
    MWall(float _x, float _y, float _w, float _h, Color c){
        super(_x, _y, c);
        width = _w;
        height = _h;
    }
    void draw(Graphics g) {
        g.setColor(color);
        g.fillRect((int)x, (int)y, (int)width, (int)height);
    }
    void collisionResolution(MObject o) {

    }
}

class MBall extends MObject{
    float radius;
    float vx;
    float vy;

    float pre_x;
    float pre_y;


    MBall(float _x, float _y, float r, Color c){
        super(_x, _y, c);
        radius = r;

        pre_x = x;
        pre_y = y;

        float speed = 100.0f;
        float angle = (float)(Math.random()*2.0*3.141592);
        vx = speed * (float)(Math.cos(angle));
        vy = speed * (float)(Math.sin(angle));

    }
    void draw(Graphics g) {
        g.setColor(color);
        g.fillOval((int)(x-radius), (int)(y-radius), (int)(2*radius), (int)(2*radius));
    }
    @Override
    void update(float dt) {

        pre_x = x;
        pre_y = y;

        x += vx*dt;
        y += vy*dt;

    }
    boolean isCollide(MObject o) {
        if(o instanceof MWall) {
            MWall w =(MWall) o;

            if(x>(w.x-radius) && x<(w.x+w.width+radius) &&
                    y>(w.y-radius) && y<(w.y+w.height+radius))
                return true;
        }
        return false;
    }

    void collisionResolution(MObject o) {
        if(o instanceof MWall) {
            MWall w =(MWall) o;

            if(pre_x < w.x - radius) {
                x = w.x-radius;
                vx = -vx;
            }

            if(pre_x > w.x+w.width+radius) {
                x = w.x+w.width + radius;
                vx = -vx;
            }

            if(pre_y < w.y - radius) {
                y = w.y - radius;
                vy = -vy;
            }
            if(pre_y > w.y + w.height + radius) {
                y = w.y + w.height + radius;
                vy = -vy;
            }

        }
    }
}



class HomeworkPanel extends JPanel implements KeyListener, Runnable{

    LinkedList<MObject> objs;
    boolean keyPressed = false;

    HomeworkPanel(){
        objs = new LinkedList<MObject>();
        objs.add(new MWall(0,0,560,20, Color.black));
        objs.add(new MWall(0,350,560,20, Color.black));

        objs.add(new MWall(0,0,20,370, Color.black));
        objs.add(new MWall(560,0,20,370, Color.black));

        objs.add(new MWall(100,100,200, 50, Color.black));
        objs.add(new MWall(300,250,150, 70, Color.black));


        addKeyListener(this);

        setFocusable(true);
        requestFocus();

        Thread t = new Thread(this);
        t.start();

    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(var o : objs)
            o.draw(g);
    }
    public void keyTyped(KeyEvent e) {  	}
    public void keyReleased(KeyEvent e) { 	}
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
            keyPressed = true;
    }
    @Override
    public void run() {
        try {
            while(true) {			//GAME LOOP
                // key input
                if(keyPressed == true) {
                    objs.add(new MBall(50,50,5, Color.red));
                    keyPressed = false;
                }

                // update
                for(var o : objs)
                    o.update(0.016f);

                // collision resolution
                for(var o : objs) {
                    if( (o instanceof MBall) != true) continue;

                    for(var w : objs) {
                        if( (w instanceof MWall) != true) continue;
                        MWall wall = (MWall) w;
                        MBall ball = (MBall) o;

                        if(ball.isCollide(wall)){
                            ball.collisionResolution(wall);
                        }
                    }
                }
                // repaint
                repaint();

                Thread.sleep(16);
            }
        }catch(Exception E) {
            return;
        }
    }


}

public class HomeworkPractice extends JFrame{

    HomeworkPractice(){

        setSize(600,400);
        setTitle("Homework Practice");

        add(new HomeworkPanel());

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new HomeworkPractice();
    }

}
