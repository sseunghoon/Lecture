package org.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import static java.lang.Thread.sleep;

enum Hw4sign {PLUS, MINUS};

class Hw4resultLabel extends JLabel {
    int result;

    public Hw4resultLabel() {
        this.result = 0;
        this.setText(Integer.toString(result));
    }

    public void catNumber(int number) {
        result = result * 10 + number;
        this.setText(Integer.toString(result));
    }

    public void setResult(int result) {
        this.result = result;
        this.setText(Integer.toString(result));
    }

    public void setResult(String result) {
        this.setText(result);
        System.out.println(result);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Color c1 = new Color(105, 105, 105);
        GradientPaint gp = new GradientPaint(100, 0, c1,
                100, 100, Color.LIGHT_GRAY);
        g2.setPaint(gp);
        g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight() * 0.9));

        g2.setFont(new Font("맑은 고딕", Font.BOLD, (int) (getHeight() * 0.7)));
        FontMetrics fontMetrics = g2.getFontMetrics();

        String displayResult = Integer.toString(result);
        g2.setPaint(Color.WHITE);
        g2.drawString(displayResult, getWidth() - fontMetrics.stringWidth(displayResult), (int) (getHeight() * 0.67));

        g2.setPaint(new Color(100, 69, 53));
        g2.drawString(displayResult, getWidth() - fontMetrics.stringWidth(displayResult), (int) (getHeight() * 0.65));

    }
}

class Hw4Button extends JButton {
    String butText;
    static String save;
    static Hw4sign plusMinusFlag = Hw4sign.PLUS;

    public Hw4Button(String str) {
        super(str);
        butText = this.getText();
        if (butText.compareTo("0") >= 0 && butText.compareTo("9") <= 0) {
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    if (Hw4mainPanel.initflag) {
                        Hw4mainPanel.resultField.result = 0;
                        Hw4mainPanel.initflag = false;
                    }
                    Hw4mainPanel.resultField.catNumber(Integer.parseInt(butText));
                }
            });
            return;
        }
        if (butText.equals("C")) {
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    Hw4mainPanel.buff = "";
                    plusMinusFlag = Hw4sign.PLUS;
                    Hw4mainPanel.resultField.setResult(0);
                }
            });
            return;
        }
        if (butText.equals("+")) {
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    if (Hw4mainPanel.resultField.result != 0) {
                        if (plusMinusFlag == Hw4sign.PLUS)
                            Hw4mainPanel.buff += "+";
                        else
                            Hw4mainPanel.buff += "-";
                        Hw4mainPanel.buff += Integer.toString(Hw4mainPanel.resultField.result);
                        Hw4mainPanel.resultField.result = 0;
                    }
                    plusMinusFlag = Hw4sign.PLUS;
                }
            });
            return;
        }
        if (butText.equals("-")) {
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    if (Hw4mainPanel.resultField.result != 0) {
                        if (plusMinusFlag == Hw4sign.PLUS)
                            Hw4mainPanel.buff += "+";
                        else
                            Hw4mainPanel.buff += "-";
                        Hw4mainPanel.buff += Integer.toString(Hw4mainPanel.resultField.result);
                        Hw4mainPanel.resultField.result = 0;
                    }
                    plusMinusFlag = Hw4sign.MINUS;
                }
            });
            return;
        }
        if (butText.equals("=")) {
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    if (Hw4mainPanel.resultField.result != 0) {
                        if (plusMinusFlag == Hw4sign.PLUS)
                            Hw4mainPanel.buff += "+";
                        else
                            Hw4mainPanel.buff += "-";
                        Hw4mainPanel.buff += Integer.toString(Hw4mainPanel.resultField.result);
                        Hw4mainPanel.resultField.result = 0;

                        Hw4mainPanel.resultField.setResult(Hw4mainPanel.calculate(Hw4mainPanel.buff));
                        Hw4mainPanel.buff = "";
                        Hw4mainPanel.initflag = true;
                    }
                    plusMinusFlag = Hw4sign.PLUS;
                }
            });
        }
        if (butText.equals("I")) {
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    Hw4Button.save = Hw4mainPanel.resultField.getText();
                    Hw4mainPanel.resultField.setResult(1801);
                    getParent().getParent().repaint();
                }
                @Override
                public void mouseReleased(MouseEvent e) {
                    super.mouseReleased(e);
                    Hw4mainPanel.resultField.setResult(Integer.parseInt(Hw4Button.save));
                }
            });
            return;
        }

        if (butText.equals("D")) {
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    Hw4Button.save = Hw4mainPanel.resultField.getText();
                    Hw4mainPanel.resultField.setResult(1740);
                    getParent().getParent().repaint();
                }
                @Override
                public void mouseReleased(MouseEvent e) {
                    super.mouseReleased(e);
                    Hw4mainPanel.resultField.setResult(Integer.parseInt(Hw4Button.save));
                }
            });
            return;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (!butText.equals("I") && !butText.equals("D")) {
            g2.setPaint(new Color(218, 171, 145));
            g2.fill(new RoundRectangle2D.Double(getWidth() * 0.1, getWidth() * 0.1, getWidth() * 0.8, getHeight() * 0.8, getWidth() * 0.1, getWidth() * 0.1));
        } else {
            g2.setPaint(Color.darkGray);
            g2.fill(new RoundRectangle2D.Double(getWidth() * 0.1, getWidth() * 0.1, getWidth() * 0.8, getHeight() * 0.8, getWidth() * 0.1, getWidth() * 0.1));
        }


        g2.setFont(new Font("Serif", Font.BOLD, (int) (getHeight() * 0.6)));
        FontMetrics fontMetrics = g2.getFontMetrics();

        g2.setPaint(Color.WHITE);
        g2.drawString(butText, (int) (getWidth() * 0.35), (int) (getHeight() * 0.68));

        g2.setPaint(Color.GRAY);
        g2.drawString(butText, (int) (getWidth() * 0.35), (int) (getHeight() * 0.66));
    }
}

