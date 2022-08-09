package com.huntersadventure.swing;


import javax.swing.*;
import java.awt.*;

class GamePage {
    JFrame window;
    JPanel topPanel;
    JPanel dialogPanel;
    JPanel middleLeftPanel;
    JLabel map;
    ImageIcon mapSrc, mapImg;
    Image resizeMap, resizedMap;
    JPanel mapPanel;
    JPanel textPanel;
    Container container;


    public GamePage(){
        window = new JFrame();
        window.setSize(1200, 900);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        window.setLocationRelativeTo(null);

        container = window.getContentPane();

        //top panel
        topPanel = new JPanel();
        topPanel.setBounds(10, 10, 1175, 55);
        topPanel.setBorder(BorderFactory.createLineBorder(Color.orange));
        topPanel.setBackground(Color.black);
        container.add(topPanel);

        //inventory panel
        middleLeftPanel = new JPanel();
        middleLeftPanel.setBounds(10, 70, 577, 350);
        middleLeftPanel.setBorder(BorderFactory.createLineBorder(Color.white));
        middleLeftPanel.setBackground(Color.black);
        container.add(middleLeftPanel);


        //map image src
        mapSrc = new ImageIcon("src/main/resources/GameText/map.png");
        resizeMap = mapSrc.getImage();
        resizedMap = resizeMap.getScaledInstance(550,330, Image.SCALE_SMOOTH);
        mapImg = new ImageIcon(resizedMap);

        map = new JLabel(mapImg);

        //map
        mapPanel = new JPanel();
        mapPanel.setBounds(590, 70, 595, 350);
        mapPanel.setBorder(BorderFactory.createLineBorder(Color.white));
        mapPanel.setBackground(Color.black);
        mapPanel.add(map);
        container.add(mapPanel);

        //dialog
        dialogPanel = new JPanel();
        dialogPanel.setBounds(10, 425, 1175, 350);
        dialogPanel.setBorder(BorderFactory.createLineBorder(Color.white));
        dialogPanel.setBackground(Color.black);
        container.add(dialogPanel);

        //text panel
        textPanel = new JPanel();
        textPanel.setBounds(10, 780, 1175, 65);
        textPanel.setBorder(BorderFactory.createLineBorder(Color.orange));
        textPanel.setBackground(Color.black);
        window.setVisible(true);
        container.add(textPanel);
    }

//    public static void main(String[] args) {
//        new GamePage();
//    }
}