package org.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

class ImagePracticePanel extends JPanel{
    Image image = null;
    BufferedImage image2 = null;
    ImagePracticePanel() {

        // 프로젝트 경로를 거친다던가, 단계가 좀 긴 방법
//        ImageIcon imageIcon = new ImageIcon("flower(1).jpeg");
//        JLabel imgLabel = new JLabel(imageIcon);
//        add(imgLabel);
//        image = imageIcon.getImage();

        // sourceCode가 있는 곳
        // 나는 /Users/songseunghun/SejongUniv/PSAP_JAVA/JAVA_GUI/target/classes/org/gui 에 있음
        URL url = getClass().getResource("flower(1).jpeg");

        try {
            image = ImageIO.read(url);
        } catch (IOException e) {
            System.out.println("error: " + e);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int h = image.getWidth(this);
        int w = image.getHeight(this);
        System.out.println("w: " + w + "h: " + h);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        float aspect = (float) w/h;
        int ih = getHeight();
        int iw = (int) (ih * aspect);

        if (iw > getWidth()) {
            iw = getWidth();
            ih = (int) (iw / aspect);
        }

//        if (image != null) {
//            // 맨 오른쪽의 this = img가 다 그려졌을 때, 다 그려졌다는 메시지를 받을 component
//            g.drawImage(image, centerX - iw/2, centerY - ih/2, iw, ih, this);
//        }

        // (100, 100) 부터 (150, 150)라는 틀 안을 이미지의 (0, 0) 부터 (200, 200) 부분으로 채우겠어
        // 이미지의 자체 크기는 (0, 0) -> (256, 256)
        g.drawImage(image, 100, 100, 150, 150, 0, 0, 200, 200, this);
    }
}

public class ImagePractice extends JFrame {
    public ImagePractice() throws HeadlessException {
        setTitle("Image Practice");
        setSize(500, 500);

        add(new ImagePracticePanel());

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new ImagePractice();
    }
}
