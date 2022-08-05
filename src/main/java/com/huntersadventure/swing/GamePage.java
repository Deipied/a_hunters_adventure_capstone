package com.huntersadventure.swing;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


class GamePage {

    JFrame gameWindow;
    JPanel topPanel;
    JPanel middlePanel;
    JPanel bottomLeftPanel;
    JPanel bottomRightPanel;
    Container container;

    public GamePage(){
        gameWindow = new JFrame();
        gameWindow.setSize(1200, 900);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.getContentPane().setBackground(Color.black);
        gameWindow.setLayout(null);
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);
        container = gameWindow.getContentPane();

        topPanel = new JPanel();
        topPanel.setBounds(10, 10, 1175, 50);
        topPanel.setBorder(BorderFactory.createLineBorder(Color.orange));
        topPanel.setBackground(Color.black);
        container.add(topPanel);

        middlePanel = new JPanel();
        middlePanel.setBounds(10, 70, 1175, 335);
        middlePanel.setBorder(BorderFactory.createLineBorder(Color.blue));
        middlePanel.setBackground(Color.black);
        container.add(middlePanel);

        bottomLeftPanel = new JPanel();
        bottomLeftPanel.setBounds(10, 415, 725, 400);
        bottomLeftPanel.setBorder(BorderFactory.createLineBorder(Color.white));
        bottomLeftPanel.setBackground(Color.black);
        container.add(bottomLeftPanel);

        bottomRightPanel = new JPanel();
        bottomRightPanel.setBounds(750, 415, 432, 400);
        bottomRightPanel.setBorder(BorderFactory.createLineBorder(Color.white));
        bottomRightPanel.setBackground(Color.black);
        container.add(bottomRightPanel);

    }

    public static void main(String[] args) {
        new GamePage();
    }


}