package org.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseListener;
import java.util.LinkedList;

class OurMouseMotionListener implements MouseMotionListener {
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
class MyMouseListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse Clicked!");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse Pressed!");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Mouse Released!");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Mouse Entered!");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Mouse Exited!");
    }
}
class MouseEventPracticePanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
    // Panel 위에 Label 같이 component위의 마우스 이벤트는 Panel이 알지 못한다?
    // Panel 위에 button이 있을 때 버튼 위에서 클릭 이벤트는 버튼이 다 채감
    JTextField jTextField = new JTextField("init");
    JTextField point = new JTextField("point");
    JTextField point2 = new JTextField("point2");
    JTextField point3 = new JTextField("point3");

    JButton but1 = new JButton("but1");
    JButton but2 = new JButton("but2");

    LinkedList<JLabel> list = new LinkedList<>();
    public MouseEventPracticePanel() {
        setBackground(Color.RED);
        setFocusable(true);
        requestFocus();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        point.setSize(100, 100);


        but2.addMouseListener(new MyMouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                but2.setText("" + e.getPoint());
                Dimension pd = getSize();
                but2.setLocation((int) (Math.random() * pd.width), (int) (Math.random() * pd.height));
                JLabel jLabel = new JLabel(">_<");
                jLabel.setLocation((int) (Math.random() * pd.width), (int) (Math.random() * pd.height));
                add(jLabel);
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
        });
        add(but1);
        add(but2);
        add(point);
        add(point2);
        add(point3);
        this.addMouseListener(new MyMouseListener() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    System.out.println("Double Click!");
                }
            }

            public void mousePressed(MouseEvent e) {
                but1.setText("loc: " + but1.getLocation() + "size: " + but1.getSize());
            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {
                jTextField.setText("Enter");
            }

            public void mouseExited(MouseEvent e) {
                jTextField.setText("Exit");
            }
        });
        add(jTextField);
        this.addMouseListener(new MyMouseListener());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("test");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // getButton 의 Button1, 2, 3은 각각 왼쪽, 중간, 오른쪽 마우스 버튼을 말함
        if (e.getButton() == MouseEvent.BUTTON1)
            jTextField.setText("Left");
        if (e.getButton() == MouseEvent.BUTTON2)
            jTextField.setText("Middle");
        if (e.getButton() == MouseEvent.BUTTON3)
            jTextField.setText("Right");
        Point p = e.getPoint();
        point.setText(p.x + " / " + p.y);
        Point p2 = e.getLocationOnScreen();
        point2.setText(p2.x + " / " + p2.y);

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

    @Override
    public void mouseDragged(MouseEvent e) {
        point3.setText("Dragged" + e.getPoint());
        System.out.println("Dragged" + e.getPoint());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        point3.setText("Moved" + e.getPoint());
        System.out.println("Moved" + e.getPoint());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Type:" + e.getKeyCode());
        System.out.println("Char:" + e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Press:" + e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Release:" + e.getKeyCode());
    }
}

public class MouseEventPractice extends JFrame {

    public MouseEventPractice() {
        setTitle("Mouse Event Practice");;
        setSize(500, 500);

        add(new MouseEventPracticePanel());

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new MouseEventPractice();
    }
}
