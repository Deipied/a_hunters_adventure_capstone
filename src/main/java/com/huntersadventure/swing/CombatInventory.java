package com.huntersadventure.swing;

import com.huntersadventure.gameobjects.Characters;
import com.huntersadventure.gameobjects.Item;

import javax.swing.*;
import java.awt.*;

public class CombatInventory {
    static JFrame frame;

    public static void testCombatInventory(Characters p1) {
        GamePage gamePage = new GamePage();
        frame = new JFrame("Current inventory");
        DefaultListModel<String> panelList = new DefaultListModel<>();
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (Item item : p1.getInventory()
        ) {
            panelList.addElement(item.getName());
        }

        JList<String> displayList = new JList<>(panelList);
//        JScrollPane scrollPane = new JScrollPane(displayList);

        displayList.setBounds(100, 100, 200, 200);


        gamePage.middleLeftPanel.setFont(new java.awt.Font("Tacoma", 0, 44));
        gamePage.middleLeftPanel.add(displayList);
        gamePage.middleLeftPanel.setBackground(Color.white);
//        Container contentPane = frame.getContentPane();
//        contentPane.add(scrollPane, BorderLayout.CENTER);
//
//        frame.setSize(200, 200);
//        frame.setVisible(true);
    }

}