class Hw4mainPanel extends JPanel implements ComponentListener {
    static String buff = "";
    static Hw4resultLabel resultField = new Hw4resultLabel();
    static int saveNumber;
    static boolean initflag = false;

    Hw4Button[] butArr = new Hw4Button[16];

    public Hw4mainPanel() {
        butArr[0] = new Hw4Button("7");
        butArr[1] = new Hw4Button("8");
        butArr[2] = new Hw4Button("9");
        butArr[3] = new Hw4Button("C");
        butArr[4] = new Hw4Button("4");
        butArr[5] = new Hw4Button("5");
        butArr[6] = new Hw4Button("6");
        butArr[7] = new Hw4Button("+");
        butArr[8] = new Hw4Button("1");
        butArr[9] = new Hw4Button("2");
        butArr[10] = new Hw4Button("3");
        butArr[11] = new Hw4Button("-");
        butArr[12] = new Hw4Button("0");
        butArr[13] = new Hw4Button("I");
        butArr[14] = new Hw4Button("D");
        butArr[15] = new Hw4Button("=");


        setLayout(null);
        resultField.setEnabled(false);
        add(resultField);
        for (JButton but : butArr) {
            add(but);
        }
        resultField.setBounds(0, 0, 330, 110);
        for (int i = 0; i < 16; i++) {
            butArr[i].setBounds(82 * (i % 4), 140 + 82 * (i / 4), 82, 82);
        }
        addComponentListener(this);
    }

