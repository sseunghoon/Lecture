package org.blockbreak;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Random;

abstract class VisualObject {
    float x;
    float y;
    Color color;

    public VisualObject(float x, float y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    abstract void draw(Graphics g);

    abstract void collisionProcess(VisualObject vo);

    abstract void update();
}

class Wall extends VisualObject {
    static final int thickness = 20;
    float width;
    float height;

    public Wall(float x, float y, Color color, float width, float height) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    static void addDefaultWalls(ArrayList arr) {
        int w = 800;
        int h = 800;

        arr.add(new Wall(0, 0, Color.DARK_GRAY, w, thickness));
        arr.add(new Wall(0, thickness, Color.DARK_GRAY, thickness, h - thickness));
        arr.add(new Wall(w - thickness, thickness, Color.DARK_GRAY, w - thickness, h - thickness));
    }

    void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint gp = new GradientPaint(x + width / 2, y, Color.WHITE,  x + width / 2, y + (int) (1.5 * height), Color.BLACK);
        g2.setPaint(gp);
        g2.fillRect((int) x + 1, (int) y + 1, (int) width - 2, (int) height - 2);

//        gp = new GradientPaint(x + width / 2, y - (int) (0.2 * height), Color.WHITE,
//                x + width / 2, y + (int) (1.5 * height), Color.DARK_GRAY);
//        g2.setPaint(gp);
        g2.setPaint(Color.GRAY);
        g2.fillRect((int) x + 3, (int) y + 3, (int) width - 6, (int) height - 6);
    }

    @Override
    void collisionProcess(VisualObject vo) {
    }

    @Override
    void update() {
    }
}

class Ball extends VisualObject {
    static final float r = 6;
    float preX;
    float preY;
    float vx;
    float vy;
    boolean alive;
    ArrayList<Wall> walls;

    public Ball(float x, float y, Color color, ArrayList<Wall> walls) {
        super(x, y, color);
        vx = 0;
        vy = 20;
        alive = true;
        this.walls = walls;
    }

    public Ball(float x, float y, Color color, float preX, float preY, float vx, float vy, boolean alive, ArrayList<Wall> walls) {
        super(x, y, color);
        this.preX = preX;
        this.preY = preY;
        this.vx = vx;
        this.vy = vy;
        this.alive = alive;
        this.walls = walls;
    }

    @Override
    void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval((int) (x - r), (int) (y - r), (int) (2 * r), (int) (2 * r));
        g.setColor(color);
        g.fillOval((int) (x - r + 1), (int) (y - r + 1), (int) (2 * r - 2), (int) (2 * r - 2));
    }

    @Override
    void collisionProcess(VisualObject vo) {
        float correctionValue = 1;
        if (vo instanceof Racket) {
            Racket racket = (Racket) vo;
            float relativePosition = racket.getRelativePosition(this);

            float r = (float) Math.hypot(vx, vy);

            vx = relativePosition * 20;
            correctionValue = (float) (Math.sqrt(Math.pow(r, 2) - Math.pow(vx, 2))) / Math.abs(vy);
        }
        if (vo instanceof Wall) {
            Wall w = (Wall) vo;

            if (preX < w.x - r) {
                x = w.x - r;
                vx = -vx;
            }
            if (preX > w.x + w.width + r) {
                x = w.x + w.width + r;
                vx = -vx;
            }
            if (preY < w.y - r) {
                y = w.y - r;
                vy = -vy;
            }
            if (preY > w.y + w.height + r) {
                y = w.y + w.height + r;
                vy = -vy;
            }
            if (vo instanceof Block && ((Block) vo).special) {
                cloneBalls();
            }

            vy *= correctionValue;
        }
    }

    @Override
    void update() {
        preX = x;
        preY = y;

        x += vx;
        y += vy;

        if (y < 0) {
            alive = false;
            System.out.println("Ceiling Out");
        }
        if (y > 800) {
            alive = false;
        }

        Iterator<Wall> wallIter = walls.iterator();
        while (wallIter.hasNext()) {
            Wall w = wallIter.next();
            if (this.isCollision(w)) {
                this.collisionProcess(w);
                w.collisionProcess(this);
                if (w instanceof Block)
                    wallIter.remove();
            }
        }
    }

    void cloneBalls() {
        Ball clone1 = new Ball(x, y, color, preX, preY, vx, vy, alive, walls);
        Ball clone2 = new Ball(x, y, color, preX, preY, vx, vy, alive, walls);

        float r = (float) Math.hypot(vx, vy);
        // 수직 기둥을 기준으로 각도가 나온다고 생각하면 됨
        // (5, 5) : 45.0
        // (-5, 5) : -45.0
        float theta1 = (float) Math.atan2(vx, vy) - 3.14f / 3.0f;
        clone1.vx = r * (float) Math.sin(theta1);
        clone1.vy = r * (float) Math.cos(theta1);

        float theta2 = (float) Math.atan2(vx, vy) + 3.14f / 3.0f;
        clone2.vx = r * (float) Math.sin(theta2);
        clone2.vy = r * (float) Math.cos(theta2);

        PlayPanel.cloneBalls.add(clone1);
        PlayPanel.cloneBalls.add(clone2);

    }

    boolean isCollision(VisualObject vo) {
        if (vo instanceof Wall) {
            Wall w = (Wall) vo;

            return x > (w.x - r) && x < (w.x + w.width + r) &&
                    y > (w.y - r) && y < (w.y + w.height + r);
        }

        return false;
    }
}

