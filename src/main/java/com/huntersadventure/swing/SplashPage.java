package com.huntersadventure.swing;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


class SplashPage {
    JFrame titleWindow;
    JPanel titlePanel, startButtonPanel;
    JLabel titleBanner;
    JButton startButton;
    Font startFont = new Font("Times New Roman", Font.PLAIN, 50);
    ImageIcon titleBannerSrc, finalImage;
    Image resizeImage, resizedImage;
    Container container;

    public SplashPage(){
        titleWindow = new JFrame();
        titleWindow.setSize(1200, 900);
        titleWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        titleWindow.getContentPane().setBackground(Color.black);
        titleWindow.setLayout(null);
        titleWindow.setLocationRelativeTo(null);
        container = titleWindow.getContentPane();

        titlePanel = new JPanel();
        titlePanel.setBounds(100, 100, 1000, 350);
        titlePanel.setBackground(Color.black);
        container.add(titlePanel);

        titleBannerSrc = new ImageIcon("src/main/resources/GameText/titleBanner.jpg");
        resizeImage = titleBannerSrc.getImage();
        resizedImage = resizeImage.getScaledInstance(800,300, Image.SCALE_SMOOTH);
        finalImage = new ImageIcon(resizedImage);

        titleBanner = new JLabel(finalImage);


        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(450,600,250,150);
        startButtonPanel.setBackground(Color.black);

        startButton = new JButton("START");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);

        startButton.setBorderPainted(false);
        startButton.setFont(startFont);

        titlePanel.add(titleBanner);

        startButtonPanel.add(startButton);
        titleWindow.add(titlePanel);
        titleWindow.add(startButtonPanel);
        titleWindow.setVisible(true);

    }

    public static void main(String[] args) throws IOException {
        new SplashPage();
    }
}