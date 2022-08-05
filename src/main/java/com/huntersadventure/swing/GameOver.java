package com.huntersadventure.swing;

import javax.swing.*;
import java.awt.*;

class GameOver {
    JFrame gameOverWindow;
    JPanel gameOverPanel, startAgainPanel, quitPanel;
    JLabel gameOverBanner;
    JButton startAgainButton, quitButton;
    Font font = new Font("Times New Roman", Font.PLAIN, 50);
    ImageIcon gameOverBannerSrc, finalGameOverBannerSrc;
    Image resizeImage, resizedImage;
    Container container;

    public GameOver() {
        //ToDo: JFrame window
        gameOverWindow = new JFrame();
        gameOverWindow.setSize(1200, 900);
        gameOverWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameOverWindow.getContentPane().setBackground(Color.black);
        gameOverWindow.setLayout(null);
        gameOverWindow.setLocationRelativeTo(null);
        container = gameOverWindow.getContentPane();

        //ToDo: panel holds you died image
        gameOverPanel = new JPanel();
        gameOverPanel.setBounds(100, 100, 1000, 350);
        gameOverPanel.setBackground(Color.black);
        container.add(gameOverPanel);

        //ToDo: resize image
        gameOverBannerSrc = new ImageIcon("src/main/resources/GameText/youDied.png");
        resizeImage = gameOverBannerSrc.getImage();
        resizedImage = resizeImage.getScaledInstance(800,300, Image.SCALE_SMOOTH);
        finalGameOverBannerSrc = new ImageIcon(resizedImage);

        //ToDo: Start again panel and button
        startAgainPanel = new JPanel();
        startAgainPanel.setBounds(400,500,400,100);
        startAgainPanel.setBackground(Color.black);

        startAgainButton = new JButton("START AGAIN");
        startAgainButton.setBackground(Color.black);
        startAgainButton.setForeground(Color.white);

        startAgainButton.setBorderPainted(false);
        startAgainButton.setFont(font);

        //ToDo: Quit panel and button
        quitPanel = new JPanel();
        quitPanel.setBounds(400,600,400,150);
        quitPanel.setBackground(Color.black);

        quitButton = new JButton("QUIT");
        quitButton.setBackground(Color.black);
        quitButton.setForeground(Color.white);

        quitButton.setBorderPainted(false);
        quitButton.setFont(font);

        //ToDo: Add all panels and buttons to GameOverWindow
        gameOverBanner = new JLabel(finalGameOverBannerSrc);
        gameOverPanel.add(gameOverBanner);
        gameOverWindow.add(gameOverPanel);
        startAgainPanel.add(startAgainButton);
        gameOverWindow.add(startAgainPanel);
        quitPanel.add(quitButton);
        gameOverWindow.add(quitPanel);
        gameOverWindow.setVisible(true);

    }

    public static void main(String[] args) {
        new GameOver();
    }

}