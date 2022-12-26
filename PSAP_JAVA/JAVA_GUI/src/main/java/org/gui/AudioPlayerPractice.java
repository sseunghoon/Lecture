package org.gui;

import java.awt.Color;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class AudioPlayerPanel extends JPanel implements LineListener{
    Clip clip;
    AudioPlayerPanel(){
        try {
            clip = AudioSystem.getClip();

            URL url = getClass().getClassLoader().getResource("soundtest.wav");
            System.out.println(url);
//			File audioFile = new File("audios/soundtest.wav");
//			AudioInputStream audioStream
//				= AudioSystem.getAudioInputStream(audioFile);
            AudioInputStream audioStream
                    = AudioSystem.getAudioInputStream(url);
            clip.open(audioStream);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }

        JButton play = new JButton("play");
        JButton stop = new JButton("Stop");
        JButton loop = new JButton("loop");
        add(play);
        add(stop);
        add(loop);
        play.addActionListener((e)->{
            clip.start();
        });
        stop.addActionListener((e)->{
            clip.stop();
            clip.setFramePosition(0);
        });
        loop.addActionListener((e)->{
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        });

        clip.addLineListener(this);
    }
    @Override
    public void update(LineEvent event) {
        if(event.getType() == LineEvent.Type.START) {
            setBackground(Color.orange);
        }
        if(event.getType() == LineEvent.Type.STOP) {
            setBackground(Color.DARK_GRAY);
            clip.setFramePosition(0);
        }
    }
}
public class AudioPlayerPractice extends JFrame{
    AudioPlayerPractice(){
        setSize(300,100);
        setTitle("My Audio Playwer");

        add(new AudioPlayerPanel());

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        new AudioPlayerPractice();
    }

}