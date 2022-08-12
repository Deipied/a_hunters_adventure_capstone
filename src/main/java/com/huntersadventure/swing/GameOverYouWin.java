package com.huntersadventure.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GameOverYouWin {
    JFrame youWinWindow;
    JPanel youWinPanel, startAgainPanel, quitPanel;
    JLabel youWinBanner;
    JButton startAgainButton, quitButton;
    Font font = new Font("Times New Roman", Font.PLAIN, 50);
    ImageIcon youWinBannerSrc, finalYouWinBannerSrc;
    Image resizeImage, resizedImage;
    Container container;

    public GameOverYouWin(){
        //ToDo: JFrame window
        youWinWindow = new JFrame();
        youWinWindow.setSize(1200, 900);
        youWinWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        youWinWindow.getContentPane().setBackground(Color.black);
        youWinWindow.setLayout(null);
        youWinWindow.setLocationRelativeTo(null);
        container = youWinWindow.getContentPane();

        //ToDo: panel holds you win image
        youWinPanel = new JPanel();
        youWinPanel.setBounds(100, 100, 1000, 350);
        youWinPanel.setBackground(Color.black);
        container.add(youWinPanel);

        //ToDo: resize image
        youWinBannerSrc = new ImageIcon (ClassLoader.getSystemResource("GameText/youWin.jpg"));
        resizeImage = youWinBannerSrc.getImage();
        resizedImage = resizeImage.getScaledInstance(900,400, Image.SCALE_SMOOTH);
        finalYouWinBannerSrc = new ImageIcon(resizedImage);

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
        youWinBanner = new JLabel(finalYouWinBannerSrc);
        youWinPanel.add(youWinBanner);
        youWinWindow.add(youWinPanel);
        startAgainPanel.add(startAgainButton);
        youWinWindow.add(startAgainPanel);
        quitPanel.add(quitButton);
        youWinWindow.add(quitPanel);
        youWinWindow.setVisible(true);

        //JButton action Listener on start again button // calls game page
        startAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                youWinWindow.setVisible(false);
                new GamePage();
            }
        });

        //JButton action Listener on quit button // calls splash page
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                youWinWindow.setVisible(false);
                new SplashPage();
            }
        });
    }

    //for test - remove later
    public static void main(String[] args) {
        new GameOverYouWin();
    }
}