class Racket extends Wall {
    public Racket(float x, float y, Color color, float width, float height) {
        super(x, y, color, width, height);
    }

    @Override
    void draw(Graphics g) {
        super.draw(g);
    }

    @Override
    void collisionProcess(VisualObject vo) {
        super.collisionProcess(vo);
    }

    float getRelativePosition(Ball ball) {
        // Racket의 중앙 부분이 0, 왼쪽 끝이 -0.5, 오른쪽 끝이 +0.5
        return (ball.x - this.x) / this.width - 0.5f;
    }
}

class Block extends Wall {
    boolean special;
    boolean alive;
    int fadeOutCount;
    int shinyCycle;

    public Block(float x, float y, Color color, float width, float height) {
        super(x, y, color, width, height);
        if (Math.random() > 0.8) {
            special = true;
            color = new Color(255, 212, 0);
        }
        fadeOutCount = -1;
        alive = true;
        shinyCycle = new Random().nextInt() % 100;
    }

    @Override
    void draw(Graphics g) {
        if (fadeOutCount > 0) {
            g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 100 - fadeOutCount * 2));
            g.fillRect((int) x, (int) y, (int) width, (int) height);
            return;
        }

        Graphics2D g2 = (Graphics2D) g;
        GradientPaint gp = new GradientPaint(x + width / 2, y, Color.GRAY,
                x + width / 2, y + 2 * height, Color.black);
        g2.setPaint(gp);
        g2.fillRect((int) x + 1, (int) y + 1, (int) width - 2, (int) height - 2);

        if (special && shinyCycle > 100) {
            g.setColor(Color.yellow);
            g.fillRect((int) x + 2, (int) y + 2, (int) width - 4, (int) height - 4);
            return;
        }
        if (special) {
            g.setColor(new Color(255, 212, 0));
            g.fillRect((int) x+3, (int) y+3, (int) width - 6, (int) height - 6);
            return;
        }
        g.setColor(new Color(158, 126, 185));
        g.fillRect((int) x + 3, (int) y + 3, (int) width - 6, (int) height - 6);
    }

    @Override
    void collisionProcess(VisualObject vo) {
        super.collisionProcess(vo);
        // 참조의 지역성을 때문에, 이 코드는 안 좋은 코드인가?
        EndPanel.currentScore += 10;
        Block fadeOutBlock = new Block(x, y, color, width, height);
        fadeOutBlock.fadeOutCount = 1;
        PlayPanel.fadeOutBlocks.add(fadeOutBlock);
    }

    @Override
    void update() {
        super.update();
        if (fadeOutCount >= 0)
            fadeOutCount++;
        if (fadeOutCount > 50) {
            fadeOutCount = 50;
            alive = false;
        }
        shinyCycle += 1;
        shinyCycle %= 110;
    }
}

