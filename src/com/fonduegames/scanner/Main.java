package com.fonduegames.scanner;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("img/test4.jpg"));
        } catch (IOException e) {
            System.out.println("Invalid file");
        }
        DisplayImage d = new DisplayImage(img);
        findLines(img);
        DisplayImage d2 = new DisplayImage(img);
    }

    public static void findRectangles(BufferedImage img) {

    }

    public static void findLines(BufferedImage img) {
        int[][] newRGB = new int[img.getWidth()][img.getHeight()];
        int maxGrayscale = 0;
        int minGrayscale = 255;
        for (int x = 1; x < img.getWidth() - 1; ++x) {
            for (int y = 1; y < img.getHeight() - 1; ++y) {
                int grayLevel = findGrayLevel(img.getRGB(x, y));

                int[] neighbours = new int[4];
                neighbours[0]= findGrayLevel(img.getRGB(x, y-1));
                neighbours[1]= findGrayLevel(img.getRGB(x, y+1));
                neighbours[2]= findGrayLevel(img.getRGB(x-1, y));
                neighbours[3] = findGrayLevel(img.getRGB(x+1, y));

                int maxDifference = 0;
                for (int i : neighbours)
                    if (Math.abs(grayLevel-i)>maxDifference)
                        maxDifference = Math.abs(grayLevel-i);

                maxGrayscale = Math.max(maxGrayscale,maxDifference);
                minGrayscale = Math.min(minGrayscale,maxDifference);

                newRGB[x][y]  = maxDifference;
            }
        }

        System.out.println(maxGrayscale+" "+minGrayscale);
        int average = (maxGrayscale+minGrayscale)/2;

        for (int i=0; i<newRGB.length; i++) {
            for (int j=0; j<newRGB[0].length; j++) {
                int newValue = newRGB[i][j] > 40 ? 255 : 0;
                //int newValue = newRGB[i][j];
                img.setRGB(i,j,(newValue << 16) + (newValue << 8) + newValue);
            }
        }
    }

    public static int findGrayLevel(int rgb) {
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = (rgb & 0xFF);

        return (r + g + b) / 3;
    }
}
