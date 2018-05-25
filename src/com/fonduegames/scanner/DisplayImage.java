package com.fonduegames.scanner;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DisplayImage extends JPanel {
    private int WINDOW_HEIGHT = 800;

    private BufferedImage img;

    public DisplayImage(BufferedImage img) {
        this.img = img;
        JFrame j = new JFrame();
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setVisible(true);
        j.setSize((img.getWidth()*WINDOW_HEIGHT)/img.getHeight(),WINDOW_HEIGHT);
        j.add(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(img,0,0,this.getWidth(),this.getHeight(),this);
    }


}