class PlayPanel extends JPanel implements Runnable {
    static ArrayList<Ball> cloneBalls = new ArrayList<Ball>();
    static ArrayList<Block> fadeOutBlocks = new ArrayList<Block>();
    ArrayList<Wall> walls;
    ArrayList<Ball> balls;
    Racket racket;
    int round;
    int currentScore;
    Thread thread;
    JavaHW5 frame;

    public PlayPanel(JavaHW5 frame) {
        setFocusable(true);
        this.frame = frame;
        round = 1;

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    racket.x += -15;
                    if (racket.x < Wall.thickness)
                        racket.x = Wall.thickness;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    racket.x += 15;
                    if (racket.x + racket.width > 800 - Wall.thickness)
                        racket.x = 800 - Wall.thickness - racket.width;
                }
            }
        });
    }

    void initComponent(int round) {
        walls = new ArrayList<>();
        balls = new ArrayList<>();
        Wall.addDefaultWalls(walls);
        balls.add(new Ball(400, 600, Color.WHITE, walls));

        racket = new Racket(300, 700, Color.RED, 200, 50);
        walls.add(racket);
        createBlocks(round);
        requestFocus();

        Thread thread = new Thread(this);
        thread.start();
        System.out.println("new Thread");
    }

    void createBlocks(int round) {
        int width = 800;
        int height = 800;

        // i가 행, j가 열
        for (int i = 0; i < round * 3; i++) {
            for (int j = 0; j < round * 3; j++) {
                float x = Wall.thickness + (800.0f - Wall.thickness * 2) / (round * 3) * j;
                float y = Wall.thickness + (800.0f - Wall.thickness) / (round * 9) * i;
                float w = (800.0f - Wall.thickness * 2) / (round * 3);
                float h = (800.0f - Wall.thickness * 2) / (round * 9);
                walls.add(new Block(x, y, new Color(158, 126, 185), w, h));
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        Color floorColor = new Color(103, 141, 171);
        GradientPaint gp = new GradientPaint(100, 0, Color.BLACK,
                100, 700, floorColor);
        g2.setPaint(gp);
        g2.fillRect(0, 0, 800, 800);

        try {
            Iterator<Wall> wallIterator = walls.iterator();
            while (wallIterator.hasNext()) {
                Wall w = wallIterator.next();
                w.draw(g);
            }
            Iterator<Ball> ballIterator = balls.iterator();
            while (ballIterator.hasNext()) {
                Ball b = ballIterator.next();
                b.draw(g);
            }
            Iterator<Block> fadeOutBlockIterator = fadeOutBlocks.iterator();
            while (fadeOutBlockIterator.hasNext()) {
                Block b = fadeOutBlockIterator.next();
                b.draw(g);
            }

        } catch (ConcurrentModificationException e) {
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (balls == null || walls == null) {
                    System.out.println("Roading...");
                    Thread.sleep(1000);
                }
                if (balls.size() == 0) {
                    System.out.println("Game Over...");
                    if (EndPanel.maxScore < EndPanel.currentScore)
                        EndPanel.maxScore = EndPanel.currentScore;
                    frame.cards.show(frame.getContentPane(), "EndPanel");
                    frame.endPanel.requestFocus();
                    break;
                }
                if (walls.size() == 4) {
                    System.out.println("Road Next Round...");
                    initComponent(++round);
                    break;
                }
                Thread.sleep(30);
                Iterator<Ball> ballIter = balls.iterator();
                while (ballIter.hasNext()) {
                    Ball b = ballIter.next();
                    if (!b.alive) {
                        ballIter.remove();
                        continue;
                    }
                    b.update();
                }
                balls.addAll(cloneBalls);
                cloneBalls.clear();
                Iterator<Block> fadeOutBlockIterator = fadeOutBlocks.iterator();
                while (fadeOutBlockIterator.hasNext()) {
                    Block b = fadeOutBlockIterator.next();
                    b.update();
                    if (!b.alive)
                        fadeOutBlockIterator.remove();
                }
                Iterator<Wall> specialBlockIterator = walls.iterator();
                while (specialBlockIterator.hasNext()) {
                    Wall w =  specialBlockIterator.next();
                    if (w instanceof Block) {
                        Block b = (Block) w;
                        b.update();
                    }
                }

                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                // 1000ms 를 기다렸으나 roading 되지 않음
            } catch (ConcurrentModificationException e) {
            }
        }
    }
}