    public static int calculate(String buff) {
        int result = 0;
        int plusIdx;
        int minusIdx;
        int signIdx;
        Hw4sign plusMinusFlag;
        while (buff.length() > 0) {
            if (buff.charAt(0) == '+') {
                plusMinusFlag = Hw4sign.PLUS;
            } else {
                plusMinusFlag = Hw4sign.MINUS;
            }
            buff = buff.substring(1);

            plusIdx = (buff.indexOf('+') == -1) ? Integer.MAX_VALUE : buff.indexOf('+');
            minusIdx = (buff.indexOf('-') == -1) ? Integer.MAX_VALUE : buff.indexOf('-');
            if (plusIdx == Integer.MAX_VALUE && minusIdx == Integer.MAX_VALUE) {
                signIdx = buff.length();
            } else {
                signIdx = (plusIdx < minusIdx) ? plusIdx : minusIdx;
            }

            if (plusMinusFlag == Hw4sign.PLUS)
                result += Integer.parseInt(buff.substring(0, signIdx));
            else
                result -= Integer.parseInt(buff.substring(0, signIdx));
            buff = buff.substring(signIdx);
        }
        return result;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // display 뒤에 있는 하얀색 그라데이션 배경
        GradientPaint gp = new GradientPaint(0, 0, Color.WHITE,
                100, 100, Color.LIGHT_GRAY);
        g2.setPaint(gp);
        g2.fillRect(0, 0, getWidth(), (int) (getHeight() * 0.28));

        // 숫자 바로 뒤에 있는 배경

        gp = new GradientPaint(100, -100, Color.LIGHT_GRAY,
                100, 100, Color.GRAY);
        g2.setPaint(gp);
        g2.fill(new Rectangle2D.Double(getWidth() * 0.02, getWidth() * 0.02,
                getWidth() * 0.96, getHeight() * 0.28 - getWidth() * 0.04));

        // butArr 뒤에 있는 하얀 배경
        g2.setPaint(Color.WHITE);
        g2.fill(new Rectangle2D.Double(0, getHeight() * 0.28, getWidth(), getHeight() * 0.66));
    }

    @Override
    public void componentResized(ComponentEvent e) {
        resultField.setBounds((int) (getWidth() * 0.1), (int) (getWidth() * 0.05), (int) (getWidth() * 0.8), (int) (getHeight() * 0.22));
        for (int i = 0; i < 16; i++) {
            butArr[i].setBounds((int) (getWidth() * 0.25) * (i % 4), (int) (getHeight() * 0.28) + (int) (getWidth() * 0.25) * (i / 4), (int) (getWidth() * 0.25), (int) (getWidth() * 0.25));
        }
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }

}

public class JavaHW4 extends JFrame implements ComponentListener {

    Hw4mainPanel mainPanel;
    static int w;
    static int h;

    public int[] getMainPanelBounds() {
        int frameWidth = getWidth();
        int frameHeight = getHeight();
        int x, y, w, h;

        // frame의 높이가 충분할 때
        if (frameWidth * 1.5 < frameHeight) {
            w = frameWidth;
            h = (int) (frameWidth * 1.5);
            x = 0;
            y = (int) (frameHeight * 0.5 - h * 0.5);
        } else { // frame의 너비가 충분할 때
            w = (int) (frameHeight * 0.66);
            h = frameHeight;
            x = (int) (frameWidth * 0.5 - w * 0.5);
            y = 0;
        }
        return new int[]{x, y, w, h};
    }

    public JavaHW4() throws HeadlessException {
        w = 500;
        h = 500;
        setSize(w, h);
        setTitle("Homework2");
        this.getContentPane().setBackground(Color.black);
        setLayout(null);
        addComponentListener(this);

        mainPanel = new Hw4mainPanel();
        add(mainPanel);
        int[] arr = getMainPanelBounds();
        mainPanel.setBounds(arr[0], arr[1], arr[2], arr[3]);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new JavaHW4();
    }

    @Override
    public void componentResized(ComponentEvent e) {
        int[] arr = getMainPanelBounds();
        mainPanel.setBounds(arr[0], arr[1], arr[2], arr[3]);
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
}
