package org.gui;

import javax.swing.*;
import java.awt.*;


class Hw3_2NorthPanel extends JPanel {
    public Hw3_2NorthPanel() {
        JLabel jLabel = new JLabel("수식 입력");
        JTextField jTextField = new JTextField(15);
        setBackground(Color.GRAY);
        add(jLabel);
        add(jTextField);
    }
}
class Hw3_2CenterPanel extends JPanel {
    public Hw3_2CenterPanel() {
        setLayout(new GridLayout(4,4, 3, 3));
        add(new JButton("0"));
        add(new JButton("1"));
        add(new JButton("2"));
        add(new JButton("3"));
        add(new JButton("4"));
        add(new JButton("5"));
        add(new JButton("6"));
        add(new JButton("7"));
        add(new JButton("8"));
        add(new JButton("9"));
        add(new JButton("CE"));
        add(new JButton("계산"));
        JButton plusButton = new JButton("+");
        JButton minusButton = new JButton("-");
        JButton multipleButton = new JButton("x");
        JButton divideButton = new JButton("/");
        plusButton.setBackground(new Color(137, 119, 173));
        plusButton.setOpaque(true);
        plusButton.setBorderPainted(false);

        minusButton.setBackground(new Color(137, 119, 173));
        minusButton.setOpaque(true);
        minusButton.setBorderPainted(false);


        multipleButton.setBackground(new Color(137, 119, 173));
        multipleButton.setOpaque(true);
        multipleButton.setBorderPainted(false);

        divideButton.setBackground(new Color(137, 119, 173));
        divideButton.setOpaque(true);
        divideButton.setBorderPainted(false);
        add(plusButton);
        add(minusButton);
        add(multipleButton);
        add(divideButton);
    }
}

class Hw3_2SouthPanel extends JPanel {
    public Hw3_2SouthPanel() {
        JLabel jLabel = new JLabel("계산 결과");
        JTextField jTextField = new JTextField(15);
        setBackground(Color.YELLOW);
        add(jLabel);
        add(jTextField);
    }


}

public class Hw3_2 extends JFrame {
    public Hw3_2() throws HeadlessException {
        setTitle("계산기 프레임");
        setSize(300, 500);

        add(new Hw3_2NorthPanel(), BorderLayout.NORTH);
        add(new Hw3_2CenterPanel(), BorderLayout.CENTER);
        add(new Hw3_2SouthPanel(), BorderLayout.SOUTH);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Hw3_2();
    }
}