class TitlePanel extends JPanel implements Runnable{
    boolean textVisible;
    public TitlePanel(JavaHW5 frame) {
        setFocusable(true);
        textVisible = false;

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    frame.cards.show(frame.getContentPane(), "PlayPanel");
                    frame.playPanel.initComponent(1);
                    frame.playPanel.currentScore = 0;
                }
            }
        });
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(400);
                textVisible = !textVisible;
                repaint();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        Color floorColor = new Color(103, 141, 171);
        GradientPaint gp = new GradientPaint(100, 0, Color.BLACK,
                100, 700, floorColor);
        g2.setPaint(gp);
        g2.fillRect(0, 0, 800, 800);

        g2.setPaint(Color.WHITE);
        g2.setFont(new Font("Aria",Font.BOLD, 50));
        g2.drawString("Java Programming", 160, 150);
        g2.drawString("Homework #5", 230, 200);

        g2.setFont(new Font("Aria",Font.BOLD, 80));
        g2.drawString("Block Breaker", 120, 350);

        if (textVisible) {
            g2.setFont(new Font("Aria", Font.BOLD, 40));
            g2.setPaint(Color.BLACK);
            g2.drawString("Press Space Bar to Start!", 152, 602);
            g2.setPaint(Color.red);
            g2.drawString("Press Space Bar to Start!", 150, 600);
        }
    }
}

class EndPanel extends JPanel implements Runnable{
    static int currentScore = 0;
    static int maxScore = 0;
    boolean textVisible = true;

    public EndPanel(JavaHW5 frame) {
        setFocusable(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    frame.cards.show(frame.getContentPane(), "TitlePanel");
                    frame.titlePanel.requestFocus();
                    currentScore = 0;
                    repaint();
                }
            }
        });

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(400);
                textVisible = !textVisible;
                repaint();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        Color floorColor = new Color(103, 141, 171);
        GradientPaint gp = new GradientPaint(100, 0, Color.BLACK,
                100, 700, floorColor);
        g2.setPaint(gp);
        g2.fillRect(0, 0, 800, 800);

        g2.setPaint(Color.WHITE);
        g2.setFont(new Font("Aria",Font.BOLD, 100));
        g2.drawString("Game Over", 120, 350);

        g2.setFont(new Font("Aria", Font.BOLD, 40));
        g2.setPaint(Color.BLACK);
        String highScore = "High Score: " + maxScore;
        g2.drawString(highScore, 242, 472);
        g2.setPaint(Color.DARK_GRAY);
        g2.drawString(highScore, 240, 470);

        g2.setPaint(Color.BLACK);
        String yourScore = "Your Score: " + currentScore;
        g2.drawString(yourScore, 242, 517);
        g2.setPaint(Color.DARK_GRAY);
        g2.drawString(yourScore, 240, 515);

        if (textVisible) {
            g2.setFont(new Font("Aria", Font.BOLD, 40));
            g2.setPaint(Color.BLACK);
            g2.drawString("Press Space Bar to Start!", 152, 602);
            g2.setPaint(Color.red);
            g2.drawString("Press Space Bar to Start!", 150, 600);
        }
    }
}

public class JavaHW5 extends JFrame {
    CardLayout cards = new CardLayout();
    TitlePanel titlePanel;
    PlayPanel playPanel;
    EndPanel endPanel;

    public JavaHW5() {
        setTitle("Block Break");
        setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(cards);

        titlePanel = new TitlePanel(this);
        add("TitlePanel", titlePanel);
        playPanel = new PlayPanel(this);
        add("PlayPanel", playPanel);

        endPanel = new EndPanel(this);
        add("EndPanel", endPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new JavaHW5();
    }


}