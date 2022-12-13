package org.finaltraining;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

class LeftPanel extends JPanel implements ListSelectionListener {
    JButton but;
    RightPanel rightPanel;
    DefaultListModel listModel;
    JList list;

    public LeftPanel() {
        listModel = new DefaultListModel();
        list = new JList(listModel);

        list.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);
        list.setPreferredSize(new Dimension(200, 400));

        add(list);
        but = new JButton("Delete");
        add(but);
        but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Iterator<Oval> iterator = rightPanel.ovals.iterator();
                int i = 0;
                while (iterator.hasNext()) {
                    iterator.next();
                    if (i == list.getSelectedIndex()) {
                        iterator.remove();
                        updateList();
                        return;
                    }
                    i++;
                }

            }
        });
    }

    public void updateList() {
        listModel.clear();
        int i = 0;
        for (var oval: rightPanel.ovals) {
            listModel.addElement(i + ": center(" + oval.x + ", " + oval.y + ")");
            i++;
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selected = list.getSelectedIndex();
        for (int i = 0; i < rightPanel.ovals.size(); i++) {
            if (i != selected)
                rightPanel.ovals.get(i).color = Color.BLACK;
            else
                rightPanel.ovals.get(i).color = Color.RED;
        }
        rightPanel.repaint();
    }
}

class RightPanel extends JPanel {
    ArrayList<Oval> ovals = new ArrayList<>();
    LeftPanel leftPanel;

    public RightPanel() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                ovals.add(new Oval(e.getX(), e.getY(), 50));
                leftPanel.updateList();
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (var oval: ovals) {
            g.setColor(oval.color);
            g.drawOval(oval.x - oval.radius, oval.y - oval.radius, 2 * oval.radius, 2 * oval.radius);
        }
    }
}

public class OvalList extends JFrame {
    public OvalList() throws HeadlessException {
        setTitle("OvalList");
        setSize(1000, 500);
        setLayout(null);

        LeftPanel leftPanel = new LeftPanel();
        RightPanel rightPanel = new RightPanel();

        leftPanel.rightPanel = rightPanel;
        rightPanel.leftPanel = leftPanel;

        leftPanel.setBounds(0, 0, 200, 500);
        rightPanel.setBounds(200, 0, 800, 500);
        leftPanel.setBackground(Color.GRAY);
        add(leftPanel);
        add(rightPanel);




        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new OvalList();
    }
}
