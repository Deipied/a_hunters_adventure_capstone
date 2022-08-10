package com.huntersadventure.swing;

import com.huntersadventures.settings.Setting;
import com.huntersadventures.settings.SettingJRadioButton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


class SplashPage {

    JFrame titleWindow;
    JPanel titlePanel, startButtonPanel;
    JLabel titleBanner;
    JButton startButton, settingsButton;
    Font startFont = new Font("Times New Roman", Font.PLAIN, 50);
    Font settingsFont = new Font("Times New Roman", Font.PLAIN, 20);
    ImageIcon titleBannerSrc, finalImage;
    Image resizeImage, resizedImage;
    Container container;

    public SplashPage() {
        //JFrame window
        titleWindow = new JFrame();
        titleWindow.setSize(1200, 900);
        titleWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        titleWindow.getContentPane().setBackground(Color.black);
        titleWindow.setLayout(null);
        titleWindow.setLocationRelativeTo(null);
        container = titleWindow.getContentPane();

        //resize and set title img
        titleBannerSrc = new ImageIcon("src/main/resources/GameText/titleBanner.jpg");
        resizeImage = titleBannerSrc.getImage();
        resizedImage = resizeImage.getScaledInstance(800, 300, Image.SCALE_SMOOTH);
        finalImage = new ImageIcon(resizedImage);
        titleBanner = new JLabel(finalImage);

        //title panel - holds game title
        titlePanel = new JPanel();
        titlePanel.setBounds(100, 100, 1000, 350);
        titlePanel.setBackground(Color.black);
        titlePanel.add(titleBanner);

        // startButton panel holds the start button that will be added to the layout panel
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(450, 600, 250, 150);
        startButtonPanel.setBackground(Color.black);

        startButton = new JButton("START");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);
        startButton.setBorderPainted(false);
        startButton.setFont(startFont);
        startButtonPanel.add(startButton);

        //Settings button
        settingsButton = new JButton("Settings");
        settingsButton.setBackground(Color.black);
        settingsButton.setForeground(Color.white);
        settingsButton.setBorderPainted(false);
        settingsButton.setFont(settingsFont);
        settingsButton.setBounds(900,10,115,30);
        titlePanel.add(settingsButton);

        //add panels to game window
        titleWindow.add(titlePanel);
        titleWindow.add(startButtonPanel);
        titleWindow.add(settingsButton);
        titleWindow.setVisible(true);

        ActionListener actionListener = e -> {
            Setting setting = new Setting();
            new SettingJRadioButton(setting);
        };

        settingsButton.addActionListener(actionListener);

        //JButton action Listener
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                titleWindow.setVisible(false);
                new GamePage();
            }
        });
    }

    public static void main(String[] args) throws IOException {
        new SplashPage();
    }
}